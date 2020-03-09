package com.pss.pssapp.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Data
@Entity
public class User {
    @NotEmpty
    private String companyName;
    @NotEmpty
    private String companyAddress;
    @NotEmpty
    private String companyNip;
    @NotEmpty
    private String name;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

    private boolean status;
    private Date registrationDate;

    @OneToMany
    private Set<Role> role;

    public User(){
        this.status = true;
        this.registrationDate = new Date();
        this.role.add(new Role("USER_ROLE"));
    }

    public User(String companyName, String companyAddress, String companyNip, String name, String lastName, String email, String password){
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyNip = companyNip;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.status = true;
        this.registrationDate = new Date();
        this.role.add(new Role("USER_ROLE"));
    }

    @Override
    public String toString() {
        return this.name + " " + this.lastName;
    }
}

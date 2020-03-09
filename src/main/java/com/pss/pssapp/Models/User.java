package com.pss.pssapp.Models;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.*;

@Data
@Entity
public class User {
    @NotNull
    private String companyName;
    @NotNull
    private String companyAddress;
    @NotNull
    private String companyNip;
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
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
}

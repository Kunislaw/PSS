package com.pss.pssapp.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    private boolean status = true;
    private Date registrationDate = new Date();

    @OneToMany
    private Set<Role> role = new HashSet<>();

    public User(){
        role.add(new Role("USER_ROLE"));
    }

    @Override
    public String toString() {
        return this.name + " " + this.lastName;
    }
}

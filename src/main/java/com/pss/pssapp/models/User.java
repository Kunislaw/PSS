package com.pss.pssapp.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

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

    @ManyToMany
    private Set<Role> role = new HashSet<>();

    @OneToMany
    private List<Delegation> delegations = new ArrayList<>();

    public User() {
        this.role.add(new Role("USER_ROLE"));
    }

}
package com.pss.pssapp.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
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

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Role> role = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Delegation> delegations = new ArrayList<>();

}
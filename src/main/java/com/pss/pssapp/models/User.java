package com.pss.pssapp.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
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

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Role> role = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Delegation> delegations = new HashSet<>();

}
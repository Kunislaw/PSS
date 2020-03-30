package com.pss.pssapp.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String roleName;


    public Role(){};
    public Role(String roleName){
        this.roleName = roleName;
    }
}
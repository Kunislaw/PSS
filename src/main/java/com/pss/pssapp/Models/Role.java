package com.pss.pssapp.Models;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Role {
    private String roleName;

    public Role(String roleName){
        this.roleName = roleName;
    }
}

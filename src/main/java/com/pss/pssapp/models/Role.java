package com.pss.pssapp.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String roleName;

    @OneToMany
    private Set<User> userWithRole;

    public Role(String roleName){
        this.roleName = roleName;
    }
}

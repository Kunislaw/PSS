package com.pss.pssapp.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Entity
public class Role {
    @NotEmpty
    private String roleName;

    @OneToMany
    private Set<User> userWithRole;

    public Role(String roleName){
        this.roleName = roleName;
    }
}

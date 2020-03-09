package com.pss.pssapp.Models;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
public class Role {
    @NotNull
    private String roleName;

    @OneToMany
    private Set<User> userWithRole;

    public Role(String roleName){
        this.roleName = roleName;
    }
}

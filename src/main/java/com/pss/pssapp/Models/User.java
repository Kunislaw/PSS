package com.pss.pssapp.Models;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

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
    private boolean status = true;
    private Date registrationDate = new Date();
    private String role = "ROLE_USER";


}

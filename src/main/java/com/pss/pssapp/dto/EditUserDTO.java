package com.pss.pssapp.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestBody;


@Getter
@Setter
public class EditUserDTO {
    private String companyAddress;
    private String companyName;
    private String companyNip;
    private String name;
    private String lastName;
}

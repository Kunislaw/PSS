package com.pss.pssapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableOAuth2Sso
public class PssappApplication {

    public static void main(String[] args) {
        SpringApplication.run(PssappApplication.class, args);
    }

}

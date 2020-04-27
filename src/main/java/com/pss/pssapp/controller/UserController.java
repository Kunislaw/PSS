package com.pss.pssapp.controller;


import com.google.common.net.HttpHeaders;
import com.pss.pssapp.models.Delegation;
import com.pss.pssapp.models.User;
import com.pss.pssapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpProperties;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Convert;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin(origins = "*")
    @GetMapping("/login")
    public String login(@RequestHeader(value="Authorization") String basic){
        byte[] decocedBasicBytes = Base64.getDecoder().decode(basic.substring(6));
        String decodedAuth = new String(decocedBasicBytes);
        String[] splittedDecodedAuth = decodedAuth.split(":");
        User user = userService.getUserByEmail(splittedDecodedAuth[0]);
        if(user == null) return "-1";
        else return new Long(user.getId()).toString();

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    public void registerUser(@RequestBody User user){
        userService.registerUser(user);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/home/allusers")
    @ResponseBody
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @PutMapping("/home/changepassword")
    public void changePassword(@RequestParam("userId") long userId, @RequestParam("newPassword") String newPassword){
        userService.changePassword(userId, newPassword);
    }
    @DeleteMapping("/home/deleteuser")
    public boolean deleteUserById(@RequestParam("userId") long userId){
        return userService.deleteUserById(userId);
    }
    @PostMapping("/home/adddelegation")
    public void addDelegation(@RequestParam("userId") long userId, @RequestBody Delegation delegation){
        userService.addDelegation(userId, delegation);
    }
    @DeleteMapping("/home/deletedelegation")
    public boolean removeDelegation(@RequestParam("userId") long userId, @RequestParam("delegationId") long delegationId){
        return userService.removeDelegation(userId,delegationId);
    }


}

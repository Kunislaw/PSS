package com.pss.pssapp.controller;


import com.pss.pssapp.models.Delegation;
import com.pss.pssapp.models.User;
import com.pss.pssapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    public void registerUser(@RequestBody User user){
        userService.registerUser(user);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/allusers")
    @ResponseBody
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @PutMapping("/changepassword")
    public void changePassword(@RequestParam("userId") long userId, @RequestParam("newPassword") String newPassword){
        userService.changePassword(userId, newPassword);
    }
    @DeleteMapping("/deleteuser")
    public boolean deleteUserById(@RequestParam("userId") long userId){
        return userService.deleteUserById(userId);
    }
    @PostMapping("/adddelegation")
    public void addDelegation(@RequestParam("userId") long userId, @RequestBody Delegation delegation){
        userService.addDelegation(userId, delegation);
    }
    @DeleteMapping("/deletedelegation")
    public boolean removeDelegation(@RequestParam("userId") long userId, @RequestParam("delegationId") long delegationId){
        return userService.removeDelegation(userId,delegationId);
    }


}

package com.pss.pssapp.controller;

import java.util.List;

import com.pss.pssapp.models.Delegation;
import com.pss.pssapp.models.User;
import com.pss.pssapp.service.DelegationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * DelegationController
 */
@RestController
public class DelegationController {

    @Autowired
    private DelegationService delegationService;

    @GetMapping("/home/getAllDelegations")
    @ResponseBody
    List<Delegation> getAllDelegations() {
        return delegationService.getAllDelegations();
    }

    @GetMapping("/home/getAllDelegationsOrderByDateStartDesc")
    @ResponseBody
    List<Delegation> getAllDelegationsOrderByDateStartDesc() {
        return delegationService.getAllDelegationsOrderByDateStartDesc();
    }

    @GetMapping("/home/getAllDelByUserOrderByDateStartDesc")
    @ResponseBody
    List<Delegation> getAllDelByUserOrderByDateStartDesc(@RequestParam(value = "userId") long userId) {
        return delegationService.getAllDelByUserOrderByDateStartDesc(userId);
    }

    @GetMapping("/home/getAllUsersByRoleName")
    @ResponseBody
    List<User> getAllUsersByRoleName(@RequestParam(value = "roleName") String roleName) {
        return delegationService.getAllUsersByRoleName(roleName);
    }
}
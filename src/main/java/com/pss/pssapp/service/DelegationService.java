package com.pss.pssapp.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.pss.pssapp.models.Delegation;
import com.pss.pssapp.models.User;
import com.pss.pssapp.repository.DelegationRepository;
import com.pss.pssapp.repository.RoleRepository;
import com.pss.pssapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DelegationService
 */
@Service
public class DelegationService {

    @Autowired
    DelegationRepository delegationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public List<Delegation> getAllDelegations() {
        return delegationRepository.findAll();
    }

    public List<Delegation> getAllDelegationsOrderByDateStartDesc() {
        return delegationRepository.findAllByOrderByDateTimeStartDesc();
    }

    public List<Delegation> getAllDelByUserOrderByDateStartDesc(long userId) {
        
        User user = userRepository.getOne(userId);
        List<Delegation> delegation = user.getDelegations();

        // Sort descending
        Collections.sort(delegation, (o1, o2) -> o1.getDateTimeStart().compareTo(o2.getDateTimeStart()));
        Collections.sort(delegation, Collections.reverseOrder());

        return delegation;
    }

    public List<User> getAllUsersByRoleName(String roleName) {
        return userRepository.findAll().stream().filter(user -> user.getDelegations().contains(roleName)).collect(Collectors.toList());
    }
}
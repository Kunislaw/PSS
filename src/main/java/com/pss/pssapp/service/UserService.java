package com.pss.pssapp.service;

import com.pss.pssapp.models.Delegation;
import com.pss.pssapp.models.Role;
import com.pss.pssapp.models.User;
import com.pss.pssapp.repository.DelegationRepository;
import com.pss.pssapp.repository.RoleRepository;
import com.pss.pssapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DelegationRepository delegationRepository;

    @Autowired
    RoleRepository roleRepository;

    public void registerUser(User user){
        Set<Role> userRoles = user.getRole();
        Role roleUser = roleRepository.getByRoleName("ROLE_USER");
        if(roleUser != null){
            userRoles.add(roleUser);
        } else {
            userRoles.add(new Role("ROLE_USER"));
        }
        user.setRole(userRoles);
        userRepository.save(user);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public void changePassword(long userId, String newPassword){
        User userToChange = userRepository.getOne(userId);
        userToChange.setPassword(newPassword);
        userRepository.save(userToChange);
    }
    public boolean deleteUserById(long userId){
        try{
            userRepository.deleteById(userId);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public void addDelegation(long userId, Delegation delegation){
        User userToAddDelegation = userRepository.getOne(userId);
        Set<Delegation> userDelegations = userToAddDelegation.getDelegations();
        Delegation newDelegation = delegation;
        userDelegations.add(newDelegation);
        userToAddDelegation.setDelegations(userDelegations);
        userRepository.save(userToAddDelegation);
    }

    public boolean removeDelegation(long userId, long delegationId) {
        User userToDeleteDelegation = userRepository.getOne(userId);
        Delegation delegation = delegationRepository.getOne(delegationId);
        if (userToDeleteDelegation != null) {
            Set<Delegation> userDelegations = userToDeleteDelegation.getDelegations();
            if (delegation != null) {
                boolean deleted = userDelegations.remove(delegation);
                if(deleted == true){
                    userToDeleteDelegation.setDelegations(userDelegations);
                    delegationRepository.deleteById(delegationId);
                    userRepository.save(userToDeleteDelegation);
                }
                return deleted;
            }
        }
        return false;
    }
}
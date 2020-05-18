package com.pss.pssapp.service;

import com.pss.pssapp.dto.EditUserDTO;
import com.pss.pssapp.models.Delegation;
import com.pss.pssapp.models.Role;
import com.pss.pssapp.models.User;
import com.pss.pssapp.repository.DelegationRepository;
import com.pss.pssapp.repository.RoleRepository;
import com.pss.pssapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user){
        Set<Role> userRoles = user.getRole();
        Role roleUser = roleRepository.findByRoleName("ROLE_USER");
        if(roleUser != null){
            userRoles.add(roleUser);
        } else {
            userRoles.add(new Role("ROLE_USER"));
        }
        user.setRole(userRoles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
        User userToAddDelegation = userRepository.findById(userId).orElse(null);
        System.out.println(delegation);
        if(userToAddDelegation != null){
            List<Delegation> userDelegations = userToAddDelegation.getDelegations();
            Delegation newDelegation = delegation;
            userDelegations.add(newDelegation);
            userToAddDelegation.setDelegations(userDelegations);
            userRepository.save(userToAddDelegation);
        }

    }

    public boolean removeDelegation(long userId, long delegationId) {
        User userToDeleteDelegation = userRepository.getOne(userId);
        Delegation delegation = delegationRepository.getOne(delegationId);
        if (userToDeleteDelegation != null) {
            List<Delegation> userDelegations = userToDeleteDelegation.getDelegations();
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
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User getUserByID(long id){
        return userRepository.findById(id).orElse(null);
    }
    public boolean editUser(long userId, EditUserDTO editUserDTO){
        User searchUser = userRepository.findById(userId).orElse(null);
        if(searchUser != null){
            searchUser.setCompanyAddress(editUserDTO.getCompanyAddress());
            searchUser.setCompanyName(editUserDTO.getCompanyName());
            searchUser.setCompanyNip(editUserDTO.getCompanyNip());
            searchUser.setName(editUserDTO.getName());
            searchUser.setLastName(editUserDTO.getLastName());
            userRepository.save(searchUser);
            return true;
        } else {
            return false;
        }
    }
}
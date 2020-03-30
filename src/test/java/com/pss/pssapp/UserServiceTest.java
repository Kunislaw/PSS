package com.pss.pssapp;

import com.pss.pssapp.models.User;
import com.pss.pssapp.models.Role;
import com.pss.pssapp.repository.DelegationRepository;
import com.pss.pssapp.repository.RoleRepository;
import com.pss.pssapp.repository.UserRepository;
import com.pss.pssapp.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.*;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @TestConfiguration
    static class UserServiceTestsContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserService();
        }
    }



    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private DelegationRepository delegationRepository;

    @Before
    public void setUp() {
        User newUser = new User();
        newUser.setCompanyNip("123123123");
        newUser.setEmail("testemail@wp.pl");
        newUser.setPassword("ABCEDF");
        newUser.setCompanyAddress("Testowa uluica");
        newUser.setName("Jacek");
        newUser.setLastName("Testowy");
        newUser.setCompanyName("Testowa firma");

        List<User> users = Arrays.asList(
                newUser
        );
        Role userRole = new Role("USER_ROLE");
        Mockito.when(userRepository.findAll()).thenReturn(users);
        Mockito.when(roleRepository.findByRoleName("ROLE_USER")).thenReturn(userRole);
        Mockito.when(userRepository.getOne(0l)).thenReturn(newUser);
    }


    @Test
    public void registerUserTest(){
        User newUser = new User();
        newUser.setCompanyNip("123123123");
        newUser.setEmail("testemail@wp.pl");
        newUser.setPassword("ABCEDF");
        newUser.setCompanyAddress("Testowa uluica");
        newUser.setName("Jacek");
        newUser.setLastName("Testowy");
        newUser.setCompanyName("Testowa firma");
        userService.registerUser(newUser);
        Mockito.verify(userRepository, Mockito.times(1)).save(newUser);
    }

    @Test
    public void getAllUsersTest(){
        User newUser = new User();
        newUser.setCompanyNip("123123123");
        newUser.setEmail("testemail@wp.pl");
        newUser.setPassword("ABCEDF");
        newUser.setCompanyAddress("Testowa uluica");
        newUser.setName("Jacek");
        newUser.setLastName("Testowy");
        newUser.setCompanyName("Testowa firma");
        List<User> users = Arrays.asList(
                newUser
        );
        assertThat(userService.getAllUsers().toString()).isEqualTo(users.toString());
    }

    @Test
    public void changePasswordTest(){
        userService.changePassword(0, "NoweHaslo");
        assertThat(userRepository.getOne(0l).getPassword()).isEqualTo("NoweHaslo");
    }



}

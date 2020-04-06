package com.pss.pssapp;

import com.pss.pssapp.models.Delegation;
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


import javax.validation.constraints.AssertTrue;
import java.util.*;

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


        Delegation delegation1 = new Delegation();
        Delegation delegation2 = new Delegation();
        delegation1.setDateTimeStart(new Date());
        delegation1.setDateTimeStop(new Date());
        delegation2.setDateTimeStart(new Date());
        delegation2.setDateTimeStop(new Date());

        List<Delegation> delegationList = new ArrayList<>();
        delegationList.add(delegation1);
        delegationList.add(delegation2);

        newUser.setDelegations(delegationList);
        List<User> users = Arrays.asList(
                newUser
        );
        Role userRole = new Role("USER_ROLE");
        Mockito.when(userRepository.findAll()).thenReturn(users);
        Mockito.when(delegationRepository.findAll()).thenReturn(delegationList);
        Mockito.when(roleRepository.findByRoleName("ROLE_USER")).thenReturn(userRole);
        Mockito.when(userRepository.getOne(0l)).thenReturn(newUser);
        Mockito.when(delegationRepository.getOne(0l)).thenReturn(delegation1);
    }


    @Test
    public void registerUserTest(){
        User newUser = new User();
        newUser.setCompanyNip("1231233123");
        newUser.setEmail("test3email@wp.pl");
        newUser.setPassword("ABCE3DF");
        newUser.setCompanyAddress("Testowa uluica");
        newUser.setName("Jace3k");
        newUser.setLastName("Test3owy");
        newUser.setCompanyName("Testowa firmaa");
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
    @Test
    public void deleteUserByIdTest(){
        userService.deleteUserById(userRepository.getOne(0l).getId());
        Mockito.verify(userRepository, Mockito.times(1)).delete(userRepository.getOne(0l));
    }
    @Test
    public void addDelegationTest(){
        User user = userRepository.getOne(0l);
        Delegation newDelegation = new Delegation();
        newDelegation.setDateTimeStart(new Date());
        newDelegation.setDateTimeStop(new Date());
        userService.addDelegation(user.getId(), newDelegation);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }
    @Test
    public void removeDelegationTest() {
        User user = userRepository.getOne(0l);
        Delegation delegation = delegationRepository.getOne(0l);
        boolean deleted = userService.removeDelegation(user.getId(), delegation.getId());
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }
}

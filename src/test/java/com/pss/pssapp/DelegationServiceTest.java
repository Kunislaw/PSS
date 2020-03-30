package com.pss.pssapp;

import com.pss.pssapp.models.Delegation;
import com.pss.pssapp.models.Role;
import com.pss.pssapp.models.User;
import com.pss.pssapp.repository.DelegationRepository;
import com.pss.pssapp.repository.RoleRepository;
import com.pss.pssapp.repository.UserRepository;
import com.pss.pssapp.service.DelegationService;
import com.pss.pssapp.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
public class DelegationServiceTest {

    @TestConfiguration
    static class DelegationServiceTestsContextConfiguration {

        @Bean
        public DelegationService delegationService() {
            return new DelegationService();
        }
    }

    @Autowired
    private DelegationService delegationService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private DelegationRepository delegationRepository;

    @Before
    public void setUp() {
        Delegation delegation1 = new Delegation();
        Delegation delegation2 = new Delegation();
        delegation1.setDateTimeStart(new Date());
        delegation1.setDateTimeStop(new Date());
        delegation2.setDateTimeStart(new Date());
        delegation2.setDateTimeStop(new Date());
        List<Delegation> delegationList = new ArrayList<>();


        Delegation delegation3 = new Delegation();
        Delegation delegation4 = new Delegation();
        delegation3.setDateTimeStart(new Date(2020,9,3));
        delegation3.setDateTimeStop(new Date());
        delegation4.setDateTimeStart(new Date(2020, 6,3));
        delegation4.setDateTimeStop(new Date());
        List<Delegation> delegationList2 = new ArrayList<>();


        delegationList.add(delegation1);
        delegationList.add(delegation2);
        delegationList2.add(delegation3);
        delegationList2.add(delegation4);
        Mockito.when(delegationRepository.findAll()).thenReturn(delegationList);
        Mockito.when(delegationRepository.findAllByOrderByDateTimeStartDesc()).thenReturn(delegationList2);
    }

    @Test
    public void getAllDelegationsTest(){
        Delegation delegation1 = new Delegation();
        Delegation delegation2 = new Delegation();
        delegation1.setDateTimeStart(new Date());
        delegation1.setDateTimeStop(new Date());
        delegation2.setDateTimeStart(new Date());
        delegation2.setDateTimeStop(new Date());
        List<Delegation> delegations = new ArrayList<>();
        delegations.add(delegation1);
        delegations.add(delegation2);
        assertThat(delegationService.getAllDelegations().toString()).isEqualTo(delegations.toString());
    }
    @Test
    public void getAllDelegationOrderByDateStartDesc(){
        Delegation delegation1 = new Delegation();
        Delegation delegation2 = new Delegation();
        delegation1.setDateTimeStart(new Date(2020,9,3));
        delegation1.setDateTimeStop(new Date());
        delegation2.setDateTimeStart(new Date(2020,6, 3));
        delegation2.setDateTimeStop(new Date());
        List<Delegation> delegations = new ArrayList<>();
        delegations.add(delegation1);
        delegations.add(delegation2);
        assertThat(delegationService.getAllDelegationsOrderByDateStartDesc().toString()).isEqualTo(delegations.toString());
    }

}

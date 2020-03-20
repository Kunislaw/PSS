package com.pss.pssapp.repository;

import com.pss.pssapp.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * RoleRepository
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getByRoleName(String roleName);
}
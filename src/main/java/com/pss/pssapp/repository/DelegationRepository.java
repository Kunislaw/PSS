package com.pss.pssapp.repository;

import com.pss.pssapp.models.Delegation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DelegationRepository
 */
@Repository
public interface DelegationRepository extends JpaRepository<Delegation, Long> {}
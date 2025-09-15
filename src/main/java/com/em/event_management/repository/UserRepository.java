package com.em.event_management.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.em.event_management.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
    
}

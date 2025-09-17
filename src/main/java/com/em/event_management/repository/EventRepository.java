package com.em.event_management.repository;

import com.em.event_management.domain.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class EventRepository extends JpaRepository<Event, UUID> {
}

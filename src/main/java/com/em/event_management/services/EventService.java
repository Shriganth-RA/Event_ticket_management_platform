package com.em.event_management.services;

import java.util.UUID;

import com.em.event_management.domain.CreateEventRequest;
import com.em.event_management.domain.entities.Event;

public interface EventService {
    Event creaEvent(UUID organizerId, CreateEventRequest request);
}

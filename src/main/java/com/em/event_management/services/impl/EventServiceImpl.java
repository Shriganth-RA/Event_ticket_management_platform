package com.em.event_management.services.impl;

import com.em.event_management.domain.CreateEventRequest;
import com.em.event_management.domain.entities.Event;
import com.em.event_management.domain.entities.TicketType;
import com.em.event_management.domain.entities.User;
import com.em.event_management.exceptions.UserNotFoundException;
import com.em.event_management.repository.EventRepository;
import com.em.event_management.repository.UserRepository;
import com.em.event_management.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    public Event createEvent(UUID organizerId, CreateEventRequest event) {
        User organizer = userRepository.findById(organizerId).orElseThrow(() -> new UserNotFoundException(String.format("User with ID '%s' not found", organizerId)));

        List<TicketType> ticketTypesToCreate = event.getTicket_types().stream().map(ticketType -> {
            TicketType ticketTypeToCreate = new TicketType();
            ticketTypeToCreate.setName(ticketType.getName());
            ticketTypeToCreate.setDescription(ticketType.getDescription());
            ticketTypeToCreate.setPrice(ticketType.getPrice());
            return ticketTypeToCreate;
        }).toList();

        Event createNewEvent = new Event();
        createNewEvent.setName(event.getName());
        createNewEvent.setStart(event.getStart());
        createNewEvent.setEnd(event.getEnd());
        createNewEvent.setVenue(event.getVenue());
        createNewEvent.setSalesStart(event.getSalesStart());
        createNewEvent.setSalesEnd(event.getSalesEnd());
        createNewEvent.setStatus(event.getStatus());
        createNewEvent.setOrganizer(organizer);
        createNewEvent.setTicketTypes(ticketTypesToCreate);

        return eventRepository.save(createNewEvent);
    }
}

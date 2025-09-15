package com.em.event_management.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.em.event_management.domain.entities.EventStatusEnum;
import com.em.event_management.domain.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventRequest {
    
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private String venue;
    private LocalDateTime salesStart;
    private LocalDateTime salesEnd;
    private EventStatusEnum status;
    private User organizer;
    private List<CreateTicketTypeRequest> ticket_types = new ArrayList<>();

}

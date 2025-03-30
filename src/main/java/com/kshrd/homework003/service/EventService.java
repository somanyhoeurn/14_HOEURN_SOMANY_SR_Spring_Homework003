package com.kshrd.homework003.service;


import com.kshrd.homework003.model.request.EventRequest;
import com.kshrd.homework003.model.response.EventResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import java.util.List;

public interface EventService {
    List<EventResponse> getAllEvents(@Positive Integer offset, @Positive Integer limit);

    EventResponse getEventById(Long eventId);

    EventResponse addEvent(@Valid EventRequest eventRequest);

    void deleteEventById(Long eventId);

    EventResponse updateEventById(@Positive Long eventId, @Valid EventRequest request);
}

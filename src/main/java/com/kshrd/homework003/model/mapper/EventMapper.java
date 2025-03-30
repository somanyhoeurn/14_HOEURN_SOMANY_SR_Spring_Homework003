package com.kshrd.homework003.model.mapper;

import com.kshrd.homework003.model.entity.Event;
import com.kshrd.homework003.model.entity.Venue;
import com.kshrd.homework003.model.response.EventResponse;
import com.kshrd.homework003.model.response.VenueResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventResponse toEventResponse(Event event);
    VenueResponse toVenueResponse(Venue venue);
    List<EventResponse> toEventResponseList(List<Event> events);
}

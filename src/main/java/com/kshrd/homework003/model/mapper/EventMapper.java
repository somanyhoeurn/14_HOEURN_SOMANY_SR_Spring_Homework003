package com.kshrd.homework003.model.mapper;

import com.kshrd.homework003.model.entity.Event;
import com.kshrd.homework003.model.response.EventResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventResponse toEventResponse(Event event);
}

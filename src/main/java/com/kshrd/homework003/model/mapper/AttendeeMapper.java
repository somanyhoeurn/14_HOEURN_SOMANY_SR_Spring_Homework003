package com.kshrd.homework003.model.mapper;

import com.kshrd.homework003.model.entity.Attendee;
import com.kshrd.homework003.model.response.AttendeeResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttendeeMapper {
    AttendeeResponse toAttendeeResponse(Attendee attendee);
}

package com.kshrd.homework003.service;

import com.kshrd.homework003.model.request.AttendeeRequest;
import com.kshrd.homework003.model.response.AttendeeResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import java.util.List;

public interface AttendeeService {
    List<AttendeeResponse> getAllAttendees(@Positive Integer offset, @Positive Integer limit);

    AttendeeResponse getAttendeeById(@Positive Long attendeeId);

    AttendeeResponse addAttendee(@Valid AttendeeRequest request);

    void deleteAttendeeById(@Positive Long attendeeId);

    AttendeeResponse updateAttendeeById(@Positive Long attendeeId, @Valid AttendeeRequest request);
}

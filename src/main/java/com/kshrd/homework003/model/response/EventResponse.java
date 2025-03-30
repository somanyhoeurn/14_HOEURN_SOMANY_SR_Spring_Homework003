package com.kshrd.homework003.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {
    private Long eventId;
    private String eventName;
    private LocalDateTime eventDate;
    private VenueResponse venues;
    private List<AttendeeResponse> attendees;
}

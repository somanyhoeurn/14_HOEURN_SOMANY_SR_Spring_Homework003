package com.kshrd.homework003.model.entity;

import com.kshrd.homework003.model.response.AttendeeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Long eventId;
    private String eventName;
    private LocalDateTime eventDate;
    private Venue venues;
    private List<Attendee> attendees;
}

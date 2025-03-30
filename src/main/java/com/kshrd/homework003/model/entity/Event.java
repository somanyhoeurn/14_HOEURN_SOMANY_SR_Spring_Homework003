package com.kshrd.homework003.model.entity;

import com.kshrd.homework003.model.response.AttendeeResponse;
import com.kshrd.homework003.model.response.VenueResponse;
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
    private List<VenueResponse> venues;
    private List<AttendeeResponse> attendees;
}

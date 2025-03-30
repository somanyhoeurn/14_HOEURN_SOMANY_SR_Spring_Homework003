package com.kshrd.homework003.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeResponse {
    private Long attendeeId;
    private String attendeeName;
    private String email;
}

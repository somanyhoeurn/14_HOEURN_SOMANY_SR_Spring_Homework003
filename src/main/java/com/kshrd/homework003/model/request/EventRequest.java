package com.kshrd.homework003.model.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    @NotBlank(message = "must not be blank")
//    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String eventName;

    @NotNull(message = "must not be null")
//    @Future(message = "Date must be in the future")
    private LocalDateTime eventDate;

    @NotNull(message = "Venue Id is required!!!")
    private Long venuesId;

    @NotNull(message = "Attendee Id is required!!!")
    private Set<Long> attendeesId;

}

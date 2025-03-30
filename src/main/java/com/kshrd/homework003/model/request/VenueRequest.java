package com.kshrd.homework003.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueRequest {
    @NotBlank(message = "Name is required")
//    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String venueName;
    @NotBlank(message = "Location is required")
    private String location;
}

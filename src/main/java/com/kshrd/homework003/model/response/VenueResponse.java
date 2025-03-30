package com.kshrd.homework003.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueResponse {
    private Long venueId;
    private String venueName;
    private String location;
}

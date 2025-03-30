package com.kshrd.homework003.service;


import com.kshrd.homework003.model.entity.Venue;
import com.kshrd.homework003.model.request.VenueRequest;
import com.kshrd.homework003.model.response.VenueResponse;

import java.util.List;

public interface VenueService {
    List<VenueResponse> getAllVenues(Long page, Long size);

    VenueResponse getVenueById(Long venueId);

    VenueResponse addVenue(VenueRequest request);

    void deleteVenue(Long venueId);

    VenueResponse updateVenueById(Long venueId, VenueRequest request);
}

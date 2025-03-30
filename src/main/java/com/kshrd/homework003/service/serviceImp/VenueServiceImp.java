package com.kshrd.homework003.service.serviceImp;

import com.kshrd.homework003.exception.NotFoundException;
import com.kshrd.homework003.model.entity.Venue;
import com.kshrd.homework003.model.mapper.VenueMapper;
import com.kshrd.homework003.model.request.VenueRequest;
import com.kshrd.homework003.model.response.VenueResponse;
import com.kshrd.homework003.repository.VenueRepository;
import com.kshrd.homework003.service.VenueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImp implements VenueService {

    private final VenueRepository venueRepository;
    private final VenueMapper venueMapper;

    @Override
    public List<VenueResponse> getAllVenues(Long offset, Long size) {
        offset = (offset - 1) * size;
        return venueRepository
                .getAllVenues(offset, size)
                .stream()
                .map(venueMapper::toVenueResponse)
                .toList();
    }

    @Override
    public VenueResponse getVenueById(Long venueId) {
        Venue venue = venueRepository.getVenueById(venueId);
        if (venue == null) {
           throw new NotFoundException("The venue with id " + venueId + " has not been founded");
        }
        return venueMapper.toVenueResponse(venue);
    }

    @Override
    public VenueResponse addVenue(@Valid VenueRequest request) {
        Venue venue = venueRepository.addVenue(request);
        if (venue == null) {
            throw new NotFoundException("Failed to add venue");
        }
        return venueMapper.toVenueResponse(venue);
    }

    @Override
    public void deleteVenue(Long venueId) {
        int row = venueRepository.deleteVenue(venueId);
        if (row == 0) {
            throw new NotFoundException("The venue with id " + venueId + " has not been founded");
        }
    }

    @Override
    public VenueResponse updateVenueById(Long venueId, VenueRequest request) {
        Venue venue = venueRepository.getVenueById(venueId);
        if (venue == null) {
            throw new NotFoundException("The venue with id " + venueId + " has not been founded");
        }
        Venue updateVenue = venueRepository.updateVenue(venueId, request);
        return venueMapper.toVenueResponse(updateVenue);
    }
}

package com.kshrd.homework003.controller;

import com.kshrd.homework003.api_response.APIResponse;
import com.kshrd.homework003.model.request.VenueRequest;
import com.kshrd.homework003.model.response.VenueResponse;
import com.kshrd.homework003.service.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/v1/venue")
@RequiredArgsConstructor
public class VenueController {
    private final VenueService venueService;

    @GetMapping
    @Operation(summary = "Get all Venues", description = "Retrieve a paginated list of all Venues.")
    public ResponseEntity<APIResponse<List<VenueResponse>>> getAllVenue(@Positive @RequestParam(defaultValue = "1") Long page, @Positive @RequestParam(defaultValue = "10") Long size) {
        List<VenueResponse> venue = venueService.getAllVenues(page,size);
        return ResponseEntity.ok().body(new APIResponse<>("All venues have been successfully fetched.",venue,OK));
    }

    @GetMapping("/{venue-id}")
    @Operation(summary = "Get venue by ID", description = "Retrieve venue by id.")
    public ResponseEntity<APIResponse<VenueResponse>> getVenueById(@Positive @PathVariable("venue-id") Long venueId) {
        VenueResponse venue = venueService.getVenueById(venueId);
        return ResponseEntity.ok().body(new APIResponse<>("All venues have been successfully fetched.",venue,OK));
    }

    @PostMapping
    @Operation(summary = "Add a new venue", description = "Insert a new venue.")
    public ResponseEntity<APIResponse<VenueResponse>> addVenue(@Valid @RequestBody VenueRequest request) {
        VenueResponse venue = venueService.addVenue(request);
        return ResponseEntity.ok().body(new APIResponse<>("Venues have been successfully added.",venue,CREATED));
    }

    @DeleteMapping("/{venue-id}")
    @Operation(summary = "Delete venue by ID", description = "Remove venue by Id.")
    public ResponseEntity<?> deleteVenue(@Positive @PathVariable("venue-id") Long venueId) {
        venueService.deleteVenue(venueId);
        return ResponseEntity.ok().body(new APIResponse<>("Venues have been removed.",null,OK));
    }

    @PutMapping("/{venue-id}")
    @Operation(summary = "Update venue by ID", description = "Modify venue by Id.")
    public ResponseEntity<APIResponse<VenueResponse>> updateVenueById(@Positive @PathVariable("venue-id") Long venueId,@Valid @RequestBody VenueRequest request) {
        VenueResponse venue = venueService.updateVenueById(venueId, request);
        return ResponseEntity.ok().body(new APIResponse<>("Venues have been updated.",venue,OK));
    }

}

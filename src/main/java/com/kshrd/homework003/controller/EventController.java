package com.kshrd.homework003.controller;

import com.kshrd.homework003.api_response.APIResponse;
import com.kshrd.homework003.model.request.EventRequest;
import com.kshrd.homework003.model.response.EventResponse;
import com.kshrd.homework003.service.EventService;
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
@RequestMapping("api/v1/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    @Operation(summary = "Get all Events", description = "Retrieve a paginated list of all Events.")
    public ResponseEntity<APIResponse<List<EventResponse>>> getAllEvents(@Positive @RequestParam(defaultValue = "1") Integer offset, @Positive @RequestParam(defaultValue = "10") Integer limit) {
        List<EventResponse> event = eventService.getAllEvents(offset, limit);
        return ResponseEntity.ok().body(new APIResponse<>("All events have been successfully fetched.", event, OK));
    }

    @GetMapping("/{event-id}")
    @Operation(summary = "Get event by ID", description = "Retrieve event by id.")
    public ResponseEntity<APIResponse<EventResponse>> getEventById(@Positive @PathVariable("event-id") Long eventId) {
        EventResponse event = eventService.getEventById(eventId);
        return ResponseEntity.ok().body(new APIResponse<>("Events with id : " + eventId + " have been successfully fetched.", event, OK));
    }

    @PostMapping
    @Operation(summary = "Add a new event", description = "Insert a new event.")
    public ResponseEntity<APIResponse<EventResponse>> addEvent(@Valid @RequestBody EventRequest eventRequest) {
        EventResponse event = eventService.addEvent(eventRequest);
        return ResponseEntity.ok().body(new APIResponse<>("Event added successfully", event, CREATED));
    }

    @DeleteMapping("/{event-id}")
    @Operation(summary = "Delete event by ID", description = "Remove event by Id.")
    public ResponseEntity<?> deleteEvent(@Positive @PathVariable("event-id") Long eventId) {
        eventService.deleteEventById(eventId);
        return ResponseEntity.ok().body(new APIResponse<>("Event deleted successfully",null,OK));
    }

    @PutMapping("/{event-id}")
    @Operation(summary = "Update event by ID", description = "Modify event by Id.")
    public ResponseEntity<APIResponse<EventResponse>> updateEventById(@Positive @PathVariable("event-id") Long eventId, @Valid @RequestBody EventRequest request) {
        EventResponse event = eventService.updateEventById(eventId,request);
        return ResponseEntity.ok().body(new APIResponse<>("Event have been updated.",event,OK));
    }


}

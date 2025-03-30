package com.kshrd.homework003.controller;

import com.kshrd.homework003.api_response.APIResponse;
import com.kshrd.homework003.model.request.AttendeeRequest;
import com.kshrd.homework003.model.response.AttendeeResponse;
import com.kshrd.homework003.service.AttendeeService;
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
@RequestMapping("api/v1/attendee")
@RequiredArgsConstructor
public class AttendeeController {
    private final AttendeeService attendeeService;

    @GetMapping
    @Operation(summary = "Get all attendees", description = "Retrieve a paginated list of all attendees.")
    public ResponseEntity<APIResponse<List<AttendeeResponse>>> getAllAttendees(@Positive @RequestParam(defaultValue = "1") Integer offset,@Positive @RequestParam(defaultValue = "10") Integer limit) {
        List<AttendeeResponse> attendee = attendeeService.getAllAttendees(offset,limit);
        return ResponseEntity.ok().body(new APIResponse<>("All attendee have been successfully fetched.",attendee,OK));
    }

    @GetMapping("/{attendee-id}")
    @Operation(summary = "Get attendee by ID", description = "Retrieve attendee by id.")
    public ResponseEntity<APIResponse<AttendeeResponse>> getAttendeeById(@Positive @PathVariable("attendee-id") Long attendeeId) {
        AttendeeResponse attendee = attendeeService.getAttendeeById(attendeeId);
        return ResponseEntity.ok().body(new APIResponse<>("Attendee have been successfully fetched by Id.",attendee,OK));
    }

    @PostMapping
    @Operation(summary = "Add a new attendee", description = "Insert a new attendees.")
    public ResponseEntity<APIResponse<AttendeeResponse>> addAttendee(@Valid @RequestBody AttendeeRequest request) {
        AttendeeResponse attendee = attendeeService.addAttendee(request);
        return ResponseEntity.ok().body(new APIResponse<>("Attendee have been added.",attendee,CREATED));
    }

    @DeleteMapping("/{attendee-id}")
    @Operation(summary = "Delete attendee by ID", description = "Remove attendee by Id.")
    public ResponseEntity<?> deleteAttendeeById(@Positive @PathVariable("attendee-id") Long attendeeId) {
        attendeeService.deleteAttendeeById(attendeeId);
        return ResponseEntity.ok().body(new APIResponse<>("Attendee have been deleted.",null,OK));
    }

    @PutMapping("/{attendee-id}")
    @Operation(summary = "Update attendee by ID", description = "Modify attendee by Id.")
    public ResponseEntity<APIResponse<AttendeeResponse>> updateAttendeeById(@Positive @PathVariable("attendee-id") Long attendeeId, @Valid @RequestBody AttendeeRequest request) {
        AttendeeResponse attendee = attendeeService.updateAttendeeById(attendeeId,request);
        return ResponseEntity.ok().body(new APIResponse<>("Attendee have been updated.",attendee,OK));
    }



}

package com.kshrd.homework003.exception;

import com.kshrd.homework003.model.request.AttendeeRequest;
import com.kshrd.homework003.model.request.EventRequest;
import com.kshrd.homework003.model.request.VenueRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Bad request");

        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        problemDetail.setProperty("errors", errors);
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handleMethodValidationException(HandlerMethodValidationException e) {
        Map<String, String> errors = new HashMap<>();

        e.getParameterValidationResults().forEach(parameterError -> {
            String paramName = parameterError.getMethodParameter().getParameterName();
            Object argument = parameterError.getArgument();

            if (argument instanceof EventRequest eventRequest) {
                if (isAllFieldsBlankEvent(eventRequest)) {
                    errors.put("eventRequest", "must not be blank");
                } else {
                    for (var messageError : parameterError.getResolvableErrors()) {
                        errors.put(paramName, messageError.getDefaultMessage());
                    }
                }
            } else if (argument instanceof VenueRequest venueRequest) {
                if (isAllFieldsBlankVenue(venueRequest)) {
                    errors.put("venueRequest", "must not be blank");
                } else {
                    for (var messageError : parameterError.getResolvableErrors()) {
                        errors.put(paramName, messageError.getDefaultMessage());
                    }
                }
            } else if (argument instanceof AttendeeRequest attendeeRequest) {
                if (isAllFieldsBlankAttendee(attendeeRequest)) {
                    errors.put("attendeeRequest", "must not be blank");
                } else {
                    for (var messageError : parameterError.getResolvableErrors()) {
                        errors.put(paramName, messageError.getDefaultMessage());
                    }
                }
            } else {
                for (var messageError : parameterError.getResolvableErrors()) {
                    errors.put(paramName, messageError.getDefaultMessage());
                }
            }
        });

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Bad Request");
        problemDetail.setProperties(Map.of(
                "errors", errors,
                "timestamp", LocalDateTime.now().toString()
        ));

        return problemDetail;
    }

    private boolean isAllFieldsBlankEvent(EventRequest eventRequest) {
        return (eventRequest.getEventName() == null || eventRequest.getEventName().isBlank()) &&
                (eventRequest.getEventDate() == null) &&
                (eventRequest.getVenuesId() == null) &&
                (eventRequest.getAttendeesId() == null || eventRequest.getAttendeesId().isEmpty());
    }

    private boolean isAllFieldsBlankVenue(VenueRequest venueRequest) {
        return (venueRequest.getVenueName() == null || venueRequest.getVenueName().isBlank()) &&
                (venueRequest.getLocation() == null || venueRequest.getLocation().isBlank());
    }

    private boolean isAllFieldsBlankAttendee(AttendeeRequest attendeeRequest) {
        return (attendeeRequest.getAttendeeName() == null || attendeeRequest.getAttendeeName().isBlank()) &&
                (attendeeRequest.getEmail() == null || attendeeRequest.getEmail().isBlank());
    }

}

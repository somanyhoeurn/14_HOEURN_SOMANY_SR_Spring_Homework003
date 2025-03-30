package com.kshrd.homework003.api_response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<T> {
    private final String message;
    private final T payload;
    private final HttpStatus status;
    private LocalDateTime timestamp = LocalDateTime.now();
}

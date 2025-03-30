package com.kshrd.homework003.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeRequest {
    @NotBlank(message = "Name is required")
//    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String attendeeName;
    @NotBlank(message = "email is required")
    @Email(message = "must be a well-formed email address")
    private String email;
}

package com.example.digcompsys.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class StatusUpdateRequest {
    @NotNull(message = "Complaint ID should be Entered")
    private Long complaintId;
    @NotBlank(message = "Enter Status of Complaint")
    private String newStatus;
}

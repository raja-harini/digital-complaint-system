package com.example.digcompsys.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class StatusHistoryResponse {
    @NotBlank(message = "Verify old status")
    private String oldStatus;
    @NotBlank(message = "Check for Status Update")
    private String newStatus;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalDateTime escalationTime;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalDateTime resolutionTime;
}

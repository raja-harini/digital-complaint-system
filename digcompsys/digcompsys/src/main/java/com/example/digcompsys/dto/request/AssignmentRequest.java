package com.example.digcompsys.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class AssignmentRequest {
    @NotNull(message = "Complaint ID should not be null")
    private Long assignmentId;
    @NotNull(message = "Team Id is required for allocation")
    private Long teamId;
}

package com.example.digcompsys.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class ComplaintResponse {
    private Long id;
    private Long complaintId;
    private String title;
    private String description;
    private String category;
    private String priority;
    @NotBlank(message = "Must check the Last Status Update")
    private String status;
}

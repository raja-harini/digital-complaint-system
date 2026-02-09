package com.example.digcompsys.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
public class TeamRequest {
    @NotBlank(message = "Enter Team Name")
    private String teamName;
    @Email(message = "Enter Valid Email")
    private String contact;
}

package com.example.digcompsys.dto.response;

import jakarta.validation.constraints.NotNull;

public class TeamResponse {
    @NotNull(message = "TeamID should not be null")
    private Long teamId;
    private String teamName;
    private String contact;
}

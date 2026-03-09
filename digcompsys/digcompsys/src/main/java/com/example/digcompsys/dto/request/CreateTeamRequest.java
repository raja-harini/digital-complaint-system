package com.example.digcompsys.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTeamRequest {

    private String teamName;
    private String contact;
    private Long teamLeadId;
    private List<Long> employeeIds;
}
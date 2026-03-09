package com.example.digcompsys.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReassignRequest {

    private Long assignmentId;
    private Long newTeamId;
    private Long adminId;
}
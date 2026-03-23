package com.example.digcompsys.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignComplaintRequest {

    private Long complaintId;
    private Long teamId;
}
package com.example.digcompsys.dto.response;

import com.example.digcompsys.model.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusHistoryResponse {

    private Long historyId;
    private Long complaintId;
    private Long userId;
    private Long teamId;
    private Status oldStatus;
    private Status newStatus;
    private LocalDateTime escalationTime;
    private LocalDateTime resolutionTime;
    private Boolean activeFlag;
}
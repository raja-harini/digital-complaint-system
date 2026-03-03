package com.example.digcompsys.dto.request;

import com.example.digcompsys.model.Status;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateStatusRequest {

    private Long complaintId;
    private Status newStatus;
    private Long userId;
    private Long teamId;
}
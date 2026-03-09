package com.example.digcompsys.dto.request;

import com.example.digcompsys.model.NotificationType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequest {

    private Long userId;
    private Long complaintId;
    private NotificationType notificationType;
    private String message;
}
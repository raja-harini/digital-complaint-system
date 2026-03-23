package com.example.digcompsys.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationResponse {
    private Long notificationId;
    private String message;
    private boolean readFlag;
}
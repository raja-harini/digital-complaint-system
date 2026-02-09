package com.example.digcompsys.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class NotificationResponse {
    @NotNull(message = "Notification should be unique")
    private Long notificationId;
    @NotBlank(message = "Notification Type should not be blank")
    private String notificationType;
    private String message;
    private LocalDateTime sentAt;
}

package com.example.digcompsys.dto.response;

import com.example.digcompsys.model.RoleName;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long userId;
    private String userName;
    private String email;
    private String phone;
    private RoleName roleName;
    private LocalDateTime createdAt;
    private String documentUrl;
}
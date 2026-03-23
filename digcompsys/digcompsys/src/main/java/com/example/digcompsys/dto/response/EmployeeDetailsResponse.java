package com.example.digcompsys.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDetailsResponse {

    private Long userId;
    private String userName;
    private String email;
    private String fieldOfWork;
    private String preferredRole;
    private String documentUrl;
}
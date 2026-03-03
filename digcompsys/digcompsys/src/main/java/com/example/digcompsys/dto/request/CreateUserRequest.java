package com.example.digcompsys.dto.request;

import com.example.digcompsys.model.RoleName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {

    private String userName;
    private String password;
    private String email;
    private String phone;
    private RoleName roleName;
}
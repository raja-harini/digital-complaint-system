package com.example.digcompsys.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank(message = "Username is Required")
    private String userName;
    @Email(message = "Invalid Email")
    private String email;
    @Size(min = 8, message = "Password must be atleast 8 characters")
    private String password;
    @Size(max = 10, message = "Phone Number should not exceed Maximum Limit 10")
    private String phone;
    private String roleName;
}

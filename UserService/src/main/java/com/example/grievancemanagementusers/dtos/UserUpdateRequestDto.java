package com.example.grievancemanagementusers.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequestDto {
    private String name;
    private String email;
    private String phone;
    private String role;
}

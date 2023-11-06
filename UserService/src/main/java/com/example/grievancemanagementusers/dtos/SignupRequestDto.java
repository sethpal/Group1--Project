package com.example.grievancemanagementusers.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SignupRequestDto {
    private String name;
    private String email;
    private String phone;
    private String password;
    private List<String> roles;
}

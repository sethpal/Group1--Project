package com.example.grievancemanagementusers.contollers;


import com.example.grievancemanagementusers.dtos.LogoutDto;
import com.example.grievancemanagementusers.dtos.UserDto;
import com.example.grievancemanagementusers.dtos.LoginRequestDto;
import com.example.grievancemanagementusers.dtos.SignupRequestDto;
import com.example.grievancemanagementusers.models.SessionStatus;
import com.example.grievancemanagementusers.services.AuthService;
import com.example.grievancemanagementusers.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public UserDto signup(@RequestBody SignupRequestDto signupRequestDto){
        return authService.signup(signupRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader Map<String, String> headers, @RequestBody LogoutDto logoutDto){
        String token = headers.get("set-cookie");

        SessionStatus sessionStatus = authService.validate(token);
        if(sessionStatus==null || sessionStatus.equals(SessionStatus.ENDED))
            throw new RuntimeException("Invalid Token");

        String response =  authService.logout(token, logoutDto.getUserId());

        return ResponseEntity.ok(response);
    }
}

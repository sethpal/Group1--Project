package com.example.grievancemanagementusers.contollers;

import com.example.grievancemanagementusers.dtos.UserDto;
import com.example.grievancemanagementusers.dtos.SignupRequestDto;
import com.example.grievancemanagementusers.dtos.UserUpdateRequestDto;
import com.example.grievancemanagementusers.models.SessionStatus;
import com.example.grievancemanagementusers.models.User;
import com.example.grievancemanagementusers.services.AuthService;
import com.example.grievancemanagementusers.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id, @RequestHeader Map<String, String> headers){
        String token = headers.get("set-cookie");

        SessionStatus sessionStatus = authService.validate(token);
        if(sessionStatus==null || sessionStatus.equals(SessionStatus.ENDED))
            throw new RuntimeException("Invalid Token");

        User user = userService.getUserById(id);
        return ResponseEntity.ok(UserDto.from(user));
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody SignupRequestDto signupRequestDto){

        UserDto userDto = userService.createUser(signupRequestDto);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String id, @RequestBody UserUpdateRequestDto userSignupDto, @RequestHeader Map<String, String> headers){
        String token = headers.get("set-cookie");

        SessionStatus sessionStatus = authService.validate(token);
        if(sessionStatus==null || sessionStatus.equals(SessionStatus.ENDED))
            throw new RuntimeException("Invalid Token");

        UserDto userDto =  userService.updateUser(id, userSignupDto);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id,@RequestHeader Map<String, String> headers){
        String token = headers.get("set-cookie");

        SessionStatus sessionStatus = authService.validate(token);
        if(sessionStatus==null || sessionStatus.equals(SessionStatus.ENDED))
            throw new RuntimeException("Invalid Token");

        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}

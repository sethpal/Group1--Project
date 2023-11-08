package com.example.grievancemanagementusers.services;

import com.example.grievancemanagementusers.dtos.UserDto;
import com.example.grievancemanagementusers.dtos.SignupRequestDto;
import com.example.grievancemanagementusers.models.Session;
import com.example.grievancemanagementusers.models.SessionStatus;
import com.example.grievancemanagementusers.models.User;
import com.example.grievancemanagementusers.repositories.*;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionRepository sessionRepository;

    private String secretKeyString = "abcghsgssgshsjsshshbdhsddhddhdhdhdhh";
    private SecretKey secretKey;

    private MacAlgorithm alg;

    public AuthService(){
        alg = Jwts.SIG.HS256;
        //convert  string to byte array
        byte[] secretKeyBytes = secretKeyString.getBytes(StandardCharsets.UTF_8);
        secretKey = new SecretKeySpec(secretKeyBytes, "HmacSHA256");
    }

    public UserDto signup(SignupRequestDto signupRequestDto) {
        UserDto user = userService.createUser(signupRequestDto);
        return user;
    }

    public ResponseEntity<UserDto> login(String email, String password) {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(!optionalUser.isPresent())
            throw new RuntimeException("EmailId not found");

        User user = optionalUser.get();

        if(!user.getPassword().equals(password)){
            throw new RuntimeException("Password mismatch");
        }

        Map<String, Object> jsonForJwt = new HashMap<>();
        jsonForJwt.put("email", user.getEmail());
        jsonForJwt.put("roles", user.getRoles());
        jsonForJwt.put("createdAt", new Date());

        jsonForJwt.put("expiryAt", new Date(LocalDate.now().plusDays(3).toEpochDay()));

        String token = Jwts.builder()
                .claims(jsonForJwt)
                .signWith(secretKey, alg)
                .compact();

        Session session = new Session();
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setToken(token);
        session.setUser(user);
        sessionRepository.save(session);

        UserDto userDto = UserDto.from(user);

        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth_token" + token);

        ResponseEntity<UserDto> response = new ResponseEntity<>(userDto, headers, HttpStatus.OK);

        return response;
    }

    public String logout(String token, String userId){

        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, UUID.fromString(userId));

        Session session = sessionOptional.get();
        session.setSessionStatus(SessionStatus.ENDED);

        sessionRepository.save(session);
        return "logout successfully";
    }
    public SessionStatus validate(String token){
        Optional<Session> optionalSession = sessionRepository.findByToken(token);

        if (optionalSession.isEmpty()) {
            return null;
        }

        Session session = optionalSession.get();

        if (!session.getSessionStatus().equals(SessionStatus.ACTIVE)) {
            return SessionStatus.ENDED;
        }

        byte[] secretKeyBytes = secretKeyString.getBytes(StandardCharsets.UTF_8);
        SecretKey secretKey = new SecretKeySpec(secretKeyBytes, "HmacSHA256");

        Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);


        return SessionStatus.ACTIVE;
    }

}

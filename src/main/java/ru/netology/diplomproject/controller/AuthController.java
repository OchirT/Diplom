package ru.netology.diplomproject.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.diplomproject.jwt.AuthRequest;
import ru.netology.diplomproject.repository.UserTokenRepository;
import ru.netology.diplomproject.service.AuthenticationService;

@RestController
@AllArgsConstructor
public class AuthController {

    private AuthenticationService service;
    private UserTokenRepository tokenRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(service.createAuthenticationToken(authRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("auth-token") String authToken) {
        tokenRepository.deleteUserAndToken(authToken);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}


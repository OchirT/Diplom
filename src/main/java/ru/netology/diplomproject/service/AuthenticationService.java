package ru.netology.diplomproject.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.netology.diplomproject.jwt.AuthRequest;
import ru.netology.diplomproject.jwt.AuthResponse;
import ru.netology.diplomproject.jwt.JWTUtil;
import ru.netology.diplomproject.repository.UserTokenRepository;


@AllArgsConstructor
@Service
@Slf4j
public class AuthenticationService {

    private JWTUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private UserTokenRepository userTokenRepository;

    public AuthResponse createAuthenticationToken(AuthRequest authRequest) {
        final String userName = authRequest.login();
        final String password = authRequest.password();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName,
                        password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(authentication);
        userTokenRepository.saveUserToken(userName, jwt);
        log.info("User {} authentication. Token {}",userName,jwt);
        return new AuthResponse(jwt);
    }
}

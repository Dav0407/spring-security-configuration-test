package com.example.rest_security_jwt_hometask.controllers;

import com.example.rest_security_jwt_hometask.config.JwtTokenUtil;
import com.example.rest_security_jwt_hometask.dto.TokenRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    public AuthController(JwtTokenUtil jwtTokenUtil,
                          AuthenticationManager authenticationManager) {

        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/token")
    public String token(@RequestBody TokenRequest tokenRequest) {
        String username = tokenRequest.username();
        String password = tokenRequest.password();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authentication);
        return jwtTokenUtil.generateToken(username);
    }

}

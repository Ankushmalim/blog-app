package com.blog_app.controllers;

import com.blog_app.components.JwtUtil;
import com.blog_app.dtos.LoginRequestDto;
import com.blog_app.dtos.RegisterRequestDto;
import com.blog_app.services.UserService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService,
                          AuthenticationManager authManager,
                          JwtUtil jwtUtil
    ) {
        this.userService = userService;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterRequestDto dto) {
        userService.registerUser(dto);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto dto) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(), dto.getPassword()));

        return jwtUtil.generateToken(dto.getUsername());
    }
}

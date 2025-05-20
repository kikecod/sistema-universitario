package com.example.sistemauniversitario.registro.controller;

import com.example.sistemauniversitario.registro.dto.*;
import com.example.sistemauniversitario.registro.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // POST /api/auth/login
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    // POST /api/auth/signup
    @PostMapping("/signup")
    public ResponseEntity<LoginResponseDTO> signup(@Valid @RequestBody SignupRequestDTO request) {
        return ResponseEntity.ok(authService.signup(request));
    }

    // GET /api/auth/session-info
    @GetMapping("/session-info")
    public ResponseEntity<SessionInfoDTO> sessionInfo(Principal principal) {
        return ResponseEntity.ok(authService.getSessionInfo(principal));
    }

    // POST /api/auth/logout
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        return ResponseEntity.ok(authService.logout(request));
    }
}
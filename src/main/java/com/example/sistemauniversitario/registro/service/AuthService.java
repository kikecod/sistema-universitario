package com.example.sistemauniversitario.registro.service;



import com.example.sistemauniversitario.registro.dto.LoginRequestDTO;
import com.example.sistemauniversitario.registro.dto.SignupRequestDTO;
import com.example.sistemauniversitario.registro.dto.LoginResponseDTO;
import com.example.sistemauniversitario.registro.dto.SessionInfoDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.security.Principal;

public interface AuthService {
    LoginResponseDTO login(LoginRequestDTO request);
    LoginResponseDTO signup(SignupRequestDTO request);
    SessionInfoDTO getSessionInfo(Principal principal);
    String logout(HttpServletRequest request);
}

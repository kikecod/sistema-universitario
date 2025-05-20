package com.example.sistemauniversitario.registro.service.impl;

import com.example.sistemauniversitario.registro.dto.*;
import com.example.sistemauniversitario.registro.model.Usuario;
import com.example.sistemauniversitario.registro.model.Rol;
import com.example.sistemauniversitario.registro.repository.RolRepository;
import com.example.sistemauniversitario.registro.repository.UsuarioRepository;
import com.example.sistemauniversitario.registro.security.JwtUtil;
import com.example.sistemauniversitario.registro.service.AuthService;
import com.example.sistemauniversitario.repository.PersonaRepositoy;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RolRepository rolRepository;
    private final PersonaRepositoy personaRepositoy;
    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String token = jwtUtil.generateToken(
                new User(usuario.getUsername(), usuario.getPassword(),
                        java.util.Collections.emptyList()),
                usuario.getRol().getNombre()
        );

        return LoginResponseDTO.builder()
                .token(token)
                .username(usuario.getUsername())
                .rol(usuario.getRol().getNombre())
                .build();
    }

    @Override
    public LoginResponseDTO signup(SignupRequestDTO request) {

        Rol rol = rolRepository.findByNombre(request.getRol())
                .orElseThrow(() -> new RuntimeException("Rol no válido"));

        Usuario usuario = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(rol)
                .build();

        usuarioRepository.save(usuario);

        String token = jwtUtil.generateToken(
                new User(usuario.getUsername(), usuario.getPassword(), java.util.Collections.emptyList()),
                usuario.getRol().getNombre()
        );

        return LoginResponseDTO.builder()
                .token(token)
                .username(usuario.getUsername())
                .rol(usuario.getRol().getNombre())
                .build();
    }

    @Override
    public SessionInfoDTO getSessionInfo(Principal principal) {
        Usuario usuario = usuarioRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return SessionInfoDTO.builder()
                .username(usuario.getUsername())
                .type("Bearer")
                .rol(usuario.getRol().getNombre())
                .build();
    }

    @Override
    public String logout(HttpServletRequest request) {
        return "Sesión cerrada correctamente (el token expirará automáticamente)";
    }
}
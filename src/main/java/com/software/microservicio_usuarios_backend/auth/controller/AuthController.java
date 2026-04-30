package com.software.microservicio_usuarios_backend.auth.controller;

import com.software.microservicio_usuarios_backend.auth.dto.LoginRequestDTO;
import com.software.microservicio_usuarios_backend.auth.dto.LoginResponseDTO;
import com.software.microservicio_usuarios_backend.auth.dto.RegisterRequestDTO;
import com.software.microservicio_usuarios_backend.auth.dto.RegisterResponseDTO;
import com.software.microservicio_usuarios_backend.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador de autenticación.
 * Expone endpoints de login y registro.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // ==========================
    // LOGIN
    // ==========================
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    // ==========================
    // REGISTER
    // ==========================
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
package com.software.microservicio_usuarios_backend.auth.service.impl;

import com.software.microservicio_usuarios_backend.auth.dto.LoginRequestDTO;
import com.software.microservicio_usuarios_backend.auth.dto.LoginResponseDTO;
import com.software.microservicio_usuarios_backend.auth.service.AuthService;
import com.software.microservicio_usuarios_backend.entity.Usuario;
import com.software.microservicio_usuarios_backend.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Lógica de autenticación
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthServiceImpl(UsuarioRepository usuarioRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        boolean passwordOk = passwordEncoder.matches(
                request.getPassword(),
                usuario.getPassword()
        );

        if (!passwordOk) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        return new LoginResponseDTO(
                usuario.getIdUsuario(),
                usuario.getNombreUsuario(),
                usuario.getEmail(),
                "LOGIN EXITOSO"
        );
    }
}
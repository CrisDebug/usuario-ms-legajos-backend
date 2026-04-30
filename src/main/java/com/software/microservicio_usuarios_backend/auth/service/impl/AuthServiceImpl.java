package com.software.microservicio_usuarios_backend.auth.service.impl;

import com.software.microservicio_usuarios_backend.auth.dto.LoginRequestDTO;
import com.software.microservicio_usuarios_backend.auth.dto.LoginResponseDTO;
import com.software.microservicio_usuarios_backend.auth.dto.RegisterRequestDTO;
import com.software.microservicio_usuarios_backend.auth.dto.RegisterResponseDTO;
import com.software.microservicio_usuarios_backend.auth.service.AuthService;
import com.software.microservicio_usuarios_backend.entity.Usuario;
import com.software.microservicio_usuarios_backend.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Implementación de la lógica de autenticación.
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

    // ==========================
    // LOGIN
    // ==========================
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
                usuario.getRol(),
                "LOGIN EXITOSO"
        );
    }

    // ==========================
    // REGISTER
    // ==========================
    @Override
    public RegisterResponseDTO register(RegisterRequestDTO request) {

        // validar si email ya existe
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(request.getNombreUsuario());
        usuario.setEmail(request.getEmail());

        // encriptar password
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));

        // valores por defecto
        usuario.setRol("USER");
        usuario.setEstado("ACTIVO");
        usuario.setFechaCreacion(LocalDateTime.now());

        Usuario saved = usuarioRepository.save(usuario);

        return new RegisterResponseDTO(
                saved.getIdUsuario(),
                saved.getNombreUsuario(),
                saved.getEmail(),
                saved.getRol(),
                "REGISTRO EXITOSO"
        );
    }
}
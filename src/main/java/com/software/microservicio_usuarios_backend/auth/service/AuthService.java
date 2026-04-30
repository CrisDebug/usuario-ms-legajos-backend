package com.software.microservicio_usuarios_backend.auth.service;

import com.software.microservicio_usuarios_backend.auth.dto.LoginRequestDTO;
import com.software.microservicio_usuarios_backend.auth.dto.LoginResponseDTO;
import com.software.microservicio_usuarios_backend.auth.dto.RegisterRequestDTO;
import com.software.microservicio_usuarios_backend.auth.dto.RegisterResponseDTO;

/**
 * Interfaz de servicio de autenticación.
 * Define las operaciones disponibles para login y registro.
 */
public interface AuthService {

    /**
     * Autentica un usuario en el sistema.
     */
    LoginResponseDTO login(LoginRequestDTO request);

    /**
     * Registra un nuevo usuario en el sistema.
     */
    RegisterResponseDTO register(RegisterRequestDTO request);
}
package com.software.microservicio_usuarios_backend.auth.service;

import com.software.microservicio_usuarios_backend.auth.dto.LoginRequestDTO;
import com.software.microservicio_usuarios_backend.auth.dto.LoginResponseDTO;

public interface AuthService {

    LoginResponseDTO login(LoginRequestDTO request);
}
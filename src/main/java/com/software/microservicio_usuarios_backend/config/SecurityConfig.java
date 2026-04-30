package com.software.microservicio_usuarios_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuración de seguridad mínima.
 * Solo se usa para definir el Bean BCryptPasswordEncoder.
 */
@Configuration
public class SecurityConfig {

    /**
     * Bean que permite encriptar passwords usando BCrypt.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
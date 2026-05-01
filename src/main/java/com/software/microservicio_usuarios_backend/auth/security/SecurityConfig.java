package com.software.microservicio_usuarios_backend.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    // 🔐 Cadena principal de seguridad
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // ❌ desactivar CSRF (porque usamos JWT, no sesiones)
            .csrf(csrf -> csrf.disable())

            // 🧠 API stateless (sin sesión)
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // 🛡️ reglas de autorización
            .authorizeHttpRequests(auth -> auth

                // 🔓 endpoints públicos
                .requestMatchers("/api/auth/**").permitAll()

                // 👤 endpoints solo USER o ADMIN
                .requestMatchers("/api/usuarios/**").hasAnyRole("USER", "ADMIN")

                // 🔒 endpoints solo ADMIN
                .requestMatchers("/api/admin/**").hasRole("ADMIN")

                // 🔐 todo lo demás requiere autenticación
                .anyRequest().authenticated()
            )

            // 🔧 agregar nuestro filtro JWT antes del filtro de Spring
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // 🔑 AuthenticationManager (lo usa Spring internamente)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
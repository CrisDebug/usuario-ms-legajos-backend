package com.software.microservicio_usuarios_backend.auth.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 🔍 1. Leer header Authorization
        String authHeader = request.getHeader("Authorization");

        // ❌ si no hay token, continuar sin autenticación
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 🔐 2. Extraer token puro
        String token = authHeader.substring(7);

        // ❌ validar token (firma + expiración)
        if (!jwtUtil.isTokenValid(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 📦 3. Extraer datos del token
        String email = jwtUtil.extractEmail(token);
        String rol = jwtUtil.extractRol(token);

        // 🧠 4. Convertir rol a autoridad de Spring Security
        // IMPORTANTE: Spring usa prefijo ROLE_
        List<SimpleGrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority("ROLE_" + rol));

        // 🛡️ 5. Crear objeto de autenticación
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        email,          // usuario autenticado
                        null,           // no password aquí
                        authorities     // roles/permisos
                );

        // 📌 detalles adicionales del request
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // 🔐 6. Guardar autenticación en contexto de Spring Security
        SecurityContextHolder.getContext().setAuthentication(auth);

        // 🚀 7. Continuar cadena de filtros
        filterChain.doFilter(request, response);
    }
}
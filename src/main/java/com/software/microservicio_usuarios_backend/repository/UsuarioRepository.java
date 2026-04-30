package com.software.microservicio_usuarios_backend.repository;

import com.software.microservicio_usuarios_backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio Usuario
 * Se encarga de la comunicación con la base de datos usando JPA.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Buscar usuario por email.
     * Se usa para validaciones (ej: evitar duplicados).
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Verifica si existe un usuario con un email.
     * Útil para validaciones antes de registrar.
     */
    boolean existsByEmail(String email);
}
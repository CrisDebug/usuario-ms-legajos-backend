package com.software.microservicio_usuarios_backend.dto;

/**
 * DTO de entrada (Request)
 * Se usa cuando el cliente envía datos para registrar o actualizar un usuario.
 */
public class UsuarioRequestDTO {

    private String nombreUsuario;
    private String email;
    private String password;

    // ===========================
    // CONSTRUCTORES
    // ===========================

    public UsuarioRequestDTO() {
    }

    public UsuarioRequestDTO(String nombreUsuario, String email, String password) {
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
    }

    // ===========================
    // GETTERS Y SETTERS
    // ===========================

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    /**
     * IMPORTANTE:
     * Aquí llega el password en texto plano desde el cliente,
     * luego en el Service se debe encriptar antes de guardar.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
package com.software.microservicio_usuarios_backend.dto;

import java.time.LocalDateTime;

/**
 * DTO de salida (Response)
 * Se usa para devolver información al cliente sin exponer datos sensibles como password.
 */
public class UsuarioResponseDTO {

    private Long idUsuario;
    private String nombreUsuario;
    private String email;
    private String estado;
    private LocalDateTime fechaCreacion;

    // ===========================
    // CONSTRUCTORES
    // ===========================

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(Long idUsuario, String nombreUsuario, String email, String estado, LocalDateTime fechaCreacion) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    // ===========================
    // GETTERS Y SETTERS
    // ===========================

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
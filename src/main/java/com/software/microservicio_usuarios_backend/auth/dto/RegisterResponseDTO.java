package com.software.microservicio_usuarios_backend.auth.dto;

/**
 * DTO de respuesta para el proceso de registro de usuario.
 *
 * Este objeto se envía al frontend después de crear un usuario
 * y NO debe contener información sensible como password.
 */
public class RegisterResponseDTO {

    /**
     * Identificador único del usuario generado en la base de datos.
     */
    private Long idUsuario;

    /**
     * Nombre del usuario registrado.
     */
    private String nombreUsuario;

    /**
     * Correo electrónico del usuario registrado.
     */
    private String email;

    /**
     * Rol asignado al usuario (ej: USER, ADMIN).
     */
    private String rol;

    /**
     * Mensaje informativo del resultado del registro.
     * Ejemplo: "REGISTRO EXITOSO"
     */
    private String mensaje;

    // ==========================
    // CONSTRUCTOR VACÍO
    // ==========================
    public RegisterResponseDTO() {
    }

    // ==========================
    // CONSTRUCTOR CON PARÁMETROS
    // ==========================
    public RegisterResponseDTO(Long idUsuario,
                               String nombreUsuario,
                               String email,
                               String rol,
                               String mensaje) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.rol = rol;
        this.mensaje = mensaje;
    }

    // ==========================
    // GETTERS Y SETTERS
    // ==========================

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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
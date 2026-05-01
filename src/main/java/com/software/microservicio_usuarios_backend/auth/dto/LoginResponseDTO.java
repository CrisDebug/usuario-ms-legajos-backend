package com.software.microservicio_usuarios_backend.auth.dto;

/**
 * DTO de respuesta para Login.
 * 
 * Retorna información básica del usuario autenticado y el token JWT.
 */
public class LoginResponseDTO {

    private Long idUsuario;
    private String nombreUsuario;
    private String email;

    // Rol del usuario (USER / ADMIN)
    private String rol;

    // Token JWT generado al iniciar sesión
    private String token;

    // Mensaje opcional para el cliente
    private String mensaje;

    public LoginResponseDTO() {
    }

    /**
     * Constructor completo recomendado
     */
    public LoginResponseDTO(Long idUsuario,
                            String nombreUsuario,
                            String email,
                            String rol,
                            String token,
                            String mensaje) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.rol = rol;
        this.token = token;
        this.mensaje = mensaje;
    }

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

    // ====== ROL ======
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // ====== TOKEN ======
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // ====== MENSAJE ======
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
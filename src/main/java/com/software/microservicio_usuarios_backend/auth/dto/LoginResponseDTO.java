package com.software.microservicio_usuarios_backend.auth.dto;

/**
 * Respuesta del login
 */
public class LoginResponseDTO {

    private Long idUsuario;
    private String nombreUsuario;
    private String email;
    private String mensaje;
    private String rol;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(Long idUsuario, String nombreUsuario, String email, String mensaje,String rol) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.mensaje = mensaje;
        this.rol = rol;

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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    //rol
    public String getRol()
    {
        return rol;
    }
    public void setRol(String rol)
    {
        this.rol = rol;
    }
}
package com.software.microservicio_usuarios_backend.auth.dto;


public class RegisterRequestDTO {

    private String nombreUsuario;
    private String email;
    private String password;

    public RegisterRequestDTO() {}

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
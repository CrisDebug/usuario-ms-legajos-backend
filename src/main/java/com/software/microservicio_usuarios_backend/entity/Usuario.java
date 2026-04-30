package com.software.microservicio_usuarios_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad Usuario
 * Representa la tabla USUARIO en la base de datos Oracle.
 */
@Entity
@Table(name = "USUARIO")
public class Usuario {

    /**
     * Clave primaria autogenerada.
     * En Oracle se usa IDENTITY según la tabla creada.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    /**
     * Nombre del usuario.
     */
    @Column(name = "NOMBRE_USUARIO", nullable = false, length = 100)
    private String nombreUsuario;

    /**
     * Email único del usuario.
     */
    @Column(name = "EMAIL", nullable = false, unique = true, length = 150)
    private String email;

    /**
     * Password encriptado (nunca se debe guardar texto plano).
     */
    @Column(name = "PASSWORD", nullable = false, length = 255)
    private String password;

    /**
     * Estado del usuario.
     * Ejemplo: ACTIVO / INACTIVO
     */
    @Column(name = "ESTADO", length = 20)
    private String estado;

    /**
     * Fecha de creación del registro.
     * Se genera automáticamente cuando se inserta.
     */
    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;


    /**
     * Rol del usuario.
     * Ejemplo: ADMIN / USER
     */
        
    @Column(name = "ROL", nullable = false, length = 20)
    private String rol;

    // ===========================
    // CONSTRUCTORES
    // ===========================

    public Usuario() {
        // Constructor vacío requerido por JPA
    }

    public Usuario(Long idUsuario, String nombreUsuario, String email, String password, String estado, LocalDateTime fechaCreacion, String rol) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.rol = rol;
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

    public String getPassword() {
        return password;
    }
    public String getRol() {
        return rol;
    }
    
    
    /**
     * IMPORTANTE:
     * Aquí debe ir siempre password encriptado.
     */
    public void setPassword(String password) {
        this.password = password;
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
    public void setRol(String rol) {
        this.rol = rol; 
    }
}
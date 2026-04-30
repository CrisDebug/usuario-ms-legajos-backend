package com.software.microservicio_usuarios_backend.exception;

/**
 * Excepción personalizada para recursos no encontrados.
 * Ejemplo: Usuario no encontrado.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}
package com.software.microservicio_usuarios_backend.service;

import com.software.microservicio_usuarios_backend.dto.UsuarioRequestDTO;
import com.software.microservicio_usuarios_backend.dto.UsuarioResponseDTO;

import java.util.List;

/**
 * Servicio Usuario (Interfaz)
 * Aquí definimos las reglas de negocio que implementará el ServiceImpl.
 */
public interface UsuarioService {

    /**
     * Registrar un nuevo usuario.
     */
    UsuarioResponseDTO crearUsuario(UsuarioRequestDTO requestDTO);

    /**
     * Obtener todos los usuarios.
     */
    List<UsuarioResponseDTO> listarUsuarios();

    /**
     * Buscar usuario por ID.
     */
    UsuarioResponseDTO obtenerUsuarioPorId(Long idUsuario);

    /**
     * Actualizar usuario.
     */
    UsuarioResponseDTO actualizarUsuario(Long idUsuario, UsuarioRequestDTO requestDTO);

    /**
     * Eliminar usuario.
     */
    void eliminarUsuario(Long idUsuario);

    
    /**
     * Eliminar usuario.
     */
    
}
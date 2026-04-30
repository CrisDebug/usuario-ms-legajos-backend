package com.software.microservicio_usuarios_backend.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.software.microservicio_usuarios_backend.dto.UsuarioRequestDTO;
import com.software.microservicio_usuarios_backend.dto.UsuarioResponseDTO;
import com.software.microservicio_usuarios_backend.entity.Usuario;
import com.software.microservicio_usuarios_backend.exception.ResourceNotFoundException;
import com.software.microservicio_usuarios_backend.repository.UsuarioRepository;
import com.software.microservicio_usuarios_backend.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio Usuario.
 * Contiene la lógica de negocio del CRUD de usuarios.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Inyección por constructor (buena práctica Spring).
     */
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
                              BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Convierte entidad Usuario a DTO de respuesta.
     * Nunca se expone el password al frontend.
     */
    private UsuarioResponseDTO mapToResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getIdUsuario(),
                usuario.getNombreUsuario(),
                usuario.getEmail(),
                usuario.getEstado(),
                usuario.getFechaCreacion(),
                usuario.getRol() // 🔥 IMPORTANTE: ahora incluimos rol
        );
    }

    @Override
    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO requestDTO) {

        // Validación de email duplicado
        if (usuarioRepository.existsByEmail(requestDTO.getEmail())) {
            throw new RuntimeException("Ya existe un usuario con ese email: " + requestDTO.getEmail());
        }

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(requestDTO.getNombreUsuario());
        usuario.setEmail(requestDTO.getEmail());

        // Encriptación de password
        usuario.setPassword(passwordEncoder.encode(requestDTO.getPassword()));

        // Estado por defecto
        usuario.setEstado("ACTIVO");

        // Rol por defecto (IMPORTANTE PARA PAUTA)
        usuario.setRol("USER");

        usuario.setFechaCreacion(LocalDateTime.now());

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return mapToResponseDTO(usuarioGuardado);
    }

    @Override
    public List<UsuarioResponseDTO> listarUsuarios() {

        return usuarioRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO obtenerUsuarioPorId(Long idUsuario) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario no encontrado con ID: " + idUsuario));

        return mapToResponseDTO(usuario);
    }

    @Override
    public UsuarioResponseDTO actualizarUsuario(Long idUsuario, UsuarioRequestDTO requestDTO) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario no encontrado con ID: " + idUsuario));

        usuario.setNombreUsuario(requestDTO.getNombreUsuario());
        usuario.setEmail(requestDTO.getEmail());

        // Encriptación siempre aplicada
        usuario.setPassword(passwordEncoder.encode(requestDTO.getPassword()));

        Usuario usuarioActualizado = usuarioRepository.save(usuario);

        return mapToResponseDTO(usuarioActualizado);
    }

    @Override
    public void eliminarUsuario(Long idUsuario) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario no encontrado con ID: " + idUsuario));

        usuarioRepository.delete(usuario);
    }

}
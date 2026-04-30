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
 * Aquí va la lógica de negocio real.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Inyección por constructor (la más recomendada).
     */
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;
    }

    /**
     * Convierte una entidad Usuario a un DTO de respuesta.
     * (Nunca devolvemos password al cliente).
     */
    private UsuarioResponseDTO mapToResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getIdUsuario(),
                usuario.getNombreUsuario(),
                usuario.getEmail(),
                usuario.getEstado(),
                usuario.getFechaCreacion()
        );
    }

    @Override
    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO requestDTO) {

        // Validar si el email ya existe
        if (usuarioRepository.existsByEmail(requestDTO.getEmail())) {
            throw new RuntimeException("Ya existe un usuario registrado con ese email: " + requestDTO.getEmail());
        }

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(requestDTO.getNombreUsuario());
        usuario.setEmail(requestDTO.getEmail());

        // IMPORTANTE: aquí deberías encriptar el password (más adelante lo hacemos con BCrypt)
        usuario.setPassword(passwordEncoder.encode(requestDTO.getPassword()));

        // Estado por defecto
        usuario.setEstado("ACTIVO");

        // Fecha actual (aunque la tabla ya tiene DEFAULT, lo seteamos igual por consistencia)
        usuario.setFechaCreacion(LocalDateTime.now());

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return mapToResponseDTO(usuarioGuardado);
    }

    @Override
    public List<UsuarioResponseDTO> listarUsuarios() {

        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO obtenerUsuarioPorId(Long idUsuario) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + idUsuario));

        return mapToResponseDTO(usuario);
    }

    @Override
    public UsuarioResponseDTO actualizarUsuario(Long idUsuario, UsuarioRequestDTO requestDTO) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + idUsuario));

        // Actualizamos los campos
        usuario.setNombreUsuario(requestDTO.getNombreUsuario());
        usuario.setEmail(requestDTO.getEmail());

        // Si quieres permitir cambiar password:
        usuario.setPassword(passwordEncoder.encode(requestDTO.getPassword()));

        Usuario usuarioActualizado = usuarioRepository.save(usuario);

        return mapToResponseDTO(usuarioActualizado);
    }

    @Override
    public void eliminarUsuario(Long idUsuario) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + idUsuario));

        usuarioRepository.delete(usuario);
    }
}
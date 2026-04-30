package com.software.microservicio_usuarios_backend.controller;

import com.software.microservicio_usuarios_backend.dto.UsuarioRequestDTO;
import com.software.microservicio_usuarios_backend.dto.UsuarioResponseDTO;
import com.software.microservicio_usuarios_backend.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador Usuario
 * Expone los endpoints REST para gestionar usuarios.
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    /**
     * Inyección por constructor (recomendada).
     */
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint para crear un usuario.
     * POST: http://localhost:8084/api/usuarios
     */
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crearUsuario(@RequestBody UsuarioRequestDTO requestDTO) {
        UsuarioResponseDTO responseDTO = usuarioService.crearUsuario(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Endpoint para listar todos los usuarios.
     * GET: http://localhost:8084/api/usuarios
     */
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    /**
     * Endpoint para buscar usuario por ID.
     * GET: http://localhost:8084/api/usuarios/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuarioPorId(@PathVariable("id") Long idUsuario) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioPorId(idUsuario));
    }

    /**
     * Endpoint para actualizar un usuario.
     * PUT: http://localhost:8084/api/usuarios/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuario(
            @PathVariable("id") Long idUsuario,
            @RequestBody UsuarioRequestDTO requestDTO
    ) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(idUsuario, requestDTO));
    }

    /**
     * Endpoint para eliminar usuario.
     * DELETE: http://localhost:8084/api/usuarios/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable("id") Long idUsuario) {
        usuarioService.eliminarUsuario(idUsuario);
        return ResponseEntity.ok("Usuario eliminado correctamente con ID: " + idUsuario);
    }
}
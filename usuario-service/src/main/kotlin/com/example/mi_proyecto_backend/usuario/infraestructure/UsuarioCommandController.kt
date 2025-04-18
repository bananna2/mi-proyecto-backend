package com.example.mi_proyecto_backend.usuario.infraestructure

import com.example.mi_proyecto_backend.usuario.application.UsuarioCommandService

import com.example.mi_proyecto_backend.usuario.domain.Usuario
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/usuarios")
class UsuarioCommandController(
    private val usuarioCommandService: UsuarioCommandService
) {

    @PostMapping
    fun crearUsuario(@RequestBody usuario: Usuario): ResponseEntity<Usuario> {
        val nuevoUsuario = usuarioCommandService.crearUsuario(usuario)
        return ResponseEntity.ok(nuevoUsuario)
    }
}

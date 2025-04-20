package com.example.mi_proyecto_backend.usuario.infraestructure

import com.example.mi_proyecto_backend.usuario.application.UsuarioCommandService
import com.example.mi_proyecto_backend.usuario.domain.Usuario
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

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

    @PutMapping("/{id}")
    fun actualizarUsuario(
        @PathVariable id: String,
        @RequestBody cambios: Map<String, Any> // Cambiado de Usuario a Map
    ): ResponseEntity<Usuario> {
        val actualizado = usuarioCommandService.actualizarUsuario(id, cambios)
        return ResponseEntity.ok(actualizado)
    }

    @DeleteMapping("/{id}")
    fun eliminarUsuario(@PathVariable id: String): ResponseEntity<Void> {
        usuarioCommandService.eliminarUsuario(id)
        return ResponseEntity.noContent().build()
    }
}
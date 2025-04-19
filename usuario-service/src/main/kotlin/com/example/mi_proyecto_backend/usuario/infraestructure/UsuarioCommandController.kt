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

    @PutMapping("/{id}")
    fun actualizarUsuario(
        @PathVariable id: String,
        @RequestBody usuario: Usuario
    ): ResponseEntity<Usuario> {
        // Asegúrate de que el campo id del JSON coincide con el PathVariable
        val actualizado = usuarioCommandService.actualizarUsuario(usuario.copy(id = id))
        return ResponseEntity.ok(actualizado)
    }

    @DeleteMapping("/{id}")
    fun eliminarUsuario(@PathVariable id: String): ResponseEntity<Void> {
        usuarioCommandService.eliminarUsuario(id)
        return ResponseEntity.noContent().build()
    }
}

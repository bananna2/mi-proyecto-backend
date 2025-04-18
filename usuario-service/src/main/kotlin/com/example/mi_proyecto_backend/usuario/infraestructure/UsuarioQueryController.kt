package com.example.mi_proyecto_backend.usuario.infrastructure

import com.example.usuario.application.query.UsuarioQueryService
import com.example.usuario.domain.Usuario
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/usuarios")
class UsuarioQueryController(
    private val usuarioQueryService: UsuarioQueryService
) {

    @GetMapping("/{id}")
    fun obtenerUsuarioPorId(@PathVariable id: String): ResponseEntity<Usuario?> {
        val usuario = usuarioQueryService.obtenerUsuarioPorId(id)
        return ResponseEntity.ok(usuario)
    }
}

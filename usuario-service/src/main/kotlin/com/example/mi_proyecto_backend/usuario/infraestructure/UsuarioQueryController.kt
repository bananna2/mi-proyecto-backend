package com.example.mi_proyecto_backend.usuario.infraestructure


import com.example.mi_proyecto_backend.usuario.application.UsuarioQueryService

import com.example.mi_proyecto_backend.usuario.domain.Usuario
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/usuarios")
class UsuarioQueryController(
    private val usuarioQueryService: UsuarioQueryService
) {

    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: String): ResponseEntity<Usuario?> {
        val usuario = usuarioQueryService.obtenerPorId(id)
        return ResponseEntity.ok(usuario)
    }
}

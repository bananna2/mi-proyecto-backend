package com.example.mi_proyecto_backend.usuario.domain.events

import java.time.LocalDateTime

data class UsuarioCreadoEvent(
    val id: String,
    val nombre: String,
    val email: String,
    val fechaCreacion: LocalDateTime = LocalDateTime.now()
)

data class UsuarioActualizadoEvent(
    val id: String,
    val cambios: Map<String, Any>,
    val fechaActualizacion: LocalDateTime = LocalDateTime.now()
)

data class UsuarioEliminadoEvent(
    val id: String,
    val fechaEliminacion: LocalDateTime = LocalDateTime.now()
)
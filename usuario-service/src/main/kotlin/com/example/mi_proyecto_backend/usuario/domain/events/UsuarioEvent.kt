package com.example.mi_proyecto_backend.usuario.domain.events

import java.time.LocalDateTime

sealed class UsuarioEvent {
    abstract val id: String
    abstract val fecha: LocalDateTime
}

data class UsuarioCreadoEvent(
    override val id: String,
    val nombre: String,
    val email: String,
    override val fecha: LocalDateTime = LocalDateTime.now()
) : UsuarioEvent()

data class UsuarioActualizadoEvent(
    override val id: String,
    val cambios: Map<String, Any>,
    override val fecha: LocalDateTime = LocalDateTime.now()
) : UsuarioEvent()

data class UsuarioEliminadoEvent(
    override val id: String,
    override val fecha: LocalDateTime = LocalDateTime.now()
) : UsuarioEvent()
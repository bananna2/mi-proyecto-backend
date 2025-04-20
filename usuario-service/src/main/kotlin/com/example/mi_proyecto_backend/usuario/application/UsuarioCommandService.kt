package com.example.mi_proyecto_backend.usuario.application

import com.example.mi_proyecto_backend.usuario.domain.Usuario
import com.example.mi_proyecto_backend.usuario.domain.events.UsuarioEvent
import com.example.mi_proyecto_backend.usuario.infraestructure.messaging.UsuarioEventProducer
import com.example.mi_proyecto_backend.usuario.infraestructure.UsuarioCommandRepository
import org.springframework.stereotype.Service

@Service
class UsuarioCommandService(
    private val repository: UsuarioCommandRepository,
    private val eventProducer: UsuarioEventProducer
) {
    fun crearUsuario(usuario: Usuario): Usuario {
        val usuarioGuardado = repository.save(usuario)
        eventProducer.enviarUsuarioCreado(usuarioGuardado)
        return usuarioGuardado
    }

    fun actualizarUsuario(usuario: Usuario): Usuario {
        val usuarioActualizado = repository.save(usuario)
        eventProducer.enviarUsuarioActualizado(usuarioActualizado)
        return usuarioActualizado
    }

    fun eliminarUsuario(id: String) {
        repository.deleteById(id)
        eventProducer.enviarUsuarioEliminado(id)
    }
}
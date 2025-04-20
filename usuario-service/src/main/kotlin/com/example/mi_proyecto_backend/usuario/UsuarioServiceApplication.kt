package com.example.mi_proyecto_backend.usuario.application

import com.example.mi_proyecto_backend.usuario.domain.events.*
import com.example.mi_proyecto_backend.usuario.domain.Usuario
import com.example.mi_proyecto_backend.usuario.infraestructure.messaging.UsuarioEventProducer
import com.example.mi_proyecto_backend.usuario.infraestructure.UsuarioCommandRepository
import com.example.mi_proyecto_backend.usuario.infraestructure.UsuarioQueryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class UsuarioServiceApplication(
    private val commandRepository: UsuarioCommandRepository,
    private val queryRepository: UsuarioQueryRepository,
    private val eventProducer: UsuarioEventProducer
) {

    @Transactional
    fun crearUsuario(usuario: Usuario): Usuario {
        val usuarioGuardado = commandRepository.save(usuario)

        eventProducer.publicarUsuarioCreado(
            UsuarioCreadoEvent(
                id = usuarioGuardado.id!!.toString(),
                nombre = usuarioGuardado.nombre,
                email = usuarioGuardado.email,
                fecha = LocalDateTime.now()
            )
        )
        return usuarioGuardado
    }

    @Transactional
    fun actualizarUsuario(id: String, cambios: Map<String, Any>): Usuario {
        val usuario = queryRepository.findById(id).orElseThrow()
        // Aplica cambios al usuario...
        val usuarioActualizado = commandRepository.save(usuario)

        eventProducer.publicarUsuarioActualizado(
            UsuarioActualizadoEvent(
                id = usuarioActualizado.id!!.toString(),
                cambios = cambios,
                fecha = LocalDateTime.now()
            )
        )
        return usuarioActualizado
    }

    @Transactional
    fun eliminarUsuario(id: String) {
        val usuario = queryRepository.findById(id).orElseThrow()
        commandRepository.delete(usuario)

        eventProducer.publicarUsuarioEliminado(
            UsuarioEliminadoEvent(
                id = id,
                fecha = LocalDateTime.now()
            )
        )
    }

    fun obtenerPorId(id: String): Usuario? {
        return queryRepository.findById(id).orElse(null)
    }

    fun listarUsuarios(): List<Usuario> {
        return queryRepository.findAll()
    }
}
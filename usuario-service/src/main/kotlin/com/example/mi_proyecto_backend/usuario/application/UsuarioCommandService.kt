package com.example.mi_proyecto_backend.usuario.application

import com.example.mi_proyecto_backend.usuario.domain.Usuario
import com.example.mi_proyecto_backend.usuario.domain.events.*
import com.example.mi_proyecto_backend.usuario.infraestructure.messaging.UsuarioEventProducer
import com.example.mi_proyecto_backend.usuario.infraestructure.UsuarioCommandRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UsuarioCommandService(
    private val usuarioRepository: UsuarioCommandRepository,
    private val eventProducer: UsuarioEventProducer
) {

    @Transactional
    fun crearUsuario(usuario: Usuario): Usuario {
        val usuarioGuardado = usuarioRepository.save(usuario).also {
            eventProducer.publicarEvento(
                UsuarioCreadoEvent(
                    id = it.id!!,
                    nombre = it.nombre,
                    email = it.email
                )
            )
        }
        return usuarioGuardado
    }

    @Transactional
    fun actualizarUsuario(id: String, cambios: Map<String, Any>): Usuario {
        return usuarioRepository.findById(id).map { usuarioExistente ->
            // Aplicar cambios
            cambios.forEach { (campo, valor) ->
                when (campo) {
                    "nombre" -> usuarioExistente.nombre = valor as String
                    "email" -> usuarioExistente.email = valor as String
                }
            }

            usuarioRepository.save(usuarioExistente).also {
                eventProducer.publicarEvento(
                    UsuarioActualizadoEvent(
                        id = id,
                        cambios = cambios
                    )
                )
            }
        }.orElseThrow { IllegalArgumentException("Usuario no encontrado") }
    }

    @Transactional
    fun eliminarUsuario(id: String) {
        usuarioRepository.deleteById(id).also {
            eventProducer.publicarEvento(
                UsuarioEliminadoEvent(id = id)
            )
        }
    }
}
package com.example.mi_proyecto_backend.usuario.infraestructure.messaging

import com.example.mi_proyecto_backend.usuario.domain.Usuario
import com.example.mi_proyecto_backend.usuario.domain.events.*
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class UsuarioEventProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {
    private val logger = LoggerFactory.getLogger(UsuarioEventProducer::class.java)

    fun enviarUsuarioCreado(usuario: Usuario) {
        val event = UsuarioCreadoEvent(
            id = usuario.id!!,
            nombre = usuario.nombre,
            email = usuario.email
        )
        enviarEvento("usuarios-creados", usuario.id, event)
    }

    fun enviarUsuarioActualizado(usuario: Usuario) {
        val event = UsuarioActualizadoEvent(
            id = usuario.id!!,
            cambios = mapOf(
                "nombre" to usuario.nombre,
                "email" to usuario.email
            )
        )
        enviarEvento("usuarios-actualizados", usuario.id, event)
    }

    fun enviarUsuarioEliminado(usuarioId: String) {
        val event = UsuarioEliminadoEvent(id = usuarioId)
        enviarEvento("usuarios-eliminados", usuarioId, event)
    }

    private fun enviarEvento(topic: String, key: String, event: Any) {
        try {
            kafkaTemplate.send(topic, key, event)
            logger.info("Evento enviado al topic {}: {}", topic, event)
        } catch (ex: Exception) {
            logger.error("Error al enviar evento al topic {}: {}", topic, ex.message)
            // Implementar lógica de reintento si es necesario
        }
    }
}
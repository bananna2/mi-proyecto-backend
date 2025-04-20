package com.example.mi_proyecto_backend.usuario.infraestructure.messaging

import com.example.mi_proyecto_backend.usuario.domain.events.*
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.listener.MessageListener
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Component

@Component
class UsuarioEventProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {

    private val logger = LoggerFactory.getLogger(UsuarioEventProducer::class.java)

    // Método genérico para todos los eventos
    fun publicarEvento(event: Any) {
        when (event) {
            is UsuarioCreadoEvent -> publicarUsuarioCreado(event)
            is UsuarioActualizadoEvent -> publicarUsuarioActualizado(event)
            is UsuarioEliminadoEvent -> publicarUsuarioEliminado(event)
            else -> logger.warn("Tipo de evento no reconocido: {}", event.javaClass)
        }
    }

    // Métodos específicos con parámetros bien definidos
    fun publicarUsuarioCreado(event: UsuarioCreadoEvent) {
        enviarEvento("usuarios-creados", event.id, event)
    }

    fun publicarUsuarioActualizado(event: UsuarioActualizadoEvent) {
        enviarEvento("usuarios-actualizados", event.id, event)
    }

    fun publicarUsuarioEliminado(event: UsuarioEliminadoEvent) {
        enviarEvento("usuarios-eliminados", event.id, event)
    }

    private fun enviarEvento(topic: String, key: String, event: Any) {
        try {
            val result: SendResult<String, Any> = kafkaTemplate.send(topic, key, event).get()  // Synchronously await the result.
            logger.info("Evento enviado al topic {}: {}", topic, result)
        } catch (ex: Exception) {
            logger.error("Error al enviar evento al topic {}: {}", topic, ex.message)
        }
    }
}

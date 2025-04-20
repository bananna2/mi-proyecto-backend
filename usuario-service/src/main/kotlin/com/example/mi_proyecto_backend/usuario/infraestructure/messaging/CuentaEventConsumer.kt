package com.example.mi_proyecto_backend.cuenta.infraestructure.messaging

import com.example.mi_proyecto_backend.usuario.domain.events.UsuarioCreadoEvent
import com.example.mi_proyecto_backend.usuario.domain.events.UsuarioActualizadoEvent
import com.example.mi_proyecto_backend.usuario.domain.events.UsuarioEliminadoEvent

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Component

@Component
class CuentaEventConsumer {
    private val logger = LoggerFactory.getLogger(CuentaEventConsumer::class.java)

    // Creación
    @KafkaListener(
        topics = ["usuarios-creados"],
        groupId = "cuenta-service-group",
        containerFactory = "usuarioListenerContainerFactory"
    )
    @Retryable(maxAttempts = 3, backoff = Backoff(delay = 1000))
    fun manejarUsuarioCreado(@Payload event: UsuarioCreadoEvent) {
        logger.info("Nuevo usuario registrado: {}", event.id)
        // Lógica: Crear cuenta automática
    }

    // Actualización
    @KafkaListener(
        topics = ["usuarios-actualizados"],
        containerFactory = "usuarioListenerContainerFactory"
    )
    fun manejarUsuarioActualizado(@Payload event: UsuarioActualizadoEvent) {
        logger.info("Usuario actualizado: {}", event.id)
        // Lógica: Actualizar datos de cuentas asociadas
    }

    // Eliminación
    @KafkaListener(
        topics = ["usuarios-eliminados"],
        containerFactory = "usuarioListenerContainerFactory"
    )
    fun manejarUsuarioEliminado(@Payload event: UsuarioEliminadoEvent) {
        logger.info("Eliminando cuentas del usuario: {}", event.id)
        // Lógica: Eliminar cuentas asociadas
    }
}
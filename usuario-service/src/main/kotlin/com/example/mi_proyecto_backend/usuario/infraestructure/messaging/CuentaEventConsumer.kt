package com.example.mi_proyecto_backend.usuario.infraestructure.messaging

import com.example.mi_proyecto_backend.cuenta.domain.events.*
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class CuentaEventConsumer {
    private val logger = LoggerFactory.getLogger(CuentaEventConsumer::class.java)

    @KafkaListener(topics = ["cuentas-creadas"], groupId = "usuario-service-group")
    fun handleCuentaCreada(event: CuentaCreadaEvent) {
        logger.info("Nueva cuenta creada para usuario {}: {}", event.usuarioId, event.numeroCuenta)
        // Aquí puedes implementar lógica adicional si es necesario
    }

    @KafkaListener(topics = ["cuentas-eliminadas"], groupId = "usuario-service-group")
    fun handleCuentaEliminada(event: CuentaEliminadaEvent) {
        logger.info("Cuenta eliminada para usuario {}: {}", event.usuarioId, event.id)
        // Lógica para manejar eliminación de cuenta
    }
}
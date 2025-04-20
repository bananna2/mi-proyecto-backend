package com.example.mi_proyecto_backend.cuenta.infraestructure.messaging

import com.example.mi_proyecto_backend.cuenta.application.CuentaCommandService
import com.example.mi_proyecto_backend.cuenta.domain.Cuenta
import com.example.mi_proyecto_backend.usuario.domain.events.UsuarioCreadoEvent
import com.example.mi_proyecto_backend.usuario.domain.events.UsuarioEliminadoEvent
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class UsuarioEventConsumer(
    private val cuentaCommandService: CuentaCommandService
) {
    private val logger = LoggerFactory.getLogger(UsuarioEventConsumer::class.java)

    @KafkaListener(topics = ["usuarios-creados"], groupId = "cuenta-service-group")
    fun handleUsuarioCreado(event: UsuarioCreadoEvent) {
        logger.info("Creando cuenta automática para usuario: {}", event.id)

        val nuevaCuenta = Cuenta(
            numeroCuenta = generarNumeroCuenta(),
            saldo = 0.0,
            tipoCuenta = "AHORROS",
            usuarioId = event.id
        )

        cuentaCommandService.crearCuenta(nuevaCuenta)
    }

    @KafkaListener(topics = ["usuarios-eliminados"], groupId = "cuenta-service-group")
    fun handleUsuarioEliminado(event: UsuarioEliminadoEvent) {
        logger.info("Procesando eliminación de cuentas para usuario: {}", event.id)
        // Implementar lógica para eliminar cuentas del usuario
    }

    private fun generarNumeroCuenta(): String {
        return "CTE-${System.currentTimeMillis()}"
    }
}
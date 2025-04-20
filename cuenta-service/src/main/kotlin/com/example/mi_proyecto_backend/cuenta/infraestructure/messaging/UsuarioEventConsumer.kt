package com.example.mi_proyecto_backend.cuenta.infraestructure.messaging

import com.example.mi_proyecto_backend.cuenta.application.CuentaCommandService
import com.example.mi_proyecto_backend.cuenta.domain.Cuenta
import com.example.mi_proyecto_backend.cuenta.domain.events.UsuarioCreadoEvent
import com.example.mi_proyecto_backend.cuenta.domain.events.UsuarioActualizadoEvent
import com.example.mi_proyecto_backend.cuenta.domain.events.UsuarioEliminadoEvent
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

/**
 * Consumidor de eventos de Usuario para el módulo de Cuenta.
 * Procesa la creación, actualización y eliminación de usuarios
 * y ejecuta la lógica de cuentas correspondiente.
 */
@Component
class UsuarioEventConsumer(
    private val cuentaCommandService: CuentaCommandService
) {
    private val logger = LoggerFactory.getLogger(UsuarioEventConsumer::class.java)

    /**
     * Escucha el topic 'usuarios-creados' y crea una cuenta automática.
     */
    @KafkaListener(topics = ["usuarios-creados"])
    fun manejarUsuarioCreado(event: UsuarioCreadoEvent) {
        logger.info("Creando cuenta automática para usuario: {}", event.id)

        val nuevaCuenta = Cuenta(
            numeroCuenta = generarNumeroCuenta(),
            saldo = 0.0,
            tipoCuenta = "AHORROS",
            usuarioId = event.id
        )

        cuentaCommandService.crearCuenta(nuevaCuenta)
    }

    /**
     * Escucha el topic 'usuarios-actualizados' y aplica cambios a las cuentas.
     */
    @KafkaListener(topics = ["usuarios-actualizados"])
    fun manejarUsuarioActualizado(event: UsuarioActualizadoEvent) {
        logger.info("Usuario actualizado: {}. Aquí podrías propagar cambios a las cuentas.", event.id)
        // Ejemplo: cuentaCommandService.actualizarCuentasPorUsuario(event.id, event.cambios)
    }

    /**
     * Escucha el topic 'usuarios-eliminados' y elimina las cuentas asociadas.
     */
    @KafkaListener(topics = ["usuarios-eliminados"])
    fun manejarUsuarioEliminado(event: UsuarioEliminadoEvent) {
        logger.info("Eliminando cuentas para usuario: {}", event.id)
        cuentaCommandService.eliminarCuentasPorUsuario(event.id)
    }

    private fun generarNumeroCuenta(): String = "CTE-${System.currentTimeMillis()}"
}

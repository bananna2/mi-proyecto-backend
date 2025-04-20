package com.example.mi_proyecto_backend.cuenta.infraestructure.messaging

import com.example.mi_proyecto_backend.cuenta.domain.Cuenta
import com.example.mi_proyecto_backend.cuenta.domain.events.*
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class CuentaEventProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {
    private val logger = LoggerFactory.getLogger(CuentaEventProducer::class.java)

    fun enviarCuentaCreada(cuenta: Cuenta) {
        val event = CuentaCreadaEvent(
            id = cuenta.id!!,
            numeroCuenta = cuenta.numeroCuenta,
            usuarioId = cuenta.usuarioId,
            tipoCuenta = cuenta.tipoCuenta,
            saldoInicial = BigDecimal.valueOf(cuenta.saldo)
        )
        enviarEvento("cuentas-creadas", cuenta.usuarioId, event)
    }

    fun enviarCuentaActualizada(cuenta: Cuenta) {
        val event = CuentaActualizadaEvent(
            id = cuenta.id!!,
            cambios = mapOf(
                "saldo" to cuenta.saldo,
                "tipoCuenta" to cuenta.tipoCuenta
            ),
            usuarioId = cuenta.usuarioId
        )
        enviarEvento("cuentas-actualizadas", cuenta.id, event)
    }

    fun enviarCuentaEliminada(cuentaId: String) {
        // Nota: Necesitarías obtener el usuarioId de la cuenta
        val event = CuentaEliminadaEvent(
            id = cuentaId,
            usuarioId = "obtener-del-repositorio" // Implementar lógica para obtener usuarioId
        )
        enviarEvento("cuentas-eliminadas", cuentaId, event)
    }

    private fun enviarEvento(topic: String, key: String, event: Any) {
        try {
            kafkaTemplate.send(topic, key, event)
            logger.info("Evento enviado al topic {}: {}", topic, event)
        } catch (ex: Exception) {
            logger.error("Error al enviar evento al topic {}: {}", topic, ex.message)
        }
    }

    fun enviarCuentasEliminadasPorUsuario(usuarioId: String) {
        val event = CuentasEliminadasPorUsuarioEvent(
            usuarioId = usuarioId
        )
        enviarEvento("cuentas-eliminadas-por-usuario", usuarioId, event)
    }
}
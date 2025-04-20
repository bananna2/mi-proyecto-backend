package com.example.mi_proyecto_backend.cuenta.domain.events

import java.math.BigDecimal
import java.time.LocalDateTime

data class CuentaCreadaEvent(
    val id: String,
    val numeroCuenta: String,
    val usuarioId: String,
    val tipoCuenta: String,
    val saldoInicial: BigDecimal,
    val fechaCreacion: LocalDateTime = LocalDateTime.now()
)

data class CuentaActualizadaEvent(
    val id: String,
    val cambios: Map<String, Any>,
    val usuarioId: String,
    val fechaActualizacion: LocalDateTime = LocalDateTime.now()
)

data class CuentaEliminadaEvent(
    val id: String,
    val usuarioId: String,
    val fechaEliminacion: LocalDateTime = LocalDateTime.now()
)
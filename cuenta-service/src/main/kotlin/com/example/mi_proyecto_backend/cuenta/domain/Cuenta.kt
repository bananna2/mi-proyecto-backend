package com.example.mi_proyecto_backend.cuenta.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "cuentas")
data class Cuenta(
    @Id val id: String? = null,
    val numeroCuenta: String,
    val saldo: Double,
    val tipoCuenta: String, // Ej. Ahorros, Corriente
    val usuarioId: String  // Relación con la entidad Usuario
)

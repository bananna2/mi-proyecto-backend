package com.example.mi_proyecto_backend.cuenta.application

import com.example.cuenta.domain.Cuenta
import com.example.cuenta.infrastructure.repository.CuentaQueryRepository
import org.springframework.stereotype.Service

@Service
class CuentaQueryService(
    private val cuentaQueryRepository: CuentaQueryRepository
) {
    fun obtenerPorId(id: String): Cuenta? {
        return cuentaQueryRepository.findById(id).orElse(null)
    }

    fun obtenerTodas(): List<Cuenta> {
        return cuentaQueryRepository.findAll()
    }
}

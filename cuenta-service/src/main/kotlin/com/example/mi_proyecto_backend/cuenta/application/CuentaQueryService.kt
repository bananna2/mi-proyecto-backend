package com.example.mi_proyecto_backend.cuenta.application



import com.example.mi_proyecto_backend.cuenta.domain.Cuenta
import com.example.mi_proyecto_backend.cuenta.infraestructure.CuentaQueryRepository

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

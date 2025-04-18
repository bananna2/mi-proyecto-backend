package com.example.mi_proyecto_backend.cuenta.application


import com.example.mi_proyecto_backend.cuenta.domain.Cuenta
import com.example.mi_proyecto_backend.cuenta.infraestructure.CuentaCommandRepository

import org.springframework.stereotype.Service

@Service
class CuentaCommandService(
    private val cuentaCommandRepository: CuentaCommandRepository
) {
    fun crearCuenta(cuenta: Cuenta): Cuenta {
        return cuentaCommandRepository.save(cuenta)
    }

    fun actualizarCuenta(cuenta: Cuenta): Cuenta {
        return cuentaCommandRepository.save(cuenta)
    }

    fun eliminarCuenta(id: String) {
        cuentaCommandRepository.deleteById(id)
    }
}

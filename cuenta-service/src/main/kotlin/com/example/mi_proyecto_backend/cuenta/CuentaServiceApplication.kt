package com.example.mi_proyecto_backend.cuenta


import com.example.mi_proyecto_backend.cuenta.domain.Cuenta
import com.example.mi_proyecto_backend.cuenta.application.CuentaCommandService
import com.example.mi_proyecto_backend.cuenta.application.CuentaQueryService

import org.springframework.stereotype.Service

@Service
class CuentaServiceApplication(
    private val cuentaCommandService: CuentaCommandService,
    private val cuentaQueryService: CuentaQueryService
) {
    fun crearCuenta(cuenta: Cuenta): Cuenta {
        return cuentaCommandService.crearCuenta(cuenta)
    }

    fun obtenerCuentaPorId(id: String): Cuenta? {
        return cuentaQueryService.obtenerPorId(id)
    }

    fun obtenerTodasCuentas(): List<Cuenta> {
        return cuentaQueryService.obtenerTodas()
    }

    fun actualizarCuenta(cuenta: Cuenta): Cuenta {
        return cuentaCommandService.actualizarCuenta(cuenta)
    }

    fun eliminarCuenta(id: String) {
        cuentaCommandService.eliminarCuenta(id)
    }
}

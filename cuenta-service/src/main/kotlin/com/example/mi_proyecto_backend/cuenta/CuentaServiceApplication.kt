package com.example.mi_proyecto_backend.cuenta

import com.example.cuenta.application.command.CuentaCommandService
import com.example.cuenta.application.query.CuentaQueryService
import com.example.cuenta.domain.Cuenta
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

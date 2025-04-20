package com.example.mi_proyecto_backend.cuenta.application

import com.example.mi_proyecto_backend.cuenta.domain.Cuenta
import com.example.mi_proyecto_backend.cuenta.infraestructure.messaging.CuentaEventProducer
import com.example.mi_proyecto_backend.cuenta.infraestructure.CuentaCommandRepository
import org.springframework.stereotype.Service

@Service
class CuentaCommandService(
    private val cuentaCommandRepository: CuentaCommandRepository,
    private val cuentaEventProducer: CuentaEventProducer
) {
    fun crearCuenta(cuenta: Cuenta): Cuenta {
        val cuentaGuardada = cuentaCommandRepository.save(cuenta)
        cuentaEventProducer.enviarCuentaCreada(cuentaGuardada)
        return cuentaGuardada
    }

    fun actualizarCuenta(cuenta: Cuenta): Cuenta {
        val cuentaActualizada = cuentaCommandRepository.save(cuenta)
        cuentaEventProducer.enviarCuentaActualizada(cuentaActualizada)
        return cuentaActualizada
    }

    fun eliminarCuenta(id: String) {
        cuentaCommandRepository.deleteById(id)
        cuentaEventProducer.enviarCuentaEliminada(id)
    }

    fun eliminarCuentasPorUsuario(idUsuario: String) {
        cuentaCommandRepository.deleteByUsuarioId(idUsuario)
        cuentaEventProducer.enviarCuentasEliminadasPorUsuario(idUsuario)
    }

}
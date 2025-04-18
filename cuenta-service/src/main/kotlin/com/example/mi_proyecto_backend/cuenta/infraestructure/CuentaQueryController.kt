package com.example.mi_proyecto_backend.cuenta.infraestructure


import com.example.mi_proyecto_backend.cuenta.domain.Cuenta
import com.example.mi_proyecto_backend.cuenta.application.CuentaQueryService

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cuentas")
class CuentaQueryController(
    private val cuentaQueryService: CuentaQueryService
) {

    @GetMapping("/{id}")
    fun obtenerCuentaPorId(@PathVariable id: String): ResponseEntity<Cuenta?> {
        val cuenta = cuentaQueryService.obtenerPorId(id)
        return ResponseEntity.ok(cuenta)
    }

    @GetMapping
    fun obtenerTodasLasCuentas(): ResponseEntity<List<Cuenta>> {
        val cuentas = cuentaQueryService.obtenerTodas()
        return ResponseEntity.ok(cuentas)
    }
}

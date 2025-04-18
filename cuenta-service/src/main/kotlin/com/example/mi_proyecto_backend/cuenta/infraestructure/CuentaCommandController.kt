package com.example.mi_proyecto_backend.cuenta.infraestructure



import com.example.mi_proyecto_backend.cuenta.domain.Cuenta
import com.example.mi_proyecto_backend.cuenta.application.CuentaCommandService

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cuentas")
class CuentaCommandController(
    private val cuentaCommandService: CuentaCommandService
) {

    @PostMapping
    fun crearCuenta(@RequestBody cuenta: Cuenta): ResponseEntity<Cuenta> {
        val nuevaCuenta = cuentaCommandService.crearCuenta(cuenta)
        return ResponseEntity.ok(nuevaCuenta)
    }

    @PutMapping("/{id}")
    fun actualizarCuenta(@PathVariable id: String, @RequestBody cuenta: Cuenta): ResponseEntity<Cuenta> {
        val cuentaActualizada = cuentaCommandService.actualizarCuenta(cuenta)
        return ResponseEntity.ok(cuentaActualizada)
    }

    @DeleteMapping("/{id}")
    fun eliminarCuenta(@PathVariable id: String): ResponseEntity<Void> {
        cuentaCommandService.eliminarCuenta(id)
        return ResponseEntity.noContent().build()
    }
}

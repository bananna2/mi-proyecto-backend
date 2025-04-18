// cuenta-service/src/main/kotlin/com/example/mi_proyecto_backend/cuenta/CuentaApplication.kt
package com.example.mi_proyecto_backend.cuenta.infraestructure

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CuentaApplication

fun main(args: Array<String>) {
    runApplication<CuentaApplication>(*args)
}

package com.example.mi_proyecto_backend.cuenta

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CuentaApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<CuentaApplication>(*args)
        }
    }
}

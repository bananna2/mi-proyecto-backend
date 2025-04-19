package com.example.mi_proyecto_backend.usuario

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UsuarioApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<UsuarioApplication>(*args)
        }
    }
}

package com.example.mi_proyecto_backend

import com.example.mi_proyecto_backend.usuario.infraestructure.UsuarioApplication
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [UsuarioApplication::class])
class MiProyectoBackendApplicationTests {

    @Test
    fun contextLoads() {
        // Si llegamos hasta aquí, el contexto de Spring Boot arranca sin errores
    }
}

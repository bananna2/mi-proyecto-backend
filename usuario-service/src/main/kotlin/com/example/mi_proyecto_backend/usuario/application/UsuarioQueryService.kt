package com.example.mi_proyecto_backend.usuario.application

import com.example.mi_proyecto_backend.usuario.domain.Usuario

import com.example.mi_proyecto_backend.usuario.infraestructure.UsuarioQueryRepository
import org.springframework.stereotype.Service

@Service
class UsuarioQueryService(
    private val usuarioQueryRepository: UsuarioQueryRepository
) {
    fun obtenerPorId(id: String): Usuario? {
        return usuarioQueryRepository.findById(id).orElse(null)
    }

    fun obtenerTodos(): List<Usuario> {
        return usuarioQueryRepository.findAll()
    }

    fun existeUsuario(id: String): Boolean {
       return usuarioQueryRepository.existsById(id)
    }
}

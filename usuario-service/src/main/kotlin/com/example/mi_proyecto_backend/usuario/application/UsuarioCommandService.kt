package com.example.mi_proyecto_backend.usuario.application


import com.example.mi_proyecto_backend.usuario.domain.Usuario
import com.example.mi_proyecto_backend.usuario.infraestructure.UsuarioCommandRepository


import org.springframework.stereotype.Service

@Service
class UsuarioCommandService(
    private val usuarioCommandRepository: UsuarioCommandRepository
) {
    fun crearUsuario(usuario: Usuario): Usuario {
        return usuarioCommandRepository.save(usuario)
    }

    fun actualizarUsuario(usuario: Usuario): Usuario {
        return usuarioCommandRepository.save(usuario)
    }

    fun eliminarUsuario(id: String) {
        usuarioCommandRepository.deleteById(id)
    }
}

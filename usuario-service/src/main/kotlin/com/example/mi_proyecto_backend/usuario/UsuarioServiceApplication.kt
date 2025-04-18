package com.example.mi_proyecto_backend.usuario

import com.example.usuario.application.command.UsuarioCommandService
import com.example.usuario.application.query.UsuarioQueryService
import com.example.usuario.domain.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioServiceApplication(
    private val usuarioCommandService: UsuarioCommandService,
    private val usuarioQueryService: UsuarioQueryService
) {
    fun crearUsuario(usuario: Usuario): Usuario {
        return usuarioCommandService.crearUsuario(usuario)
    }

    fun obtenerUsuarioPorId(id: String): Usuario? {
        return usuarioQueryService.obtenerPorId(id)
    }

    fun obtenerTodosUsuarios(): List<Usuario> {
        return usuarioQueryService.obtenerTodos()
    }

    fun actualizarUsuario(usuario: Usuario): Usuario {
        return usuarioCommandService.actualizarUsuario(usuario)
    }

    fun eliminarUsuario(id: String) {
        usuarioCommandService.eliminarUsuario(id)
    }
}

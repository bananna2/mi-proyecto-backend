package com.example.mi_proyecto_backend.usuario.infrastructure

import com.example.usuario.domain.Usuario
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UsuarioCommandRepository : MongoRepository<Usuario, String> {}

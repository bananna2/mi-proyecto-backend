package com.example.mi_proyecto_backend.usuario.infraestructure

import com.example.mi_proyecto_backend.usuario.domain.Usuario

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository


@Repository
interface UsuarioQueryRepository : MongoRepository<Usuario, String> {}

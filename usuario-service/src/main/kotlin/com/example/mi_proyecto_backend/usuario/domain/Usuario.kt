package com.example.mi_proyecto_backend.usuario.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "usuarios")
data class Usuario(
    @Id val id: String? = null,
    val nombre: String,
    val email: String,
    val contrasena: String
)

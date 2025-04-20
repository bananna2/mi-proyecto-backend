package com.example.mi_proyecto_backend.usuario.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "usuarios")
data class Usuario(
    @Id val id: String? = null,
    var nombre: String,
    var email: String,
    val contrasena: String
)

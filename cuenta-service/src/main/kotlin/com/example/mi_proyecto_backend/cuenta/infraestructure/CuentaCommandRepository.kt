package com.example.mi_proyecto_backend.cuenta.infrastructure

import com.example.cuenta.domain.Cuenta
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CuentaCommandRepository : MongoRepository<Cuenta, String>

plugins {
    id("org.springframework.boot") version "3.4.4"  // ¡Misma versión que en cuenta-service!
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot Básico
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.kafka:spring-kafka")

    // Kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
}

// Configuración específica para Spring Boot
springBoot {
    mainClass.set("com.example.mi_proyecto_backend.usuario.UsuarioApplication")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Configuración para el JAR
tasks.bootJar {
    archiveFileName.set("usuario-service.jar")
}

tasks.build {
    dependsOn(tasks.bootJar)
}
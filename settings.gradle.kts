pluginManagement {
    plugins {
        id("org.springframework.boot") version "3.4.4"
        id("io.spring.dependency-management") version "1.1.7"
        kotlin("jvm") version "1.9.25"
        kotlin("plugin.spring") version "1.9.25"
    }
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "mi_proyecto_backend"

include(":usuario-service")
include(":cuenta-service")
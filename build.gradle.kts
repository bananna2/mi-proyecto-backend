// root build.gradle
plugins {
    // Declaras los plugins pero NO los aplicas en la raíz
    id("org.springframework.boot") version "3.4.4" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
    kotlin("jvm") version "1.9.25" apply false
    kotlin("plugin.spring") version "1.9.25" apply false
    id("java")
}

allprojects {
    group = "com.example"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

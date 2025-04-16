# Usa la imagen base de OpenJDK 17
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR del proyecto dentro del contenedor
COPY build/libs/mi_proyecto_backend-0.0.1-SNAPSHOT.jar /app/mi_proyecto_backend-0.0.1-SNAPSHOT.jar

# Exponer el puerto en el que se ejecuta la aplicación (por defecto, Spring Boot usa el puerto 8080)
EXPOSE 8080

# Comando para ejecutar el contenedor
ENTRYPOINT ["java", "-jar", "/app/mi_proyecto_backend-0.0.1-SNAPSHOT.jar"]

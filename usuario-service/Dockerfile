# usuario-service/Dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copia el JAR generado para el módulo usuario
COPY build/libs/usuario-service.jar ./usuario-service.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/usuario-service.jar"]

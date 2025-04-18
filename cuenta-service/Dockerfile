# cuenta-service/Dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copia el JAR generado para el módulo cuenta
COPY build/libs/cuenta-service-0.0.1-SNAPSHOT.jar ./cuenta-service.jar

EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/cuenta-service.jar"]

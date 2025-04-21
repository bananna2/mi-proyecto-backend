🚀 Despliegue del Backend
A continuación se explican los pasos necesarios para desplegar el backend del proyecto utilizando Docker.

🧱 Requisitos previos
Antes de empezar, asegúrate de tener instaladas las siguientes herramientas:

Docker

Docker Compose

Acceso a un entorno compatible con Java 17 y Kotlin (por ejemplo, IntelliJ IDEA si deseas correr el proyecto localmente sin Docker)

🗂️ Estructura del proyecto
El proyecto está dividido en microservicios. Cada uno tiene su propio Dockerfile. Además, hay un archivo docker-compose.yml que orquesta toda la infraestructura.

📦 Servicios incluidos
usuario-service: gestión de usuarios.

cuenta-service: gestión de cuentas.

MongoDB: base de datos NoSQL para persistencia.

Kafka: mensajería entre microservicios.

🛠️ Pasos para desplegar
Clonar el repositorio

git clone https://github.com/tu-usuario/mi-proyecto-backend.git
cd mi-proyecto-backend

Construir los servicios

./gradlew clean build
Levantar los contenedores

docker-compose up --build
Esto construirá y levantará todos los servicios definidos.

Verificar el despliegue

Puedes acceder al microservicio de usuarios con:

curl http://localhost:8080/api/usuarios/all

Y a Kafka para consumir eventos:

docker run --rm -it \
  --network mi-proyecto-backend_default \
  confluentinc/cp-kafka:latest \
  kafka-console-consumer \
    --bootstrap-server kafka:9092 \
    --topic cuenta-creada \
    --from-beginning \
    --property print.value=true

    
🧪 Datos de prueba
Puedes crear un usuario con:

bash
Copiar código
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Pepito Pérez","email":"pepito.perez@example.com","contrasena":"secreto123"}'
  
Y consultar usuarios existentes con:
curl http://localhost:8080/api/usuarios/all

✅ Verificación en MongoDB
Para conectarte a la base de datos y consultar los datos directamente:



docker exec -it mongo mongosh -u root -p example --authenticationDatabase admin
Dentro de la consola de MongoDB:

js
Copiar código
use mi-proyecto
db.usuario.find().pretty()
db.cuenta.find().pretty()

Prueba Técnica — Desarrollador Fullstack
🎯 Objetivo

Evaluar las habilidades y conocimientos del candidato en experiencia de usuario (UX/UI) y desarrollo full-stack, utilizando:

Java 17+ y Spring Boot para el backend.

Angular 8+ para el frontend.

Base de datos abierta (MySQL, PostgreSQL, H2 o SQLite).

Tiempo estimado: 240 minutos

⚡ En este caso, el tiempo real utilizado fue: X horas Y minutos (ajustar según tu ejecución).

🗂️ Modelo de dominio
Entidades:

Departamento

codigo (String)

nombre (String)

Ciudad

codigo (String)

nombre (String)

departamento (Relación con Departamento)

Material

nombre (String)

descripcion (String)

tipo (String)

precio (Decimal)

fechaCompra (Date)

fechaVenta (Date)

estado (Enum: ACTIVO, DISPONIBLE, ASIGNADO)

ciudad (Relación con Ciudad)

⚙️ Requisitos funcionales de la API REST

La API permite:

✅ Obtener todos los materiales

✅ Buscar materiales por tipo

✅ Buscar materiales por fecha de compra

✅ Buscar materiales por ciudad

✅ Crear un nuevo material

✅ Actualizar un material existente

🏗️ Arquitectura

Controller → Endpoints HTTP (REST)

Service → Lógica de negocio

Model/DTOs → Requests & Responses

Entities → Entidades JPA

Repository → Persistencia con Spring Data JPA

🛡️ Validaciones y reglas

fechaCompra no puede ser posterior a fechaVenta.

Manejo de excepciones centralizado con GlobalExceptionHandler.

Respuestas estandarizadas en JSON con estructura:

{
  "status": 200,
  "message": "Operación exitosa",
  "data": { ... }
}

Códigos de respuesta HTTP:

200 → Operación exitosa

404 → Recurso no encontrado

500 → Error interno del servidor

🖥️ Tecnologías utilizadas

Backend

Java 17

Spring Boot 3+

Spring Data JPA

Base de datos: MySQL (configurable a PostgreSQL, H2 o SQLite)

Maven

Frontend

Angular 15+

Angular Material (UI)

TypeScript

RxJS / HttpClient

🚀 Instrucciones de ejecución
1️⃣ Backend (Spring Boot)
Requisitos previos

Java 17+

Maven 3.9+

MySQL 8+ (o usar H2 para pruebas rápidas)

Comandos
# Clonar repositorio
git clone https://github.com/usuario/backend-materials.git
cd backend-materials

# Compilar y correr
mvn clean install
mvn spring-boot:run

Configuración BD (src/main/resources/application.properties)
spring.datasource.url=jdbc:mysql://localhost:3306/materialsdb?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


El backend quedará disponible en:
👉 http://localhost:8080/api/materiales

2️⃣ Frontend (Angular)
Requisitos previos

Node.js 18+

Angular CLI 15+

Comandos
# Clonar repositorio
git clone https://github.com/usuario/frontend-materials.git
cd frontend-materials

# Instalar dependencias
npm install

# Ejecutar
ng serve -o


El frontend quedará disponible en:
👉 http://localhost:4200

📌 Endpoints principales (ejemplos)
Método	Endpoint	Descripción
GET	/api/materiales	Listar todos los materiales
GET	/api/materiales/{id}	Buscar material por ID
GET	/api/materiales/tipo/{tipo}	Buscar materiales por tipo
GET	/api/materiales/ciudad/{id}	Buscar materiales por ciudad
POST	/api/materiales	Crear un nuevo material
PUT	/api/materiales/{id}	Actualizar material existente
DELETE	/api/materiales/{id}	Eliminar material
📝 Documentación

Swagger/OpenAPI habilitado en:
👉 http://localhost:8080/swagger-ui.html

JavaDoc en métodos de servicio.

🔒 Extras implementados (opcional)

 Autenticación con JWT

 Manejo de logs con SLF4J

 Pruebas unitarias con JUnit & Mockito

 CI/CD (GitHub Actions)

 Autenticación JWT

Login: POST /api/auth/login con JSON:

{ "username": "admin", "password": "admin123" }


Retorna un token JWT que debe enviarse en el header:

Authorization: Bearer <token>
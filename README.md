# Proyecto: Foro backend

Este proyecto es una API desarrollada en Java con Spring Boot para gestionar tópicos en un foro. Incluye operaciones CRUD para manejar los datos de los tópicos y un sistema de autenticación con tokens JWT.

## Endpoints

### 1. Autenticación
**POST** `/login`
- Endpoint para autenticarse y generar un token JWT.
- **Body:** Debe incluir las credenciales del usuario en formato JSON:
  ```json
  {
      "login": "usuario",
      "password": "contraseña"
  }
  ```
- **Respuesta:** Retorna un token JWT con estado `200 OK` si las credenciales son válidas o `401 Unauthorized` si no lo son.

### 2. Listar todos los tópicos
**GET** `/topicos`
- Retorna una lista paginada de los tópicos.
- **Parámetros:** Soporta parámetros de paginación (page, size, sort).
- **Autenticación:** Requiere un token JWT válido en el encabezado `Authorization`.
  ```
  Authorization: Bearer <token>
  ```

### 3. Obtener un tópico por ID
**GET** `/topicos/{id}`
- Busca un tópico por su ID.
- **Respuesta:** Retorna el tópico si existe o un estado `404 Not Found` si no se encuentra.
- **Autenticación:** Requiere un token JWT válido.

### 4. Crear un nuevo tópico
**POST** `/topicos`
- Crea un nuevo tópico.
- **Body:** Debe incluir un objeto `Topico` en formato JSON.
- **Respuesta:** Retorna el tópico creado con estado `201 Created`.
- **Autenticación:** Requiere un token JWT válido.

### 5. Actualizar un tópico
**PUT** `/topicos/{id}`
- Actualiza los detalles de un tópico existente.
- **Body:** Debe incluir un objeto `Topico` con los campos a actualizar.
- **Respuesta:** Retorna el tópico actualizado o un estado `404 Not Found` si no se encuentra.
- **Autenticación:** Requiere un token JWT válido.

### 6. Eliminar un tópico
**DELETE** `/topicos/{id}`
- Elimina un tópico por su ID.
- **Respuesta:** Retorna un estado `204 No Content` si se elimina correctamente o `404 Not Found` si no se encuentra.
- **Autenticación:** Requiere un token JWT válido.

## Tecnologías utilizadas
- **Java 17**
- **Spring Boot**
  - Spring Web
  - Spring Data JPA
  - Spring Security
- **PostgreSQL**
- **JWT (JSON Web Tokens)**
- **Maven**

## Instalación y ejecución
1. Clona este repositorio:
   ```bash
   git clone <url-del-repositorio>
   ```
2. Navega al directorio del proyecto:
   ```bash
   cd foroHub
   ```
3. Configura la base de datos en el archivo `application.properties`
4. Compila y ejecuta la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```
5. Accede a la API

## Seguridad
- La autenticación se realiza mediante tokens JWT.
- El token debe incluirse en el encabezado `Authorization` para acceder a los endpoints protegidos.

## Notas
- Asegúrate de configurar las variables de entorno o el archivo `application.properties` para conectar la base de datos y establecer la clave secreta del token JWT (`api.security.secret`).
- Para pruebas locales, puedes usar una herramienta como Postman para autenticarte y probar los endpoints con el token generado.



# 📘 Sistema Universitario – Documentación General

## 🛠️ Recomendaciones para Ejecutar el Proyecto

1. **Requisitos previos:**
   - Java 21
   - Maven
   - PostgreSQL
   - Redis instalado y ejecutándose (`redis-server`)
   - IDE (IntelliJ, VSCode o similar)
   - Postman o Swagger para pruebas de API

2. **Configuraciones necesarias (`application.properties`):**
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/sistema_universitario
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.datasource.driver-class-name=org.postgresql.Driver
   spring.jpa.hibernate.ddl-auto=update

   # Redis
   spring.cache.type=redis
   spring.data.redis.host=localhost
   spring.data.redis.port=6379
   ```

3. **Compilar y ejecutar:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Swagger UI:**
   - URL: `http://localhost:8080/swagger-ui/index.html`
   - Usa el botón `Authorize` con:
     ```
     Bearer <token_jwt>
     ```
5. Para probar login y endpoints protegidos:
   - POST `/api/auth/signup`
   - POST `/api/auth/login` → Copiar token
   - Usar token con `Authorization: Bearer <token>` para endpoints protegidos.
     
---
# 📁 Estructura del Proyecto `sistemauniversitario`

Este proyecto está estructurado en base a buenas prácticas para proyectos Spring Boot con autenticación JWT, seguridad, cacheo con Redis y separación de responsabilidades por módulos.

---

## 📂 `com.example.sistemauniversitario`

### 🔹 `config/`
- `OpenApiConfig.java`: Configuración de Swagger/OpenAPI.

### 🔹 `controller/`
- `EstudianteController.java`
- `InscripcionController.java`
- `MateriaController.java`
- `ParaleloController.java`

### 🔹 `dto/`
- `EstudianteDTO.java`
- `InscripcionDTO.java`
- `InscripcionRequestDTO.java`
- `MateriaBaseDTO.java`
- `MateriaDTO.java`
- `ParaleloDTO.java`

### 🔹 `model/`
- `Docente.java`
- `Estudiante.java`
- `Evaluacion.java`
- `Inscripcion.java`
- `Materia.java`
- `MateriaMencion.java`
- `MateriaMencionId.java`
- `Mencion.java`
- `Nota.java`
- `Paralelo.java`
- `Persona.java`

### 🔹 `repository/`
- `DocenteRepository.java`
- `EstudianteRepository.java`
- `InscripcionRepository.java`
- `MateriaMencionRepository.java`
- `MateriaRepository.java`
- `MencionRepository.java`
- `ParaleloRepository.java`
- `PersonaRepository.java`

### 🔹 `service/`
- `EstudianteService.java`
- `InscripcionService.java`
- `MateriaBaseService.java`
- `MateriaService.java`
- `ParaleloService.java`

### 🔹 `service/impl/`
- `EstudianteServiceImpl.java`
- `InscripcionServiceImpl.java`
- `MateriaBaseServiceImpl.java`
- `MateriaServiceImpl.java`
- `ParaleloServiceImpl.java`

### 🔹 `validation/`
- `ApiError.java`
- `CIValidator.java`
- `EstudianteValidator.java`
- `GlobalExceptionHandler.java`
- `ValidCI.java`

---

## 📂 `registro`

### 🔹 `config/`
- `SecurityConfig.java`: Configuración de seguridad, filtros y control de acceso con roles.

### 🔹 `controller/`
- `AuthController.java`: Endpoints de login, signup, logout y session-info.

### 🔹 `dto/`
- `LoginRequestDTO.java`
- `LoginResponseDTO.java`
- `SessionInfoDTO.java`
- `SignupRequestDTO.java`

### 🔹 `model/`
- `Rol.java`
- `Usuario.java`

### 🔹 `repository/`
- `RolRepository.java`
- `UsuarioRepository.java`

### 🔹 `security/`
- `JwtAuthenticationEntryPoint.java`: Controlador de acceso no autorizado.
- `JwtAuthenticationFilter.java`: Filtro para extraer y validar tokens.
- `JwtUtil.java`: Generación y validación de tokens JWT.

### 🔹 `service/`
- `AuthService.java`
- `CustomUserDetailsService.java`

### 🔹 `service/impl/`
- `AuthServiceImpl.java`

---

## 🔐 Autenticación JWT

- `POST /api/auth/login` → Genera token
- `POST /api/auth/signup` → Registro con rol
- `GET /api/auth/session-info` → Usuario actual
- `POST /api/auth/logout` → Cierre lógico

**Roles soportados:**
- `Administrador`
- `Estudiante`
- `Docente`

**Configuración de seguridad:**
```java
.authorizeHttpRequests(auth -> auth
    .requestMatchers("/api/auth/**").permitAll()
    .requestMatchers("/api/paralelos/**", "/api/estudiantes/**").hasAuthority("Administrador")
    .requestMatchers("/api/inscripcion/**").hasAnyAuthority("Estudiante", "Administrador")
    .requestMatchers("/api/materias/**").hasAnyAuthority("Docente", "Administrador")
)
```

---

## 🧠 Validaciones

- Anotaciones JSR-380 (`@NotNull`, `@Email`, etc.)
- Validaciones personalizadas (`EstudianteValidator`)
- Manejo de errores centralizado (`@RestControllerAdvice`)

---

## 🚀 Endpoints por Controlador

### 🔹 `materia-controller`

| Método | Endpoint | Descripción | Respuesta |
|--------|----------|-------------|-----------|
| GET | `/api/materias` | Listar todas las materias | 200 OK |
| GET | `/api/materias/{id}` | Obtener una materia por su ID | 200 OK |
| POST | `/api/materias` | Crear nueva materia | 200 OK |
| PUT | `/api/materias/{id}` | Actualizar materia por ID | 200 OK |
| DELETE | `/api/materias/{id}` | Eliminar materia por ID | 200 OK |
| GET | `/api/materias/materia-mencion` | Listar materias con su mención | 200 OK |
| POST | `/api/materias/materia-mencion` | Crear materia junto con mención | 200 OK |

---

### 🔹 `inscripcion-controller`

| Método | Endpoint | Descripción | Respuesta |
|--------|----------|-------------|-----------|
| GET | `/api/inscripcion` | Listar todas las inscripciones | 200 OK |
| GET | `/api/inscripcion/{id}` | Obtener inscripción por ID | 200 OK |
| POST | `/api/inscripcion` | Registrar nueva inscripción | 200 OK |
| PUT | `/api/inscripcion/{id}` | Actualizar inscripción | 200 OK |
| DELETE | `/api/inscripcion/{id}` | Eliminar inscripción | 200 OK |
| GET | `/api/inscripcion/{id}/paralelo` | Obtiene Estudiantes de un paralelo | 200 OK |
| GET | `/api/inscripcion/{id}/estudiante` | Inscripciones del estudiante | 200 OK |

---

### 🔹 `estudiante-controller`

| Método | Endpoint | Descripción | Respuesta |
|--------|----------|-------------|-----------|
| GET | `/api/estudiantes` | Listar estudiantes | 200 OK |
| GET | `/api/estudiantes/{id}` | Obtener estudiante por ID | 200 OK |
| POST | `/api/estudiantes` | Crear nuevo estudiante | 200 OK |
| PUT | `/api/estudiantes/{id}` | Actualizar estudiante | 200 OK |
| DELETE | `/api/estudiantes/{id}` | Eliminar estudiante | 200 OK |

---

### 🔹 `paralelo-controller`

| Método | Endpoint | Descripción | Respuesta |
|--------|----------|-------------|-----------|
| GET | `/api/paralelos` | Listar todos los paralelos | 200 OK |
| POST | `/api/paralelos/asignar-docente` | Asignar docente a paralelo | 200 OK |

---

### 🔹 `auth-controller`

| Método | Endpoint | Descripción | Respuesta |
|--------|----------|-------------|-----------|
| POST | `/api/auth/signup` | Registrar nuevo usuario | 200 OK |
| POST | `/api/auth/login` | Iniciar sesión (token JWT) | 200 OK |
| POST | `/api/auth/logout` | Cerrar sesión | 200 OK |
| GET | `/api/auth/session-info` | Obtener sesión actual | 200 OK |

---

## 🔁 Cache con Redis

- Redis configurado vía `@EnableCaching`
- Uso de `@Cacheable("nombreCache")` en:
  - `listar()` de Materias, Estudiantes, Inscripciones
- Si Redis está apagado:
  - Se muestra error `"Unable to connect to Redis"` (controlado)

---

## ✅ Estado Final

- 🔐 Seguridad y login robusto con JWT y roles
- 🔍 Swagger integrado con botón de autorización
- 🧠 Validaciones automáticas y personalizadas
- 📂 Controladores REST completos y organizados
- ⚡ Redis funcionando como caché para consultas costosas
- 🔄 Endpoints separados por contexto y responsabilidad

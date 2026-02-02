# 📋 Documentación - Tabla REGISTROS

## Estructura Completa de la Tabla Registros

### 1. 📊 Tabla en Base de Datos
```sql
CREATE TABLE registros (
  id_registro INT PRIMARY KEY AUTO_INCREMENT,
  nombres VARCHAR(100) NOT NULL,
  apellidos VARCHAR(100),
  email VARCHAR(100) UNIQUE NOT NULL,
  llave_secreta VARCHAR(255) NOT NULL,
  access_token VARCHAR(500) UNIQUE,
  estado TINYINT(4) DEFAULT 1,
  id_usuario INT,
  FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);
```

---

## 2. 📁 Clases Creadas

### A. Entity - `Registros.java`
**Ubicación:** `src/main/java/web/Sushi_Inka/entity/Registros.java`

**Campos:**
- `id_registro` - ID único (AUTO_INCREMENT)
- `nombres` - Nombres del usuario externo
- `apellidos` - Apellidos
- `email` - Email único
- `llave_secreta` - Secret key encriptada con BCrypt
- `access_token` - Token JWT (default: "default_access_token")
- `estado` - 1=Activo, 0=Inactivo
- `id_usuario` - FK opcional a tabla usuario

**Métodos principales:**
- `getId_registro()` / `setId_registro()`
- `getNombres()` / `setNombres()`
- `getApellidos()` / `setApellidos()`
- `getEmail()` / `setEmail()`
- `getId_usuario()` / `setId_usuario()` - Genera hash SHA-256
- `getLlave_secreta()` / `setLlave_secreta()` - Encripta con BCrypt
- `getAccess_token()` / `setAccess_token()` - Con valor default
- `getEstado()` / `setEstado()`

---

### B. Repository - `RegistrosRepository.java`
**Ubicación:** `src/main/java/web/Sushi_Inka/repository/RegistrosRepository.java`

```java
public interface RegistrosRepository extends JpaRepository<Registros, Integer> {
    Optional<Registros> findByEmail(String email);
    Optional<Registros> findByAccessToken(String accessToken);
}
```

**Métodos disponibles:**
- `findAll()` - Heredado de JpaRepository
- `findById(Integer id)` - Heredado
- `findByEmail(String email)` - Buscar por email
- `findByAccessToken(String accessToken)` - Buscar por token
- `save(Registros registro)` - Guardar
- `delete(Registros registro)` - Eliminar
- `deleteById(Integer id)` - Eliminar por ID

---

### C. Service Interface - `IRegistrosService.java`
**Ubicación:** `src/main/java/web/Sushi_Inka/service/IRegistrosService.java`

```java
public interface IRegistrosService {
    List<Registros> listar();
    Optional<Registros> obtenerPorId(Integer id);
    Optional<Registros> obtenerPorEmail(String email);
    Optional<Registros> obtenerPorToken(String token);
    Registros crear(Registros registro);
    Registros actualizar(Integer id, Registros registro);
    void eliminar(Integer id);
}
```

---

### D. Service Implementation - `RegistrosServiceImpl.java`
**Ubicación:** `src/main/java/web/Sushi_Inka/service/impl/RegistrosServiceImpl.java`

**Métodos implementados:**

| Método | Descripción |
|--------|-------------|
| `listar()` | Retorna todos los registros activos |
| `obtenerPorId(Integer id)` | Busca por ID |
| `obtenerPorEmail(String email)` | Busca por email |
| `obtenerPorToken(String token)` | Busca por access_token |
| `crear(Registros registro)` | Crea nuevo registro |
| `actualizar(Integer id, Registros registro)` | Actualiza registro existente |
| `eliminar(Integer id)` | Elimina (soft delete) |

---

### E. Controller - `RegistrosController.java`
**Ubicación:** `src/main/java/web/Sushi_Inka/controller/RegistrosController.java`

**Endpoints disponibles:**

#### GET
```
GET /restful/registros
├─ Listar todos los registros
└─ Response: List<Registros>

GET /restful/registros/{id}
├─ Obtener registro por ID
└─ Response: Registros

GET /restful/registros/email/{email}
├─ Obtener por email
└─ Response: Registros

GET /restful/registros/token/{token}
├─ Obtener por access_token
└─ Response: Registros
```

#### POST
```
POST /restful/registros
├─ Crear nuevo registro
├─ Body: { "nombres": "...", "apellidos": "...", "email": "...", "llave_secreta": "..." }
└─ Response: Registros (201 CREATED)
```

#### PUT
```
PUT /restful/registros/{id}
├─ Actualizar registro
├─ Body: { "nombres": "...", "email": "...", ... }
└─ Response: Registros (200 OK)
```

#### DELETE
```
DELETE /restful/registros/{id}
├─ Eliminar registro (soft delete)
└─ Response: "Registro eliminado correctamente"
```

---

## 3. 🔄 Flujo de Autenticación

```
Usuario Externo (Cliente API)
    ↓
POST /restful/registros
    ├─ Validar email único
    ├─ Encriptar llave_secreta con BCrypt
    ├─ Generar access_token o usar default
    └─ Guardar en BD
    ↓
GET /restful/registros/token/{token}
    ├─ Validar token
    ├─ Obtener datos del usuario externo
    └─ Usar para autenticar en JwtFilter
    ↓
ExternalAuthFilter valida token
    ├─ Busca registro en tabla registros
    ├─ Si existe: Autentica usuario
    └─ Si no existe: Rechaza
```

---

## 4. 💾 Script SQL Completo

```sql
-- Crear tabla registros
CREATE TABLE IF NOT EXISTS `registros` (
  `id_registro` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `llave_secreta` varchar(255) NOT NULL COMMENT 'Secret key para validar acceso',
  `access_token` varchar(500) DEFAULT NULL COMMENT 'Token JWT generado',
  `estado` tinyint(4) DEFAULT 1 COMMENT '1=Activo, 0=Inactivo',
  `id_usuario` int(11) DEFAULT NULL COMMENT 'FK opcional a usuario interno del sistema',
  `fecha_creacion` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`id_registro`),
  UNIQUE KEY `uk_email` (`email`),
  UNIQUE KEY `uk_access_token` (`access_token`),
  KEY `idx_estado` (`estado`),
  KEY `idx_id_usuario` (`id_usuario`),
  KEY `fk_registros_usuario` (`id_usuario`),
  CONSTRAINT `fk_registros_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Insertar registro de ejemplo
INSERT INTO registros (nombres, apellidos, email, llave_secreta, access_token, estado, id_usuario) 
VALUES 
('Cliente', 'API', 'cliente@example.com', '$2a$10$...', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...', 1, NULL);
```

---

## 5. 📝 Ejemplos de Uso

### Crear Registro
```bash
curl -X POST http://localhost:8080/restful/registros \
  -H "Content-Type: application/json" \
  -d '{
    "nombres": "Juan",
    "apellidos": "Pérez",
    "email": "juan@example.com",
    "llave_secreta": "mi_secret_key_123",
    "access_token": "eyJhbGc...",
    "estado": 1
  }'
```

### Buscar por Email
```bash
curl -X GET http://localhost:8080/restful/registros/email/juan@example.com \
  -H "Authorization: Bearer {token}"
```

### Buscar por Token
```bash
curl -X GET http://localhost:8080/restful/registros/token/eyJhbGc... \
  -H "Authorization: Bearer {token}"
```

### Actualizar Registro
```bash
curl -X PUT http://localhost:8080/restful/registros/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer {token}" \
  -d '{
    "nombres": "Juan Carlos",
    "estado": 1
  }'
```

### Eliminar Registro
```bash
curl -X DELETE http://localhost:8080/restful/registros/1 \
  -H "Authorization: Bearer {token}"
```

---

## 6. 🔐 Seguridad

- ✅ Contraseña encriptada con BCrypt
- ✅ Token JWT con expiración
- ✅ Email único en la BD
- ✅ Access token único
- ✅ Soft delete (estado = 0)
- ✅ Relacionada opcionalmente con tabla usuario
- ✅ Filtros de autenticación integrados

---

## 7. 📋 Checklist Final

- ✅ Entity `Registros.java` creada
- ✅ Repository `RegistrosRepository.java` creada
- ✅ Service Interface `IRegistrosService.java` creada
- ✅ Service Impl `RegistrosServiceImpl.java` creada
- ✅ Controller `RegistrosController.java` creada
- ✅ Tabla SQL generada
- ✅ Relación FK con tabla usuario
- ✅ Indices optimizados
- ✅ Métodos CRUD completos
- ✅ Integración con ExternalAuthFilter

---

**Estado:** ✅ COMPLETADO

**Próximos pasos:**
1. Ejecutar el script SQL en la base de datos
2. Compilar el proyecto Maven
3. Iniciar la aplicación
4. Probar los endpoints


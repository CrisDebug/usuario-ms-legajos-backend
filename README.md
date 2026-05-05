# 👤 Microservicio de Usuarios - Legajos System

Este microservicio forma parte del sistema de gestión de legajos y se encarga de la autenticación, registro y administración de usuarios mediante JWT.

---

## 🧱 Responsabilidad del servicio

Este servicio gestiona:

- Registro de usuarios
- Autenticación (login)
- Generación de token JWT
- Validación de usuarios
- Control de acceso por roles

---

## ⚙️ Tecnologías utilizadas

- Java 17+
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Oracle Database XE
- Maven

---

## 🔐 Autenticación JWT

Este servicio implementa autenticación stateless con JWT.

### 📌 Endpoints principales

#### Registro de usuario


Ejemplo:

```json
{
  "nombreUsuario": "test user",
  "email": "testuser@test.com",
  "password": "123456"
}
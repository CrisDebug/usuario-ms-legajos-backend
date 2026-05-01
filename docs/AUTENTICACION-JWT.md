# 📘 Bitácora Técnica — Autenticación JWT (Auth Service)

## 🔐 Resumen general
Se implementó un sistema de autenticación basado en JWT dentro del microservicio de usuarios, permitiendo login seguro, registro de usuarios y control básico de acceso por roles.

---

## 🔑 Flujo de Login

1. El usuario envía credenciales a:
   - POST /api/auth/login

2. El backend realiza:
   - Búsqueda del usuario por email
   - Validación de password con BCrypt
   - Generación de JWT si las credenciales son correctas

3. El JWT incluye:
   - idUsuario
   - email (subject)
   - rol

4. Se retorna al cliente:
   - datos del usuario
   - token JWT

---

## 🧾 Flujo de Registro

1. El usuario envía datos a:
   - POST /api/auth/register

2. El backend realiza:
   - Validación de email único
   - Encriptación de password con BCrypt
   - Asignación de rol por defecto (USER)
   - Creación del usuario en base de datos

3. Se retorna:
   - datos del usuario creado
   - mensaje de confirmación

---

## 🔐 Estructura del JWT

- subject: email del usuario
- claims:
  - idUsuario
  - rol
- expiration: 1 hora
- firmado con SECRET_KEY

---

## 🛡️ Seguridad actual

- Spring Security integrado
- Autenticación stateless (sin sesiones)
- JWT generado en login
- Filtro JWT en implementación (validación de token por request)

---

## 📌 Estado del sistema

✔ Login funcional con JWT  
✔ Registro funcional con validación de email único  
✔ Password encriptado con BCrypt  
✔ Roles definidos en base de datos  
✔ Base para control de acceso por roles  

---

## 🚀 Próximo paso

- Implementar SecurityConfig
- Validación de roles por endpoint (USER / ADMIN)
- Protección completa de rutas con JWT
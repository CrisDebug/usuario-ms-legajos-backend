## 🔐 Auth - Register (Prueba Postman)

Se validó el endpoint de registro de usuarios utilizando **Postman**.

### URL probada
`POST http://localhost:8084/api/auth/register`

### Configuración Postman
- Método: `POST`
- Headers: `Content-Type: application/json`
- Body: `raw (JSON)`

### Resultado
El endpoint respondió correctamente con un JSON confirmando el registro del usuario, incluyendo:
- idUsuario generado
- nombreUsuario
- email
- rol asignado por defecto (`USER`)
- mensaje de confirmación

### Observaciones
- El password se almacena encriptado con BCrypt en la base de datos.
- El rol por defecto queda asignado automáticamente como `USER`.
- El sistema valida que el email no esté duplicado antes de insertar.
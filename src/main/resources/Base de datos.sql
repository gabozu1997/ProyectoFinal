-- 1. Crear base de datos
DROP DATABASE IF EXISTS gimnasio;usuariousuario
CREATE DATABASE gimnasio;
USE gimnasio;

-- 2. Crear tabla usuario
CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    password VARCHAR(255) NOT NULL,
    activo BOOLEAN DEFAULT FALSE,
    codigo_confirmacion VARCHAR(64)
);

-- 3. (Opcional) Crear usuario MySQL
-- Solo si querés que Spring Boot se conecte como usuario_admin
CREATE USER IF NOT EXISTS 'usuario_admin'@'localhost' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON gimnasio.* TO 'usuario_admin'@'localhost';
FLUSH PRIVILEGES;
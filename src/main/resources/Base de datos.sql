CREATE DATABASE gimnasio;
USE gimnasio;

CREATE USER 'usuario_admin'@'%' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON gimnasio.* TO 'usuario_admin'@'%';
FLUSH PRIVILEGES;

CREATE TABLE gimnasio.usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    apellidos VARCHAR(20) NOT NULL,
    email VARCHAR(100) NULL,
    telefono VARCHAR(20) NULL,
    password VARCHAR(255) NOT NULL,
    ruta_imagen VARCHAR(1024),
	activo BOOLEAN DEFAULT FALSE
);
 
CREATE TABLE gimnasio.rol (
	id_rol INT NOT NULL AUTO_INCREMENT,
    nombre varchar (20),
    id_usuario int,
    PRIMARY KEY (id_rol),
    FOREIGN KEY fk_rol_usuario (id_usuario) references usuario (id_usuario)
);

INSERT INTO gimnasio.usuario (id_usuario, username, nombre, apellidos, email, telefono, password, ruta_imagen, activo) VALUES
('1', 'gbonilla', 'Gabriel', 'Bonilla Zúñiga', 'gabozu97@gmail.com', '84657047', '123', NULL, '1'),
('2', 'julloa', 'Jasson', 'Ulloa Madriz', 'jasson2682@gmail.com', '83525137', '456', NULL, '1'),
('3', 'mjimenez', 'Miguel', 'Jimenez Valverde', 'miguel123@gmail.com', '87519654', '789', NULL, '1');

insert into gimnasio.rol (id_rol, nombre, id_usuario) values
 (1,'ADMIN',1), (2,'PERSONAL',1), (3,'USUARIO',1),
 (4,'PERSONAL',2), (5,'USUARIO',2),
 (6,'USUARIO',3);

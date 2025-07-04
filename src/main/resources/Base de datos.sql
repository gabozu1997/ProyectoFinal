CREATE SCHEMA gimnasio;

CREATE USER 'usuario_admin'@'%' identified by '1234';

GRANT ALL PRIVILEGES ON gimnasio.* to 'usuario_admin'@'%';
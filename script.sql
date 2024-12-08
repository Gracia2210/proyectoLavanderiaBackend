DROP DATABASE IF EXISTS DB_LAVANDERIA;
-- Crear la base de datos
CREATE DATABASE DB_LAVANDERIA;
-- Seleccionar la base de datos recién creada
USE DB_LAVANDERIA;

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE
);

-- Tabla de roles
CREATE TABLE rol (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion  VARCHAR(255) NOT NULL,
    nombre VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE usuario_rol (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    rol_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (rol_id) REFERENCES rol(id)
);
CREATE TABLE persona (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido_paterno VARCHAR(255) NOT NULL,
    apellido_materno VARCHAR(255) NOT NULL,
    sexo VARCHAR(1) NOT NULL,
    email VARCHAR(255) NULL,
    telefono VARCHAR(255),
	login_email BOOLEAN DEFAULT FALSE,
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

INSERT INTO rol (nombre, descripcion) VALUES ('ROLE_PERSONAL', 'PERSONAL');
INSERT INTO rol (nombre, descripcion) VALUES ('ROLE_ADMIN', 'ADMINISTRADOR');

INSERT INTO usuario (username, password, enabled) VALUES ('72274736', '$2a$10$Y5u2Um9LRI6WeAnz7rQ.u.DSdvfLxwsoLVYFpHDQXwS9Pspa4TlzK', true);
INSERT INTO usuario (username, password, enabled) VALUES ('74866913', '$2a$10$Y5u2Um9LRI6WeAnz7rQ.u.DSdvfLxwsoLVYFpHDQXwS9Pspa4TlzK', true);

INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (1, 1);
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (2, 2);


INSERT INTO persona (
    nombre, apellido_paterno, apellido_materno, sexo, email, telefono, id_usuario
) 
VALUES (
    'GRACIA ANTUANETTE', 'MARCA', 'TORRES', 'F', 'marcaurteagam@gmail.com', '913256561', 1
);

INSERT INTO persona (
    nombre, apellido_paterno, apellido_materno, sexo, email, telefono, id_usuario
) 
VALUES (
    'ROBERT D’JESÚS', 'PAREDES', 'GARCIA', 'M', 'dg080119@gmail.com', '913256561', 2
);

CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    doc_iden VARCHAR(20) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    apellido_paterno VARCHAR(255) NOT NULL,
    apellido_materno VARCHAR(255) NOT NULL,
	telefono VARCHAR(255) NOT NULL,
	email VARCHAR(255) NULL,
	enabled BOOLEAN DEFAULT TRUE
);






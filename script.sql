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

CREATE TABLE servicio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL,
	enabled BOOLEAN DEFAULT TRUE
);

INSERT INTO servicio (descripcion) VALUES 
('Lavado y Secado Básico'),
('Planchado'),
('Lavado en Seco (Dry Cleaning)'),
('Desmanchado y Tratamiento Especial'),
('Servicios Personalizados'),
('Recolección y Entrega a Domicilio'),
('Suscripciones y Paquetes Especiales'),
('Servicio Express'),
('Edredones o Cobertores'),
('Alfombras'),
('Cortinas');

CREATE TABLE subservicio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    unidad VARCHAR(255) NULL,
    solo_seleccion  BOOLEAN DEFAULT FALSE,
	enabled BOOLEAN DEFAULT TRUE,
	servicio_id INT,
	FOREIGN KEY (servicio_id) REFERENCES servicio(id)
);

-- Lavado y Secado Básico
INSERT INTO subservicio (descripcion, monto, servicio_id,unidad) VALUES 
('Lavado Estándar', 6.00, 1,'KILO'),
('Lavado Delicado (por kilo)', 8.00, 1,'KILO'),
('Secado Regular (por kilo)', 4.00, 1,'KILO'),
('Secado en Tendedero (por kilo)', 5.00, 1,'KILO');

-- Planchado
INSERT INTO subservicio (descripcion, monto, servicio_id,unidad) VALUES 
('Planchado Básico', 3.00, 2,'PRENDA'),
('Planchado de Prendas Delicadas', 5.00, 2,'PRENDA'),
('Planchado de Prendas Grandes', 8.00, 2,'PRENDA');

-- Lavado en Seco (Dry Cleaning)
INSERT INTO subservicio (descripcion, monto, servicio_id) VALUES 
('Trajes (completo)', 20.00, 3),
('Vestidos de Fiesta', 25.00, 3),
('Abrigos o Ropa de Invierno', 20.00, 3);

-- Desmanchado y Tratamiento Especial
INSERT INTO subservicio (descripcion, monto, servicio_id,unidad) VALUES 
('Desmanchado Básico', 3.00, 4,'PRENDA'),
('Desmanchado Profundo', 6.00, 4,'PRENDA'),
('Aromatización', 2.00, 4,'KILO'),
('Tratamiento Antibacteriano', 3.00, 4,'KILO');

-- Servicios Personalizados
INSERT INTO subservicio (descripcion, monto, servicio_id,unidad) VALUES 
('Empaque al Vacío', 3.00, 5,'PAQUETE'),
('Doblez Especial (por prenda)', 2.00, 5,'PRENDA'),
('Almidonado (por prenda)', 4.00, 5,'PRENDA');

-- Recolección y Entrega a Domicilio
INSERT INTO subservicio (descripcion, monto, servicio_id,solo_seleccion) VALUES 
('Recogida y Entrega en la Misma Zona', 10.00, 6,true),
('Recogida y Entrega Fuera de Zona', 15.00, 6,true);

-- Paquetes y Suscripciones
INSERT INTO subservicio (descripcion, monto, servicio_id,unidad) VALUES 
('Paquete Básico Semanal (hasta 5 kg de ropa)', 35.00, 7,'KILO'),
('Paquete Mensual (hasta 20 kg de ropa)', 120.00, 7,'KILO'),
('Suscripción para Empresas (por kilo)', 5.00, 7,'KILO');

-- Servicio Express
INSERT INTO subservicio (descripcion, monto, servicio_id,unidad) VALUES 
('Lavado y Entrega Rápida (por kilo, entrega en 24 horas)', 10.00, 8,'KILO');

-- Edredones o Cobertores
INSERT INTO subservicio (descripcion, monto, servicio_id,solo_seleccion) VALUES 
('1 Plaza', 20.00, 9,true),
('1 Plaza y Media', 25.00, 9,true),
('2 Plazas', 30.00, 9,true),
('2 Plazas y Media', 35.00, 9,true),
('Queen', 40.00, 9,true),
('King', 40.00, 9,true);

-- Alfombras
INSERT INTO subservicio (descripcion, monto, servicio_id,solo_seleccion) VALUES 
('Pequeña', 25.00, 10,true),
('Mediana', 35.00, 10,true),
('Grande', 50.00, 10,true);

-- Cortinas
INSERT INTO subservicio (descripcion, monto, servicio_id,solo_seleccion) VALUES 
('Pequeña', 15.00, 11,true),
('Mediana', 20.00, 11,true),
('Grande', 25.00, 11,true);




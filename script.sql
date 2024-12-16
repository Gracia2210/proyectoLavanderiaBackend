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
('Cortinas'),
('Servicios Especiales');

CREATE TABLE subservicio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    tipo char(1) DEFAULT '1' NOT NULL,
    detalle_tipo VARCHAR(255) NULL,
	enabled BOOLEAN DEFAULT TRUE,
	servicio_id INT,
    solo_seleccion  BOOLEAN DEFAULT FALSE,
	FOREIGN KEY (servicio_id) REFERENCES servicio(id)
);

-- Lavado y Secado Básico
INSERT INTO subservicio (descripcion, monto, servicio_id,detalle_tipo) VALUES 
('Lavado Estándar', 6.00, 1,'KILO'),
('Lavado Delicado (por kilo)', 8.00, 1,'KILO'),
('Secado Regular (por kilo)', 4.00, 1,'KILO'),
('Secado en Tendedero (por kilo)', 5.00, 1,'KILO');

-- Planchado
INSERT INTO subservicio (descripcion, monto, servicio_id,detalle_tipo) VALUES 
('Planchado Básico', 3.00, 2,'PRENDA'),
('Planchado de Prendas Delicadas', 5.00, 2,'PRENDA'),
('Planchado de Prendas Grandes', 8.00, 2,'PRENDA');

-- Lavado en Seco (Dry Cleaning)
INSERT INTO subservicio (descripcion, monto, servicio_id,detalle_tipo) VALUES 
('Trajes (completo)', 20.00, 3,'TRAJE'),
('Vestidos de Fiesta', 25.00, 3,'TRAJE'),
('Abrigos o Ropa de Invierno', 20.00, 3,'TRAJE');

-- Desmanchado y Tratamiento Especial
INSERT INTO subservicio (descripcion, monto, servicio_id,detalle_tipo) VALUES 
('Desmanchado Básico', 3.00, 4,'PRENDA'),
('Desmanchado Profundo', 6.00, 4,'PRENDA'),
('Aromatización', 2.00, 4,'KILO'),
('Tratamiento Antibacteriano', 3.00, 4,'KILO');

-- Servicios Personalizados
INSERT INTO subservicio (descripcion, monto, servicio_id,detalle_tipo) VALUES 
('Empaque al Vacío', 3.00, 5,'PAQUETE'),
('Doblez Especial (por prenda)', 2.00, 5,'PRENDA'),
('Almidonado (por prenda)', 4.00, 5,'PRENDA');

-- Recolección y Entrega a Domicilio
INSERT INTO subservicio (descripcion, monto, servicio_id,solo_seleccion) VALUES 
('Recogida y Entrega en la Misma Zona', 10.00, 6,true),
('Recogida y Entrega Fuera de Zona', 15.00, 6,true);

-- Paquetes y Suscripciones
INSERT INTO subservicio (descripcion, monto, servicio_id,detalle_tipo) VALUES 
('Paquete Básico Semanal (hasta 5 kg de ropa)', 35.00, 7,'KILO'),
('Paquete Mensual (hasta 20 kg de ropa)', 120.00, 7,'KILO'),
('Suscripción para Empresas (por kilo)', 5.00, 7,'KILO');

-- Servicio Express
INSERT INTO subservicio (descripcion, monto, servicio_id,detalle_tipo) VALUES 
('Lavado y Entrega Rápida (por kilo, entrega en 24 horas)', 10.00, 8,'KILO');

-- Edredones o Cobertores
INSERT INTO subservicio (descripcion, monto, servicio_id,detalle_tipo) VALUES 
('Plaza', 20.00, 9,'PLAZA'),
('Plaza y Media', 25.00, 9,'PLAZA'),
('Plazas', 30.00, 9,'PLAZA'),
('Plazas y Media', 35.00, 9,'PLAZA'),
('Queen', 40.00, 9,'PLAZA'),
('King', 40.00, 9,'PLAZA');

-- Alfombras
INSERT INTO subservicio (descripcion, monto, servicio_id) VALUES 
('Pequeña', 25.00, 10),
('Mediana', 35.00, 10),
('Grande', 50.00, 10);

-- Cortinas
INSERT INTO subservicio (descripcion, monto, servicio_id) VALUES 
('Pequeña', 15.00, 11),
('Mediana', 20.00, 11),
('Grande', 25.00, 11);

-- Servicios especiales
INSERT INTO subservicio (descripcion, monto, servicio_id) VALUES 
('Cuero', 10.00, 12),
('kasmir', 10.00, 12),
('Cuerina', 10.00, 12),
('Terciopelo', 10.00, 12),
('Pima', 10.00, 12);



CREATE TABLE medio_pago (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL,
	enabled BOOLEAN DEFAULT TRUE
);

INSERT INTO medio_pago (descripcion) VALUES 
('EFECTIVO'),
('YAPE'),
('PLIN'),
('TARJETA DÉBITO'),
('TARJETA DE CRÉDITO');

-- DROP TABLE PAGO;
-- DROP TABLE PAGO_DETALLE;
CREATE TABLE pago (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(255) NOT NULL,
	pagado BOOLEAN DEFAULT FALSE,
	entregado BOOLEAN DEFAULT FALSE,
    cliente_id INT NOT NULL,
    medio_pago_id INT,
	porcentaje_pago DECIMAL(10, 2) NULL,
    monto_pagado_inicial  DECIMAL(10, 2) NOT NULL,
    monto_total DECIMAL(10, 2) NULL,
    usuario_id INT NOT NULL,
	fecha_recojo DATE NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    observacion TEXT NULL,
	enabled BOOLEAN DEFAULT TRUE,
	FOREIGN KEY (medio_pago_id) REFERENCES medio_pago(id),
	FOREIGN KEY (cliente_id) REFERENCES cliente(id),
	FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE pago_detalle (
    id INT AUTO_INCREMENT PRIMARY KEY,
	pago_id INT NOT NULL,
    cantidad INT NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    monto_total DECIMAL(10, 2) NOT NULL,
    tipo char(1) NOT NULL,
    detalle_tipo VARCHAR(255) NULL,
    subservicio_id INT NOT NULL,
	enabled BOOLEAN DEFAULT TRUE,
	solo_seleccion  BOOLEAN DEFAULT FALSE,
 	FOREIGN KEY (pago_id) REFERENCES pago(id),
	FOREIGN KEY (subservicio_id) REFERENCES subservicio(id)   
);

CREATE TABLE contador_serie (
    id INT AUTO_INCREMENT PRIMARY KEY,
    valor_actual VARCHAR(255) NOT NULL
);
INSERT INTO contador_serie (valor_actual)
VALUES ('001');

CREATE TABLE contador_secuencia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    valor_actual INT NOT NULL
);
INSERT INTO contador_secuencia (valor_actual)
VALUES (1);

CREATE TABLE configuracion_sistema (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NULL,
    descripcion VARCHAR(255) NULL,
    direccion VARCHAR(255) NULL,
    telefono VARCHAR(20) NULL,
    imagen LONGBLOB  NULL,
    nombre_imagen VARCHAR(150) NULL,
    tipo_imagen VARCHAR(10) NULL
);

INSERT INTO configuracion_sistema
(nombre,descripcion,direccion,telefono)
VALUES('SOFTCLEAN','Laundry Service','Calle Alberto Montellanos 157 - La Victoria','2426452');

--------------------------------------------

SELECT p.id, p.codigo,
 p.pagado,
 p.entregado,
CONCAT(c.apellido_paterno, ' ', c.apellido_materno, '' ,c.nombre) AS cliente,
mp.descripcion mediio_pago,
p.porcentaje_pago,
p.monto_pagado_inicial,
p.monto_total,
CONCAT(pe.apellido_paterno, ' ',pe.apellido_materno, '' ,pe.nombre) AS usuario,
DATE_FORMAT(p.fecha_creacion, '%d/%m/%Y %H:%i:%s') AS fecha_creacion,
DATE_FORMAT(p.fecha_recojo, '%d/%m/%Y') AS fecha_entrega
 FROM pago p
INNER JOIN cliente c on p.cliente_id=c.id
INNER JOIN medio_pago mp on p.medio_pago_id=mp.id
INNER JOIN usuario u on p.usuario_id=u.id
INNER JOIN persona pe on u.id=pe.id_usuario
WHERE c.id=4 AND p.entregado=FALSE and p.enabled=true
ORDER BY P.fecha_creacion DESC;

SELECT id cod ,descripcion nombre FROM servicio WHERE enabled=1 ORDER BY cod ASC;
SELECT id cod ,descripcion nombre FROM subservicio WHERE enabled=1 AND servicio_id=1 ORDER BY cod ASC;

SELECT p.id,p.codigo,p.pagado,p.entregado,p.medio_pago_id,p.porcentaje_pago,
	p.monto_pagado_inicial,
    p.monto_total,
    DATE_FORMAT(p.fecha_recojo, '%Y-%m-%d') AS fecha_entrega,
    DATE_FORMAT(p.fecha_creacion, '%d/%m/%Y %H:%i:%s') AS fecha_creacion,
    p.observacion
 FROM pago p WHERE p.id=3 AND p.enabled=true;
 
 SELECT d.cantidad,d.monto,d.monto_total,d.tipo,d.detalle_tipo,d.subservicio_id FROM pago_detalle d
 WHERE d.pago_id=3 AND D.enabled=true;
 
 SELECT d.subservicio_id cod,
 CONCAT(s.descripcion,' / ',sub.descripcion) AS nombre,
 d.solo_seleccion,
 d.tipo,
 d.detalle_tipo,
 d.monto,
 d.cantidad,
 d.monto_total
 FROM pago_detalle d
 INNER JOIN subservicio sub ON d.subservicio_id=sub.id
 INNER JOIN servicio s ON sub.servicio_id=s.id
 WHERE d.pago_id=3 AND D.enabled=true;

 
SELECT p.id, p.codigo,
CASE 
        WHEN p.pagado = TRUE THEN 'PAGADO'
        ELSE 'PENDIENTE DE PAGO'
    END AS pagado,
CASE 
        WHEN p.entregado = TRUE THEN 'SI'
        ELSE 'NO'
    END AS entregado,
 CONCAT(c.apellido_paterno, ' ', c.apellido_materno, ' ' ,c.nombre) AS cliente, 
 mp.descripcion AS medio_pago, p.monto_pagado_inicial,
 p.monto_total, CONCAT(pe.apellido_paterno, ' ',pe.apellido_materno, ' ' ,pe.nombre) 
 AS usuario, DATE_FORMAT(p.fecha_creacion, '%d/%m/%Y %H:%i:%s') AS fecha_creacion, 
 DATE_FORMAT(p.fecha_recojo, '%d/%m/%Y') AS fecha_entrega,pe.telefono
 FROM pago p 
 INNER JOIN cliente c on p.cliente_id=c.id INNER JOIN medio_pago mp on p.medio_pago_id=mp.id
 INNER JOIN usuario u on p.usuario_id=u.id INNER JOIN persona pe on u.id=pe.id_usuario
 WHERE p.id=1 AND p.entregado=FALSE 
AND p.enabled=true


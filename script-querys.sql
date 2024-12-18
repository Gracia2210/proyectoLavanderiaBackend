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
       DATE_FORMAT(p.fecha_recojo, '%d/%m/%Y') AS fecha_entrega,c.telefono
FROM pago p
         INNER JOIN cliente c on p.cliente_id=c.id INNER JOIN medio_pago mp on p.medio_pago_id=mp.id
         INNER JOIN usuario u on p.usuario_id=u.id INNER JOIN persona pe on u.id=pe.id_usuario
WHERE p.id=1 AND p.entregado=FALSE
  AND p.enabled=true;
SELECT s.id id_serie,s.valor_actual serie,c.id id_secuencia,c.valor_actual secuencia  FROM contador_serie s,contador_secuencia c;

SELECT serie FROM pago ORDER BY id DESC LIMIT 1;


--------------------------------





SELECT DATE_FORMAT(p.fecha_recojo, '%d/%m/%Y') detalle1,SUM(p.monto_total) detalle2 , count(*) 
detalle3 FROM pago p
WHERE p.enabled=true
 AND p.fecha_creacion BETWEEN '2024-01-01 00:00:00' AND '2024-12-31 23:59:59'
GROUP by DATE_FORMAT(p.fecha_recojo, '%d/%m/%Y');

-- SERVICIOS MAS SELECCIONADOS
SELECT CONCAT(se.descripcion,' / ',s.descripcion) detalle1,count(p.id) AS detalle2 FROM subservicio s
INNER JOIN servicio se ON s.servicio_id=se.id
INNER JOIN pago_detalle p ON s.id=p.subservicio_id
INNER JOIN pago pa ON p.pago_id=pa.id
WHERE pa.fecha_creacion BETWEEN '2024-01-01 00:00:00' AND '2024-12-31 23:59:59'
AND pa.enabled=true and p.enabled=true
GROUP BY s.id
ORDER BY detalle2 DESC;


-- TIPOS DE CATEGORIAS (GRAFICO)
SELECT 'PENDIENTE' AS detalle1, count(*) AS detalle2 FROM pago p WHERE p.entregado = false 
AND p.cancelado = false AND p.enabled=true 
AND p.fecha_creacion BETWEEN '2024-01-01 00:00:00' AND '2024-12-31 23:59:59'
UNION
SELECT 'COMPLETADO' AS detalle1, count(*) AS detalle2  FROM pago p WHERE p.entregado = true 
AND p.cancelado = false AND p.enabled=true
AND p.fecha_creacion BETWEEN '2024-01-01 00:00:00' AND '2024-12-31 23:59:59'
UNION
SELECT 'CANCELADO' AS detalle1, count(*) AS detalle2  FROM pago p WHERE p.cancelado = true 
AND p.enabled=true
AND p.fecha_creacion BETWEEN '2024-01-01 00:00:00' AND '2024-12-31 23:59:59'
ORDER BY detalle2 DESC;

-- TIPO DE CATEGORIA (DETALLE)

SELECT p.codigo detalle1,
CASE WHEN p.entregado = false AND p.cancelado = false THEN
'PENDIENTE'
 WHEN p.entregado = true AND p.cancelado = false THEN
 'COMPLETADO'
 WHEN p.cancelado = true  THEN
 'CANCELADO'
 END detalle2,
 p.observacion detalle3
 FROM pago p WHERE p.enabled=true
 AND p.fecha_creacion BETWEEN '2024-01-01 00:00:00' AND '2024-12-31 23:59:59'
 ORDER BY id ASC;

-- reporte cliente
SELECT CONCAT(c.nombre, ' ', c.apellido_paterno, ' ' ,c.apellido_materno) AS detalle1,
COUNT(p.id) detalle2,max(DATE_FORMAT(p.fecha_recojo, '%d/%m/%Y')) detalle3
 from cliente c
INNER JOIN pago p ON c.id=p.cliente_id
WHERE p.enabled=true
 AND p.fecha_creacion BETWEEN '2024-01-01 00:00:00' AND '2024-12-31 23:59:59'
GROUP BY c.id
 ORDER BY detalle1 DESC;
 
 
-- medio de pago

SELECT m.descripcion detalle1,sum(p.monto_total) detalle2 FROM pago p
INNER JOIN medio_pago m ON p.medio_pago_id=m.id
WHERE p.enabled=true
 AND p.fecha_creacion BETWEEN '2024-01-01 00:00:00' AND '2024-12-31 23:59:59'
GROUP BY m.id;


-- usuarios
SELECT CONCAT(pe.nombre, ' ',pe.apellido_materno, ' ' ,pe.apellido_materno) AS detalle1,
COUNT(p.id) detalle2,
SUM(p.monto_total) detalle3
 from usuario u
INNER JOIN persona pe ON u.id=pe.id_usuario
INNER JOIN pago p ON u.id=p.usuario_id
WHERE p.enabled=true
 AND p.fecha_creacion BETWEEN '2024-01-01 00:00:00' AND '2024-12-31 23:59:59'
 GROUP BY pe.id;

-- deudas

SELECT p.codigo detalle1, CONCAT(c.nombre, ' ', c.apellido_paterno, ' ' ,c.apellido_materno) AS detalle2,
(p.monto_total-p.monto_pagado_inicial) detalle3
 FROM  cliente c
INNER JOIN pago p ON c.id=p.cliente_id
INNER JOIN pago_detalle d ON p.id=d.pago_id
WHERE p.enabled=true
 AND p.fecha_creacion BETWEEN '2024-01-01 00:00:00' AND '2024-12-31 23:59:59'
 AND p.pagado= false


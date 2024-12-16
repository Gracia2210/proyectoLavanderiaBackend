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
  AND p.enabled=true


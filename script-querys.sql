----------LISTA POR CATEGORIA
SELECT p.id AS id_persona, CONCAT(p.apellido_paterno, ' ', p.apellido_materno,' ',p.nombre) AS nombre,
       ca.id AS id_categoria,
       ca.nombre AS categoria,
       COUNT(*) AS total_consultas,
       DATE_FORMAT(MAX(c.fecha_creacion), '%d/%m/%Y %H:%i:%s') AS ultima_fecha
FROM consultas c
         INNER JOIN persona p ON c.id_persona = p.id
         INNER JOIN categoria ca ON c.id_categoria = ca.id
WHERE p.id=3 AND ca.id=1
GROUP BY id_persona,nombre,id_categoria, categoria
ORDER BY nombre ASC;


--LISTA POR CATEGORIA DETALLE

SELECT CONCAT(p.apellido_paterno, ' ', p.apellido_materno,' ',p.nombre) AS nombre,
       ca.nombre AS categoria,
       c.msj_persona pregunta,
       c.msj_bot respuesta,
       DATE_FORMAT(c.fecha_creacion, '%d/%m/%Y %H:%i:%s') AS fecha
FROM consultas c
         INNER JOIN persona p ON c.id_persona = p.id
         INNER JOIN categoria ca ON c.id_categoria = ca.id
WHERE p.id=3 AND ca.id=1
ORDER BY fecha,pregunta ASC;

--LISTA DE ESTUDIANTES

SELECT p.id codigo,
       CONCAT(p.apellido_paterno, ' ', p.apellido_materno,' ',p.nombre) AS cod
FROM persona p
         INNER JOIN usuario u ON p.id_usuario=u.id
         INNER JOIN usuario_rol ur ON u.id=ur.usuario_id
WHERE ur.rol_id=1
ORDER BY nombre ASC;

--LISTA DE CATEGORIAS
SELECT id cod,nombre FROM categoria ORDER BY id asc;

---------SCRIPTS DASHBOARD




SELECT ca.id, ca.nombre as name, COUNT(*) as y , false as sliced
FROM consultas c
         INNER JOIN categoria ca ON c.id_categoria = ca.id
WHERE c.fecha_creacion BETWEEN '2024-01-01 00:00:00' AND '2024-12-31 23:59:59'
GROUP BY ca.id, ca.nombre ORDER BY Y DESC;


SELECT DATE_FORMAT(fecha_creacion, '%d/%m/%Y') AS dias, COUNT(*) AS consultas
FROM consultas
WHERE fecha_creacion BETWEEN '2024-01-01 00:00:00' AND '2024-12-31 23:59:59'
GROUP BY dias
ORDER BY consultas DESC
    LIMIT 10;



SELECT
    MONTH(fecha_creacion) AS nro,
    CONCAT(DATE_FORMAT(fecha_creacion, '%M'), ' ', YEAR(fecha_creacion)) AS mes,
    COUNT(*) AS consultas
FROM consultas
WHERE fecha_creacion BETWEEN '2024-01-01 00:00:00' AND '2024-12-31 23:59:59'
GROUP BY nro, mes
ORDER BY nro
    LIMIT 12;


SELECT ca.id, ca.nombre as name, COUNT(*) as y
FROM consultas c
         INNER JOIN categoria ca ON c.id_categoria = ca.id
WHERE c.fecha_creacion BETWEEN '2024-01-01 00:00:00' AND '2024-12-31 23:59:59'
GROUP BY ca.id, ca.nombre ORDER BY Y DESC;


SELECT p.id , CONCAT(p.apellido_paterno, ' ', p.apellido_materno,' ',p.nombre) AS nombre,
       COUNT(*) AS consultas
FROM consultas c
         INNER JOIN persona p ON c.id_persona = p.id
WHERE c.fecha_creacion BETWEEN '2024-01-01 00:00:00' AND '2024-12-31 23:59:59'
GROUP BY id_persona
ORDER BY consultas desc LIMIT 5 ;


SELECT
    ca.id AS id_categoria,
    ca.nombre AS categoria,
    COUNT(*) AS consultas
FROM consultas c
         INNER JOIN persona p ON c.id_persona = p.id
         INNER JOIN categoria ca ON c.id_categoria = ca.id
WHERE p.id=2
GROUP BY id_categoria, categoria
ORDER BY consultas ASC;

package pe.edu.usmp.lavanderia.app.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import pe.edu.usmp.lavanderia.app.config.Auth;
import pe.edu.usmp.lavanderia.app.repository.ReporteRepository;
import pe.edu.usmp.lavanderia.app.request.ReporteRequest;
import pe.edu.usmp.lavanderia.app.response.ReporteResponse;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ReporteRepositoryImpl extends JdbcDaoSupport implements ReporteRepository {

    @Autowired
    private ApplicationContext context;
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private Auth auth;

    @Autowired
    public void DataSource(DataSource setDataSource) {
        setDataSource(setDataSource);
        this.jdbcTemplate = context.getBean("db_desa", JdbcTemplate.class);
    }

    @Override
    public List<ReporteResponse> listaIngresoTotalesPeriodo(ReporteRequest datos) {
        String sql = "SELECT DATE_FORMAT(p.fecha_recojo, '%d/%m/%Y') AS detalle1, " +
                "SUM(p.monto_total) AS detalle2, " +
                "COUNT(*) AS detalle3 " +
                "FROM pago p " +
                "WHERE p.enabled = true " +
                "AND p.fecha_creacion BETWEEN ? AND ? " +
                "GROUP BY DATE_FORMAT(p.fecha_recojo, '%d/%m/%Y') LIMIT 5";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ReporteResponse.class), datos.getInicio(), datos.getFin());
    }

    @Override
    public List<ReporteResponse> listaServicioSolicitado(ReporteRequest datos) {
        String sql = "SELECT CONCAT(se.descripcion, ' / ', s.descripcion) AS detalle1, " +
                "COUNT(p.id) AS detalle2 " +
                "FROM subservicio s " +
                "INNER JOIN servicio se ON s.servicio_id = se.id " +
                "INNER JOIN pago_detalle p ON s.id = p.subservicio_id " +
                "INNER JOIN pago pa ON p.pago_id = pa.id " +
                "WHERE pa.fecha_creacion BETWEEN ? AND ? " +
                "AND pa.enabled = true " +
                "AND p.enabled = true " +
                "GROUP BY s.id " +
                "ORDER BY detalle2 DESC LIMIT 5";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ReporteResponse.class), datos.getInicio(), datos.getFin());

    }

    @Override
    public List<ReporteResponse> listaEstadosPagos(ReporteRequest datos) {
        String sql = "SELECT 'PENDIENTE' AS detalle1, COUNT(*) AS detalle2 " +
                "FROM pago p " +
                "WHERE p.entregado = false " +
                "AND p.cancelado = false " +
                "AND p.enabled = true " +
                "AND p.fecha_creacion BETWEEN ? AND ? " +
                "UNION " +
                "SELECT 'COMPLETADO' AS detalle1, COUNT(*) AS detalle2 " +
                "FROM pago p " +
                "WHERE p.entregado = true " +
                "AND p.cancelado = false " +
                "AND p.enabled = true " +
                "AND p.fecha_creacion BETWEEN ? AND ? " +
                "UNION " +
                "SELECT 'CANCELADO' AS detalle1, COUNT(*) AS detalle2 " +
                "FROM pago p " +
                "WHERE p.cancelado = true " +
                "AND p.enabled = true " +
                "AND p.fecha_creacion BETWEEN ? AND ? " +
                "ORDER BY detalle2 DESC";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ReporteResponse.class), datos.getInicio(), datos.getFin(), datos.getInicio(), datos.getFin(), datos.getInicio(), datos.getFin());

    }

    @Override
    public List<ReporteResponse> listaEstadosPagosDetalle(ReporteRequest datos) {
        String sql = "SELECT p.codigo AS detalle1, " +
                "CASE " +
                "  WHEN p.entregado = false AND p.cancelado = false THEN 'PENDIENTE' " +
                "  WHEN p.entregado = true AND p.cancelado = false THEN 'COMPLETADO' " +
                "  WHEN p.cancelado = true THEN 'CANCELADO' " +
                "END AS detalle2, " +
                "p.observacion AS detalle3 " +
                "FROM pago p " +
                "WHERE p.enabled = true " +
                "AND p.fecha_creacion BETWEEN ? AND ? " +
                "ORDER BY p.id ASC";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ReporteResponse.class), datos.getInicio(), datos.getFin());
    }

    @Override
    public List<ReporteResponse> listaClientesFrecuentes(ReporteRequest datos) {
        String sql = "SELECT CONCAT(c.nombre, ' ', c.apellido_paterno, ' ', c.apellido_materno) AS detalle1, " +
                "COUNT(p.id) AS detalle2, " +
                "MAX(DATE_FORMAT(p.fecha_recojo, '%d/%m/%Y')) AS detalle3 " +
                "FROM cliente c " +
                "INNER JOIN pago p ON c.id = p.cliente_id " +
                "WHERE p.enabled = true " +
                "AND p.fecha_creacion BETWEEN ? AND ? " +
                "GROUP BY c.id " +
                "ORDER BY detalle1 DESC LIMIT 5";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ReporteResponse.class), datos.getInicio(), datos.getFin());

    }

    @Override
    public List<ReporteResponse> listaMedioPagosMonto(ReporteRequest datos) {
        String sql = "SELECT m.descripcion AS detalle1, " +
                "SUM(p.monto_total) AS detalle2 " +
                "FROM pago p " +
                "INNER JOIN medio_pago m ON p.medio_pago_id = m.id " +
                "WHERE p.enabled = true " +
                "AND p.fecha_creacion BETWEEN ? AND ? " +
                "GROUP BY m.id";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ReporteResponse.class), datos.getInicio(), datos.getFin());

    }

    @Override
    public List<ReporteResponse> listaIngresoUsuario(ReporteRequest datos) {
        String sql = "SELECT CONCAT(pe.nombre, ' ', pe.apellido_paterno, ' ', pe.apellido_materno) AS detalle1, " +
                "COUNT(p.id) AS detalle2, " +
                "SUM(p.monto_total) AS detalle3 " +
                "FROM usuario u " +
                "INNER JOIN persona pe ON u.id = pe.id_usuario " +
                "INNER JOIN pago p ON u.id = p.usuario_id " +
                "WHERE p.enabled = true  AND p.entregado=true " +
                "AND p.fecha_creacion BETWEEN ? AND ?" +
                "GROUP BY pe.id LIMIT 7";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ReporteResponse.class), datos.getInicio(), datos.getFin());

    }

    @Override
    public List<ReporteResponse> listarDeudores(ReporteRequest datos) {
        String sql = "SELECT p.id AS detalle1, " +
                "p.codigo AS detalle2, " +
                "CONCAT(c.nombre, ' ', c.apellido_paterno, ' ', c.apellido_materno) AS detalle3, " +
                "(p.monto_total - p.monto_pagado_inicial) AS detalle4, " +
                "DATE_FORMAT(p.fecha_creacion, '%d/%m/%Y %H:%i:%s') AS detalle5 " +
                "FROM cliente c " +
                "INNER JOIN pago p ON c.id = p.cliente_id " +
                "INNER JOIN pago_detalle d ON p.id = d.pago_id " +
                "WHERE p.enabled = true " +
                "AND p.fecha_creacion BETWEEN ? AND ? " +
                "AND p.pagado = false " +
                "AND p.cancelado = false " +
                "ORDER BY p.fecha_creacion ASC";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ReporteResponse.class), datos.getInicio(), datos.getFin());
    }


}

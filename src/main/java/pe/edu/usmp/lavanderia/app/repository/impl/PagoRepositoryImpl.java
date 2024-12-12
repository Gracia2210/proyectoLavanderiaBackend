package pe.edu.usmp.lavanderia.app.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import pe.edu.usmp.lavanderia.app.repository.PagoRepository;
import pe.edu.usmp.lavanderia.app.response.CodNombreResponse;
import pe.edu.usmp.lavanderia.app.response.ListaClientePagoResponse;
import pe.edu.usmp.lavanderia.app.response.SubserviciosPagoResponse;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PagoRepositoryImpl extends JdbcDaoSupport implements PagoRepository {
    @Autowired
    private ApplicationContext context;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void DataSource(DataSource setDataSource) {
        setDataSource(setDataSource);
        this.jdbcTemplate = context.getBean("db_desa", JdbcTemplate.class);
    }
    @Override
    public List<ListaClientePagoResponse> listarPagosxCliente(Integer clienteId) {
        String sql="SELECT p.id,p.codigo, CASE p.estado WHEN '1' THEN 'PENDIENTE' ELSE 'PAGADO' END AS estado,\n" +
                "CONCAT(c.apellido_paterno, ' ', c.apellido_materno, '' ,c.nombre) AS cliente, mp.descripcion medio_pago,p.porcentaje_pago,p.monto_pagado_inicial, p.monto_total, CONCAT(pe.apellido_paterno, ' ',pe.apellido_materno, '' ,pe.nombre) AS usuario, DATE_FORMAT(p.fecha_creacion, '%d/%m/%Y %H:%i:%s') AS fecha_creacion, DATE_FORMAT(p.fecha_entrega, '%d/%m/%Y %H:%i:%s') AS fecha_entrega, p.direccion, p.observacion FROM pago p INNER JOIN cliente c on p.cliente_id=c.id INNER JOIN medio_pago mp on p.medio_pago_id=mp.id INNER JOIN usuario u on p.usuario_id=u.id INNER JOIN persona pe on u.id=pe.id_usuario WHERE c.id=? AND p.estado='1' ORDER BY P.fecha_creacion DESC";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ListaClientePagoResponse.class),clienteId);
    }

    @Override
    public List<CodNombreResponse> listarServicios() {
        String sql="SELECT id cod ,descripcion nombre FROM servicio WHERE enabled=1 ORDER BY cod ASC";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CodNombreResponse.class));

    }

    @Override
    public List<SubserviciosPagoResponse> listarSubservicios(Integer servicioId) {
        String sql="SELECT id AS cod , descripcion AS nombre, monto,unidad,solo_seleccion FROM subservicio WHERE enabled=1 AND servicio_id=? ORDER BY id ASC";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(SubserviciosPagoResponse.class),servicioId);
    }
}

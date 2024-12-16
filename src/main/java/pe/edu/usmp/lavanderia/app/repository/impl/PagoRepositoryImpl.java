package pe.edu.usmp.lavanderia.app.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import pe.edu.usmp.lavanderia.app.config.Auth;
import pe.edu.usmp.lavanderia.app.repository.PagoRepository;
import pe.edu.usmp.lavanderia.app.request.ServicioPagoRequest;
import pe.edu.usmp.lavanderia.app.response.*;
import pe.edu.usmp.lavanderia.app.request.OrdenPagoRequest;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

@Repository
public class PagoRepositoryImpl extends JdbcDaoSupport implements PagoRepository {
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
    public List<ListaClientePagoResponse> listarPagosxCliente(Integer clienteId) {
        String sql="SELECT p.id, p.codigo, p.pagado, p.entregado, CONCAT(c.apellido_paterno, ' ', c.apellido_materno, ' ' ,c.nombre) AS cliente, mp.descripcion medio_pago, p.porcentaje_pago, p.monto_pagado_inicial, p.monto_total, CONCAT(pe.apellido_paterno, ' ',pe.apellido_materno, ' ' ,pe.nombre) AS usuario, DATE_FORMAT(p.fecha_creacion, '%d/%m/%Y %H:%i:%s') AS fecha_creacion, DATE_FORMAT(p.fecha_recojo, '%d/%m/%Y') AS fecha_entrega FROM pago p INNER JOIN cliente c on p.cliente_id=c.id INNER JOIN medio_pago mp on p.medio_pago_id=mp.id INNER JOIN usuario u on p.usuario_id=u.id INNER JOIN persona pe on u.id=pe.id_usuario WHERE c.id=? AND p.entregado=FALSE and p.enabled=true ORDER BY P.fecha_creacion DESC";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ListaClientePagoResponse.class),clienteId);
    }

    @Override
    public List<CodNombreResponse> listarServicios() {
        String sql="SELECT id cod ,descripcion nombre FROM servicio WHERE enabled=1 ORDER BY cod ASC";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CodNombreResponse.class));

    }

    @Override
    public List<SubserviciosPagoResponse> listarSubservicios(Integer servicioId) {
        String sql="SELECT id AS cod , descripcion AS nombre, monto,tipo,detalle_tipo,solo_seleccion FROM subservicio WHERE enabled=1 AND servicio_id=? ORDER BY id ASC";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(SubserviciosPagoResponse.class),servicioId);
    }

    @Override
    public List<CodNombreResponse> listarMedioPagos() {
        String sql="SELECT id AS cod , descripcion AS nombre FROM medio_pago WHERE enabled=true ORDER BY id ASC";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CodNombreResponse.class));
    }
    @Override
    public boolean existeCodigo(String codigo) {
        String sql = "SELECT COUNT(*) FROM pago WHERE codigo = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, codigo);
        return count != null && count > 0;
    }
    @Override
    public String generarCodigo() {
        String contadorSerie = jdbcTemplate.queryForObject(
                "SELECT valor_actual FROM contador_serie LIMIT 1",
                String.class
        );

        Integer contadorSecuencia = jdbcTemplate.queryForObject(
                "SELECT valor_actual FROM contador_secuencia LIMIT 1",
                Integer.class
        );
        String codigoGenerado = contadorSerie + "-" + String.format("%03d", contadorSecuencia);
        return codigoGenerado;
    }



    @Override
    public Integer insertarCabeceraBoleta(OrdenPagoRequest ordenPagoRequest) {
        String sql="INSERT INTO pago ( codigo, pagado, cliente_id, medio_pago_id, porcentaje_pago, monto_pagado_inicial, monto_total, usuario_id, fecha_recojo, observacion ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
        jdbcTemplate.update(sql,
                ordenPagoRequest.getCodigo(),
                ordenPagoRequest.isPagado(),
                ordenPagoRequest.getCliente(),
                ordenPagoRequest.getMedioPago(),
                ordenPagoRequest.getPorcentaje(),
                ordenPagoRequest.getMontoPagado(),
                ordenPagoRequest.getMontoTotal(),
                auth.idUsuario(),
                LocalDate.parse(ordenPagoRequest.getFechaRecojo()),
                ordenPagoRequest.getObservacion());

        String idSql = "SELECT LAST_INSERT_ID()";
        return jdbcTemplate.queryForObject(idSql, Integer.class);
    }

    @Override
    public Integer insertarDetalleBoleta(ServicioPagoRequest servicioPagoRequest,Integer pago) {
        String sql = "INSERT INTO pago_detalle (pago_id, cantidad, monto, monto_total, tipo, detalle_tipo, subservicio_id,solo_seleccion) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return   jdbcTemplate.update(sql,
                pago,
                servicioPagoRequest.getCantidad(),
                servicioPagoRequest.getMonto(),
                servicioPagoRequest.getMontoTotal(),
                servicioPagoRequest.getTipo(),
                servicioPagoRequest.getDetalleTipo(),
                servicioPagoRequest.getCod(),
                servicioPagoRequest.getSoloSeleccion()
                );
    }

    @Override
    public void actualizarContadorSecuencia() {
        jdbcTemplate.update(
                "UPDATE contador_secuencia SET valor_actual = valor_actual + 1"
        );
    }

    @Override
    public OrdenPagoEditResponse obtenerPagoEdit(Integer pago) {
        String pagoQuery = "SELECT " +
                "p.id, " +
                "p.codigo, " +
                "p.pagado, " +
                "p.entregado, " +
                "p.medio_pago_id AS medioPagoId, " +
                "p.porcentaje_pago AS porcentajePago, " +
                "p.monto_pagado_inicial AS montoPagadoInicial, " +
                "DATE_FORMAT(p.fecha_creacion, '%d/%m/%Y %H:%i:%s') AS fecha_creacion, "+
                "p.monto_total AS montoTotal, " +
                "DATE_FORMAT(p.fecha_recojo, '%Y-%m-%d') AS fechaEntrega, " +
                "p.observacion " +
                "FROM pago p " +
                "WHERE p.id = ? AND p.enabled = true";

        String detalleQuery = " SELECT d.subservicio_id cod,\n" +
                " CONCAT(s.descripcion,' / ',sub.descripcion) AS nombre," +
                " d.solo_seleccion," +
                " d.tipo," +
                " d.detalle_tipo," +
                " d.monto," +
                " d.cantidad," +
                " d.monto_total" +
                " FROM pago_detalle d" +
                " INNER JOIN subservicio sub ON d.subservicio_id=sub.id" +
                " INNER JOIN servicio s ON sub.servicio_id=s.id" +
                " WHERE d.pago_id=? AND D.enabled=true ORDER BY d.id ASC";

        OrdenPagoEditResponse ordenPago = jdbcTemplate.queryForObject(
                pagoQuery,
                BeanPropertyRowMapper.newInstance(OrdenPagoEditResponse.class),
                pago
        );

        List<ServicioPagoEditResponse> detalles = jdbcTemplate.query(
                detalleQuery,
                BeanPropertyRowMapper.newInstance(ServicioPagoEditResponse.class),
                pago
        );
        ordenPago.setPago(detalles);
        return ordenPago;
    }

    @Override
    public Integer editarCabeceraBoleta(OrdenPagoRequest ordenPagoRequest) {
        String sql = "UPDATE pago " +
                "SET  pagado = ?, medio_pago_id = ?, porcentaje_pago = ?, " +
                "monto_pagado_inicial = ?, monto_total = ?, fecha_recojo = ?, observacion = ? " +
                "WHERE id = ?";

        return jdbcTemplate.update(sql,
                ordenPagoRequest.isPagado(),
                ordenPagoRequest.getMedioPago(),
                ordenPagoRequest.getPorcentaje(),
                ordenPagoRequest.getMontoPagado(),
                ordenPagoRequest.getMontoTotal(),
                LocalDate.parse(ordenPagoRequest.getFechaRecojo()),
                ordenPagoRequest.getObservacion(),
                ordenPagoRequest.getId()
        );
    }

    @Override
    public void eliminarDetalleTable(Integer pago) {
        jdbcTemplate.update( "DELETE FROM pago_detalle WHERE pago_id = ?", pago);
    }

    @Override
    public OrdenPagoEditResponse imprimirBoleta(Integer pago) {
        String pagoQuery = "SELECT " +
                "p.id, " +
                "p.codigo, " +
                "CASE " +
                "    WHEN p.pagado = TRUE THEN 'PAGADO' " +
                "    ELSE 'PENDIENTE DE PAGO' " +
                "END AS pagadoTexto, " +
                "CASE " +
                "    WHEN p.entregado = TRUE THEN 'SI' " +
                "    ELSE 'NO' " +
                "END AS entregadoTexto, " +
                "CONCAT(c.apellido_paterno, ' ', c.apellido_materno, ' ', c.nombre) AS cliente, " +
                "mp.descripcion AS medio_pago, " +
                "p.monto_pagado_inicial AS montoPagadoInicial, " +
                "p.monto_total AS montoTotal,p.observacion, " +
                "CONCAT(pe.apellido_paterno, ' ', pe.apellido_materno, ' ', pe.nombre) AS usuario, " +
                "DATE_FORMAT(p.fecha_creacion, '%d/%m/%Y %H:%i:%s') AS fecha_creacion, " +
                "DATE_FORMAT(p.fecha_recojo, '%d/%m/%Y') AS fecha_entrega, " +
                "c.telefono " +
                "FROM pago p " +
                "INNER JOIN cliente c ON p.cliente_id = c.id " +
                "INNER JOIN medio_pago mp ON p.medio_pago_id = mp.id " +
                "INNER JOIN usuario u ON p.usuario_id = u.id " +
                "INNER JOIN persona pe ON u.id = pe.id_usuario " +
                "WHERE p.id = ? AND p.entregado = FALSE AND p.enabled = TRUE";

        String detalleQuery = " SELECT d.subservicio_id cod," +
                " CONCAT(s.descripcion,' / ',sub.descripcion) AS nombre," +
                " d.solo_seleccion," +
                " d.tipo," +
                " d.detalle_tipo," +
                " d.monto," +
                " d.cantidad," +
                " d.monto_total" +
                " FROM pago_detalle d" +
                " INNER JOIN subservicio sub ON d.subservicio_id=sub.id" +
                " INNER JOIN servicio s ON sub.servicio_id=s.id" +
                " WHERE d.pago_id=? AND D.enabled=true ORDER BY d.id ASC";

        OrdenPagoEditResponse ordenPago = jdbcTemplate.queryForObject(
                pagoQuery,
                BeanPropertyRowMapper.newInstance(OrdenPagoEditResponse.class),
                pago
        );

        List<ServicioPagoEditResponse> detalles = jdbcTemplate.query(
                detalleQuery,
                BeanPropertyRowMapper.newInstance(ServicioPagoEditResponse.class),
                pago
        );
        ordenPago.setPago(detalles);
        return ordenPago;
    }

}

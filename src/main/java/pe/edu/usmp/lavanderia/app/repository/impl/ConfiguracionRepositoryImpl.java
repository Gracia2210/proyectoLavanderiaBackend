package pe.edu.usmp.lavanderia.app.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import pe.edu.usmp.lavanderia.app.repository.ConfiguracionRepository;
import pe.edu.usmp.lavanderia.app.request.EditarConfiguracionRequest;
import pe.edu.usmp.lavanderia.app.request.EditarSecuenciaRequest;
import pe.edu.usmp.lavanderia.app.response.ConfiguracionResponse;
import pe.edu.usmp.lavanderia.app.response.ListaSecuenciaResponse;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
@Repository
public class ConfiguracionRepositoryImpl extends JdbcDaoSupport implements ConfiguracionRepository {

    @Autowired
    private ApplicationContext context;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void DataSource(DataSource setDataSource) {
        setDataSource(setDataSource);
        this.jdbcTemplate = context.getBean("db_desa", JdbcTemplate.class);
    }

    @Override
    public List<ConfiguracionResponse> listarConfiguracion() {
        String sql = "SELECT id,nombre,descripcion,direccion,telefono,nombre_imagen FROM configuracion_sistema";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ConfiguracionResponse.class));
    }

    @Override
    public void editarConfiguracion(EditarConfiguracionRequest datos) {
        if(datos.getImagen()!=null) {
            String sql = "UPDATE configuracion_sistema SET nombre = ?, descripcion = ? , direccion = ?, telefono = ? , imagen = ? , nombre_imagen = ? , tipo_imagen= ?  WHERE id = ?";
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, datos.getNombre());
                ps.setString(2, datos.getDescripcion());
                ps.setString(3, datos.getDireccion());
                ps.setString(4, datos.getTelefono());
                ps.setBytes(5, datos.getImagen());
                ps.setString(6, datos.getNombreImagen());
                ps.setString(7, datos.getExtensionImagen());
                ps.setLong(8, datos.getId());
                return ps;
            });
        }
        else{
            String sql = "UPDATE configuracion_sistema SET nombre = ? , descripcion = ? , direccion = ?, telefono = ?   WHERE id = ?";
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, datos.getNombre());
                ps.setString(2, datos.getDescripcion());
                ps.setString(3, datos.getDireccion());
                ps.setString(4, datos.getTelefono());
                ps.setLong(5, datos.getId());
                return ps;
            });
        }


    }

    @Override
    public byte[] obtenerImagen(Long idArchivo) {
        try {
            String sql = "SELECT imagen FROM configuracion_sistema WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{idArchivo}, byte[].class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<ListaSecuenciaResponse> listarSecuencia() {
        String sql = "SELECT s.id id_serie,s.valor_actual serie,c.id id_secuencia,c.valor_actual secuencia  FROM contador_serie s,contador_secuencia c";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ListaSecuenciaResponse.class));
    }

    @Override
    public Integer obtenerUltimaSecuenciaPago() {
        String sql = "SELECT secuencia FROM pago ORDER BY id DESC LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public String obtenerUltimaSeriePago() {
        String sql = "SELECT serie FROM pago ORDER BY id DESC LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(sql, String.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void editarSecuencia(EditarSecuenciaRequest datos) {
        jdbcTemplate.update("UPDATE contador_serie SET valor_actual = ? WHERE id = 1",datos.getSerie());
        jdbcTemplate.update("UPDATE contador_secuencia SET valor_actual = ? WHERE id = 1",datos.getSecuencia());
    }
}

package pe.edu.usmp.lavanderia.app.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import pe.edu.usmp.lavanderia.app.repository.SubservicioRepository;
import pe.edu.usmp.lavanderia.app.response.SubservicioResponse;

import javax.sql.DataSource;
import java.util.List;
@Repository
public class SubservicioRepositoryImpl extends JdbcDaoSupport implements SubservicioRepository {

    @Autowired
    private ApplicationContext context;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void DataSource(DataSource setDataSource) {
        setDataSource(setDataSource);
        this.jdbcTemplate = context.getBean("db_desa", JdbcTemplate.class);
    }

    @Override
    public List<SubservicioResponse> listarSubserviciosPorServicioId(int servicioId) {
        String sql = "SELECT id, descripcion, monto, servicio_id FROM subservicio WHERE servicio_id = ? AND enabled = TRUE";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(SubservicioResponse.class), servicioId);
    }
    @Override
    public int insertarSubservicio(SubservicioResponse subservicio) {
        String sql = "INSERT INTO subservicio (descripcion, monto, servicio_id, enabled) VALUES (?, ?, ?, TRUE)";
        return jdbcTemplate.update(sql, subservicio.getDescripcion(), subservicio.getMonto(), subservicio.getServicioId());
    }

    @Override
    public int editarSubservicio(SubservicioResponse subservicio) {
        String sql = "UPDATE subservicio SET descripcion = ?, monto = ?, servicio_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql, subservicio.getDescripcion(), subservicio.getMonto(), subservicio.getServicioId(), subservicio.getId());
    }

    @Override
    public SubservicioResponse buscarPorId(int id) {
        String sql = "SELECT id, descripcion, monto, servicio_id FROM subservicio WHERE id = ? AND enabled = TRUE";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(SubservicioResponse.class), id);
    }

    @Override
    public int eliminarSubservicioPorId(int id) {
        String sql = "UPDATE subservicio SET enabled = FALSE WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

}

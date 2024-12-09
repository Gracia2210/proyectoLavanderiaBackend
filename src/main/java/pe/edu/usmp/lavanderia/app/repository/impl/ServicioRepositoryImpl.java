package pe.edu.usmp.lavanderia.app.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import pe.edu.usmp.lavanderia.app.repository.ServicioRepository;
import pe.edu.usmp.lavanderia.app.response.ServicioResponse;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ServicioRepositoryImpl extends JdbcDaoSupport implements ServicioRepository {
    @Autowired
    private ApplicationContext context;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void DataSource(DataSource setDataSource) {
        setDataSource(setDataSource);
        this.jdbcTemplate = context.getBean("db_desa", JdbcTemplate.class);
    }
    @Override
    public List<ServicioResponse> listarServicios() {
        String sql = "SELECT id, descripcion FROM servicio WHERE enabled = TRUE";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ServicioResponse.class));
    }
    @Override
    public int insertarServicio(String descripcion) {
        String sql = "INSERT INTO servicio (descripcion, enabled) VALUES (?, TRUE)";
        return jdbcTemplate.update(sql, descripcion);
    }

    @Override
    public int editarServicio(int id, String descripcion) {
        String sql = "UPDATE servicio SET descripcion = ? WHERE id = ?";
        return jdbcTemplate.update(sql, descripcion, id);
    }

    @Override
    public ServicioResponse buscarPorId(int id) {
        String sql = "SELECT id, descripcion FROM servicio WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(ServicioResponse.class), id);
    }

    @Override
    public int eliminarServicioPorId(int id) {
        String sql = "UPDATE servicio SET enabled = FALSE WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

}

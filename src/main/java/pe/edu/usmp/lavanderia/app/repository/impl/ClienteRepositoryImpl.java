package pe.edu.usmp.lavanderia.app.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import pe.edu.usmp.lavanderia.app.repository.ClienteRepository;
import pe.edu.usmp.lavanderia.app.request.BusquedaClienteRequest;
import pe.edu.usmp.lavanderia.app.response.ClienteResponse;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
@SuppressWarnings("deprecation")
public class ClienteRepositoryImpl  extends JdbcDaoSupport implements ClienteRepository {

    @Autowired
    private ApplicationContext context;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void DataSource(DataSource setDataSource) {
        setDataSource(setDataSource);
        this.jdbcTemplate = context.getBean("db_desa", JdbcTemplate.class);
    }


    @Override
    public List<ClienteResponse> listarClientes() {
        String sql = "SELECT id, doc_iden, nombre, apellido_paterno, apellido_materno, telefono, email FROM cliente WHERE enabled=TRUE";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ClienteResponse.class));
    }

    @Override
    public int actualizarCliente(ClienteResponse cliente) {
        String sql = "UPDATE cliente SET doc_iden = ?, nombre = ?, apellido_paterno = ?, apellido_materno = ?, telefono = ?, email = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                cliente.getDocIden(),
                cliente.getNombre(),
                cliente.getApellidoPaterno(),
                cliente.getApellidoMaterno(),
                cliente.getTelefono(),
                cliente.getEmail(),
                cliente.getId());
    }

    @Override
    public int insertarCliente(ClienteResponse cliente) {
        String sql = "INSERT INTO cliente (doc_iden, nombre, apellido_paterno, apellido_materno, telefono, email) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                cliente.getDocIden(),
                cliente.getNombre(),
                cliente.getApellidoPaterno(),
                cliente.getApellidoMaterno(),
                cliente.getTelefono(),
                cliente.getEmail());
        String idSql = "SELECT LAST_INSERT_ID()";
        return jdbcTemplate.queryForObject(idSql, Integer.class);
    }

    @Override
    public ClienteResponse buscarPorId(int id) {
        String sql = "SELECT id, doc_iden, nombre, apellido_paterno, apellido_materno, telefono, email FROM cliente WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(ClienteResponse.class), id);
    }

    @Override
    public int eliminarClientePorId(int id) {
        String sql = "UPDATE cliente SET enabled=FALSE WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<ClienteResponse> buscarClientes(BusquedaClienteRequest request) {
        StringBuilder sql = new StringBuilder("SELECT id, doc_iden, nombre, apellido_paterno, apellido_materno, telefono, email FROM cliente WHERE enabled=TRUE");
        List<Object> params = new ArrayList<>();

        if (request.getTipo() == 1) {
            if (request.getNombre() != null && !request.getNombre().isEmpty()) {
                sql.append("AND nombre LIKE ? ");
                params.add("%" + request.getNombre() + "%");
            }
            if (request.getPaterno() != null && !request.getPaterno().isEmpty()) {
                sql.append("AND apellido_paterno LIKE ? ");
                params.add("%" + request.getPaterno() + "%");
            }
            if (request.getMaterno() != null && !request.getMaterno().isEmpty()) {
                sql.append("AND apellido_materno LIKE ? ");
                params.add("%" + request.getMaterno() + "%");
            }
        } else if (request.getTipo() == 2 && request.getDni() != null && !request.getDni().isEmpty()) {
            sql.append("AND doc_iden = ? ");
            params.add(request.getDni());
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), BeanPropertyRowMapper.newInstance(ClienteResponse.class));
    }

    @Override
    public boolean dniYaRegistrado(String dni, int id) {
        try {
            String sql;
            Integer count = 0;

            if (dni != null) {
                sql = "SELECT COUNT(*) FROM cliente WHERE doc_iden = ? and id <> ? and enabled=TRUE";
                count = jdbcTemplate.queryForObject(sql, new Object[] { dni,id }, Integer.class);
            } else {
                sql = "SELECT COUNT(*) FROM cliente WHERE doc_iden = ? and enabled=TRUE";
                count = jdbcTemplate.queryForObject(sql, new Object[] { dni }, Integer.class);
            }

            return count != null && count > 0;
        } catch (EmptyResultDataAccessException e) {
            return false;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }
    }

}

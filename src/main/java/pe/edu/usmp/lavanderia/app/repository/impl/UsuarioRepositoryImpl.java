package pe.edu.usmp.lavanderia.app.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import pe.edu.usmp.lavanderia.app.config.Auth;
import pe.edu.usmp.lavanderia.app.model.Persona;
import pe.edu.usmp.lavanderia.app.model.Rol;
import pe.edu.usmp.lavanderia.app.model.Usuario;
import pe.edu.usmp.lavanderia.app.repository.UsuarioRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import pe.edu.usmp.lavanderia.app.request.ConsultaRequest;

@Repository
public class UsuarioRepositoryImpl extends JdbcDaoSupport implements UsuarioRepository {

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

	@SuppressWarnings("deprecation")
	public Usuario findByUsername(String username) {
		String sql = "SELECT * FROM usuario WHERE username = ?";
		Usuario usuario = null;
		try {
			usuario = jdbcTemplate.queryForObject(sql, new Object[]{username}, this::mapUsuario);
			;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return usuario;
	}

	private Usuario mapUsuario(ResultSet rs, int rowNum) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setId(rs.getLong("id"));
		usuario.setUsername(rs.getString("username"));
		usuario.setPassword(rs.getString("password"));
		usuario.setEnabled(rs.getBoolean("enabled"));

		// Obtener los roles del usuario
		usuario.setRoles(findRolesByUsuarioId(usuario.getId()));

		return usuario;
	}

	@SuppressWarnings("deprecation")
	private List<Rol> findRolesByUsuarioId(Long usuarioId) {
		String sql = "SELECT r.* FROM rol r " + "INNER JOIN usuario_rol ur ON r.id = ur.rol_id "
				+ "WHERE ur.usuario_id = ?";
		return jdbcTemplate.query(sql, new Object[]{usuarioId}, this::mapRol);
	}

	private Rol mapRol(ResultSet rs, int rowNum) throws SQLException {
		Rol rol = new Rol();
		rol.setId(rs.getLong("id"));
		rol.setNombre(rs.getString("nombre"));
		rol.setDescripcion(rs.getString("descripcion"));
		return rol;
	}

	@Override
	@SuppressWarnings("deprecation")
	public Persona buscarPersonaBPorUsername(String username) {
		String sql = "SELECT u.username, a.*,u.id as idUsuario FROM persona a JOIN usuario u ON a.id_usuario = u.id  WHERE u.username = ?";

		return jdbcTemplate.queryForObject(sql, new Object[]{username}, this::mapPersona);
	}

	private Persona mapPersona(ResultSet rs, int rowNum) throws SQLException {
		Persona persona = new Persona();
		persona.setId(rs.getLong("id"));
		persona.setUsuario(rs.getString("username"));
		persona.setNombre(rs.getString("nombre"));
		persona.setApellidoPaterno(rs.getString("apellido_paterno"));
		persona.setApellidoMaterno(rs.getString("apellido_materno"));
		persona.setSexo(rs.getString("sexo"));
		persona.setEmail(rs.getString("email"));
		persona.setTelefono(rs.getString("telefono"));
		return persona;
	}
}
package pe.edu.usmp.lavanderia.app.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import pe.edu.usmp.lavanderia.app.repository.AdminRepository;
import pe.edu.usmp.lavanderia.app.request.CreaModiRolRequest;
import pe.edu.usmp.lavanderia.app.request.CreaModiUsuarioRequest;
import pe.edu.usmp.lavanderia.app.request.ListarUsuarioRequest;
import pe.edu.usmp.lavanderia.app.response.CodNombreResponse;
import pe.edu.usmp.lavanderia.app.response.PersonaResponse;
import pe.edu.usmp.lavanderia.app.response.RolesResponse;
import pe.edu.usmp.lavanderia.app.utils.UtilResource;

import java.util.ArrayList;
import java.util.List;

@Repository
@SuppressWarnings("deprecation")
public class AdminRepositoryImpl extends JdbcDaoSupport implements AdminRepository {

	@Autowired
	private ApplicationContext context;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void DataSource(DataSource setDataSource) {
		setDataSource(setDataSource);
		this.jdbcTemplate = context.getBean("db_desa", JdbcTemplate.class);
	}

	@Override
	public List<PersonaResponse> listarUsuarios(ListarUsuarioRequest datos) {
		StringBuilder sql = new StringBuilder(
				"SELECT u.username as usuario,u.id as usuario_id, p.id, p.nombre, p.apellido_paterno, p.apellido_materno, p.sexo, p.email, p.telefono FROM persona p INNER JOIN usuario u ON p.id_usuario = u.id  WHERE EXISTS (SELECT 1 FROM usuario_rol ur WHERE ur.usuario_id = u.id)");

		List<Object> params = new ArrayList<>();

		if (datos.getNombre() != null && !datos.getNombre().isEmpty()) {
			sql.append(" AND p.nombre LIKE ?");
			params.add("%" + datos.getNombre() + "%");
		}

		if (datos.getApellidoPaterno() != null && !datos.getApellidoPaterno().isEmpty()) {
			sql.append(" AND p.apellido_paterno LIKE ?");
			params.add("%" + datos.getApellidoPaterno() + "%");
		}

		if (datos.getApellidoMaterno() != null && !datos.getApellidoMaterno().isEmpty()) {
			sql.append(" AND p.apellido_materno LIKE ?");
			params.add("%" + datos.getApellidoMaterno() + "%");
		}

		if (datos.getUsuario() != null && !datos.getUsuario().isEmpty()) {
			sql.append(" AND u.username LIKE ?");
			params.add("%" + datos.getUsuario() + "%");
		}

		return jdbcTemplate.query(sql.toString(), params.toArray(),
				BeanPropertyRowMapper.newInstance(PersonaResponse.class));
	}

	@Override
	public PersonaResponse buscarPersona(Long idPersona) {
		try {
			String sql = "SELECT  u.username as usuario, u.id as usuario_id, p.id, p.nombre, p.apellido_paterno, p.apellido_materno, p.sexo, p.email, p.telefono FROM persona p INNER JOIN usuario u ON p.id_usuario = u.id WHERE EXISTS (SELECT 1 FROM usuario_rol ur WHERE ur.usuario_id = u.id) AND p.id = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { idPersona },
					BeanPropertyRowMapper.newInstance(PersonaResponse.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void crearUsuario(CreaModiUsuarioRequest datos) {
		System.out.println("Usuario: "+datos.getUsuario());
		System.out.println("Contraseña: "+UtilResource.contrasenaVacia(datos.getPassword()));
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(UtilResource.contrasenaVacia(datos.getPassword()));
		String sqlUsuario = "INSERT INTO usuario (username, password, enabled) VALUES (?, ?, ?)";
		jdbcTemplate.update(sqlUsuario, datos.getUsuario(), hashedPassword, true);
		Long usuarioId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

		String sqlPersona = "INSERT INTO persona (nombre, apellido_paterno, apellido_materno, sexo, email, telefono, id_usuario) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlPersona, datos.getNombre(), datos.getApellidoPaterno(), datos.getApellidoMaterno(),
				datos.getSexo(), datos.getEmail(), datos.getTelefono(),
				usuarioId);

		// Insertar roles en la tabla `usuario_rol`
		String sqlUsuarioRol = "INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (?, ?)";
		for (CreaModiRolRequest rol : datos.getRoles()) {
			jdbcTemplate.update(sqlUsuarioRol, usuarioId, rol.getId());
		}
	}

	@Override
	public void editarUsuario(CreaModiUsuarioRequest datos) {
		if (datos.getPassword() != null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(datos.getPassword());
			String sqlUpdateUsuarioPass = "UPDATE usuario SET username = ?, password = ? WHERE id = ?";
			jdbcTemplate.update(sqlUpdateUsuarioPass, datos.getUsuario(), hashedPassword,datos.getUsuario_id());
			System.out.println("Se actualiza la contraseña");
		} else {
			String sqlUpdateUsuarioNoPass = "UPDATE usuario SET username = ?  WHERE id = ?";
			jdbcTemplate.update(sqlUpdateUsuarioNoPass, datos.getUsuario(),datos.getUsuario_id());
		}

		String sqlUpdateAlumno = "UPDATE persona SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, sexo = ?, "
				+ " email = ?, telefono = ? WHERE id_usuario = ?";
		jdbcTemplate.update(sqlUpdateAlumno, datos.getNombre(), datos.getApellidoPaterno(), datos.getApellidoMaterno(),
				datos.getSexo(), datos.getEmail(), datos.getTelefono(),
				Long.parseLong(datos.getId()));
		String sqlDeleteRoles = "DELETE FROM usuario_rol WHERE usuario_id = ?";
		jdbcTemplate.update(sqlDeleteRoles, Long.parseLong(datos.getId()));
		String sqlInsertRol = "INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (?, ?)";
		for (CreaModiRolRequest rol : datos.getRoles()) {
			jdbcTemplate.update(sqlInsertRol, Long.parseLong(datos.getId()), rol.getId());
		}
	}

	@Override
	public boolean esUsernameUsado(String username, Long usuario) {
		try {
			String sql = "";
			Integer count = 0;

			if (usuario != null) {
				sql = "SELECT COUNT(*) FROM usuario WHERE username = ? and id <> ?";
				count = jdbcTemplate.queryForObject(sql, new Object[] { username,usuario }, Integer.class);
			} else {
				sql = "SELECT COUNT(*) FROM usuario WHERE username = ?";
				count = jdbcTemplate.queryForObject(sql, new Object[] { username }, Integer.class);
			}

			return count != null && count > 0;
		} catch (EmptyResultDataAccessException e) {
			return false;
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		}
	}

	@Override
	public void eliminarUsuario(Long usuarioId) {
		// Eliminar roles del usuario en la tabla usuario_rol
		String sqlDeleteRoles = "DELETE FROM usuario_rol WHERE usuario_id = ?";
		jdbcTemplate.update(sqlDeleteRoles, usuarioId);

		// Eliminar la información de la persona asociada al usuario
		String sqlDeletePersona = "DELETE FROM persona WHERE id_usuario = ?";
		jdbcTemplate.update(sqlDeletePersona, usuarioId);

		// Eliminar el usuario en la tabla usuario
		String sqlDeleteUsuario = "DELETE FROM usuario WHERE id = ?";
		jdbcTemplate.update(sqlDeleteUsuario, usuarioId);
	}

	@Override
	public List<RolesResponse> listarRoles(Long usuario) {
		return jdbcTemplate.query("SELECT rol_id as id FROM usuario_rol where usuario_id=?", new Object[] { usuario },
				BeanPropertyRowMapper.newInstance(RolesResponse.class));
	}

	@Override
	public List<CodNombreResponse> listarCategoria() {
		return jdbcTemplate.query("SELECT id cod,nombre FROM categoria ORDER BY id asc", BeanPropertyRowMapper.newInstance(CodNombreResponse.class));
	}
}

package pe.edu.usmp.lavanderia.app.repository;


import pe.edu.usmp.lavanderia.app.model.Persona;
import pe.edu.usmp.lavanderia.app.model.Usuario;
import pe.edu.usmp.lavanderia.app.request.ConsultaRequest;

public interface UsuarioRepository {

	Usuario findByUsername(String username);
	
	Persona buscarPersonaBPorUsername(String username);
}

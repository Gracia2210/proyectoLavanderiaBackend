package pe.edu.usmp.lavanderia.app.service;

import pe.edu.usmp.lavanderia.app.model.Persona;
import pe.edu.usmp.lavanderia.app.model.Usuario;

public interface UsuarioService {
	
	Usuario findByUsername(String username);
	
	Persona buscarPersonaBPorUsername(String username);

}

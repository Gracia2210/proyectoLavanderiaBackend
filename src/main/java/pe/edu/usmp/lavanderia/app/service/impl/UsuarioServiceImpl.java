package pe.edu.usmp.lavanderia.app.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.usmp.lavanderia.app.model.Persona;
import pe.edu.usmp.lavanderia.app.model.Usuario;
import pe.edu.usmp.lavanderia.app.repository.UsuarioRepository;
import pe.edu.usmp.lavanderia.app.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repo;

	@Override
	public Usuario findByUsername(String username) {
		return repo.findByUsername(username);
	}

	@Override
	public Persona buscarPersonaBPorUsername(String username) {
		return repo.buscarPersonaBPorUsername(username);
	}

}

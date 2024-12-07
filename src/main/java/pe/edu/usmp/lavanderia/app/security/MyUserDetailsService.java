package pe.edu.usmp.lavanderia.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.usmp.lavanderia.app.model.Usuario;
import pe.edu.usmp.lavanderia.app.service.UsuarioService;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.findByUsername(username);
		return new MyUserDetails(usuario);
	}
}
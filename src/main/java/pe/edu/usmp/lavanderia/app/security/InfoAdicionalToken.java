package pe.edu.usmp.lavanderia.app.security;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import pe.edu.usmp.lavanderia.app.model.Persona;
import pe.edu.usmp.lavanderia.app.service.UsuarioService;



@Component
public class InfoAdicionalToken implements TokenEnhancer {
	@Autowired
	private UsuarioService us;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<String, Object>();
		Persona usuario = us.buscarPersonaBPorUsername(authentication.getName());
		info.put("idPersona", usuario.getId());
		info.put("usuario", usuario.getUsuario());
		info.put("nombre_completo",usuario.getApellidoPaterno()+" "+usuario.getApellidoMaterno()+" "+usuario.getNombre());
		info.put("nombre", usuario.getNombre());
		info.put("paterno", usuario.getApellidoPaterno());
		info.put("materno", usuario.getApellidoMaterno());
		info.put("sexo", usuario.getSexo());
		info.put("telefono", usuario.getTelefono());
		info.put("email", usuario.getEmail());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}
}

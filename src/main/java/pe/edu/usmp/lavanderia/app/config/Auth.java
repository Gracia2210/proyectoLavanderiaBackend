package pe.edu.usmp.lavanderia.app.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import org.json.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import pe.edu.usmp.lavanderia.app.model.AuthModel;

@Service
public class Auth {
	@Autowired(required = false)
	private TokenStore tokenstore;

	public AuthModel usuario() {
		String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
				.getHeader("Authorization").replace("Bearer ", "").trim();

		Map<String, Object> details = tokenstore.readAccessToken(token).getAdditionalInformation();
		JSONObject jsonObj = new JSONObject(details);
		AuthModel usuario = new AuthModel();
		usuario.setIdPersona(jsonObj.getLong("idPersona"));
		usuario.setUsuario(getOrNull(jsonObj, "usuario"));
		usuario.setNombre_completo(getOrNull(jsonObj, "nombre_completo"));
		usuario.setNombre(getOrNull(jsonObj, "nombre"));
		usuario.setPaterno(getOrNull(jsonObj, "paterno"));
		usuario.setMaterno(getOrNull(jsonObj, "materno"));
		usuario.setSexo(getOrNull(jsonObj, "sexo"));
		usuario.setTelefono(getOrNull(jsonObj, "telefono"));
		usuario.setEmail(getOrNull(jsonObj, "email"));
		usuario.setIdUsuario(jsonObj.getLong("idUsuario"));

		return usuario;
	}

	private static String getOrNull(JSONObject jsonObj, String key) {
		return jsonObj.optString(key, null);
	}

	public Map<String, Object> getDetail(OAuth2Authentication authentication) {
		OAuth2AuthenticationDetails oauth2authenticationdetails = (OAuth2AuthenticationDetails) authentication
				.getDetails();
		Map<String, Object> details = tokenstore.readAccessToken(oauth2authenticationdetails.getTokenValue())
				.getAdditionalInformation();
		return details;
	}
	public Long idUsuario(){return usuario().getIdUsuario();};
	public Long IdPersona() {
		return usuario().getIdPersona();
	}
	public String nombreCompleto() {
		return usuario().getNombre()+" "+usuario().getPaterno()+" "+usuario().getMaterno();
	}

}

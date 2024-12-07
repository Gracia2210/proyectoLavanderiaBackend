package pe.edu.usmp.lavanderia.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.usmp.lavanderia.app.request.CreaModiUsuarioMasivoRequest;
import pe.edu.usmp.lavanderia.app.request.CreaModiUsuarioRequest;
import pe.edu.usmp.lavanderia.app.request.ListarUsuarioRequest;
import pe.edu.usmp.lavanderia.app.response.*;
import pe.edu.usmp.lavanderia.app.service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService se;

	@RequestMapping(value = "listarUsuarios", method = RequestMethod.POST)
	ListModelResponse<PersonaResponse> listarUsuarios(@RequestBody ListarUsuarioRequest datos) {
		return se.listarUsuarios(datos);
	}

	@RequestMapping(value = "buscarPersona", method = RequestMethod.GET)
	public ModelResponse<PersonaResponse> buscarPersona(@RequestParam Long idPersona) {
		return se.buscarPersona(idPersona);
	}

	@RequestMapping(value = "crearUsuario", method = RequestMethod.POST)
	public MsgResponse crearUsuario(@RequestBody CreaModiUsuarioRequest datos) {
		return se.crearUsuario(datos);
	}

	@RequestMapping(value = "editarUsuario", method = RequestMethod.POST)
	public MsgResponse editarUsuario(@RequestBody CreaModiUsuarioRequest datos) {
		return se.editarUsuario(datos);
	}

	@RequestMapping(value = "eliminarUsuario/{usuarioId}", method = RequestMethod.POST)
	public MsgResponse eliminarUsuario(@PathVariable Long usuarioId) {
		return se.eliminarUsuario(usuarioId);
	}
	
	@RequestMapping(value = "crearUsuariosMasivo", method = RequestMethod.POST)
	public MsgResponse crearUsuariosMasivo(@RequestBody CreaModiUsuarioMasivoRequest datos) {
		return se.crearUsuariosMasivo(datos);
	}
	@RequestMapping(value = "listarCategoria", method = RequestMethod.GET)
	public List<CodNombreResponse> listarCategoria() {
		return se.listarCategoria();
	}
}

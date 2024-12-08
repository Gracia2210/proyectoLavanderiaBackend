package pe.edu.usmp.lavanderia.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.usmp.lavanderia.app.repository.AdminRepository;
import pe.edu.usmp.lavanderia.app.request.CreaModiUsuarioMasivoRequest;
import pe.edu.usmp.lavanderia.app.request.CreaModiUsuarioRequest;
import pe.edu.usmp.lavanderia.app.request.ListarUsuarioRequest;
import pe.edu.usmp.lavanderia.app.response.*;
import pe.edu.usmp.lavanderia.app.service.AdminService;
import pe.edu.usmp.lavanderia.app.utils.Constantes;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository repo;

	@Override
	public ListModelResponse<PersonaResponse> listarUsuarios(ListarUsuarioRequest datos) {
		ListModelResponse<PersonaResponse> resp = new ListModelResponse<>();
		List<PersonaResponse> lista = repo.listarUsuarios(datos);
		if (!lista.isEmpty()) {
			resp.setCod(Constantes.SUCCESS_COD);
			resp.setIcon(Constantes.ICON_SUCCESS);
			resp.setMensaje("Se ha encontrado usuarios");
			resp.setList(lista);
		} else {
			resp.setCod(Constantes.NULL_COD);
			resp.setIcon(Constantes.ICON_INFO);
			;
			resp.setMensaje("No se han encontrado usuarios");
		}
		return resp;
	}

	@Override
	public ModelResponse<PersonaResponse> buscarPersona(Long idPersona) {
		ModelResponse<PersonaResponse> resp = new ModelResponse<>();
		PersonaResponse model = repo.buscarPersona(idPersona);
		if (model != null) {
			resp.setCod(Constantes.SUCCESS_COD);
			resp.setIcon(Constantes.ICON_SUCCESS);
			;
			resp.setMensaje("Se ha encontrado al usuario");
			model.setRoles(repo.listarRoles(model.getUsuario_id()));
			resp.setModel(model);
		} else {
			resp.setMensaje("No se ha encontrado al usuario");
		}
		return resp;
	}

	@Override
	public MsgResponse crearUsuario(CreaModiUsuarioRequest datos) {
		MsgResponse resp = new MsgResponse();

		if (repo.esUsernameUsado(datos.getUsuario(), datos.getUsuario_id())) {
			resp.setIcon(Constantes.ICON_INFO);
			resp.setMensaje("El usuario " + datos.getUsuario() + " ya existe");
		} else {
			repo.crearUsuario(datos);
			resp.setCod(Constantes.SUCCESS_COD);
			resp.setIcon(Constantes.ICON_SUCCESS);
			;
			resp.setMensaje("Se ha registrado al usuario satifactoriamente");
		}
		return resp;
	}

	@Override
	public MsgResponse editarUsuario(CreaModiUsuarioRequest datos) {
		MsgResponse resp = new MsgResponse();
		if (repo.esUsernameUsado(datos.getUsuario(), datos.getUsuario_id())) {
			resp.setIcon(Constantes.ICON_INFO);
			resp.setMensaje("El usuario " + datos.getUsuario() + " ya existe");
		} else {
			repo.editarUsuario(datos);
			resp.setCod(Constantes.SUCCESS_COD);
			resp.setIcon(Constantes.ICON_SUCCESS);
			resp.setMensaje("Se ha modificado al usuario satifactoriamente");
		}
		return resp;
	}

	@Override
	public MsgResponse eliminarUsuario(Long usuarioId) {
		MsgResponse resp = new MsgResponse();
		repo.eliminarUsuario(usuarioId);
		resp.setCod(Constantes.SUCCESS_COD);
		resp.setIcon(Constantes.ICON_INFO);
		;
		resp.setMensaje("Se ha eliminado al usuario satifactoriamente");
		return resp;
	}

	@Override
	public MsgResponse crearUsuariosMasivo(CreaModiUsuarioMasivoRequest datos) {

		MsgResponse resp = new MsgResponse();

		// VALIDARMOS QUE TODOS LOS DATOS SEAN CORRECTOS
		Boolean validError = false;
		String txtError = "";
		for (CreaModiUsuarioRequest valid : datos.getLista()) {
			if (repo.esUsernameUsado(valid.getUsuario(), valid.getUsuario_id())) {
				validError = true;
				txtError = "El usuario " + valid.getUsuario() + " de la fila " + valid.getFila()
						+ " ya se encuentra registrado";
				break;
			}

		}
		if (validError) {
			resp.setMensaje(txtError);
			resp.setMensajeTxt("Por favor validar que todos los datos sean correctos");
		} else {
			//SI TODO ESTA CORRECTO SE CREA UNO POR UNO
			for (CreaModiUsuarioRequest crea : datos.getLista()) {
				repo.crearUsuario(crea);
			}
			resp.setCod(Constantes.SUCCESS_COD);
			resp.setIcon(Constantes.ICON_SUCCESS);
			String titulo=datos.getLista().size()==1?"Se ha registrado el usuario satisfactoriamente":"Se han registrado los usuarios satisfactoriamente";
			resp.setMensaje(titulo);
		}
		return resp;
	}

	@Override
	public List<CodNombreResponse> listarCategoria() {
		return repo.listarCategoria();
	}
}

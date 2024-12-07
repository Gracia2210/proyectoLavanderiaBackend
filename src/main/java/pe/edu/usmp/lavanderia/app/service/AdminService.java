package pe.edu.usmp.lavanderia.app.service;
import pe.edu.usmp.lavanderia.app.request.CreaModiUsuarioMasivoRequest;
import pe.edu.usmp.lavanderia.app.request.CreaModiUsuarioRequest;
import pe.edu.usmp.lavanderia.app.request.ListarUsuarioRequest;
import pe.edu.usmp.lavanderia.app.response.*;

import java.util.List;

public interface AdminService {

    ListModelResponse<PersonaResponse> listarUsuarios(ListarUsuarioRequest datos);

    ModelResponse<PersonaResponse> buscarPersona(Long idPersona);

    MsgResponse crearUsuario(CreaModiUsuarioRequest datos);

    MsgResponse editarUsuario(CreaModiUsuarioRequest datos);

    MsgResponse eliminarUsuario(Long usuarioId);

    MsgResponse crearUsuariosMasivo(CreaModiUsuarioMasivoRequest datos);

    List<CodNombreResponse> listarCategoria();
}

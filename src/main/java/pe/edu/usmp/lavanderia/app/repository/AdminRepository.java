package pe.edu.usmp.lavanderia.app.repository;

import pe.edu.usmp.lavanderia.app.request.CreaModiUsuarioRequest;
import pe.edu.usmp.lavanderia.app.request.ListarUsuarioRequest;
import pe.edu.usmp.lavanderia.app.response.CodNombreResponse;
import pe.edu.usmp.lavanderia.app.response.PersonaResponse;
import pe.edu.usmp.lavanderia.app.response.RolesResponse;

import java.util.List;

public interface AdminRepository {

    List<PersonaResponse> listarUsuarios(ListarUsuarioRequest datos);

    PersonaResponse buscarPersona(Long idPersona);

    void crearUsuario(CreaModiUsuarioRequest datos);
    
    void editarUsuario(CreaModiUsuarioRequest datos);

    boolean esUsernameUsado(String username,Long usuario);

    void eliminarUsuario(Long usuarioId);

    List<RolesResponse> listarRoles(Long usuario);

    List<CodNombreResponse> listarCategoria();


}

package pe.edu.usmp.lavanderia.app.repository;

import pe.edu.usmp.lavanderia.app.request.EditarConfiguracionRequest;
import pe.edu.usmp.lavanderia.app.response.ConfiguracionResponse;

import java.util.List;

public interface ConfiguracionRepository {

    List<ConfiguracionResponse> listarConfiguracion();
    void editarConfiguracion(EditarConfiguracionRequest datos);
    byte[] obtenerImagen(Long idArchivo);

}

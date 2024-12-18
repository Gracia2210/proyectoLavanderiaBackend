package pe.edu.usmp.lavanderia.app.repository;

import pe.edu.usmp.lavanderia.app.request.EditarConfiguracionRequest;
import pe.edu.usmp.lavanderia.app.request.EditarSecuenciaRequest;
import pe.edu.usmp.lavanderia.app.response.ConfiguracionResponse;
import pe.edu.usmp.lavanderia.app.response.ListaSecuenciaResponse;

import java.util.List;

public interface ConfiguracionRepository {

    List<ConfiguracionResponse> listarConfiguracion();
    void editarConfiguracion(EditarConfiguracionRequest datos);
    byte[] obtenerImagen(Long idArchivo);
    List<ListaSecuenciaResponse> listarSecuencia();
    Integer obtenerUltimaSecuenciaPago();
    String obtenerUltimaSeriePago();
    void editarSecuencia(EditarSecuenciaRequest datos);

}

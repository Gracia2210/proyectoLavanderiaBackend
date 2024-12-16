package pe.edu.usmp.lavanderia.app.service;

import org.springframework.web.multipart.MultipartFile;
import pe.edu.usmp.lavanderia.app.response.*;

public interface ConfiguracionService {
    ListModelResponse<ConfiguracionResponse> listarConfiguracion();
    MsgResponse editarConfiguracion(Long id, String nombre,String descripcion,String direccion, String telefono, MultipartFile archivo);
    ModelResponse<byte[]> obtenerImagen(Long idArchivo);
    ConfiguracionGlobalResponse configuracionGlobal();
    ModelResponse<ConfiguracionGlobalResponse>  obtenerConfiguracionGlobal();

}

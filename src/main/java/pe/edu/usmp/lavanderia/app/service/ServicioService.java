package pe.edu.usmp.lavanderia.app.service;

import pe.edu.usmp.lavanderia.app.response.ListModelResponse;
import pe.edu.usmp.lavanderia.app.response.ModelResponse;
import pe.edu.usmp.lavanderia.app.response.MsgResponse;
import pe.edu.usmp.lavanderia.app.response.ServicioResponse;

public interface ServicioService {
    ListModelResponse<ServicioResponse> listarServicios();
    ModelResponse<ServicioResponse> insertarServicio(String descripcion);
    MsgResponse editarServicio(int id, String descripcion);
    ModelResponse<ServicioResponse> buscarPorId(int id);
    MsgResponse eliminarServicioPorId(int id);
    MsgResponse activarServicio(int id, Boolean activo);
}

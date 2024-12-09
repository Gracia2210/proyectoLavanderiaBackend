package pe.edu.usmp.lavanderia.app.service;

import pe.edu.usmp.lavanderia.app.response.ListModelResponse;
import pe.edu.usmp.lavanderia.app.response.ModelResponse;
import pe.edu.usmp.lavanderia.app.response.MsgResponse;
import pe.edu.usmp.lavanderia.app.response.SubservicioResponse;

public interface SubservicioService {
    ListModelResponse<SubservicioResponse> listarSubserviciosPorServicioId(int servicioId);
    ModelResponse<SubservicioResponse> insertarSubservicio(SubservicioResponse subservicio);
    MsgResponse editarSubservicio(SubservicioResponse subservicio);
    ModelResponse<SubservicioResponse> buscarPorId(int id);
    MsgResponse eliminarSubservicioPorId(int id);
    MsgResponse activarSubservicio(int id,Boolean activo);
}

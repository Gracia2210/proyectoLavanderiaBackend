package pe.edu.usmp.lavanderia.app.service;

import pe.edu.usmp.lavanderia.app.response.CodNombreResponse;
import pe.edu.usmp.lavanderia.app.response.ListModelResponse;
import pe.edu.usmp.lavanderia.app.response.ListaClientePagoResponse;
import pe.edu.usmp.lavanderia.app.response.SubserviciosPagoResponse;

import java.util.List;

public interface PagoService {
    ListModelResponse<ListaClientePagoResponse> listarPagosxCliente(Integer clienteId) ;
    List<CodNombreResponse> listarServicios();
    List<SubserviciosPagoResponse> listarSubservicios(Integer servicioId);
}

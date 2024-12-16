package pe.edu.usmp.lavanderia.app.service;

import pe.edu.usmp.lavanderia.app.request.OrdenPagoRequest;
import pe.edu.usmp.lavanderia.app.response.*;

import java.util.List;

public interface PagoService {
    ListModelResponse<ListaClientePagoResponse> listarPagosxCliente(Integer clienteId) ;
    List<CodNombreResponse> listarServicios();
    List<SubserviciosPagoResponse> listarSubservicios(Integer servicioId);
    List<CodNombreResponse> listarMedioPagos();
    ModelResponse<Integer> generarBoleta(OrdenPagoRequest request);
    ModelResponse<OrdenPagoEditResponse> obtenerPagoEdit(Integer pago);
    MsgResponse edicionBoleta(OrdenPagoRequest request);
    ModelResponse<String> imprimirBoleta(Integer pago) throws Exception;
}

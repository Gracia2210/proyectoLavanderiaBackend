package pe.edu.usmp.lavanderia.app.service;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.usmp.lavanderia.app.request.OrdenPagoRequest;
import pe.edu.usmp.lavanderia.app.response.*;

import java.util.List;

public interface PagoService {
    ListModelResponse<ListaClientePagoResponse> listarPagosxCliente(Integer clienteId) ;
    List<CodNombreResponse> listarServicios();
    List<SubserviciosPagoResponse> listarSubservicios(Integer servicioId);
    List<CodNombreResponse> listarMedioPagos();
    ModelResponse<String> generarBoleta(OrdenPagoRequest request);
    ModelResponse<OrdenPagoEditResponse> obtenerPagoEdit(@RequestParam Integer pago);
    MsgResponse edicionBoleta(OrdenPagoRequest request);

}

package pe.edu.usmp.lavanderia.app.repository;

import pe.edu.usmp.lavanderia.app.response.CodNombreResponse;
import pe.edu.usmp.lavanderia.app.response.ListaClientePagoResponse;
import pe.edu.usmp.lavanderia.app.response.OrdenPagoResponse;
import pe.edu.usmp.lavanderia.app.response.SubserviciosPagoResponse;

import java.util.List;

public interface PagoRepository {
    List<ListaClientePagoResponse> listarPagosxCliente(Integer clienteId);
    List<CodNombreResponse> listarServicios();
    List<SubserviciosPagoResponse> listarSubservicios(Integer servicioId);
    List<CodNombreResponse> listarMedioPagos();
    int insertarCabeceraBoleta(OrdenPagoResponse ordenPagoResponse);

}

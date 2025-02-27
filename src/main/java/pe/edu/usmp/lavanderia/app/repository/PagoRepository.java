package pe.edu.usmp.lavanderia.app.repository;

import pe.edu.usmp.lavanderia.app.request.ListarPagosxClienteRequest;
import pe.edu.usmp.lavanderia.app.request.ServicioPagoRequest;
import pe.edu.usmp.lavanderia.app.response.*;
import pe.edu.usmp.lavanderia.app.request.OrdenPagoRequest;

import java.util.List;

public interface PagoRepository {
    List<ListaClientePagoResponse> listarPagosxCliente(ListarPagosxClienteRequest request);
    List<CodNombreResponse> listarServicios();
    List<SubserviciosPagoResponse> listarSubservicios(Integer servicioId);
    List<CodNombreResponse> listarMedioPagos();
    boolean existeCodigo(String codigo);
    GenerateCodigoDBResponse generarCodigo();
    Integer insertarCabeceraBoleta(OrdenPagoRequest ordenPagoRequest);
    Integer insertarDetalleBoleta(ServicioPagoRequest servicioPagoRequest,Integer pago);
    void actualizarContadorSecuencia();
    OrdenPagoEditResponse obtenerPagoEdit(Integer pago);
    Integer editarCabeceraBoleta(OrdenPagoRequest ordenPagoRequest);
    void eliminarDetalleTable(Integer pago);
    OrdenPagoEditResponse imprimirBoleta(Integer pago);
    Integer anularPago(Integer pago);
}

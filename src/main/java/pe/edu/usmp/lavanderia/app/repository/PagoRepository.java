package pe.edu.usmp.lavanderia.app.repository;

import pe.edu.usmp.lavanderia.app.request.ServicioPagoRequest;
import pe.edu.usmp.lavanderia.app.response.CodNombreResponse;
import pe.edu.usmp.lavanderia.app.response.ListaClientePagoResponse;
import pe.edu.usmp.lavanderia.app.request.OrdenPagoRequest;
import pe.edu.usmp.lavanderia.app.response.OrdenPagoEditResponse;
import pe.edu.usmp.lavanderia.app.response.SubserviciosPagoResponse;

import java.util.List;

public interface PagoRepository {
    List<ListaClientePagoResponse> listarPagosxCliente(Integer clienteId);
    List<CodNombreResponse> listarServicios();
    List<SubserviciosPagoResponse> listarSubservicios(Integer servicioId);
    List<CodNombreResponse> listarMedioPagos();
    boolean existeCodigo(String codigo);
    String generarCodigo();
    Integer insertarCabeceraBoleta(OrdenPagoRequest ordenPagoRequest);
    Integer insertarDetalleBoleta(ServicioPagoRequest servicioPagoRequest,Integer pago);
    void actualizarContadorSecuencia();
    OrdenPagoEditResponse obtenerPagoEdit(Integer pago);
    Integer editarCabeceraBoleta(OrdenPagoRequest ordenPagoRequest);
    void eliminarDetalleTable(Integer pago);
    OrdenPagoEditResponse imprimirBoleta(Integer pago);}

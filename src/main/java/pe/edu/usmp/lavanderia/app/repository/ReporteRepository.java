package pe.edu.usmp.lavanderia.app.repository;

import pe.edu.usmp.lavanderia.app.request.ReporteRequest;
import pe.edu.usmp.lavanderia.app.response.ReporteResponse;

import java.util.List;

public interface ReporteRepository  {

    List<ReporteResponse> listaIngresoTotalesPeriodo(ReporteRequest datos);
    List<ReporteResponse> listaServicioSolicitado(ReporteRequest datos);
    List<ReporteResponse> listaEstadosPagos(ReporteRequest datos);
    List<ReporteResponse> listaEstadosPagosDetalle(ReporteRequest datos);
    List<ReporteResponse> listaClientesFrecuentes(ReporteRequest datos);
    List<ReporteResponse> listaMedioPagosMonto(ReporteRequest datos);
    List<ReporteResponse> listaIngresoUsuario(ReporteRequest datos);


}

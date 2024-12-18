package pe.edu.usmp.lavanderia.app.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.usmp.lavanderia.app.request.ReporteRequest;
import pe.edu.usmp.lavanderia.app.response.ListModelResponse;
import pe.edu.usmp.lavanderia.app.response.ReporteResponse;
import pe.edu.usmp.lavanderia.app.service.ReporteService;

import java.util.List;

@RestController
@RequestMapping("/reporte")
public class ReporteController {
    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @RequestMapping(value = "listaIngresoTotalesPeriodo", method = RequestMethod.POST)
    public List<ReporteResponse> listaIngresoTotalesPeriodo(@RequestBody ReporteRequest datos) {
        return reporteService.listaIngresoTotalesPeriodo(datos);
    }
    @RequestMapping(value = "listaServicioSolicitado", method = RequestMethod.POST)
    public List<ReporteResponse> listaServicioSolicitado(@RequestBody ReporteRequest datos) {
        return reporteService.listaServicioSolicitado(datos);
    }
    @RequestMapping(value = "listaEstadosPagos", method = RequestMethod.POST)
    public List<ReporteResponse> listaEstadosPagos(@RequestBody ReporteRequest datos) {
        return reporteService.listaEstadosPagos(datos);
    }
    @RequestMapping(value = "listaEstadosPagosDetalle", method = RequestMethod.POST)
    public List<ReporteResponse> listaEstadosPagosDetalle(@RequestBody ReporteRequest datos) {
        return reporteService.listaEstadosPagosDetalle(datos);
    }
    @RequestMapping(value = "listaClientesFrecuentes", method = RequestMethod.POST)
    public List<ReporteResponse> listaClientesFrecuentes(@RequestBody ReporteRequest datos) {
        return reporteService.listaClientesFrecuentes(datos);
    }
    @RequestMapping(value = "listaMedioPagosMonto", method = RequestMethod.POST)
    public List<ReporteResponse> listaMedioPagosMonto(@RequestBody ReporteRequest datos) {
        return reporteService.listaMedioPagosMonto(datos);
    }
    @RequestMapping(value = "listaIngresoUsuario", method = RequestMethod.POST)
    public List<ReporteResponse> listaIngresoUsuario(@RequestBody ReporteRequest datos) {
        return reporteService.listaIngresoUsuario(datos);
    }
    @RequestMapping(value = "listarDeudores", method = RequestMethod.POST)
    public ListModelResponse<ReporteResponse> listarDeudores(@RequestBody ReporteRequest datos) {
        return reporteService.listarDeudores(datos);
    }
}

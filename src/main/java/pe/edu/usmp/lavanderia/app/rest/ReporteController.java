package pe.edu.usmp.lavanderia.app.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.usmp.lavanderia.app.request.ReporteRequest;
import pe.edu.usmp.lavanderia.app.response.ListModelResponse;
import pe.edu.usmp.lavanderia.app.response.ReporteResponse;
import pe.edu.usmp.lavanderia.app.service.ReporteService;
import java.util.*;

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
    @RequestMapping(value = "exportarReportesPDF", method = RequestMethod.POST)
    public ResponseEntity<String> exportarReportesPDF(@RequestBody ReporteRequest datos) throws Exception {
        String reportePdf =reporteService.exportarReportesPDF(datos);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=Reporte.pdf");
        return ResponseEntity.ok().headers(headers).body(reportePdf);
    }
    @RequestMapping(value = "exportarReportesExcel", method = RequestMethod.POST)
    public ResponseEntity<byte []> exportarReportesExcel(@RequestBody ReporteRequest datos)  throws Exception{
        byte [] response= reporteService.exportarReportesExcel(datos);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Reporte.xlsx");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}

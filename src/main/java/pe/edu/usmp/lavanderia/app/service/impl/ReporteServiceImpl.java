package pe.edu.usmp.lavanderia.app.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.usmp.lavanderia.app.repository.ReporteRepository;
import pe.edu.usmp.lavanderia.app.request.ReporteRequest;
import pe.edu.usmp.lavanderia.app.response.ClienteResponse;
import pe.edu.usmp.lavanderia.app.response.ListModelResponse;
import pe.edu.usmp.lavanderia.app.response.ReporteResponse;
import pe.edu.usmp.lavanderia.app.service.ReporteService;
import pe.edu.usmp.lavanderia.app.utils.Constantes;

import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService {

    private final ReporteRepository reporteRepository;

    public ReporteServiceImpl(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    @Override
    public List<ReporteResponse> listaIngresoTotalesPeriodo(ReporteRequest datos) {
        return reporteRepository.listaIngresoTotalesPeriodo(datos);
    }

    @Override
    public List<ReporteResponse> listaServicioSolicitado(ReporteRequest datos) {
        return reporteRepository.listaServicioSolicitado(datos);
    }

    @Override
    public List<ReporteResponse> listaEstadosPagos(ReporteRequest datos) {
        return reporteRepository.listaEstadosPagos(datos);
    }

    @Override
    public List<ReporteResponse> listaEstadosPagosDetalle(ReporteRequest datos) {
        return reporteRepository.listaEstadosPagosDetalle(datos);
    }

    @Override
    public List<ReporteResponse> listaClientesFrecuentes(ReporteRequest datos) {
        return reporteRepository.listaClientesFrecuentes(datos);
    }

    @Override
    public List<ReporteResponse> listaMedioPagosMonto(ReporteRequest datos) {
        return reporteRepository.listaMedioPagosMonto(datos);
    }

    @Override
    public List<ReporteResponse> listaIngresoUsuario(ReporteRequest datos) {
        return reporteRepository.listaIngresoUsuario(datos);
    }

    @Override
    public ListModelResponse<ReporteResponse> listarDeudores(ReporteRequest datos) {
        ListModelResponse<ReporteResponse> resp = new ListModelResponse<>();
        List<ReporteResponse> lista = reporteRepository.listarDeudores(datos);
        if (!lista.isEmpty()) {
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_SUCCESS);
            resp.setMensaje("Se han encontrado pagos pendientes");
            resp.setList(lista);
        } else {
            resp.setCod(Constantes.NULL_COD);
            resp.setIcon(Constantes.ICON_INFO);
            resp.setMensaje("No se han encontrado pagos pendientes");
        }
        return resp;
    }
}

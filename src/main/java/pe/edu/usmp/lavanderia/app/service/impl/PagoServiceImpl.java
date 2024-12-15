package pe.edu.usmp.lavanderia.app.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.usmp.lavanderia.app.repository.PagoRepository;
import pe.edu.usmp.lavanderia.app.response.CodNombreResponse;
import pe.edu.usmp.lavanderia.app.response.ListModelResponse;
import pe.edu.usmp.lavanderia.app.response.ListaClientePagoResponse;
import pe.edu.usmp.lavanderia.app.response.SubserviciosPagoResponse;
import pe.edu.usmp.lavanderia.app.service.PagoService;
import pe.edu.usmp.lavanderia.app.utils.Constantes;

import java.util.List;

@Service
public class PagoServiceImpl implements PagoService {
    private final PagoRepository pagoRepository;

    public PagoServiceImpl(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    @Override
    public ListModelResponse<ListaClientePagoResponse> listarPagosxCliente(Integer clienteId) {
        ListModelResponse<ListaClientePagoResponse> resp = new ListModelResponse<>();
        List<ListaClientePagoResponse> lista = pagoRepository.listarPagosxCliente(clienteId);
        if (!lista.isEmpty()) {
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_SUCCESS);
            resp.setMensaje("Pagos encontrados.");
            resp.setList(lista);
        } else {
            resp.setCod(Constantes.NULL_COD);
            resp.setIcon(Constantes.ICON_INFO);
            resp.setMensaje("No se han encontrado pagos pendientes");
            resp.setMensajeTxt("Puede registrar un nuevo pago dando click al boton 'Registrar Nuevo Servicio''");
        }
        return resp;
    }

    @Override
    public List<CodNombreResponse> listarServicios() {
        return pagoRepository.listarServicios();
    }

    @Override
    public List<SubserviciosPagoResponse> listarSubservicios(Integer servicioId) {
        return pagoRepository.listarSubservicios(servicioId);
    }

    @Override
    public List<CodNombreResponse> listarMedioPagos() {
        return pagoRepository.listarMedioPagos();
    }
}

package pe.edu.usmp.lavanderia.app.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.usmp.lavanderia.app.response.*;
import pe.edu.usmp.lavanderia.app.service.PagoService;

import java.util.List;

@RestController
@RequestMapping("/pago")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @RequestMapping(value = "listarPagosxCliente", method = RequestMethod.GET)
    public ListModelResponse<ListaClientePagoResponse> listarPagosxCliente(@RequestParam Integer clienteId) {
        return pagoService.listarPagosxCliente(clienteId);
    }
    @RequestMapping(value = "listarServicios", method = RequestMethod.GET)
    public List<CodNombreResponse> listarServicios() {
        return pagoService.listarServicios();
    }
    @RequestMapping(value = "listarSubservicios", method = RequestMethod.GET)
    public List<SubserviciosPagoResponse> listarSubservicios(@RequestParam Integer servicioId) {
        return pagoService.listarSubservicios(servicioId);
    }
}

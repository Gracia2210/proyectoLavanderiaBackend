package pe.edu.usmp.lavanderia.app.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.usmp.lavanderia.app.request.ListarPagosxClienteRequest;
import pe.edu.usmp.lavanderia.app.request.OrdenPagoRequest;
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

    @RequestMapping(value = "listarPagosxCliente", method = RequestMethod.POST)
    public ListModelResponse<ListaClientePagoResponse> listarPagosxCliente(@RequestBody ListarPagosxClienteRequest request) {
        return pagoService.listarPagosxCliente(request);
    }
    @RequestMapping(value = "listarServicios", method = RequestMethod.GET)
    public List<CodNombreResponse> listarServicios() {
        return pagoService.listarServicios();
    }
    @RequestMapping(value = "listarSubservicios", method = RequestMethod.GET)
    public List<SubserviciosPagoResponse> listarSubservicios(@RequestParam Integer servicioId) {
        return pagoService.listarSubservicios(servicioId);
    }
    @RequestMapping(value = "listarMedioPagos", method = RequestMethod.GET)
    public List<CodNombreResponse> listarMedioPagos() {
        return pagoService.listarMedioPagos();
    }
    @RequestMapping(value = "generarBoleta", method = RequestMethod.POST)
    public ModelResponse<Integer> generarBoleta(@RequestBody OrdenPagoRequest request){
        return pagoService.generarBoleta(request);
    }
    @RequestMapping(value = "obtenerPagoEdit", method = RequestMethod.GET)
    public ModelResponse<OrdenPagoEditResponse> obtenerPagoEdit(@RequestParam Integer pago){
        return pagoService.obtenerPagoEdit(pago);
    }
    @RequestMapping(value = "edicionBoleta", method = RequestMethod.POST)
    MsgResponse edicionBoleta(@RequestBody OrdenPagoRequest request){
        return pagoService.edicionBoleta(request);
    }
    @RequestMapping(value = "imprimirBoleta", method = RequestMethod.GET)
    public ModelResponse<String> imprimirBoleta(@RequestParam Integer pago) throws Exception {
        return pagoService.imprimirBoleta(pago);
    }
}

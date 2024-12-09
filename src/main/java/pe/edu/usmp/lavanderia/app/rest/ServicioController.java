package pe.edu.usmp.lavanderia.app.rest;

import org.springframework.web.bind.annotation.*;
import pe.edu.usmp.lavanderia.app.response.ListModelResponse;
import pe.edu.usmp.lavanderia.app.response.ModelResponse;
import pe.edu.usmp.lavanderia.app.response.MsgResponse;
import pe.edu.usmp.lavanderia.app.response.ServicioResponse;
import pe.edu.usmp.lavanderia.app.service.ServicioService;

@RestController
@RequestMapping("/servicio")
public class ServicioController {
    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }
    @RequestMapping(value = "listar", method = RequestMethod.GET)
    public ListModelResponse<ServicioResponse> listarServicios() {
        return servicioService.listarServicios();
    }

    @RequestMapping(value = "insertar", method = RequestMethod.POST)
    public ModelResponse<ServicioResponse> insertarServicio(@RequestBody ServicioResponse request) {
        return servicioService.insertarServicio(request.getDescripcion());
    }

    @RequestMapping(value = "editar", method = RequestMethod.POST)
    public MsgResponse editarServicio(@RequestBody ServicioResponse request) {
        return servicioService.editarServicio(request.getId(), request.getDescripcion());
    }

    @RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET)
    public ModelResponse<ServicioResponse> buscarPorId(@PathVariable int id) {
        return servicioService.buscarPorId(id);
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.POST)
    public MsgResponse eliminarServicio(@PathVariable int id) {
        return servicioService.eliminarServicioPorId(id);
    }
}

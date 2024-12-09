package pe.edu.usmp.lavanderia.app.rest;

import org.springframework.web.bind.annotation.*;
import pe.edu.usmp.lavanderia.app.response.ListModelResponse;
import pe.edu.usmp.lavanderia.app.response.ModelResponse;
import pe.edu.usmp.lavanderia.app.response.MsgResponse;
import pe.edu.usmp.lavanderia.app.response.SubservicioResponse;
import pe.edu.usmp.lavanderia.app.service.SubservicioService;

@RestController
@RequestMapping("/subservicio")
public class SubservicioController {

    private final SubservicioService subservicioService;

    public SubservicioController(SubservicioService subservicioService) {
        this.subservicioService = subservicioService;
    }

    @RequestMapping(value = "listar/{servicioId}", method = RequestMethod.GET)
    public ListModelResponse<SubservicioResponse> listarSubservicios(@PathVariable int servicioId) {
        return subservicioService.listarSubserviciosPorServicioId(servicioId);
    }


    @RequestMapping(value = "insertar", method = RequestMethod.POST)
    public ModelResponse<SubservicioResponse> insertarSubservicio(@RequestBody SubservicioResponse subservicio) {
        return subservicioService.insertarSubservicio(subservicio);
    }

    @RequestMapping(value = "editar", method = RequestMethod.POST)
    public MsgResponse editarSubservicio(@RequestBody SubservicioResponse subservicio) {
        return subservicioService.editarSubservicio(subservicio);
    }

    @RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET)
    public ModelResponse<SubservicioResponse> buscarSubservicioPorId(@PathVariable int id) {
        return subservicioService.buscarPorId(id);
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.POST)
    public MsgResponse eliminarSubservicio(@PathVariable int id) {
        return subservicioService.eliminarSubservicioPorId(id);
    }

    @RequestMapping(value = "/habilitar/{id}/{activo}", method = RequestMethod.POST)
    public MsgResponse activarSubservicio(@PathVariable int id,@PathVariable  Boolean activo){
        return subservicioService.activarSubservicio(id,activo);
    }

}



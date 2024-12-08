package pe.edu.usmp.lavanderia.app.rest;
import org.springframework.web.bind.annotation.*;
import pe.edu.usmp.lavanderia.app.request.BusquedaClienteRequest;
import pe.edu.usmp.lavanderia.app.response.ClienteResponse;
import pe.edu.usmp.lavanderia.app.response.ListModelResponse;
import pe.edu.usmp.lavanderia.app.response.ModelResponse;
import pe.edu.usmp.lavanderia.app.response.MsgResponse;
import pe.edu.usmp.lavanderia.app.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @RequestMapping(value = "listar", method = RequestMethod.GET)
    public ListModelResponse<ClienteResponse> listarClientes() {
        return clienteService.listarClientes();
    }

    @RequestMapping(value = "editar", method = RequestMethod.POST)
    public MsgResponse editarCliente(@RequestBody ClienteResponse cliente) {
        return clienteService.editarCliente(cliente);
    }

    @RequestMapping(value = "insertar", method = RequestMethod.POST)
    public ModelResponse<ClienteResponse> insertarCliente(@RequestBody ClienteResponse cliente) {
        return clienteService.insertarCliente(cliente);
    }
    @RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET)
    public ModelResponse<ClienteResponse> buscarClientePorId(@PathVariable int id) {
        return clienteService.buscarClientePorId(id);
    }
    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.POST)
    public MsgResponse eliminarCliente(@PathVariable int id) {
        return clienteService.eliminarClientePorId(id);
    }
    @RequestMapping(value = "buscar-filtro", method = RequestMethod.POST)
    public ListModelResponse<ClienteResponse> buscarClientes(@RequestBody BusquedaClienteRequest request) {
        return clienteService.buscarClientes(request);
    }
}

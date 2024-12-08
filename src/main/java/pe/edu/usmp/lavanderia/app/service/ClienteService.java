package pe.edu.usmp.lavanderia.app.service;

import pe.edu.usmp.lavanderia.app.request.BusquedaClienteRequest;
import pe.edu.usmp.lavanderia.app.response.ClienteResponse;
import pe.edu.usmp.lavanderia.app.response.ListModelResponse;
import pe.edu.usmp.lavanderia.app.response.ModelResponse;
import pe.edu.usmp.lavanderia.app.response.MsgResponse;

public interface ClienteService {
    ListModelResponse<ClienteResponse> listarClientes();
    MsgResponse editarCliente(ClienteResponse cliente);
    ModelResponse<ClienteResponse> insertarCliente(ClienteResponse cliente);
    ModelResponse<ClienteResponse> buscarClientePorId(int id);
    MsgResponse eliminarClientePorId(int id);
    ListModelResponse<ClienteResponse> buscarClientes(BusquedaClienteRequest request);

}

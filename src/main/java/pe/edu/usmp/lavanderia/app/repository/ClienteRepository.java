package pe.edu.usmp.lavanderia.app.repository;

import pe.edu.usmp.lavanderia.app.request.BusquedaClienteRequest;
import pe.edu.usmp.lavanderia.app.response.ClienteResponse;

import java.util.List;

public interface ClienteRepository {
    List<ClienteResponse> listarClientes();
    int actualizarCliente(ClienteResponse cliente);
    int insertarCliente(ClienteResponse cliente);
    ClienteResponse buscarPorId(int id);
    int eliminarClientePorId(int id);
    List<ClienteResponse> buscarClientes(BusquedaClienteRequest request);
    boolean dniYaRegistrado(String dni, int id);
}

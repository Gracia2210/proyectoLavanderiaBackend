package pe.edu.usmp.lavanderia.app.service.impl;
import org.springframework.stereotype.Service;
import pe.edu.usmp.lavanderia.app.repository.ClienteRepository;
import pe.edu.usmp.lavanderia.app.request.BusquedaClienteRequest;
import pe.edu.usmp.lavanderia.app.response.ClienteResponse;
import pe.edu.usmp.lavanderia.app.response.ListModelResponse;
import pe.edu.usmp.lavanderia.app.response.ModelResponse;
import pe.edu.usmp.lavanderia.app.response.MsgResponse;
import pe.edu.usmp.lavanderia.app.service.ClienteService;
import pe.edu.usmp.lavanderia.app.utils.Constantes;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ListModelResponse<ClienteResponse> listarClientes() {
        ListModelResponse<ClienteResponse> resp = new ListModelResponse<>();
        List<ClienteResponse> lista = clienteRepository.listarClientes();
        if (!lista.isEmpty()) {
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_SUCCESS);
            resp.setMensaje("Se han encontrado clientes");
            resp.setList(lista);
        } else {
            resp.setCod(Constantes.NULL_COD);
            resp.setIcon(Constantes.ICON_INFO);
            resp.setMensaje("No se han encontrado clientes");
        }
        return resp;
    }

    @Override
    public MsgResponse editarCliente(ClienteResponse cliente) {
        MsgResponse resp = new MsgResponse();

        if(clienteRepository.dniYaRegistrado(cliente.getDocIden(),cliente.getId())){
            resp.setIcon(Constantes.ICON_INFO);
            resp.setMensaje("El DNI " + cliente.getDocIden() + " ya ha sido registrado");
        }
        else{
            int filasAfectadas = clienteRepository.actualizarCliente(cliente);
            if (filasAfectadas > 0) {
                resp.setCod(Constantes.SUCCESS_COD);
                resp.setIcon(Constantes.ICON_SUCCESS);
                resp.setMensaje("Se ha actualizado el cliente correctamente.");
            } else {
                resp.setCod(Constantes.NULL_COD);
                resp.setIcon(Constantes.ICON_ERROR);
                resp.setMensaje("No se encontró el cliente para actualizar.");
            }
        }
        return resp;
    }

    @Override
    public ModelResponse<ClienteResponse> insertarCliente(ClienteResponse cliente) {
        ModelResponse<ClienteResponse> resp = new ModelResponse<>();
        if(clienteRepository.dniYaRegistrado(cliente.getDocIden(),cliente.getId())){
            resp.setIcon(Constantes.ICON_INFO);
            resp.setMensaje("El DNI " + cliente.getDocIden() + " ya ha sido registrado");
        }
        else{
            int id = clienteRepository.insertarCliente(cliente);
            if (id > 0) {
                ClienteResponse clienteCreado = clienteRepository.buscarPorId(id);
                resp.setCod(Constantes.SUCCESS_COD);
                resp.setIcon(Constantes.ICON_SUCCESS);
                resp.setMensaje("Se ha registrado el cliente satisfactoriamente");
                resp.setModel(clienteCreado);
            } else {
                resp.setCod(Constantes.NULL_COD);
                resp.setIcon(Constantes.ICON_ERROR);
                resp.setMensaje("No se pudo insertar el cliente.");
            }
        }
        return resp;
    }
    @Override
    public ModelResponse<ClienteResponse> buscarClientePorId(int id) {
        ModelResponse<ClienteResponse> resp = new ModelResponse<>();
        try {
            ClienteResponse cliente = clienteRepository.buscarPorId(id);
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_SUCCESS);
            resp.setMensaje("Cliente encontrado.");
            resp.setModel(cliente);
        } catch (Exception e) {
            resp.setCod(Constantes.NULL_COD);
            resp.setIcon(Constantes.ICON_ERROR);
            resp.setMensaje("No se encontró el cliente con el ID proporcionado.");
            resp.setModel(null);
        }
        return resp;
    }
    @Override
    public MsgResponse eliminarClientePorId(int id) {
        MsgResponse resp = new MsgResponse();
        int filasAfectadas = clienteRepository.eliminarClientePorId(id);
        if (filasAfectadas > 0) {
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_INFO);
            resp.setMensaje("Cliente eliminado correctamente.");
        } else {
            resp.setCod(Constantes.NULL_COD);
            resp.setIcon(Constantes.ICON_ERROR);
            resp.setMensaje("No se encontró el cliente con el ID proporcionado.");
        }
        return resp;
    }
    @Override
    public ListModelResponse<ClienteResponse> buscarClientes(BusquedaClienteRequest request) {
        ListModelResponse<ClienteResponse> resp = new ListModelResponse<>();
        List<ClienteResponse> lista = clienteRepository.buscarClientes(request);

        if (!lista.isEmpty()) {
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_SUCCESS);
            resp.setMensaje("Clientes encontrados.");
            resp.setList(lista);
        } else {
            resp.setCod(Constantes.NULL_COD);
            resp.setIcon(Constantes.ICON_INFO);
            resp.setMensaje("No se encontraron clientes con los filtros proporcionados.");
        }

        return resp;
    }
}

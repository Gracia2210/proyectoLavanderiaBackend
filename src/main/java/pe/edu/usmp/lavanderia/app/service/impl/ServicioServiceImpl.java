package pe.edu.usmp.lavanderia.app.service.impl;


import org.springframework.stereotype.Service;
import pe.edu.usmp.lavanderia.app.repository.ServicioRepository;
import pe.edu.usmp.lavanderia.app.response.ListModelResponse;
import pe.edu.usmp.lavanderia.app.response.ModelResponse;
import pe.edu.usmp.lavanderia.app.response.MsgResponse;
import pe.edu.usmp.lavanderia.app.response.ServicioResponse;
import pe.edu.usmp.lavanderia.app.service.ServicioService;
import pe.edu.usmp.lavanderia.app.utils.Constantes;

import java.util.List;

@Service
public class ServicioServiceImpl implements ServicioService {
    private final ServicioRepository servicioRepository;

    public ServicioServiceImpl(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    public ListModelResponse<ServicioResponse> listarServicios() {
        ListModelResponse<ServicioResponse> resp = new ListModelResponse<>();
        List<ServicioResponse> lista = servicioRepository.listarServicios();
        if (!lista.isEmpty()) {
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_SUCCESS);
            resp.setMensaje("Servicios encontrados.");
            resp.setList(lista);
        } else {
            resp.setCod(Constantes.NULL_COD);
            resp.setIcon(Constantes.ICON_INFO);
            resp.setMensaje("No se encontraron servicios.");
        }
        return resp;
    }

    @Override
    public ModelResponse<ServicioResponse> insertarServicio(String descripcion) {
        ModelResponse<ServicioResponse> resp = new ModelResponse<>();
        int filasAfectadas = servicioRepository.insertarServicio(descripcion);
        if (filasAfectadas > 0) {
            ServicioResponse servicioCreado = servicioRepository.buscarPorId(filasAfectadas);
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_SUCCESS);
            resp.setMensaje("Se ha creado el servicio satisfactoriamente.");
            resp.setModel(servicioCreado);
        } else {
            resp.setCod(Constantes.NULL_COD);
            resp.setIcon(Constantes.ICON_ERROR);
            resp.setMensaje("No se pudo insertar el servicio.");
        }
        return resp;
    }

    @Override
    public MsgResponse editarServicio(int id, String descripcion) {
        MsgResponse resp = new MsgResponse();
        int filasAfectadas = servicioRepository.editarServicio(id, descripcion);
        if (filasAfectadas > 0) {
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_SUCCESS);
            resp.setMensaje("Se ha actualizado el servicio correctamente");
        } else {
            resp.setCod(Constantes.NULL_COD);
            resp.setIcon(Constantes.ICON_ERROR);
            resp.setMensaje("No se pudo actualizar el servicio.");
        }
        return resp;
    }

    @Override
    public ModelResponse<ServicioResponse> buscarPorId(int id) {
        ModelResponse<ServicioResponse> resp = new ModelResponse<>();
        try {
            ServicioResponse servicio = servicioRepository.buscarPorId(id);
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_SUCCESS);
            resp.setMensaje("Servicio encontrado.");
            resp.setModel(servicio);
        } catch (Exception e) {
            resp.setCod(Constantes.NULL_COD);
            resp.setIcon(Constantes.ICON_ERROR);
            resp.setMensaje("No se encontró el servicio con el ID proporcionado.");
            resp.setModel(null);
        }
        return resp;
    }

    @Override
    public MsgResponse eliminarServicioPorId(int id) {
        MsgResponse resp = new MsgResponse();
        int filasAfectadas = servicioRepository.eliminarServicioPorId(id);
        if (filasAfectadas > 0) {
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_SUCCESS);
            resp.setMensaje("Se ha eliminado el servicio correctamente");
        } else {
            resp.setCod(Constantes.NULL_COD);
            resp.setIcon(Constantes.ICON_ERROR);
            resp.setMensaje("No se encontró el servicio con el ID proporcionado.");
        }
        return resp;
    }

    @Override
    public MsgResponse activarServicio(int id, Boolean activo) {
        MsgResponse resp = new MsgResponse();
        int filasAfectadas = servicioRepository.activarServicio(id,activo);
        if (filasAfectadas > 0) {
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_INFO);
            String activoTexto=activo?"activado":"inactivado";
            resp.setMensaje("Se ha "+activoTexto+" el servicio satisfactoriamente");
        } else {
            resp.setCod(Constantes.NULL_COD);
            resp.setIcon(Constantes.ICON_ERROR);
            resp.setMensaje("No se encontró el servicio con el ID proporcionado");
        }
        return resp;
    }
}

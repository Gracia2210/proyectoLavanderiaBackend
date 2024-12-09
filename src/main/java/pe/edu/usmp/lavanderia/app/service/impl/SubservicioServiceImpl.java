package pe.edu.usmp.lavanderia.app.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.usmp.lavanderia.app.repository.SubservicioRepository;
import pe.edu.usmp.lavanderia.app.response.ListModelResponse;
import pe.edu.usmp.lavanderia.app.response.ModelResponse;
import pe.edu.usmp.lavanderia.app.response.MsgResponse;
import pe.edu.usmp.lavanderia.app.response.SubservicioResponse;
import pe.edu.usmp.lavanderia.app.service.SubservicioService;
import pe.edu.usmp.lavanderia.app.utils.Constantes;

import java.util.List;

@Service
public class SubservicioServiceImpl implements SubservicioService {

    private final SubservicioRepository subservicioRepository;

    public SubservicioServiceImpl(SubservicioRepository subservicioRepository) {
        this.subservicioRepository = subservicioRepository;
    }
    @Override
    public ListModelResponse<SubservicioResponse> listarSubserviciosPorServicioId(int servicioId) {
        ListModelResponse<SubservicioResponse> resp = new ListModelResponse<>();
        List<SubservicioResponse> lista = subservicioRepository.listarSubserviciosPorServicioId(servicioId);
        if (!lista.isEmpty()) {
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_SUCCESS);
            resp.setMensaje("Subservicios encontrados.");
            resp.setList(lista);
        } else {
            resp.setCod(Constantes.NULL_COD);
            resp.setIcon(Constantes.ICON_INFO);
            resp.setMensaje("No se encontraron subservicios para el servicio indicado");
        }
        return resp;
    }
    @Override
    public ModelResponse<SubservicioResponse> insertarSubservicio(SubservicioResponse subservicio) {
        ModelResponse<SubservicioResponse> resp = new ModelResponse<>();
        int filasAfectadas = subservicioRepository.insertarSubservicio(subservicio);
        if (filasAfectadas > 0) {
            SubservicioResponse creado = subservicioRepository.buscarPorId(filasAfectadas);
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_SUCCESS);
            resp.setMensaje("Se ha registrado el subservicio satisfactoriamente");
            resp.setModel(creado);
        } else {
            resp.setCod(Constantes.NULL_COD);
            resp.setIcon(Constantes.ICON_ERROR);
            resp.setMensaje("No se pudo insertar el subservicio.");
        }
        return resp;
    }

    @Override
    public MsgResponse editarSubservicio(SubservicioResponse subservicio) {
        MsgResponse resp = new MsgResponse();
        int filasAfectadas = subservicioRepository.editarSubservicio(subservicio);
        if (filasAfectadas > 0) {
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_SUCCESS);
            resp.setMensaje("Se ha actualizado el subservicio satisfactoriamente");
        } else {
            resp.setCod(Constantes.NULL_COD);
            resp.setIcon(Constantes.ICON_ERROR);
            resp.setMensaje("No se pudo actualizar el subservicio.");
        }
        return resp;
    }

    @Override
    public ModelResponse<SubservicioResponse> buscarPorId(int id) {
        ModelResponse<SubservicioResponse> resp = new ModelResponse<>();
        try {
            SubservicioResponse encontrado = subservicioRepository.buscarPorId(id);
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_SUCCESS);
            resp.setMensaje("Subservicio encontrado.");
            resp.setModel(encontrado);
        } catch (Exception e) {
            resp.setCod(Constantes.NULL_COD);
            resp.setIcon(Constantes.ICON_ERROR);
            resp.setMensaje("No se encontró el subservicio con el ID proporcionado.");
        }
        return resp;
    }

    @Override
    public MsgResponse eliminarSubservicioPorId(int id) {
        MsgResponse resp = new MsgResponse();
        int filasAfectadas = subservicioRepository.eliminarSubservicioPorId(id);
        if (filasAfectadas > 0) {
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_SUCCESS);
            resp.setMensaje("Se ha eliminado el subservicio correctamente");
        } else {
            resp.setCod(Constantes.NULL_COD);
            resp.setIcon(Constantes.ICON_ERROR);
            resp.setMensaje("No se encontró el subservicio con el ID proporcionado");
        }
        return resp;
    }

}

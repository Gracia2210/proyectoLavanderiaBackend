package pe.edu.usmp.lavanderia.app.repository;

import pe.edu.usmp.lavanderia.app.response.SubservicioResponse;

import java.util.List;

public interface SubservicioRepository {
    List<SubservicioResponse> listarSubserviciosPorServicioId(int servicioId);
    int insertarSubservicio(SubservicioResponse subservicio);
    int editarSubservicio(SubservicioResponse subservicio);
    SubservicioResponse buscarPorId(int id);
    int eliminarSubservicioPorId(int id);
}

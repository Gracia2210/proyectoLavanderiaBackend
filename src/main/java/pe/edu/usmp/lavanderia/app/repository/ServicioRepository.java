package pe.edu.usmp.lavanderia.app.repository;

import pe.edu.usmp.lavanderia.app.response.ServicioResponse;

import java.util.List;

public interface ServicioRepository {
    List<ServicioResponse> listarServicios();
    int insertarServicio(String descripcion);
    int editarServicio(int id, String descripcion);
    ServicioResponse buscarPorId(int id);
    int eliminarServicioPorId(int id);
    int activarServicio(int id,Boolean activo);


}

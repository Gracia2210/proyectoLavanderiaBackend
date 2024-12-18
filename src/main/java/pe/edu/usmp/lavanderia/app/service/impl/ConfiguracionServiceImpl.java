package pe.edu.usmp.lavanderia.app.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.usmp.lavanderia.app.repository.ConfiguracionRepository;
import pe.edu.usmp.lavanderia.app.request.EditarConfiguracionRequest;
import pe.edu.usmp.lavanderia.app.request.EditarSecuenciaRequest;
import pe.edu.usmp.lavanderia.app.response.*;
import pe.edu.usmp.lavanderia.app.service.ConfiguracionService;
import pe.edu.usmp.lavanderia.app.utils.Constantes;
import pe.edu.usmp.lavanderia.app.utils.UtilResource;
import java.util.List;

@Service
public class ConfiguracionServiceImpl implements ConfiguracionService {
    private final ConfiguracionRepository configuracionRepository;

    public ConfiguracionServiceImpl(ConfiguracionRepository configuracionRepository) {
        this.configuracionRepository = configuracionRepository;
    }

    @Override
    public ListModelResponse<ConfiguracionResponse> listarConfiguracion() {
        ListModelResponse<ConfiguracionResponse> resp = new ListModelResponse<>();
        List<ConfiguracionResponse> lista = configuracionRepository.listarConfiguracion();
        if (!lista.isEmpty()) {
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_SUCCESS);
            resp.setMensaje("Se han encontrado configuracion");
            resp.setList(lista);
        } else {
            resp.setCod(Constantes.NULL_COD);
            resp.setIcon(Constantes.ICON_INFO);
            resp.setMensaje("No se han encontrado configuracion");
        }
        return resp;

    }

    @Override
    public MsgResponse editarConfiguracion(Long id, String nombre,String descripcion, String direccion, String telefono, MultipartFile archivo) {
        MsgResponse resp = new MsgResponse();
        EditarConfiguracionRequest request = new EditarConfiguracionRequest();
        request.setId(id);
        request.setNombre(nombre);
        request.setDescripcion(descripcion);
        request.setDireccion(direccion);
        request.setTelefono(telefono);
        if (archivo != null && !archivo.isEmpty()) {
            try {
                request.setImagen(archivo.getBytes());
                request.setNombreImagen(archivo.getOriginalFilename());
                request.setExtensionImagen(UtilResource.obtenerExtensionArchivo(archivo.getOriginalFilename()));
            } catch (Exception e) {
                throw new RuntimeException("Error al procesar la imagen", e);
            }

        }
        configuracionRepository.editarConfiguracion(request);
        resp.setCod(Constantes.SUCCESS_COD);
        resp.setIcon(Constantes.ICON_SUCCESS);
        resp.setMensaje("Se ha actualizado la configuración del sistema satisfactoriamente");
        resp.setMensajeTxt("Se ha salido de la sesión para que los cambios puedan ser efectuados");

        return resp;
    }

    @Override
    public ModelResponse<byte[]> obtenerImagen(Long idArchivo) {
        ModelResponse<byte[]> resp = new ModelResponse<>();
        byte[] model = configuracionRepository.obtenerImagen(idArchivo);
        if (model != null) {
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_SUCCESS);
            resp.setMensaje("Se ha encontrado imagen");
            resp.setModel(model);
        } else {
            resp.setMensaje("No se ha encontrado imagen");
        }
        return resp;
    }

    @Override
    public ConfiguracionGlobalResponse configuracionGlobal() {
        List<ConfiguracionResponse> lista = configuracionRepository.listarConfiguracion();
        ConfiguracionGlobalResponse global =null;
        if(!lista.isEmpty()){
            global = new ConfiguracionGlobalResponse();
            global.setNombre(lista.get(0).getNombre());
            global.setDescripcion(lista.get(0).getDescripcion());
            global.setDireccion(lista.get(0).getDireccion());
            global.setTelefono(lista.get(0).getTelefono());
            global.setImagen( configuracionRepository.obtenerImagen(lista.get(0).getId()));
        }
        return global;
    }

    @Override
    public ModelResponse<ConfiguracionGlobalResponse> obtenerConfiguracionGlobal() {
        ModelResponse<ConfiguracionGlobalResponse> resp= new ModelResponse<>();
        ConfiguracionGlobalResponse global =configuracionGlobal();
        if(global!=null){
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_SUCCESS);
            resp.setMensaje("Se ha encontrado configuracion");
            resp.setModel(global);
        }
        else{
            resp.setMensaje("No se ha configuración");
        }
        return resp;
    }

    @Override
    public ListModelResponse<ListaSecuenciaResponse> listarSecuencia() {
        ListModelResponse<ListaSecuenciaResponse> resp = new ListModelResponse<>();
        List<ListaSecuenciaResponse> lista = configuracionRepository.listarSecuencia();
        if (!lista.isEmpty()) {
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_SUCCESS);
            resp.setMensaje("Se han encontrado secuencias");
            resp.setList(lista);
        } else {
            resp.setCod(Constantes.NULL_COD);
            resp.setIcon(Constantes.ICON_INFO);
            resp.setMensaje("No se han encontrado secuencias");
        }
        return resp;
    }

    @Override
    public MsgResponse editarSecuencia(EditarSecuenciaRequest datos) {
        MsgResponse resp = new MsgResponse();
        Integer ultimaSecuencia=configuracionRepository.obtenerUltimaSecuenciaPago();
        String ultimaSerie=configuracionRepository.obtenerUltimaSeriePago();

       if(ultimaSerie!=null && ultimaSecuencia>0){
           if(ultimaSerie.equals(datos.getSerie())){
               if(datos.getSecuencia()<=ultimaSecuencia){
                   resp.setIcon(Constantes.ICON_INFO);
                   resp.setMensaje("No puede agregar la secuencia "+datos.getSerie()+"-"+String.format("%06d", datos.getSecuencia())+" porque ya existe o se han generado boletas menores a esta");
                   resp.setMensajeTxt("Por favor verificar el ultimo N° de Boleta generado");
                   return resp;
               }
           }
       }
        configuracionRepository.editarSecuencia(datos);
        resp.setCod(Constantes.SUCCESS_COD);
        resp.setIcon(Constantes.ICON_SUCCESS);
        resp.setMensaje("Se ha actualizado la serie y/o secuencia correctamente");
        resp.setMensajeTxt("Por favor verificar si los cambios realizados se han efectuado correctamente");
        return resp;
    }
}

package pe.edu.usmp.lavanderia.app.rest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.usmp.lavanderia.app.request.EditarSecuenciaRequest;
import pe.edu.usmp.lavanderia.app.response.*;
import pe.edu.usmp.lavanderia.app.service.ConfiguracionService;

@RestController
@RequestMapping("/configuracion")
public class ConfiguracionController {
    private final ConfiguracionService configuracionService;

    public ConfiguracionController(ConfiguracionService configuracionService) {
        this.configuracionService = configuracionService;
    }

    @RequestMapping(value = "listarConfiguracion", method = RequestMethod.GET)
    public ListModelResponse<ConfiguracionResponse> listarConfiguracion(){
        return configuracionService.listarConfiguracion();
    }

    @RequestMapping(value = "editarConfiguracion", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public MsgResponse editarConfiguracion(@RequestParam("id") Long id,
                                     @RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion, @RequestParam("direccion") String direccion,
                                     @RequestParam("telefono") String telefono, @RequestParam(value = "archivo", required = false) MultipartFile archivo) {
        return configuracionService.editarConfiguracion(id,nombre,descripcion, direccion, telefono, archivo);
    }
    @RequestMapping(value = "obtenerImagen", method = RequestMethod.GET)
    public ModelResponse<byte[]> obtenerImagen(@RequestParam Long idArchivo) {
        return configuracionService.obtenerImagen(idArchivo);
    }
    @RequestMapping(value = "listarSecuencia", method = RequestMethod.GET)
    public ListModelResponse<ListaSecuenciaResponse> listarSecuencia(){
        return configuracionService.listarSecuencia();
    }

    @RequestMapping(value = "editarSecuencia", method = RequestMethod.POST)
    public MsgResponse editarSecuencia(@RequestBody EditarSecuenciaRequest datos){
        return configuracionService.editarSecuencia(datos);
    }
}

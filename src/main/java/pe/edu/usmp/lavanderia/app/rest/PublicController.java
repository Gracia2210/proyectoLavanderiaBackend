package pe.edu.usmp.lavanderia.app.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.usmp.lavanderia.app.response.ConfiguracionGlobalResponse;
import pe.edu.usmp.lavanderia.app.response.ModelResponse;
import pe.edu.usmp.lavanderia.app.service.ConfiguracionService;

@RestController
@RequestMapping("/publico")
public class PublicController {
    private final ConfiguracionService configuracionService;

    public PublicController(ConfiguracionService configuracionService) {
        this.configuracionService = configuracionService;
    }

    @RequestMapping(value = "obtenerConfiguracionGlobal", method = RequestMethod.GET)
    public ModelResponse<ConfiguracionGlobalResponse> obtenerConfiguracionGlobal(){
        return configuracionService.obtenerConfiguracionGlobal();
    }
}

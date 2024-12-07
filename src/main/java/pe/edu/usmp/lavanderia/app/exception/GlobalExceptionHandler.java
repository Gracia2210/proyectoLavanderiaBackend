package pe.edu.usmp.lavanderia.app.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import pe.edu.usmp.lavanderia.app.response.GenericResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({ ErrorControladoException.class, Exception.class })
    @ResponseBody
    public final GenericResponse handleException(Exception ex, WebRequest request) {

        GenericResponse genericResponse = new GenericResponse();

        if (ex instanceof ErrorControladoException) {
            genericResponse.setCod(((ErrorControladoException) ex).getCod());
            genericResponse.setMensaje(((ErrorControladoException) ex).getMessage());
            LOGGER.warn(genericResponse.toString());
            return genericResponse;
        } else {
            genericResponse.setCod(-1);
            genericResponse.setMensaje("Se ha producido un error inesperado : " + ex);
            LOGGER.warn(genericResponse.toString());
            return genericResponse;
        }
    }
}

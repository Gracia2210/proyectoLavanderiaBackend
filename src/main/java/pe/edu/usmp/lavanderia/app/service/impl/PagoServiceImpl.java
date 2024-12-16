package pe.edu.usmp.lavanderia.app.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.usmp.lavanderia.app.repository.PagoRepository;
import pe.edu.usmp.lavanderia.app.request.OrdenPagoRequest;
import pe.edu.usmp.lavanderia.app.request.ServicioPagoRequest;
import pe.edu.usmp.lavanderia.app.response.*;
import pe.edu.usmp.lavanderia.app.service.PagoService;
import pe.edu.usmp.lavanderia.app.utils.Constantes;

import java.util.List;

@Service
public class PagoServiceImpl implements PagoService {
    private final PagoRepository pagoRepository;

    public PagoServiceImpl(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    @Override
    public ListModelResponse<ListaClientePagoResponse> listarPagosxCliente(Integer clienteId) {
        ListModelResponse<ListaClientePagoResponse> resp = new ListModelResponse<>();
        List<ListaClientePagoResponse> lista = pagoRepository.listarPagosxCliente(clienteId);
        if (!lista.isEmpty()) {
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_SUCCESS);
            resp.setMensaje("Pagos encontrados.");
            resp.setList(lista);
        } else {
            resp.setCod(Constantes.NULL_COD);
            resp.setIcon(Constantes.ICON_INFO);
            resp.setMensaje("No se han encontrado pagos pendientes");
            resp.setMensajeTxt("Puede registrar un nuevo pago dando click al boton 'Registrar Nuevo Servicio''");
        }
        return resp;
    }

    @Override
    public List<CodNombreResponse> listarServicios() {
        return pagoRepository.listarServicios();
    }

    @Override
    public List<SubserviciosPagoResponse> listarSubservicios(Integer servicioId) {
        return pagoRepository.listarSubservicios(servicioId);
    }

    @Override
    public List<CodNombreResponse> listarMedioPagos() {
        return pagoRepository.listarMedioPagos();
    }

    @Override
    public ModelResponse<String> generarBoleta(OrdenPagoRequest request) {
        ModelResponse<String> resp = new ModelResponse<>();
        String codigo=pagoRepository.generarCodigo();
        if(pagoRepository.existeCodigo(codigo)) {
            resp.setIcon(Constantes.ICON_INFO);
            resp.setMensaje("El código que se ha generado ("+codigo+") ya existe");
            resp.setMensajeTxt("Por favor coordinar con el administrador del sistema");
        }
        else{
            request.setCodigo(codigo);
            Integer pago = pagoRepository.insertarCabeceraBoleta(request);
            if(pago>0){
                for(ServicioPagoRequest detalle:request.getPagos()){
                    pagoRepository.insertarDetalleBoleta(detalle,pago);
                }
                pagoRepository.actualizarContadorSecuencia();
                resp.setCod(Constantes.SUCCESS_COD);
                resp.setIcon(Constantes.ICON_SUCCESS);
                resp.setMensaje("Se ha generado la boleta de pago "+codigo+" satisfactoriamente");
                resp.setMensajeTxt("Por favor revisar si todos los datos son correctos");
                resp.setModel(codigo);
            }
            else{
                resp.setMensaje("Se ha producido un error al generar el boleta");
                resp.setMensajeTxt("Por favor coordinar con soporte del sistema");
            }
        }

        return resp;
    }

    @Override
    public ModelResponse<OrdenPagoEditResponse> obtenerPagoEdit(Integer pago) {
        ModelResponse<OrdenPagoEditResponse> resp = new ModelResponse<>();
        OrdenPagoEditResponse data = pagoRepository.obtenerPagoEdit(pago);
        if(data!=null){
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_SUCCESS);
            resp.setMensaje("Pagos encontrado");
            resp.setModel(data);
        }
        else{
            resp.setMensaje("No se ha encontrado el pago seleccionado");
            resp.setMensajeTxt("Por favor coordinar con el administrador del sistema");
        }
        return resp;
    }

    @Override
    public MsgResponse edicionBoleta(OrdenPagoRequest request) {
        MsgResponse resp = new MsgResponse();
        Integer editarCabecera=pagoRepository.editarCabeceraBoleta(request);
        if(editarCabecera>0){
            pagoRepository.eliminarDetalleTable(request.getId());
            for(ServicioPagoRequest detalle:request.getPagos()){
                pagoRepository.insertarDetalleBoleta(detalle,request.getId());
            }
            pagoRepository.actualizarContadorSecuencia();
            resp.setCod(Constantes.SUCCESS_COD);
            resp.setIcon(Constantes.ICON_SUCCESS);
            resp.setMensaje("Se ha modificado la información de la boleta de pago "+request.getCodigo()+" satisfactoriamente");
            resp.setMensajeTxt("Por favor revisar si todos los datos son correctos");
        }
        else{
            resp.setMensaje("Se ha producido un error al editar la boleta");
            resp.setMensajeTxt("Por favor coordinar con soporte del sistema");
        }
        return resp;
    }
}

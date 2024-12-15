package pe.edu.usmp.lavanderia.app.response;

import java.util.List;

public class OrdenPagoResponse {
    private Integer medioPago;
    private String fechaRecojo;
    private String montoPagado;
    private double porcentaje;
    private String observacion;
    private List<ServicioPagoResponse> pagos;

    public Integer getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(Integer medioPago) {
        this.medioPago = medioPago;
    }

    public String getFechaRecojo() {
        return fechaRecojo;
    }

    public void setFechaRecojo(String fechaRecojo) {
        this.fechaRecojo = fechaRecojo;
    }

    public String getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(String montoPagado) {
        this.montoPagado = montoPagado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<ServicioPagoResponse> getPagos() {
        return pagos;
    }

    public void setPagos(List<ServicioPagoResponse> pagos) {
        this.pagos = pagos;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
}

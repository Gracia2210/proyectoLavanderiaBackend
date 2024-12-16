package pe.edu.usmp.lavanderia.app.response;

import java.util.List;

public class OrdenPagoEditResponse {
    private int id;
    private String codigo;
    private boolean pagado;
    private boolean entregado;
    private double medioPagoId;
    private double porcentajePago;
    private double montoPagadoInicial;
    private double montoTotal;
    private String fechaEntrega;
    private String fechaCreacion;
    private String observacion;
    private List<ServicioPagoEditResponse> pago;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public double getMedioPagoId() {
        return medioPagoId;
    }

    public void setMedioPagoId(double medioPagoId) {
        this.medioPagoId = medioPagoId;
    }

    public double getPorcentajePago() {
        return porcentajePago;
    }

    public void setPorcentajePago(double porcentajePago) {
        this.porcentajePago = porcentajePago;
    }

    public double getMontoPagadoInicial() {
        return montoPagadoInicial;
    }

    public void setMontoPagadoInicial(double montoPagadoInicial) {
        this.montoPagadoInicial = montoPagadoInicial;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<ServicioPagoEditResponse> getPago() {
        return pago;
    }

    public void setPago(List<ServicioPagoEditResponse> pago) {
        this.pago = pago;
    }
}

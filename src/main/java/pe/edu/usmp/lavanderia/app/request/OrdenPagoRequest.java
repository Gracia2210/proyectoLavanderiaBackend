package pe.edu.usmp.lavanderia.app.request;

import java.util.List;

public class OrdenPagoRequest {
    private Integer id;
    private Integer medioPago;
    private String codigo;
    private boolean pagado;
    private boolean entregado;
    private Integer cliente;
    private String fechaRecojo;
    private String montoPagado;
    private String montoTotal;
    private double porcentaje;
    private String observacion;
    private List<ServicioPagoRequest> pagos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(Integer medioPago) {
        this.medioPago = medioPago;
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

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
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

    public String getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(String montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<ServicioPagoRequest> getPagos() {
        return pagos;
    }

    public void setPagos(List<ServicioPagoRequest> pagos) {
        this.pagos = pagos;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }
}

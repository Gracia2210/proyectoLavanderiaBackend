package pe.edu.usmp.lavanderia.app.response;

public class ListaClientePagoResponse {
    private Integer id;
    private Integer codigo;
    private String estado;
    private String cliente;
    private String medioPago;
    private Double porcentajePago;
    private Double montoPagadoInicial;
    private Double montoTotal;
    private String usuario;
    private String fechaCreacion;
    private String fechaEntrega;
    private String direccion;
    private String observacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public Double getPorcentajePago() {
        return porcentajePago;
    }

    public void setPorcentajePago(Double porcentajePago) {
        this.porcentajePago = porcentajePago;
    }

    public Double getMontoPagadoInicial() {
        return montoPagadoInicial;
    }

    public void setMontoPagadoInicial(Double montoPagadoInicial) {
        this.montoPagadoInicial = montoPagadoInicial;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}

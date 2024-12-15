package pe.edu.usmp.lavanderia.app.response;

public class ListaClientePagoResponse {
    private Long id;
    private String codigo;
    private boolean pagado;
    private boolean entregado;
    private String cliente;
    private String medioPago;
    private double porcentajePago;
    private double montoPagadoInicial;
    private double montoTotal;
    private String usuario;
    private String fechaCreacion;
    private String fechaEntrega;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}

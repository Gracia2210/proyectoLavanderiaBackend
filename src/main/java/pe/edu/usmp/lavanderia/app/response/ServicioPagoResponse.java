package pe.edu.usmp.lavanderia.app.response;

public class ServicioPagoResponse {
    private Integer cod;
    private String nombre;
    private Boolean soloSeleccion;
    private String unidad;
    private double monto;
    private String cantidad;
    private String montoTotal;

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getSoloSeleccion() {
        return soloSeleccion;
    }

    public void setSoloSeleccion(Boolean soloSeleccion) {
        this.soloSeleccion = soloSeleccion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(String montoTotal) {
        this.montoTotal = montoTotal;
    }
}

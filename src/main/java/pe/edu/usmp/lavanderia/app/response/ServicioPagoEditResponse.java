package pe.edu.usmp.lavanderia.app.response;

public class ServicioPagoEditResponse {
    private Integer cod;
    private String nombre;
    private boolean soloSeleccion;
    private String tipo;
    private String detalleTipo;
    private double monto;
    private int cantidad;
    private double montoTotal;

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

    public boolean isSoloSeleccion() {
        return soloSeleccion;
    }

    public void setSoloSeleccion(boolean soloSeleccion) {
        this.soloSeleccion = soloSeleccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDetalleTipo() {
        return detalleTipo;
    }

    public void setDetalleTipo(String detalleTipo) {
        this.detalleTipo = detalleTipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }
}

package pe.edu.usmp.lavanderia.app.response;

public class SubserviciosPagoResponse extends CodNombreResponse {
    private Double monto;
    private String unidad;
    private Boolean soloSeleccion;

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

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
}

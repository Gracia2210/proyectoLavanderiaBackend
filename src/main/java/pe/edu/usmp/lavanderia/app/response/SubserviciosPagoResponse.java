package pe.edu.usmp.lavanderia.app.response;

public class SubserviciosPagoResponse extends CodNombreResponse {
    private Double monto;
    private String tipo;
    private String detalleTipo;
    private Boolean soloSeleccion;

    public Boolean getSoloSeleccion() {
        return soloSeleccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setSoloSeleccion(Boolean soloSeleccion) {
        this.soloSeleccion = soloSeleccion;
    }

    public String getDetalleTipo() {
        return detalleTipo;
    }

    public void setDetalleTipo(String detalleTipo) {
        this.detalleTipo = detalleTipo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
}

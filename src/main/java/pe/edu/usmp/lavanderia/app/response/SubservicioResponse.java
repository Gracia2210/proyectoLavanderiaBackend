package pe.edu.usmp.lavanderia.app.response;

public class SubservicioResponse {
    private int id;
    private String descripcion;
    private double monto;
    private int servicioId;
    private String unidad;
    private Boolean soloSeleccion;
    private Boolean enabled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getServicioId() {
        return servicioId;
    }

    public void setServicioId(int servicioId) {
        this.servicioId = servicioId;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Boolean getSoloSeleccion() {
        return soloSeleccion;
    }

    public void setSoloSeleccion(Boolean soloSeleccion) {
        this.soloSeleccion = soloSeleccion;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}

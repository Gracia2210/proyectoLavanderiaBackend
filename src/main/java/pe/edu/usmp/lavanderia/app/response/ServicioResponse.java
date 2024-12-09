package pe.edu.usmp.lavanderia.app.response;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class ServicioResponse {
    private int id;
    private String descripcion;
    private Boolean enabled;

    // Getters y Setters
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}

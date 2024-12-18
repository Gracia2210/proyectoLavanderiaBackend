package pe.edu.usmp.lavanderia.app.request;

public class EditarSecuenciaRequest {
    private String serie;
    private Integer secuencia;

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Integer getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }
}

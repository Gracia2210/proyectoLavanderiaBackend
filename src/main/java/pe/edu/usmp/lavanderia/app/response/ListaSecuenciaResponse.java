package pe.edu.usmp.lavanderia.app.response;

public class ListaSecuenciaResponse {
    private Integer idSerie;
    private String serie;
    private Integer idSecuencia;
    private Integer secuencia;

    public Integer getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Integer idSerie) {
        this.idSerie = idSerie;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Integer getIdSecuencia() {
        return idSecuencia;
    }

    public void setIdSecuencia(Integer idSecuencia) {
        this.idSecuencia = idSecuencia;
    }

    public Integer getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }
}

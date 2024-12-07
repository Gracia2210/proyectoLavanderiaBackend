package pe.edu.usmp.lavanderia.app.response;

public class TopUsuariosConsultasCategoriasResponse {
    private String categoria;
    private Integer consultas;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getConsultas() {
        return consultas;
    }

    public void setConsultas(Integer consultas) {
        this.consultas = consultas;
    }
}

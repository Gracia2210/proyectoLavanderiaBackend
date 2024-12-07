package pe.edu.usmp.lavanderia.app.request;

public class ConsultaRequest {

    private Integer categoria;
    private String msjPersona;
    private String msjBot;
    private Boolean openai;

    public ConsultaRequest(Integer categoria, String msjPersona, String msjBot, Boolean openai) {
        this.categoria = categoria;
        this.msjPersona = msjPersona;
        this.msjBot = msjBot;
        this.openai = openai;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public String getMsjPersona() {
        return msjPersona;
    }

    public void setMsjPersona(String msjPersona) {
        this.msjPersona = msjPersona;
    }

    public String getMsjBot() {
        return msjBot;
    }

    public void setMsjBot(String msjBot) {
        this.msjBot = msjBot;
    }

    public Boolean getOpenai() {
        return openai;
    }

    public void setOpenai(Boolean openai) {
        this.openai = openai;
    }
}

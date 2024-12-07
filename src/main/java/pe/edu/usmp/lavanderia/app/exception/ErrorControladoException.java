package pe.edu.usmp.lavanderia.app.exception;

@SuppressWarnings("serial")
public class ErrorControladoException extends Exception  {
    private Integer cod;
    private String mensaje;

    public ErrorControladoException(Integer cod, String mensaje) {
        this.cod = cod;
        this.mensaje = mensaje;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}

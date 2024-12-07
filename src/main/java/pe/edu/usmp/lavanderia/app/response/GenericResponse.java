package pe.edu.usmp.lavanderia.app.response;

public class GenericResponse {
	private Integer cod;
	private String mensaje;

	public GenericResponse() {
		this(-1, null);
	}

	public GenericResponse(Integer cod, String mensaje) {
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

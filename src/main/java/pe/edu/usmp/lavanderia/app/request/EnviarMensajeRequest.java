package pe.edu.usmp.lavanderia.app.request;

public class EnviarMensajeRequest {

	private Integer categoria;

	private String mensaje;

	private String prompt;

	public Integer getCategoria() {return categoria;}

	public void setCategoria(Integer categoria) {this.categoria = categoria;}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
}

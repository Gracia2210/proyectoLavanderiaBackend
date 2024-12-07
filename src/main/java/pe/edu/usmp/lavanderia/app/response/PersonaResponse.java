package pe.edu.usmp.lavanderia.app.response;

import java.util.List;

public class PersonaResponse {
    private Long id;
    private String usuario;
    private Long usuario_id;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String sexo;
    private String email;
    private String telefono;
    private List<RolesResponse> roles;
    
    public Long getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public Long getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Long usuario_id) {
		this.usuario_id = usuario_id;
	}

	public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

	public List<RolesResponse> getRoles() {
		return roles;
	}

	public void setRoles(List<RolesResponse> roles) {
		this.roles = roles;
	}
    
}

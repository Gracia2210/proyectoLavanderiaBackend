package pe.edu.usmp.lavanderia.app.response;

import pe.edu.usmp.lavanderia.app.utils.Constantes;

import java.util.List;

public class ListModelResponse<T> extends GenericResponse {
    private String mensajeTxt;
    private String icon;
    private List<T> list;

    public ListModelResponse() {
        super.setCod(-1);
        this.mensajeTxt = Constantes.NAME_SYSTEM;
        this.icon = "error";
    }

    public String getMensajeTxt() {
        return mensajeTxt;
    }

    public void setMensajeTxt(String mensajeTxt) {
        this.mensajeTxt = mensajeTxt;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

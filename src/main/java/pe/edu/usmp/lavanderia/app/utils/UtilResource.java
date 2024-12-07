package pe.edu.usmp.lavanderia.app.utils;

import java.lang.reflect.Field;
import java.util.*;

public class UtilResource {

    public static String validNullString(String data) {
        if (data == null || data.isEmpty()) {
            return "";
        }
        return data.trim();
    }

    public static int validarNullSize(String dato) {
        if (dato == null || dato.isEmpty()) {
            return 0;
        } else {
            return dato.trim().length();
        }
    }

    public static String obtenerExtensionArchivo(String nombreArchivo) {
        return Optional.ofNullable(nombreArchivo)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(nombreArchivo.lastIndexOf(".") + 1))
                .orElse("desconocido");
    }
    
    public static String contrasenaVacia(String contra) {
    	if(validNullString(contra).length()>0) {
    	   	return contra;
    	}
		return Constantes.CONTRASENA_DEFAULT;

    }
    public static List<Map<String, Object>> convertirDtoAMap(List<?> listaDto) {
        List<Map<String, Object>> listaMapas = new ArrayList<>();
        int contador = 1;

        for (Object dto : listaDto) {
            Map<String, Object> mapa = new HashMap<>();
            Field[] campos = dto.getClass().getDeclaredFields();
            for (Field campo : campos) {
                campo.setAccessible(true);
                try {
                    mapa.put(campo.getName(), campo.get(dto));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error al acceder al campo: " + campo.getName(), e);
                }
            }
            mapa.put("numero", contador++);
            listaMapas.add(mapa);
        }

        return listaMapas;
    }

}

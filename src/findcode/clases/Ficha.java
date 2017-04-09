package findcode.clases;

import java.util.HashMap;

public class Ficha {

    private int iD;
    private String titulo;
    private Sintaxis sintaxis;
    private String descripcion;
    private Sintaxis ejemplo;
    private String lenguajeProgramacion;
    private HashMap<Integer, Ficha> fichasAsociadas;
    private HashMap<Integer, Calificacion> calificacion;

    public void crear() {
    
    }
    
    public static Ficha cargar() {
        return null;
    }
    
    public void editar() {
    
    }
    
    public void borrar() {
    
    }
    
}
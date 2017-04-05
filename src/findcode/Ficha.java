package findcode;

import java.util.HashMap;

public class Ficha {

    private String titulo;
    private Sintaxis sintaxis;
    private String descripcion;
    private Sintaxis ejemplo;
    private String ID;
    private HashMap<String, Ficha> fichasAsociadas;
    private double calificacion;
    private String lenguajeProgramacion;

    public Ficha() {
       
    }
}
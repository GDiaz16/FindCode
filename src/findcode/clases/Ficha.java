package findcode.clases;

import java.util.HashMap;

public class Ficha {

    private int iD;
    private String titulo;
    private Sintaxis sintaxis;
    private String descripcion;
    private Sintaxis ejemplo;
    private HashMap<String, Ficha> fichasAsociadas;
    private double calificacion;
    private String lenguajeProgramacion;

    public Ficha() {

    }
}
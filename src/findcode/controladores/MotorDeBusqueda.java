package findcode.controladores;

import findcode.clases.Ficha;
import findcode.clases.Usuario;
import java.sql.SQLException;
import java.util.HashMap;

public class MotorDeBusqueda {

    private String busqueda;
    private String lenguaje;
    Usuario usuario;

    public MotorDeBusqueda(String busqueda, String lenguaje) {
        this.busqueda = busqueda;
        this.lenguaje = lenguaje;
    }
    
    public MotorDeBusqueda(Usuario usuario) {
        this.usuario = usuario;
    }

    public HashMap<Integer, Ficha> buscarPorTitulo() {

        HashMap<Integer, Ficha> fichas = new HashMap<>();

        try {

            MySQL db = new MySQL();

            String query = " select * from TFichas where"
                    + " titulo = ? AND iDLenguaje = ? ";

            db.setSentencia(query);
            db.getSentencia().setString(1, busqueda);
            db.getSentencia().setString(2, lenguaje);

            db.setResultados(db.getSentencia().executeQuery());

            while (db.getResultados().next()) {

                Ficha ficha = new Ficha(db.getResultados().getInt("iD"),
                        db.getResultados().getString("titulo"),
                        db.getResultados().getString("descripcion"),
                        db.getResultados().getString("ejemplo"),
                        db.getResultados().getString("iDUsuario"),
                        db.getResultados().getString("iDLenguaje"));
                
                fichas.put(db.getResultados().getInt("iD"), ficha);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return fichas;
        
    }
    
    public HashMap<Integer, Ficha> buscarPorUsuario() {

        HashMap<Integer, Ficha> fichas = new HashMap<>();

        try {

            MySQL db = new MySQL();

            String query = " select * from TFichas where"
                    + " iDUsuario = ? ";

            db.setSentencia(query);
            db.getSentencia().setString(1, usuario.getCorreo());

            db.setResultados(db.getSentencia().executeQuery());

            while (db.getResultados().next()) {

                Ficha ficha = new Ficha(db.getResultados().getInt("iD"),
                        db.getResultados().getString("titulo"),
                        db.getResultados().getString("descripcion"),
                        db.getResultados().getString("ejemplo"),
                        db.getResultados().getString("iDUsuario"),
                        db.getResultados().getString("iDLenguaje"));
                
                fichas.put(db.getResultados().getInt("iD"), ficha);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return fichas;
        
    }

}

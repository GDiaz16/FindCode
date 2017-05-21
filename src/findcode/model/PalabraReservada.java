package findcode.model;

import findcode.controladores.MySQL;
import java.sql.SQLException;
import java.util.ArrayList;

public class PalabraReservada {

    int iD;
    String palabraReservada;
    String idLenguaje;

    public PalabraReservada() {
        
    }
    
    public PalabraReservada(String palabraReservada, String idLenguaje) {
        this.palabraReservada = palabraReservada;
        this.idLenguaje = idLenguaje;
    }

    public String getPalabraReservada() {
        return palabraReservada;
    }

    public void setPalabraReservada(String palabraReservada) {
        this.palabraReservada = palabraReservada;
    }

    public String getIdLenguaje() {
        return idLenguaje;
    }

    public void setIdLenguaje(String idLenguaje) {
        this.idLenguaje = idLenguaje;
    }

    public void crear() {

        try {

            MySQL db = new MySQL();

            String query = " insert into TPalabraReservada (nombre, iDLenguaje)"
                    + " values (?, ?)";

            db.setSentencia(query);
            db.getSentencia().setString(1, palabraReservada);
            db.getSentencia().setString(1, idLenguaje);

            db.getSentencia().execute();
            db.conexion().close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public boolean cargar() {

        try {

            MySQL db = new MySQL();

            String query = " select * from TPalabraReservada where "
                    + "iD = ?";

            db.setSentencia(query);
            db.getSentencia().setInt(1, iD);

            db.setResultados(db.getSentencia().executeQuery());

            if (db.numeroColumnas() == 0) {
                return false;
            }

            while (db.getResultados().next()) {
                palabraReservada = db.getResultados().getString("nombre");
                idLenguaje = db.getResultados().getString("idLenguaje");
            }

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;

    }
    
    public static ArrayList<String> cargarPorLenguaje(String iDLenguaje) {

        ArrayList<String> palabrasReservadas = new ArrayList<>();
        
        try {

            MySQL db = new MySQL();

            String query = " select * from TPalabraReservada where "
                    + "idLenguaje = ?";

            db.setSentencia(query);
            db.getSentencia().setString(1, iDLenguaje);

            db.setResultados(db.getSentencia().executeQuery());

            while (db.getResultados().next()) {
                
                palabrasReservadas.add(db.getResultados().getString("nombre"));
                
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return palabrasReservadas;

    }

    public void editar() {

        try {

            MySQL db = new MySQL();

            String query = "UPDATE TPalabraReservada "
                    + "SET nombre = ?, "
                    + "iDLenguaje = ? "
                    + "WHERE iD = ? ";

            db.setSentencia(query);
            db.getSentencia().setString(1, palabraReservada);
            db.getSentencia().setString(2, idLenguaje);
            db.getSentencia().setInt(3, iD);

            db.getSentencia().execute();
            db.conexion().close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void borrar() {

        try {

            MySQL db = new MySQL();

            String query = "DELETE FROM TPalabraReservada "
                    + "WHERE iD = ? ";

            db.setSentencia(query);
            db.getSentencia().setInt(1, iD);

            db.getSentencia().execute();
            db.conexion().close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}

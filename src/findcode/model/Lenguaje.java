package findcode.model;

import findcode.controladores.MySQL;
import java.sql.SQLException;
import java.util.ArrayList;

public class Lenguaje {

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void crear() {

        try {

            MySQL db = new MySQL();

            String query = " insert into TLenguaje (iD)"
                    + " values (?)";

            db.setSentencia(query);
            db.getSentencia().setString(1, nombre);

            db.getSentencia().execute();
            db.conexion().close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public boolean cargar() {

        try {

            MySQL db = new MySQL();

            String query = " select * from TLenguaje where "
                    + "iD = ?";

            db.setSentencia(query);
            db.getSentencia().setString(1, nombre);

            db.setResultados(db.getSentencia().executeQuery());

            if (db.numeroColumnas() == 0) {
                return false;
            }

            while (db.getResultados().next()) {
                nombre = db.getResultados().getString("iD");
            }

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;

    }
    
    public static ArrayList<String> cargarTodos() {

        ArrayList<String> resultados = new ArrayList<>();
        
        try {

            MySQL db = new MySQL();

            String query = " select * from TLenguaje";

            db.setSentencia(query);
            db.setResultados(db.getSentencia().executeQuery());

            while (db.getResultados().next()) {
                resultados.add(db.getResultados().getString("iD"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resultados;

    }
    
    public void editar(String nombreNuevo) {

        try {

            MySQL db = new MySQL();

            String query = "UPDATE TLenguaje "
                    + "SET iD = ? "
                    + "WHERE iD = ? ";

            db.setSentencia(query);
            db.getSentencia().setString(1, nombreNuevo);
            db.getSentencia().setString(2, nombre);
            
            db.getSentencia().execute();
            db.conexion().close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void borrar() {

        try {

            MySQL db = new MySQL();

            String query = "DELETE FROM TLenguaje "
                    + "WHERE iD = ? ";

            db.setSentencia(query);
            db.getSentencia().setString(1, nombre);

            db.getSentencia().execute();
            db.conexion().close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
}

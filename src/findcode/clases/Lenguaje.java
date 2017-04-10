package findcode.clases;

import java.sql.SQLException;

public class Lenguaje {

    private String nombre;

    public Lenguaje(String nombre){
        this.nombre = nombre;
    }

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

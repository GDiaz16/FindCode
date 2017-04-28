package findcode.clases;

import findcode.controladores.MySQL;
import java.sql.SQLException;
import java.util.ArrayList;

public class Calificacion {
    
    int iD;
    int calificacion;
    int iDFicha;
    String iDUsuario;

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public int getiDFicha() {
        return iDFicha;
    }

    public void setiDFicha(int iDFicha) {
        this.iDFicha = iDFicha;
    }

    public String getiDUsuario() {
        return iDUsuario;
    }

    public void setiDUsuario(String iDUsuario) {
        this.iDUsuario = iDUsuario;
    }
    
    public void crear() {

        try {

            MySQL db = new MySQL();

            String query = " insert into TCalificacion (calificacion, iDFicha, iDUsuario)"
                    + " values (?, ?, ?)";

            db.setSentencia(query);
            db.getSentencia().setInt(1, calificacion);
            db.getSentencia().setInt(2, iDFicha);
            db.getSentencia().setString(3, iDUsuario);

            db.getSentencia().execute();
            db.conexion().close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public boolean cargar() {

        try {

            MySQL db = new MySQL();

            String query = " select * from TCalificacion where"
                    + " iDFicha = ?";

            db.setSentencia(query);
            db.getSentencia().setInt(1, iD);

            db.setResultados(db.getSentencia().executeQuery());

            if (db.numeroColumnas() == 0) {
                return false;
            }

            while (db.getResultados().next()) {
                iD = db.getResultados().getInt("iD");
                calificacion = db.getResultados().getInt("calificacion");
                iDFicha = db.getResultados().getInt("iDFicha");
                iDUsuario = db.getResultados().getString("iDUsuario");

            }

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;

    }
    
    public boolean cargarPorFichaYUsuario() {

        try {

            MySQL db = new MySQL();

            String query = " select * from TCalificacion where"
                    + " iDFicha = ? AND iDUsuario = ? ";

            db.setSentencia(query);
            db.getSentencia().setInt(1, iDFicha);
            db.getSentencia().setString(2, iDUsuario);

            db.setResultados(db.getSentencia().executeQuery());

            if (db.numeroColumnas() == 0) {
                return false;
            }

            while (db.getResultados().next()) {
                iD = db.getResultados().getInt("iD");
                calificacion = db.getResultados().getInt("calificacion");
                iDFicha = db.getResultados().getInt("iDFicha");
                iDUsuario = db.getResultados().getString("iDUsuario");

            }

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;

    }
    
    public ArrayList<Calificacion> cargarPorFicha(int iDFicha) {

        ArrayList<Calificacion> calificaciones = new ArrayList<>();
        
        try {

            MySQL db = new MySQL();

            String query = " select * from TCalificacion where"
                    + " iDFicha = ?";

            db.setSentencia(query);
            db.getSentencia().setInt(1, iDFicha);

            db.setResultados(db.getSentencia().executeQuery());

            while (db.getResultados().next()) {
                
                Calificacion calificacion1 = new Calificacion();
                calificacion1.setiD(db.getResultados().getInt("iD"));
                calificacion1.setCalificacion(db.getResultados().getInt("calificacion"));
                calificacion1.setiDFicha(db.getResultados().getInt("iDFicha"));
                calificacion1.setiDUsuario(db.getResultados().getString("iDUsuario"));
                
                calificaciones.add(calificacion1);

            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return calificaciones;

    }

    public void editar() {

        try {

            MySQL db = new MySQL();

            String query = "UPDATE TCalificacion "
                    + "SET calificacion = ? "
                    + "WHERE iD = ? ";

            db.setSentencia(query);
            db.getSentencia().setInt(1, calificacion);
            db.getSentencia().setInt(2, iD);

            db.getSentencia().execute();
            db.conexion().close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void borrar() {

        try {

            MySQL db = new MySQL();

            String query = "DELETE FROM TCalificacion "
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

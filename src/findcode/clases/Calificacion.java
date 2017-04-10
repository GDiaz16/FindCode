package findcode.clases;

import java.sql.SQLException;

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
                    + " iD = ?";

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

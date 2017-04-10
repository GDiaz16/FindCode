package findcode.clases;

import java.sql.SQLException;

public class Comentario {
    
    int iD;
    String iDUsuario;
    int iDFicha;
    String mensaje;

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getiDUsuario() {
        return iDUsuario;
    }

    public void setiDUsuario(String iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public int getiDFicha() {
        return iDFicha;
    }

    public void setiDFicha(int iDFicha) {
        this.iDFicha = iDFicha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public void crear() {

        try {

            MySQL db = new MySQL();

            String query = " insert into TComentarios (mensaje, iDUsuario, iDFicha)"
                    + " values (?, ?, ?)";

            db.setSentencia(query);
            db.getSentencia().setString(1, mensaje);
            db.getSentencia().setString(2, iDUsuario);
            db.getSentencia().setInt(3, iDFicha);

            db.getSentencia().execute();
            db.conexion().close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public boolean cargar() {

        try {

            MySQL db = new MySQL();

            String query = " select * from TComentarios where"
                    + " iD = ?";

            db.setSentencia(query);
            db.getSentencia().setInt(1, iD);

            db.setResultados(db.getSentencia().executeQuery());

            if (db.numeroColumnas() == 0) {
                return false;
            }

            while (db.getResultados().next()) {
                iD = db.getResultados().getInt("iD");
                mensaje = db.getResultados().getString("mensaje");
                iDUsuario = db.getResultados().getString("iDUsuario");
                iDFicha = db.getResultados().getInt("iDFicha");

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

            String query = "UPDATE TComentarios "
                    + "SET mensaje = ? "
                    + "WHERE iD = ? ";

            db.setSentencia(query);
            db.getSentencia().setString(1, mensaje);
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

            String query = "DELETE FROM TComentarios "
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

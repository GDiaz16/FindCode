package findcode.clases;

import java.sql.SQLException;

public class PalabraReservada {

    String palabraReservada;
    String idLenguaje;

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

            String query = " insert into TPalabraReservada (iD, iDLenguaje)"
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
            db.getSentencia().setString(1, palabraReservada);

            db.setResultados(db.getSentencia().executeQuery());

            if (db.numeroColumnas() == 0) {
                return false;
            }

            while (db.getResultados().next()) {
                palabraReservada = db.getResultados().getString("iD");
                idLenguaje = db.getResultados().getString("idLenguaje");
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

            String query = "UPDATE TPalabraReservada "
                    + "SET iD = ?, "
                    + "iDLenguaje = ? "
                    + "WHERE iD = ? ";

            db.setSentencia(query);
            db.getSentencia().setString(1, nombreNuevo);
            db.getSentencia().setString(2, idLenguaje);
            db.getSentencia().setString(3, palabraReservada);

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
            db.getSentencia().setString(1, palabraReservada);

            db.getSentencia().execute();
            db.conexion().close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}

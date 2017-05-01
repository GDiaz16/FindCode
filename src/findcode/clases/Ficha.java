package findcode.clases;

import findcode.controladores.MySQL;
import java.sql.SQLException;
import java.util.ArrayList;

public class Ficha {

    private int iD;
    private String titulo;
    private String descripcion;
    private String ejemplo;
    private String iDUsuario;
    private String iDLenguaje;
    
    public Ficha() {

        
    }

    public Ficha(int iD, String titulo, String descripcion, String ejemplo, String iDUsuario, String iDLenguaje) {
        this.iD = iD;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.ejemplo = ejemplo;
        this.iDUsuario = iDUsuario;
        this.iDLenguaje = iDLenguaje;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEjemplo() {
        return ejemplo;
    }

    public void setEjemplo(String ejemplo) {
        this.ejemplo = ejemplo;
    }

    public String getiDUsuario() {
        return iDUsuario;
    }

    public void setiDUsuario(String iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public String getiDLenguaje() {
        return iDLenguaje;
    }

    public void setiDLenguaje(String iDLenguaje) {
        this.iDLenguaje = iDLenguaje;
    }

    public void crear() {

        try {

            MySQL db = new MySQL();

            String query = " insert into TFichas (titulo, descripcion, ejemplo, iDUsuario, iDLenguaje)"
                    + " values (?, ?, ?, ?, ?)";

            db.setSentencia(query);
            db.getSentencia().setString(1, titulo);
            db.getSentencia().setString(2, descripcion);
            db.getSentencia().setString(3, ejemplo);
            db.getSentencia().setString(4, iDUsuario);
            db.getSentencia().setString(5, iDLenguaje);

            db.getSentencia().execute();
            db.conexion().close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public boolean cargar() {

        try {

            MySQL db = new MySQL();

            String query = " select * from TFichas where"
                    + " iD = ?";

            db.setSentencia(query);
            db.getSentencia().setInt(1, iD);

            db.setResultados(db.getSentencia().executeQuery());

            if (db.numeroColumnas() == 0) {
                return false;

            }

            while (db.getResultados().next()) {

                iD = db.getResultados().getInt("iD");
                titulo = db.getResultados().getString("titulo");
                descripcion = db.getResultados().getString("descripcion");
                iDUsuario = db.getResultados().getString("iDUsuario");
                iDLenguaje = db.getResultados().getString("iDLenguaje");

            }

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
    
    public boolean cargarIDIngresado() {

        try {

            MySQL db = new MySQL();

            String query = " select MAX(iD) as maximo from TFichas where iDUsuario = ? ";

            db.setSentencia(query);
            db.getSentencia().setString(1, iDUsuario);

            db.setResultados(db.getSentencia().executeQuery());

            if (db.numeroColumnas() == 0) {
                return false;

            }

            while (db.getResultados().next()) {

                iD = db.getResultados().getInt("maximo");

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

            String query = "UPDATE TFichas "
                    + "SET titulo = ?, "
                    + " descripcion = ?, "
                    + " ejemplo = ? ,"
                    + " iDLenguaje = ? "
                    + "WHERE iD = ? ";

            db.setSentencia(query);
            db.getSentencia().setString(1, titulo);
            db.getSentencia().setString(2, descripcion);
            db.getSentencia().setString(3, ejemplo);
            db.getSentencia().setString(4, iDLenguaje);
            db.getSentencia().setInt(5, iD);

            db.getSentencia().execute();
            db.conexion().close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void borrar() {

        try {

            MySQL db = new MySQL();

            String query = "DELETE FROM TFichas "
                    + "WHERE iD = ? ";

            db.setSentencia(query);
            db.getSentencia().setInt(1, iD);

            db.getSentencia().execute();
            db.conexion().close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
    public static ArrayList<Ficha> buscarTextoPorLenguaje(String busqueda, String lenguaje) {

        ArrayList<Ficha> fichas = new ArrayList<>();

        try {

            MySQL db = new MySQL();

            String query = " select * from TFichas where"
                    + " titulo LIKE ? AND iDLenguaje = ? ";

            db.setSentencia(query);
            db.getSentencia().setString(1, "%"+busqueda+"%");
            db.getSentencia().setString(2, lenguaje);

            db.setResultados(db.getSentencia().executeQuery());

            while (db.getResultados().next()) {

                Ficha ficha = new Ficha(db.getResultados().getInt("iD"),
                        db.getResultados().getString("titulo"),
                        db.getResultados().getString("descripcion"),
                        db.getResultados().getString("ejemplo"),
                        db.getResultados().getString("iDUsuario"),
                        db.getResultados().getString("iDLenguaje"));
                
                fichas.add( ficha);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return fichas;
        
    }
    
    public ArrayList<Ficha> buscarFichasRelacionadas() {

        ArrayList<Ficha> fichas;
        Usuario usuario = new Usuario();
        usuario.setCorreo(iDUsuario);
        fichas = usuario.buscarFichas();
        return fichas;
        
    }

}
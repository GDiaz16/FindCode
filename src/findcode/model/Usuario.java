package findcode.model;

import findcode.controladores.MySQL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Usuario {

    private String correo;
    private String nickname;
    private String nombre;
    private String password;

    public Usuario() {
    }
    
    public Usuario(String correo, String password) {

        this.correo = correo;
        this.password = password;

    }

    public Usuario(String correo, String nickname, String nombre, String password) {

        this.correo = correo;
        this.nickname = nickname;
        this.nombre = nombre;
        this.password = password;

    }
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void crear() {

        try {

            MySQL db = new MySQL();

            String query = " insert into TUsuarios (iD, nickName, nombre, password)"
                    + " values (?, ?, ?, ?)";

            db.setSentencia(query);
            db.getSentencia().setString(1, correo);
            db.getSentencia().setString(2, nickname);
            db.getSentencia().setString(3, nombre);
            db.getSentencia().setString(4, password);

            db.getSentencia().execute();
            db.conexion().close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public boolean cargar() {

        try {

            MySQL db = new MySQL();

            String query = " select * from TUsuarios where"
                    + " (iD = ? OR nickName = ?) AND password = ?";

            db.setSentencia(query);
            db.getSentencia().setString(1, correo);
            db.getSentencia().setString(2, correo);
            db.getSentencia().setString(3, password);

            db.setResultados(db.getSentencia().executeQuery());

            if (db.numeroColumnas() == 0) {
                return false;
            }

            while (db.getResultados().next()) {
                correo = db.getResultados().getString("iD");
                nickname = db.getResultados().getString("nickName");
                nombre = db.getResultados().getString("nombre");
                password = db.getResultados().getString("password");

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

            String query = "UPDATE TUsuarios "
                    + "SET nickname = ?, "
                    + "nombre = ?, "
                    + "password = ? "
                    + "WHERE iD = ? ";

            db.setSentencia(query);
            db.getSentencia().setString(1, nickname);
            db.getSentencia().setString(2, nombre);
            db.getSentencia().setString(3, password);
            db.getSentencia().setString(4, correo);

            db.getSentencia().execute();
            db.conexion().close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void borrar() {

        try {

            MySQL db = new MySQL();

            String query = "DELETE FROM TUsuarios "
                    + "WHERE iD = ? ";

            db.setSentencia(query);
            db.getSentencia().setString(1, correo);

            db.getSentencia().execute();
            db.conexion().close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public ArrayList<Ficha> buscarFichas() {

        ArrayList<Ficha> fichas = new ArrayList<>();

        try {

            MySQL db = new MySQL();

            String query = " select * from TFichas where"
                    + " iDUsuario = ? ";

            db.setSentencia(query);
            db.getSentencia().setString(1, correo);

            db.setResultados(db.getSentencia().executeQuery());

            while (db.getResultados().next()) {

                Ficha ficha = new Ficha(db.getResultados().getInt("iD"),
                        db.getResultados().getString("titulo"),
                        db.getResultados().getString("descripcion"),
                        db.getResultados().getString("ejemplo"),
                        db.getResultados().getString("iDUsuario"),
                        db.getResultados().getString("iDLenguaje"));
                
                fichas.add(ficha);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return fichas;
        
    }

}

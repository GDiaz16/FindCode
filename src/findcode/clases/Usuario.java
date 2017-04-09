package findcode.clases;

import java.sql.SQLException;

public class Usuario {

    private String correo;
    private String nickname;
    private String nombre;
    private String password;

    public Usuario(String correo, String nickname, String nombre, String password) {
        
        this.correo = correo;
        this.nickname = nickname;
        this.nombre = nombre;
        this.password = password;
        
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

    public static Usuario cargar() {
        return null;
    }

    public void editar() {

    }

    public void borrar() {

    }

}

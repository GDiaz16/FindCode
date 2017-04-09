package findcode.clases;

import java.sql.SQLException;

public class Usuario {

    private String correo;
    private String nickname;
    private String nombre;
    private String password;

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

    public String getNickname() {
        return nickname;
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

            if(db.numeroColumnas() == 0){
                return false;
            }
            
            while (db.getResultados().next()) {
                correo = db.getResultados().getString("iD");
                nickname = db.getResultados().getString("nickName");
                nombre = db.getResultados().getString("nombre");
                password= db.getResultados().getString("password");

            }

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
        
    }

    public void editar() {

    }

    public void borrar() {
        
    }

}

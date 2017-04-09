package findcode.clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {

    Connection conexion;

    public MySQL() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/findcode", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void crearTablas() {

        try {
            
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(leerArchivo("Usuario"));
            stmt.executeUpdate(leerArchivo("Lenguaje"));
            stmt.executeUpdate(leerArchivo("PalabraReservada"));
            stmt.executeUpdate(leerArchivo("Ficha"));
            stmt.executeUpdate(leerArchivo("Comentario"));
            stmt.executeUpdate(leerArchivo("Calificacion"));
            stmt.close();
            conexion.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public String leerArchivo(String nombre) {

        File file = new File("ArchivosMySQL/" + nombre + ".txt");
        String resultado = "";

        try {

            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);

            while (true) {

                String valor = br.readLine();

                if (valor == null) {
                    break;
                } else {
                    resultado += valor;
                }

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return resultado;

    }

}

package findcode.clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite {

    Connection connection = null;

    public SQLite() {

        try {

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");

        } catch (ClassNotFoundException | SQLException ex) {

            ex.printStackTrace();

        }

    }

    public String leerTabla(String nombre){
        
        File file = new File("tablas/" + nombre + ".txt");
        String resultado = "";

        try {

            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            
            while (true) {
                
                String valor = br.readLine();
                
                if(valor == null){
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

    public void crearTablas() {

        try {
            
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(leerTabla("Usuario"));
            stmt.close();
            connection.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}

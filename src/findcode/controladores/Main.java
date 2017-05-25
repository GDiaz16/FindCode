package findcode.controladores;

import findcode.GUI.Inicio;
import java.awt.Color;
import java.io.File;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        
        // Crear ventana
        JFrame frame = new JFrame();
        frame.setBackground(Color.decode("#58D3F7"));
        frame.add(new Inicio());
        frame.setSize(650, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        findcode.controladores.Utilidades.asignarFondoAnimado(frame, new File("imagenes/fondo"));
        findcode.controladores.Utilidades.asignarFondoFijo(frame, new File("imagenes/fondo.jpg"));
        frame.setVisible(true);
        
        // Crear tablas si no existen
        // MySQL db = new MySQL();
        // db.crearTablas();
        
    }
    
}

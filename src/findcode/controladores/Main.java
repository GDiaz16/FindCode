package findcode.controladores;

import findcode.GUI.Inicio;
import java.awt.CardLayout;
import java.awt.Color;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    public static void main(String[] args) {
        
        /*// Dar apariencia de windows
        try {
        javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
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

package findcode.controladores;

import findcode.GUI.Inicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    public static void main(String[] args) {
        
        // Dar apariencia de windows
        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Crear ventana
        JFrame frame = new JFrame();
        frame.add(new Inicio());
        frame.setSize(650, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // Crear tablas si no existen
        // MySQL db = new MySQL();
        // db.crearTablas();
        
        //findcode.clases.Ingrediente i = new findcode.clases.Ingrediente();
        //i.setTitulo("Instanciar");
        //i.setDescripcion("Instancia de la primera clase de tipo tal");
        //i.setPosInicial(1);
        //i.setPosFinal(5);
        //i.setiDFicha(1);
        //i.crear();
        
    }
    
}

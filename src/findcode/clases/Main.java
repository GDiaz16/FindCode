package findcode.clases;

import codebook.GUI.Inicio;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        
        JFrame frame = new JFrame();
        frame.add(new Inicio());
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(3);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
}

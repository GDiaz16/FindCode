package findcode.controladores;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.lang.reflect.Field;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class Utilidades {
    
    public static void personalizarCampo(JTextComponent jTextField, String textoDefault, String texto){
        
        // Dar color gris
        jTextField.setForeground(Color.decode("#D8D8D8"));
        jTextField.setText(textoDefault);
        
        if(texto.trim().equals("")){
            
            // Borrar texto al hacer click una vez
            jTextField.addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusGained(java.awt.event.FocusEvent evt) {
                    jTextField.setBackground(Color.white);
                    jTextField.setForeground(Color.decode("#000000"));
                    jTextField.setText("");
                    jTextField.removeFocusListener(this);
                }
            });
            
        } else {
            
            jTextField.setBackground(Color.white);
            jTextField.setForeground(Color.decode("#000000"));
            jTextField.setText(texto);
            
        }
        
        // Mostrar texto por defecto si el campo esta vacio
        jTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                
                if (jTextField.getText().trim().equals("")) {
                    jTextField.setBackground(Color.white);
                    jTextField.setForeground(Color.decode("#D8D8D8"));
                    jTextField.setText(textoDefault);
                    jTextField.addFocusListener(new java.awt.event.FocusAdapter() {
                        @Override
                        public void focusGained(java.awt.event.FocusEvent evt) {
                            jTextField.setBackground(Color.white);
                            jTextField.setForeground(Color.decode("#000000"));
                            jTextField.setText("");
                            jTextField.removeFocusListener(this);
                        }
                    });
                }
                
            }
        });
        
    }
    
    public static boolean validarCampo(JTextComponent jTextField){
        if(jTextField.getText().trim().equals("") || 
                jTextField.getForeground().equals(Color.decode("#D8D8D8"))) {
            jTextField.setBackground(Color.decode("#CEF6CE"));
            return false;
        }
        return true;
    }
    
    public static boolean compararCampos(JTextComponent jTextField1, JTextComponent jTextField2){
        if(!jTextField1.getText().equals(jTextField2.getText())) {
            jTextField1.setBackground(Color.decode("#CEF6CE"));
            jTextField2.setBackground(Color.decode("#CEF6CE"));
            
            jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusGained(java.awt.event.FocusEvent evt) {
                    jTextField1.setBackground(Color.white);
                }
            });
            
            jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusGained(java.awt.event.FocusEvent evt) {
                    jTextField2.setBackground(Color.white);
                }
            });
            
            return false;
        }
        return true;
    }
    
    public static void cambiarPantalla(JComponent anterior, JComponent nuevo){
        
        anterior.getParent().add(nuevo);
        nuevo.getParent().remove(anterior);
        nuevo.getParent().validate();
        nuevo.updateUI();
        nuevo.requestFocus();
        
    }
    
    public static void asignarFondo(JFrame frame, String rutaImagen) {

        // Fondo inicial
        ((JPanel) frame.getContentPane()).setOpaque(false);
        ImageIcon imagen = new ImageIcon(rutaImagen);
        imagen = new ImageIcon(imagen.getImage().getScaledInstance(frame.getWidth(),
                frame.getHeight(),
                Image.SCALE_DEFAULT));
        JLabel fondo = new JLabel();
        fondo.setIcon(imagen);
        fondo.setHorizontalAlignment(JLabel.RIGHT);
        fondo.setVerticalAlignment(JLabel.BOTTOM);
        frame.getLayeredPane().add(fondo, JLayeredPane.FRAME_CONTENT_LAYER);
        fondo.setBounds(0, 0, frame.getWidth(), frame.getHeight());

        // Fondo al redimencionar
        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
        @Override
        public void componentResized(java.awt.event.ComponentEvent evt) {
        
        fondo.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        
        }
        });

    }
    
}

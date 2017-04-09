package findcode.clases;

import java.awt.Color;
import javax.swing.JTextField;

public class Utilidades {
    
    public static void personalizarCampo(JTextField jTextField, String textoDefault){
        
        // Dar color gris
        jTextField.setForeground(Color.decode("#D8D8D8"));
        jTextField.setText(textoDefault);
        
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
        
        // Mostrar texto por defecto si el campo esta vacio
        jTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                
                if (jTextField.getText().equals("")) {
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
    
    public static boolean validarCampo(JTextField jTextField){
        if(jTextField.getText().equals("") || 
                jTextField.getForeground().equals(Color.decode("#D8D8D8"))) {
            jTextField.setBackground(Color.red);
            return false;
        }
        return true;
    }
    
    public static boolean compararCampos(JTextField jTextField1, JTextField jTextField2){
        if(!jTextField1.getText().equals(jTextField2.getText())) {
            jTextField1.setBackground(Color.red);
            jTextField2.setBackground(Color.red);
            
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
    
}

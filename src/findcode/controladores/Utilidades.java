package findcode.controladores;

import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

public class Utilidades {
    
    public static void personalizarCampo(JTextComponent jTextField, String textoDefault, String texto){
        
        // Dar color gris
        jTextField.setForeground(Color.decode("#D8D8D8"));
        jTextField.setText(textoDefault);
        
        if(texto.equals("")){
            
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
    
    public static boolean validarCampo(JTextComponent jTextField){
        if(jTextField.getText().equals("") || 
                jTextField.getForeground().equals(Color.decode("#D8D8D8"))) {
            jTextField.setBackground(Color.red);
            return false;
        }
        return true;
    }
    
    public static boolean compararCampos(JTextComponent jTextField1, JTextComponent jTextField2){
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
    
    public static void cambiarPantalla(JComponent anterior, JComponent nuevo){
        
        anterior.getParent().add(nuevo);
        nuevo.getParent().remove(anterior);
        nuevo.getParent().validate();
        nuevo.updateUI();
        nuevo.requestFocusInWindow();
        
    }
    
}

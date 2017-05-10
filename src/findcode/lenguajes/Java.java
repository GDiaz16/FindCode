/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package findcode.lenguajes;

import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;

/**
 *
 * @author FAMILIA
 */
public class Java implements Lenguaje {
    HashSet<String> simbolos = new HashSet<>();
    private JTextPane textCodigo;

    public Java(JTextPane textCodigo) {
        this.textCodigo = textCodigo;
    }
    
    @Override
    public void cargarPalabras() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cargarSimbolos() {
        simbolos.add("(");
        simbolos.add(")");
        simbolos.add(";");
        simbolos.add("=");
        simbolos.add("!");
        simbolos.add("+");
        simbolos.add("-");
        simbolos.add("/");
        simbolos.add("*");
        simbolos.add("%");
        simbolos.add("\"");
        simbolos.add(",");
    }

    @Override
    public void cargarComportamientos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setText() {
          //separador(textCodigo.getText());
        textCodigo.setText("");
        for (String cadena : codigoDesarmado) {
            SimpleAttributeSet simp = formato(cadena);
            try {
                //textCodigo.getStyledDocument().
                textCodigo.getStyledDocument().insertString(textCodigo.getCaretPosition(), cadena, simp);

            } catch (BadLocationException ex) {
                Logger.getLogger(findcode.GUI.Ficha.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

   
    
}

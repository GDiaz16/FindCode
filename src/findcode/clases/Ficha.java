package findcode.clases;

import java.awt.Color;
import java.awt.Font;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.event.KeyEvent;
import javax.swing.JList;
import javax.swing.JPopupMenu;

public class Ficha {
    
    HashSet<String> palabrasClave = new HashSet<>();
    String[] codigoDesarmado;
    DefaultListModel model;
    private JTextPane textCodigo = new JTextPane();
    private JList<String> listaIngredientes = new JList<>();
    private JPopupMenu popUp = new JPopupMenu();

    public void setTextCodigo(JTextPane textCodigo) {
        this.textCodigo = textCodigo;
    }

    public void setListaIngredientes(JList<String> listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
    }
    int caret;

    public void setPopUp(JPopupMenu popUp) {
        this.popUp = popUp;
    }

    public Ficha() {
        cargarPalabras();
        cargarDatos();
    }

    public void textCodigoKeyReleased(java.awt.event.KeyEvent evt) {
        caret = textCodigo.getCaretPosition();
        if (evt.getKeyCode() != KeyEvent.VK_LEFT && evt.getKeyCode() != KeyEvent.VK_RIGHT
                && evt.getKeyCode() != KeyEvent.VK_UP && evt.getKeyCode() != KeyEvent.VK_DOWN) {
            separador(textCodigo.getText());
            setText();
        }
        textCodigo.setCaretPosition(caret);
        setListaIngredientes();
    }

    public void textCodigoMousePressed(java.awt.event.MouseEvent evt) {
        popUp.setVisible(true);
        popUp.show(evt.getComponent(), evt.getX(), evt.getY());
        System.out.println("click");
    }

    public void cargarDatos() {
        JLabel label = new JLabel("Esta es una ayuda de nosotros para ti");
        label.setForeground(Color.blue);
        label.setBackground(Color.GREEN);
        Font font = new Font("Comic Sans Ms", Font.BOLD, 14);
        label.setFont(font);
        popUp.add(label);
    }

    private void cargarPalabras() {

        palabrasClave.add("abstract");
        palabrasClave.add("class");
        palabrasClave.add("const");
        palabrasClave.add("continue");
        palabrasClave.add("default");
        palabrasClave.add("do");
        palabrasClave.add("double");
        palabrasClave.add("else");
        palabrasClave.add("enum");
        palabrasClave.add("extends");
        palabrasClave.add("final");
        palabrasClave.add("finally");
        palabrasClave.add("float");
        palabrasClave.add("for");
        palabrasClave.add("goto");
        palabrasClave.add("if");
        palabrasClave.add("implements");
        palabrasClave.add("import");
        palabrasClave.add("instanceof");
        palabrasClave.add("int");
        palabrasClave.add("interface");
        palabrasClave.add("long");
        palabrasClave.add("native");
        palabrasClave.add("new");
        palabrasClave.add("package");
        palabrasClave.add("private");
        palabrasClave.add("protected");
        palabrasClave.add("public");
        palabrasClave.add("return");
        palabrasClave.add("short");
        palabrasClave.add("static");
        palabrasClave.add("strictfp");
        palabrasClave.add("super");
        palabrasClave.add("switch");
        palabrasClave.add("synchronized");
        palabrasClave.add("this");
        palabrasClave.add("throw");
        palabrasClave.add("throws");
        palabrasClave.add("transient");
        palabrasClave.add("try");
        palabrasClave.add("void");
        palabrasClave.add("volatile");
        palabrasClave.add("while");
    }

    private void separador(String codigo) {
        StringTokenizer st = new StringTokenizer(codigo, " \t\n", true);
        codigoDesarmado = new String[st.countTokens()];
        int i = 0;
        while (st.hasMoreTokens()) {
            codigoDesarmado[i] = st.nextToken();
            i++;
        }

    }

    private void setText() {

        textCodigo.setText("");
        for (String cadena : codigoDesarmado) {
            SimpleAttributeSet simp = formato(cadena);
            try {

                textCodigo.getStyledDocument().insertString(textCodigo.getCaretPosition(), cadena, simp);

            } catch (BadLocationException ex) {
                Logger.getLogger(findcode.GUI.Ficha.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private SimpleAttributeSet formato(String palabraReservada) {

        SimpleAttributeSet simp = new SimpleAttributeSet();
        if (palabrasClave.contains(palabraReservada)) {
            StyleConstants.setBold(simp, true);
            StyleConstants.setFontSize(simp, 12);
            StyleConstants.setForeground(simp, Color.blue);

        } else {
            StyleConstants.setBold(simp, false);
            StyleConstants.setFontSize(simp, 12);
            StyleConstants.setForeground(simp, Color.black);
        }

        return simp;
    }

    private void setListaIngredientes() {
        model = new DefaultListModel();
        for (String cadena : codigoDesarmado) {
            if (palabrasClave.contains(cadena) && !model.contains(cadena)) {
                model.addElement(cadena);
            }
        }
        listaIngredientes.setModel(model);

    }
    
}


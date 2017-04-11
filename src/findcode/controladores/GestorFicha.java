package findcode.controladores;

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
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GestorFicha {

    HashSet<String> palabrasClave = new HashSet<>();
    String[] codigoDesarmado;
    DefaultListModel model = new DefaultListModel();
    DefaultListModel modelPopUp;
    private JTextPane textCodigo = new JTextPane();
    private JList<String> listaIngredientes = new JList<>();
    private JList<String> listaPopUp;
    private JPopupMenu popUp = new JPopupMenu();
    int caret;
    String seleccion;

    public GestorFicha(JPopupMenu popUp, JList<String> listaIngredientes, JTextPane textCodigo, JList listaPopUp) {
        this.textCodigo = textCodigo;
        this.listaIngredientes = listaIngredientes;
        this.popUp = popUp;
        this.listaPopUp = listaPopUp;
        cargarPalabras();
        listEvent();
        setListaIngredientes();
        
    }

    //capturar el evento de escribir en el panel y dejar el cursor en el lugar al que se mueva
    public void textCodigoKeyReleased(java.awt.event.KeyEvent evt) {
        caret = textCodigo.getCaretPosition();
        if (evt.getKeyCode() != KeyEvent.VK_LEFT && evt.getKeyCode() != KeyEvent.VK_RIGHT
                && evt.getKeyCode() != KeyEvent.VK_UP && evt.getKeyCode() != KeyEvent.VK_DOWN&&evt.getKeyCode() != KeyEvent.SHIFT_DOWN_MASK) {
            separador(textCodigo.getText());
            setText();
        }
        textCodigo.setCaretPosition(caret);
        setListaIngredientes();
    }

    //mostrar pop UP con el click 
    public void textCodigoMouseReleased(java.awt.event.MouseEvent evt) {
        popUp.removeAll();
        if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() > 1) {
            mostrarTextoPopUp();
            System.out.println("clic izquierdo");
            popUp.setVisible(true);
            popUp.show(evt.getComponent(), evt.getX(), evt.getY());
        }
        if (evt.getButton() == MouseEvent.BUTTON3) {
            cargarListaPopUp();
            System.out.println("clic derecho");
            popUp.setVisible(true);
            popUp.show(evt.getComponent(), evt.getX(), evt.getY());

        }
        System.out.println("Se presiono");

    }

    //cargar datos que se van a mostrar en el popUP
    public void cargarListaPopUp() {
        seleccion = textCodigo.getSelectedText();
        modelPopUp = new DefaultListModel();
        modelPopUp.addElement("Guardar");
        modelPopUp.addElement("Crear");
        listaPopUp.setModel(modelPopUp);
        popUp.add(listaPopUp);

    }

    public void listEvent() {
        listaPopUp.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                if (evt.getValueIsAdjusting()) {
                    if (listaPopUp.getSelectedIndex() == 0) {
                        System.out.println("se selecciono el primer elemento");
                        model.addElement(seleccion);
                    }
                    if (listaPopUp.getSelectedIndex() == 1) {
                        System.out.println("se selecciono el segundo elemento");
                    }
                }

            }
        });

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

    public void mostrarTextoPopUp() {
        JLabel label = new JLabel("Esta es una ayuda de nosotros para ti");
        label.setForeground(Color.blue);
        label.setBackground(Color.GREEN);
        Font font = new Font("Comic Sans Ms", Font.BOLD, 14);
        label.setFont(font);
        popUp.add(label);

    }

    private void setListaIngredientes() {
        for (String cadena : codigoDesarmado) {
            if (palabrasClave.contains(cadena) && !model.contains(cadena)) {
                model.addElement(cadena);
            }
        }
        for (int i = 0; i < codigoDesarmado.length; i++) {
            if (!model.contains(codigoDesarmado[i])) {
                model.removeElement(codigoDesarmado[i]);
            }
            listaIngredientes.setModel(model);
        }
    }

}

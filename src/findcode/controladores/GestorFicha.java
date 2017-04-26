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
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GestorFicha {

    HashSet<String> palabrasClave = new HashSet<>();
    HashSet<String> ingredientes = new HashSet<>();
    String[] codigoDesarmado;
    DefaultListModel model = new DefaultListModel();
    DefaultListModel modelPopUp;
    private JTextPane textCodigo = new JTextPane();
    private JList<String> listaIngredientes = new JList<>();
    private JList<String> listaPopUp;
    private JPopupMenu popUp = new JPopupMenu();
    private JDialog ventanaGuardar;
    private JTextArea textComentario;
    private JTextField textTitulo;
    JMenuItem cargar;
    JMenuItem guardar;
    JMenuItem borrar;
    int caret;
    String seleccion;

    public GestorFicha(JPopupMenu popUp, JList<String> listaIngredientes, JTextPane textCodigo,
            JList listaPopUp, JMenuItem cargar, JMenuItem guardar, JMenuItem borrar, JDialog ventanaGuadar, JTextArea textComentario,
            JTextField textTitulo) {
        this.textCodigo = textCodigo;
        this.listaIngredientes = listaIngredientes;
        this.popUp = popUp;
        this.listaPopUp = listaPopUp;
        this.cargar = cargar;
        this.guardar = guardar;
        this.borrar = borrar;
        this.ventanaGuardar = ventanaGuadar;
        this.textComentario = textComentario;
        this.textTitulo = textTitulo;
        cargarPalabras();
        //cargarListaPopUp(1);
//        ventanaGuardar.add(textTitulo);
//        ventanaGuardar.add(this.textComentario);
        //setListaIngredientes();

    }

    //capturar el evento de escribir en el panel y dejar el cursor en el lugar al que se mueva
    public void textCodigoKeyReleased(java.awt.event.KeyEvent evt) {
        caret = textCodigo.getCaretPosition();
        if (evt.getKeyCode() != KeyEvent.VK_LEFT && evt.getKeyCode() != KeyEvent.VK_RIGHT
                && evt.getKeyCode() != KeyEvent.VK_UP && evt.getKeyCode() != KeyEvent.VK_DOWN && evt.getKeyCode() != KeyEvent.VK_SHIFT) {
            separador(textCodigo.getText());
            setText();
            //setListaIngredientes(1);
        }

        textCodigo.setCaretPosition(caret);

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
            cargarListaPopUp(1);
            System.out.println("clic derecho");
            popUp.setVisible(true);
            popUp.show(evt.getComponent(), evt.getX(), evt.getY());

        }
        System.out.println("Se presiono");

    }

    public void listaIngredientesMouseReleased(java.awt.event.MouseEvent evt) {
        popUp.removeAll();
        if (evt.getButton() == MouseEvent.BUTTON3) {
            cargarListaPopUp(2);
            popUp.setVisible(true);
            popUp.show(evt.getComponent(), evt.getX(), evt.getY());
            
        }
    }

    //cargar datos que se van a mostrar en el popUP
    public void cargarListaPopUp(int opcion) {
        if (opcion == 1) {
            seleccion = textCodigo.getSelectedText();
            System.out.println(seleccion);
            popUp.add(cargar);
            popUp.add(guardar);
        }
        if (opcion == 2) {
            popUp.add(borrar);
            //listaIngredientes.getSelectedIndex();
            System.out.println("item "+listaIngredientes.getSelectedIndex());
        }

    }

    public void cargarPalabras() {

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

    public void separador(String codigo) {
        StringTokenizer st = new StringTokenizer(codigo, " \t\n", true);
        codigoDesarmado = new String[st.countTokens()];
        int i = 0;
        while (st.hasMoreTokens()) {
            codigoDesarmado[i] = st.nextToken();
            i++;
        }

    }

    public void setText() {

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

    public SimpleAttributeSet formato(String palabraReservada) {

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

    //Texto a mostrar cuando se le de clic izquierdo
    public void mostrarTextoPopUp() {
        JLabel label = new JLabel("Esta es una ayuda de nosotros para ti");
        label.setForeground(Color.blue);
        label.setBackground(Color.GREEN);
        Font font = new Font("Comic Sans Ms", Font.BOLD, 14);
        label.setFont(font);
        popUp.add(label);

    }

    public void setListaPalabrasClave() {

//        for (String cadena : codigoDesarmado) {
//
//            if (palabrasClave.contains(cadena) && !palabrasClaveTemporal.contains(cadena)) {
//                palabrasClaveTemporal.add(cadena);
//            }
//        }
    }

    public void setListaSeleccion(String titulo) {
        if (!ingredientes.contains(titulo) && seleccion != null) {
            ingredientes.add(titulo);
        }

    }

    public void setListaIngredientes() {

        for (String cadena : ingredientes) {
            if (!model.contains(cadena)) {
                model.addElement(cadena);
            }
        }

        listaIngredientes.setModel(model);
    }

    public void confirmarLista() {
//        for (String cadena : ingredientes) {
//            if (!palabrasClaveTemporal.contains(cadena)&&!ingredientes.isEmpty()) {
//                ingredientes.remove(cadena);
//            }
//
//        }

    }
    
    public void eliminarElemento(int index){
        //listaIngredientes.remove(index);
        model.remove(index);
        listaIngredientes.setModel(model);
    }

    public void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {

        setListaSeleccion(textTitulo.getText());
        setListaIngredientes();
        ventanaGuardar.setVisible(false);
        textTitulo.setText("");
        textComentario.setText("");

    }

    public void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        ventanaGuardar.setVisible(false);
        textTitulo.setText("");
        textComentario.setText("");
    }

    public void itemGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        if (seleccion != null) {
            System.out.println("Guardar presionado...");
            ventanaGuardar.setLocationRelativeTo(textCodigo);
            ventanaGuardar.setSize(330, 260);
            ventanaGuardar.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar algún fragmento de código", "", 2);

        }
        //setListaSeleccion(titulo);
    }
    
    public void itemBorrarActionPerformed(java.awt.event.ActionEvent evt){
        eliminarElemento(listaIngredientes.getSelectedIndex());
    }
    
    public void asfsdf(){
        
        findcode.clases.Ficha ficha = new findcode.clases.Ficha();
        ficha.setDescripcion("sfdgsdf");
        ficha.setiDUsuario("dvdf");
        ficha.crear();
        ficha.editar();
        ficha.borrar();
        ficha.cargar();
        
    }
    
}

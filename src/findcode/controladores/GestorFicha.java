package findcode.controladores;

import findcode.model.BoardListener;
import findcode.model.Ingrediente;
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
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class GestorFicha {

    ArrayList<String> palabrasReservadas;
    HashSet<String> simbolos = new HashSet<>();
    HashMap<String, findcode.model.Ingrediente> ingredientes2;
    HashSet<String> ingredientes = new HashSet<>();
    String[] codigoDesarmado;
    DefaultListModel model = new DefaultListModel();
    DefaultListModel modelPopUp;
    private JTextPane textCodigo;
    private JList<String> listaIngredientes = new JList<>();
    private JPopupMenu popUp = new JPopupMenu();
    private JDialog ventanaGuardar;
    private JTextArea textComentario;
    private JTextField textTitulo;
    JMenuItem cargar;
    JMenuItem guardar;
    JMenuItem borrar;
    String seleccion;
    BoardListener clipboard;
    int caret;
    //int index;

    public GestorFicha(JPopupMenu popUp, JList<String> listaIngredientes, JTextPane textCodigo,
            JList listaPopUp, JMenuItem cargar, JMenuItem guardar, JMenuItem borrar, JDialog ventanaGuadar, JTextArea textComentario,
            JTextField textTitulo, JTextArea textDescripcion, JTextField textTituloFicha,
            HashMap<String, findcode.model.Ingrediente> ingredientes2,
            ArrayList<String> palabrasReservadas) {

        this.textCodigo = textCodigo;
        this.listaIngredientes = listaIngredientes;
        this.popUp = popUp;
        this.cargar = cargar;
        this.guardar = guardar;
        this.borrar = borrar;
        this.ventanaGuardar = ventanaGuadar;
        this.textComentario = textComentario;
        this.textTitulo = textTitulo;
        this.ingredientes2 = ingredientes2;
        this.palabrasReservadas = palabrasReservadas;
        setListaIngredientes();
        cargarPalabras();
        setText();
        clipboard = new BoardListener();
        //corrimiento();
    }

    //capturar el evento de escribir en el panel y dejar el cursor en el lugar al que se mueva
    public void textCodigoKeyReleased(java.awt.event.KeyEvent evt) {

    }

    public void textCodigoKeyPressed(java.awt.event.KeyEvent evt) {
        caret = textCodigo.getCaretPosition();
        String sel = textCodigo.getSelectedText();

//        if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_V) {
//            textCodigo.replaceSelection("");
//        }
//
//        if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_C) {
//            clipboard.setClipboard(textCodigo.getSelectedText());
//        }
//
//        if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_X) {
//
//            if (sel != null) {
//                clipboard.setClipboard(sel);
//                updateSelection();
//            }
//
//        }
        if (evt.getKeyCode() != KeyEvent.VK_LEFT && evt.getKeyCode() != KeyEvent.VK_RIGHT
                && evt.getKeyCode() != KeyEvent.VK_UP && evt.getKeyCode() != KeyEvent.VK_DOWN && evt.getKeyCode() != KeyEvent.VK_SHIFT
                && !evt.isControlDown()) {

            if (sel != null) {
                updateSelection();
            }
            caret = textCodigo.getCaretPosition();
            setText();
            switch (evt.getKeyCode()) {
                case KeyEvent.VK_BACK_SPACE:
                    if (sel != null) {
                        evt.consume();
                        corrimiento(caret, 1, sel.length());
                    } else {
                        corrimiento(caret, 1, 1);
                    }

                    break;
                case KeyEvent.VK_DELETE:
                    if (sel != null) {
                        evt.consume();
                        corrimiento(caret, 2, sel.length());
                    } else {
                        corrimiento(caret, 2, 1);
                    }

                    break;
                default:
                    if (sel != null) {
                       corrimiento(caret, 3, sel.length());
                    } else {
                       corrimiento(caret, 3, 1);
                    }
                    break;
            }
            textCodigo.setCaretPosition(caret);
        }
    }

    public void updateSelection() {
        caret = textCodigo.getCaretPosition();
        String sel = textCodigo.getSelectedText();
        textCodigo.replaceSelection("");
        if (sel.length() <= caret) {
            caret -= sel.length();
        } else {
            caret = 0;
        }

    }

    //mostrar pop UP con el click en la ventana del codigo
    public void textCodigoMouseReleased(java.awt.event.MouseEvent evt) {

        if (evt.getButton() == MouseEvent.BUTTON3) {
            cargarListaPopUp(1);
            popUp.setVisible(true);
            popUp.show(evt.getComponent(), evt.getX(), evt.getY());
        }

    }

    public void listaIngredientesMouseReleased(java.awt.event.MouseEvent evt) throws BadLocationException {

        if (evt.getButton() == MouseEvent.BUTTON3) {
            cargarListaPopUp(2);
            popUp.show(evt.getComponent(), evt.getX(), evt.getY());
        }

        if (evt.getButton() == MouseEvent.BUTTON1) {

            //System.out.println("evento list" + index++);
            try {
                mostrarElemento();
            } catch (BadLocationException ex) {
                Logger.getLogger(GestorFicha.class.getName()).log(Level.SEVERE, null, ex);

            }

        }

    }

    //cargar datos que se van a mostrar en el popUP
    public void cargarListaPopUp(int opcion) {
        popUp.removeAll();
        if (opcion == 1) {
            seleccion = textCodigo.getSelectedText();
            popUp.add(cargar);
            popUp.add(guardar);
        } else if (opcion == 2) {
            popUp.add(borrar);

        }

    }

    public void cargarPalabras() {

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

//separa las palabras cuando encuentra un espacio, salto de linea, tabulacion o parentesis y las coloca en un array
    public void separador(String codigo) {
        StringTokenizer st = new StringTokenizer(codigo, " \t\n(),\"/", true);
        codigoDesarmado = new String[st.countTokens()];
        int i = 0;
        while (st.hasMoreTokens()) {
            codigoDesarmado[i] = st.nextToken();
            i++;
        }

    }

//vuelve a colocar texto en el textpane
    public void setText() {
        boolean state = false;
        separador(textCodigo.getText());
        textCodigo.setText("");
        for (int i = 0; i < codigoDesarmado.length; i++) {
            SimpleAttributeSet simp;
            if (state || (codigoDesarmado[i].equals("/") && !codigoDesarmado[i + 1].isEmpty() && codigoDesarmado[i].equals("/"))) {
                simp = formato();
                state = true;
            }else {
                 simp = formato(codigoDesarmado[i]);
            }
            if (state && codigoDesarmado[i].equals("\n")) {
                //simp = formato(codigoDesarmado[i]);
                state = false;
            }
           if (state == false) {
              
            }
            try {

                textCodigo.getStyledDocument().insertString(textCodigo.getCaretPosition(), codigoDesarmado[i], simp);

            } catch (BadLocationException ex) {
                Logger.getLogger(findcode.GUI.Ficha.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

//le da formato al texto cuando encuentra palabras reservadas
    public SimpleAttributeSet formato(String palabraReservada) {

        SimpleAttributeSet simp = new SimpleAttributeSet();
        if (palabrasReservadas.contains(palabraReservada)) {
            StyleConstants.setBold(simp, true);
            StyleConstants.setFontSize(simp, 12);
            StyleConstants.setForeground(simp, Color.blue);

        } else if (simbolos.contains(palabraReservada)) {
            StyleConstants.setBold(simp, false);
            StyleConstants.setFontSize(simp, 12);
            StyleConstants.setForeground(simp, Color.blue);
        } else {
            StyleConstants.setBold(simp, false);
            StyleConstants.setFontSize(simp, 12);
            StyleConstants.setForeground(simp, Color.black);
        }

        return simp;
    }

    public SimpleAttributeSet formato() {
        SimpleAttributeSet simp = new SimpleAttributeSet();
        StyleConstants.setBold(simp, false);
        StyleConstants.setFontSize(simp, 12);
        StyleConstants.setForeground(simp, Color.LIGHT_GRAY);
        return simp;
    }
//Texto a mostrar cuando se seleccione un elemento de la lista

    public void mostrarTextoPopUp(String contenido) {

        popUp.removeAll();
        JLabel label = new JLabel(contenido);
        label.setForeground(Color.blue);
        Font font = new Font("Verdana", Font.BOLD, 12);
        label.setSize(100, 100);
        label.setFont(font);
        popUp.add(label);

        if (textCodigo.getCaret().getMagicCaretPosition() != null) {

            popUp.show(textCodigo, textCodigo.getCaret().getMagicCaretPosition().x, textCodigo.getCaret().getMagicCaretPosition().y - 20);
            popUp.setVisible(true);

        }

    }

//coloca los ingredientes en el modelo y luego establece el modelo en la lista
    public void setListaIngredientes() {

        for (String cadena : ingredientes2.keySet()) {
            if (!model.contains(cadena)) {
                model.addElement(cadena);

            }
        }
        listaIngredientes.setModel(model);
    }
//eliminar un elemento de la lista de ingredientes

    public void eliminarElemento(int index) {
        if (index != -1) {
            String elemento = model.getElementAt(index).toString();
            model.remove(index);
            ingredientes2.remove(elemento);
            listaIngredientes.setModel(model);
        }
    }

    public void eliminarElemento(String elemento) {
        if (!elemento.equalsIgnoreCase(null)) {
            model.removeElement(elemento);
            ingredientes2.remove(elemento);
            listaIngredientes.setModel(model);

        }
    }
//accion del boton guardar de la ventana emergente

    public void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {

        setIngredientes(textTitulo.getText(), textComentario.getText(),
                textCodigo.getSelectionStart(), textCodigo.getSelectionEnd());
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
//accion del item guardar en la lista emergente del textpane

    public void itemGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        if (seleccion != null) {
            ventanaGuardar.setLocationRelativeTo(textCodigo);
            ventanaGuardar.setSize(330, 260);
            ventanaGuardar.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar algún fragmento de código", "", 2);

        }
        //setListaSeleccion(titulo);
    }

//accion del item borrar que aparece al dar clic izquierdo en la lista de ingredientes
    public void itemBorrarActionPerformed(java.awt.event.ActionEvent evt) {
        eliminarElemento(listaIngredientes.getSelectedIndex());
    }

//crea un objeto del tipo ingrediente y lo coloca en el hashmap de ingredientes
    public void setIngredientes(String titulo, String comentario, int inicio, int fin) {

        if (!ingredientes2.containsKey(titulo)) {
            findcode.model.Ingrediente ingrediente = new findcode.model.Ingrediente();
            ingrediente.setDescripcion(comentario);
            ingrediente.setTitulo(titulo);
            ingrediente.setPosInicial(inicio);
            ingrediente.setPosFinal(fin);

            ingredientes2.put(ingrediente.getTitulo(), ingrediente);

        } else {
            JOptionPane.showMessageDialog(null, "El titulo ya existe!", "", 2);
        }
    }

//resalta con color el texto referente al elemento de la lista de ingredientes y muestra el pop up con el comentario
    public void mostrarElemento() throws BadLocationException {

        if (!model.isEmpty()) {

            String elemento = model.getElementAt(listaIngredientes.getSelectedIndex()).toString();
            int inicio = ingredientes2.get(elemento).getPosInicial();
            int fin = ingredientes2.get(elemento).getPosFinal();
            String contenido = ingredientes2.get(elemento).getDescripcion();
            Highlighter highlighter = textCodigo.getHighlighter();
            HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.decode("#81F7F3"));
            highlighter.removeAllHighlights();
            highlighter.addHighlight(inicio, fin, painter);

            textCodigo.moveCaretPosition(fin);
            mostrarTextoPopUp(contenido);
        }

    }

    public void corrimiento(int caret, int tecla, int amount) {

        boolean borrado = false;
        Ingrediente temporal = null;

        for (Ingrediente elemento : ingredientes2.values()) {
            int inicio = elemento.getPosInicial();
            int fin = elemento.getPosFinal();
            switch (tecla) {
                case 1:
                    if (caret <= elemento.getPosInicial() && caret > 0) {
                        elemento.setPosInicial(inicio - amount);
                        elemento.setPosFinal(fin - amount);
                    } else if (inicio < caret && caret <= fin) {
                        elemento.setPosFinal(fin - amount);
                    }
                    break;
                case 2:
                    if (caret == elemento.getPosInicial()) {
                        elemento.setPosFinal(fin - amount);
                    } else if (caret < elemento.getPosInicial()) {
                        elemento.setPosInicial(inicio - amount);
                        elemento.setPosFinal(fin - amount);

                    } else if (inicio < caret && caret <= fin) {
                        elemento.setPosFinal(fin - amount);
                    }
                    break;

                case 3:
                    if (caret <= elemento.getPosInicial()) {
                        elemento.setPosInicial(inicio + amount);
                        elemento.setPosFinal(fin + amount);
                    } else if (inicio < caret && caret <= fin) {
                        elemento.setPosFinal(fin + amount);
                    }
                    break;

            }

            if (inicio == fin - 1) {
                borrado = true;
                temporal = elemento;
            }
        }

        if (borrado && temporal != null) {
            //System.out.println("Borrar");
            JOptionPane.showMessageDialog(null, "Borraste un elemento del codigo \n "
                    + "este se borrara de la lista de ingredientes", "", 2);
            eliminarElemento(temporal.getTitulo());
        }
    }

}
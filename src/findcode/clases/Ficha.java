package findcode.clases;


import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
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
import java.awt.event.MouseListener;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import findcode.controladores.MySQL;
import java.sql.SQLException;


public class Ficha {

    private int iD;
    private String titulo;
    private String descripcion;
    private Sintaxis ejemplo;
    private String lenguajeProgramacion;
    private HashMap<Integer, Calificacion> calificacion;
    HashSet<String> palabrasClave = new HashSet<>();
    String[] codigoDesarmado;
    DefaultListModel model = new DefaultListModel();;
    DefaultListModel modelPopUp;
    private JTextPane textCodigo = new JTextPane();
    private JList<String> listaIngredientes = new JList<>();
    private JList<String> listaPopUp;
    private JPopupMenu popUp = new JPopupMenu();
    int caret;
    String seleccion;

    public Ficha(JPopupMenu popUp, JList<String> listaIngredientes, JTextPane textCodigo, JList listaPopUp) {
        this.textCodigo = textCodigo;
        this.listaIngredientes = listaIngredientes;
        this.popUp = popUp;
        this.listaPopUp = listaPopUp;
        cargarPalabras();
        listEvent();
    }

    //capturar el evento de escribir en el panel y dejar el cursor en el lugar al que se mueva
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

    //mostrar pop UP con el click 
    public void textCodigoMouseReleased(java.awt.event.MouseEvent evt) {
        popUp.removeAll();
        if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount()>1) {
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

    //se cargan las palabras reservadas en un array
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

    //separa el texto en palabras y las guarda en un array
    private void separador(String codigo) {
        StringTokenizer st = new StringTokenizer(codigo, " \t\n", true);
        codigoDesarmado = new String[st.countTokens()];
        int i = 0;
        while (st.hasMoreTokens()) {
            codigoDesarmado[i] = st.nextToken();
            i++;
        }

    private String ejemplo;
    private String iDUsuario;
    private String iDLenguaje;

    public Ficha(int iD, String titulo, String descripcion, String ejemplo, String iDUsuario, String iDLenguaje) {
        this.iD = iD;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.ejemplo = ejemplo;
        this.iDUsuario = iDUsuario;
        this.iDLenguaje = iDLenguaje;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }


    //reposiciona el texto en el textpane
    private void setText() {
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEjemplo() {
        return ejemplo;
    }

    public void setEjemplo(String ejemplo) {
        this.ejemplo = ejemplo;
    }

    public String getiDUsuario() {
        return iDUsuario;
    }

    public void setiDUsuario(String iDUsuario) {
        this.iDUsuario = iDUsuario;
    }


    //selecciona el formato de la fuente que tendra la palabra
    private SimpleAttributeSet formato(String palabraReservada) {
    }
    public String getiDLenguaje() {
        return iDLenguaje;
    }


    public void setiDLenguaje(String iDLenguaje) {
        this.iDLenguaje = iDLenguaje;
    }

    public void crear() {

        try {

            MySQL db = new MySQL();

            String query = " insert into TFichas (titulo, descripcion, ejemplo, iDUsuario, iDLenguaje)"
                    + " values (?, ?, ?)";

            db.setSentencia(query);
            db.getSentencia().setString(1, titulo);
            db.getSentencia().setString(2, descripcion);
            db.getSentencia().setString(3, ejemplo);
            db.getSentencia().setString(4, iDUsuario);
            db.getSentencia().setString(5, iDLenguaje);

            db.getSentencia().execute();
            db.conexion().close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    //Colocar los elementos en la lista de ingredientes
    private void setListaIngredientes() {
        
        for (String cadena : codigoDesarmado) {
            if (palabrasClave.contains(cadena) && !model.contains(cadena)) {
                model.addElement(cadena);
        for (int i = 0; i < codigoDesarmado.length; i++) {
            if (!model.contains(codigoDesarmado[i])) {
                model.removeElement(codigoDesarmado[i]);
            }
           listaIngredientes.setModel(model);
        }

       
    public boolean cargar() {

        try {

            MySQL db = new MySQL();

            String query = " select * from TFichas where"
                    + " iD = ?";

            db.setSentencia(query);
            db.getSentencia().setInt(1, iD);

            db.setResultados(db.getSentencia().executeQuery());

            if (db.numeroColumnas() == 0) {
                return false;

            }

            while (db.getResultados().next()) {
                
                iD = db.getResultados().getInt("iD");
                titulo = db.getResultados().getString("titulo");
                descripcion = db.getResultados().getString("descripcion");
                iDUsuario = db.getResultados().getString("iDUsuario");
                iDLenguaje = db.getResultados().getString("iDLenguaje");

            }

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

       // return false;

    public void mostrarTextoPopUp() {
        JLabel label = new JLabel("Esta es una ayuda de nosotros para ti");
        label.setForeground(Color.blue);
        label.setBackground(Color.GREEN);
        Font font = new Font("Comic Sans Ms", Font.BOLD, 14);
        label.setFont(font);
        popUp.add(label);

    }

    public void crear() {

    }

    public static Ficha cargar() {
        return null;

    }

    public void editar() {


        try {

            MySQL db = new MySQL();

            String query = "UPDATE TFichas "
                    + "SET titulo = ?, "
                    + " descripcion = ?, "
                    + " ejemplo = ?, "
                    + "WHERE iD = ? ";

            db.setSentencia(query);
            db.getSentencia().setString(1, titulo);
            db.getSentencia().setString(2, descripcion);
            db.getSentencia().setString(3, ejemplo);
            db.getSentencia().setInt(3, iD);

            db.getSentencia().execute();
            db.conexion().close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    public void borrar() {


        try {

            MySQL db = new MySQL();

            String query = "DELETE FROM TFichas "
                    + "WHERE iD = ? ";

            db.setSentencia(query);
            db.getSentencia().setInt(1, iD);

            db.getSentencia().execute();
            db.conexion().close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

}

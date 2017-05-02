package findcode.GUI;

import findcode.clases.Lenguaje;
import findcode.controladores.Utilidades;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.BadLocationException;

public class Ficha extends javax.swing.JPanel {

    private JPanel contenedor;
    private findcode.clases.Usuario usuario;
    private findcode.clases.Ficha ficha;
    private findcode.clases.Calificacion calificacion;
    private findcode.controladores.GestorFicha gestorFicha;
    private ArrayList<findcode.clases.Comentario> comentarios;
    private ArrayList<findcode.clases.Ficha> relacionados;
    private HashMap<String, findcode.clases.Ingrediente> ingredientes;
    private HashMap<String, findcode.clases.Ingrediente> ingredientesIniciales;

    // Consultar ficha sin usuario
    public Ficha(JPanel contenedor, findcode.clases.Ficha ficha) {

        initComponents();
        this.contenedor = contenedor;
        this.ficha = ficha;

        // Visibilidad de los objetos necesarios
        jButton8.setVisible(false);
        jButton13.setVisible(false);
        botonGuardarFicha.setVisible(false);
        botonGuardarFicha1.setVisible(false);
        botonGuardarFicha4.setVisible(false);
        jTextField1.setVisible(false);
        jTextField3.setVisible(false);
        botonGuardarFicha2.setVisible(false);
        jPanel11.setVisible(false);
        botonGuardarFicha3.setVisible(false);
        jScrollPane1.getViewport().setOpaque(false);

        // Lista de lenguajes
        String[] s = {ficha.getiDLenguaje()};
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(s));
        jComboBox1.setSelectedItem(ficha.getiDLenguaje());

        // Personalizar campos
        Utilidades.personalizarCampo(textTituloFicha, "Titulo", ficha.getTitulo());
        Utilidades.personalizarCampo(textDescripcion, "Descripcion del codigo", ficha.getDescripcion());
        Utilidades.personalizarCampo(textCodigo, "Sintaxis", ficha.getEjemplo());
        Utilidades.personalizarCampo(jTextField1, "Nuevo lenguaje", "");
        Utilidades.personalizarCampo(jTextField3, "Nuevo comentario", "");

        // OTras funciones
        busquedaComentarios();
        busquedaFichasRelacionadas();
        busquedaIngredientes();
        busquedaPromedioCalificacion();

        // Gestor de ficha
        gestorFicha = new findcode.controladores.GestorFicha(popUp, listaIngredientes, textCodigo,
                listaPopUp, itemCargar, itemGuardar, itemBorrar, ventanaGuardar, textComentario, textTitulo,
                textDescripcion, textTituloFicha, ingredientes,
                findcode.clases.PalabraReservada.cargarPorLenguaje(ficha.getiDLenguaje()));

    }

    // Consultar ficha con usuario
    public Ficha(JPanel contenedor, findcode.clases.Usuario usuario, findcode.clases.Ficha ficha) {

        initComponents();
        this.contenedor = contenedor;
        this.ficha = ficha;
        this.usuario = usuario;

        // Visibilidad de los objetos necesarios
        botonGuardarFicha.setVisible(false);
        botonGuardarFicha1.setVisible(false);
        botonGuardarFicha4.setVisible(false);
        jTextField1.setVisible(false);
        jButton8.setVisible(true);
        jButton13.setVisible(true);
        jTextField3.setVisible(true);
        botonGuardarFicha2.setVisible(true);
        jScrollPane1.getViewport().setOpaque(false);

        // Asignar datos a la ficha
        jButton8.setText(usuario.getNickname());
        String[] s = {ficha.getiDLenguaje()};
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(s));
        jComboBox1.setSelectedItem(ficha.getiDLenguaje());
        Utilidades.personalizarCampo(textTituloFicha, "Titulo", ficha.getTitulo());
        Utilidades.personalizarCampo(textDescripcion, "Descripcion del codigo", ficha.getDescripcion());
        Utilidades.personalizarCampo(textCodigo, "Sintaxis", ficha.getEjemplo());
        Utilidades.personalizarCampo(jTextField1, "Nuevo lenguaje", "");
        Utilidades.personalizarCampo(jTextField3, "Nuevo comentario", "");

        // Otras funciones
        busquedaComentarios();
        busquedaFichasRelacionadas();
        busquedaIngredientes();
        busquedaCalificaciones();
        busquedaPromedioCalificacion();

        // Permitir la edicion de datos al usuario
        if (usuario.getCorreo().equals(ficha.getiDUsuario())) {

            textTituloFicha.setEditable(true);
            textDescripcion.setEditable(true);
            textCodigo.setEditable(true);
            botonGuardarFicha1.setVisible(true);
            botonGuardarFicha4.setVisible(true);
            jTextField1.setVisible(true);
            jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(Lenguaje.cargarTodos().toArray()));
            jComboBox1.setSelectedItem(ficha.getiDLenguaje());

        }

        // Gestor de ficha
        gestorFicha = new findcode.controladores.GestorFicha(popUp, listaIngredientes, textCodigo,
                listaPopUp, itemCargar, itemGuardar, itemBorrar, ventanaGuardar, textComentario, textTitulo,
                textDescripcion, textTituloFicha, ingredientes,
                findcode.clases.PalabraReservada.cargarPorLenguaje(ficha.getiDLenguaje()));

    }

    // Crear ficha
    public Ficha(JPanel contenedor, findcode.clases.Usuario usuario) {

        initComponents();
        this.contenedor = contenedor;
        this.usuario = usuario;
        this.ficha = new findcode.clases.Ficha();

        // Visibilidad de los objetos necesarios
        jButton8.setVisible(true);
        jButton13.setVisible(true);
        jTextField3.setVisible(true);
        jPanel11.setVisible(false);
        botonGuardarFicha3.setVisible(false);
        jTextField3.setVisible(false);
        botonGuardarFicha2.setVisible(false);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane2.getViewport().setOpaque(false);
        jScrollPane3.getViewport().setOpaque(false);
        jScrollPane4.getViewport().setOpaque(false);
        jScrollPane5.getViewport().setOpaque(false);
        jScrollPane6.getViewport().setOpaque(false);
        jScrollPane7.getViewport().setOpaque(false);
        jScrollPane8.getViewport().setOpaque(false);
        jScrollPane9.getViewport().setOpaque(false);

        // Asignar datos
        jButton8.setText(usuario.getNickname());

        // Personalizar campos
        Utilidades.personalizarCampo(textTituloFicha, "Titulo", "");
        Utilidades.personalizarCampo(textDescripcion, "Descripcion del codigo", "");
        Utilidades.personalizarCampo(textCodigo, "Sintaxis", "");
        Utilidades.personalizarCampo(jTextField1, "Nuevo lenguaje", "");
        Utilidades.personalizarCampo(jTextField3, "Nuevo comentario", "");

        // Permitir editar los campos necesarios
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(Lenguaje.cargarTodos().toArray()));
        textTituloFicha.setEditable(true);
        textDescripcion.setEditable(true);
        textCodigo.setEditable(true);
        botonGuardarFicha.setVisible(true);
        botonGuardarFicha1.setVisible(false);
        botonGuardarFicha4.setVisible(false);
        jTextField1.setVisible(true);

        // Otras funciones
        busquedaComentarios();
        busquedaFichasRelacionadas();
        busquedaIngredientes();

        // Gestor de ficha
        gestorFicha = new findcode.controladores.GestorFicha(popUp, listaIngredientes, textCodigo,
                listaPopUp, itemCargar, itemGuardar, itemBorrar, ventanaGuardar, textComentario, textTitulo,
                textDescripcion, textTituloFicha, ingredientes,
                findcode.clases.PalabraReservada.cargarPorLenguaje(ficha.getiDLenguaje()));

    }

    public final void busquedaIngredientes() {

        ingredientes = findcode.clases.Ingrediente.cargarPorFicha(ficha.getiD());
        ingredientesIniciales = ingredientes;
        
        DefaultListModel modelo = new DefaultListModel();

        for (findcode.clases.Ingrediente ingrediente : ingredientes.values()) {
            modelo.addElement(ingrediente.getTitulo());
        }

        listaIngredientes.setModel(modelo);

    }

    public final void busquedaComentarios() {

        jPanel7.removeAll();
        ArrayList<Comentario> panelesComentario = new ArrayList<>();
        comentarios = findcode.clases.Comentario.cargarPorFicha(ficha.getiD());

        if (comentarios.isEmpty()) {
            jPanel7.setLayout(new java.awt.GridLayout());
            jPanel7.add(jLabel9);
            jLabel9.setVisible(true);
        }

        for (findcode.clases.Comentario comentario : comentarios) {
            panelesComentario.add(new Comentario(contenedor, comentario));
        }

        for (Comentario comentario : panelesComentario) {
            jPanel7.add(comentario);
        }

        for (int i = 0; i < panelesComentario.size(); i++) {

            final int i2 = i;

            panelesComentario.get(i).addKeyListener(new java.awt.event.KeyAdapter() {

                @Override
                public void keyPressed(java.awt.event.KeyEvent evt) {

                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                        if (i2 > 0) {
                            panelesComentario.get(i2 - 1).requestFocus();
                        }

                    }

                    if (evt.getKeyCode() == KeyEvent.VK_UP) {

                        if (i2 > 0) {
                            panelesComentario.get(i2 - 1).requestFocus();
                        }

                    }

                    if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

                        if (i2 < panelesComentario.size() - 1) {
                            panelesComentario.get(i2 + 1).requestFocus();
                        }

                    }

                }
            });
        }

    }

    public final void busquedaFichasRelacionadas() {

        jPanel3.removeAll();
        ArrayList<Resultado> panelesResultado = new ArrayList<>();
        relacionados = ficha.buscarFichasRelacionadas();

        if (relacionados.isEmpty()) {
            jPanel3.setLayout(new java.awt.GridLayout());
            jPanel3.add(jLabel10);
            jLabel10.setVisible(true);
        }

        for (findcode.clases.Ficha ficha1 : relacionados) {
            panelesResultado.add(new Resultado(this, usuario, ficha1));
        }

        for (Resultado resultado : panelesResultado) {
            jPanel3.add(resultado);
        }

    }

    public final void busquedaPromedioCalificacion() {

        jLabel7.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella1.png")
                .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
        jLabel6.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella1.png")
                .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
        jLabel3.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella1.png")
                .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
        jLabel2.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella1.png")
                .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
        jLabel1.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella1.png")
                .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

        switch (findcode.clases.Calificacion.cargarPromedioFicha(ficha.getiD())) {
            case 5:
                jLabel7.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella2.png")
                        .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
            case 4:
                jLabel6.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella2.png")
                        .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
            case 3:
                jLabel3.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella2.png")
                        .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
            case 2:
                jLabel2.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella2.png")
                        .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
            case 1:
                jLabel1.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella2.png")
                        .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
        }

    }

    public final void busquedaCalificaciones() {

        calificacion = new findcode.clases.Calificacion();
        calificacion.setiDFicha(ficha.getiD());
        calificacion.setiDUsuario(usuario.getCorreo());

        if (calificacion.cargarPorFichaYUsuario()) {

            switch (calificacion.getCalificacion()) {
                case 1:
                    jLabel8MouseClicked(null);
                    break;
                case 2:
                    jLabel11MouseClicked(null);
                    break;
                case 3:
                    jLabel12MouseClicked(null);
                    break;
                case 4:
                    jLabel13MouseClicked(null);
                    break;
                case 5:
                    jLabel15MouseClicked(null);
                    break;
            }

        } else {

            jLabel8.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella1.png")
                    .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
            jLabel11.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella1.png")
                    .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
            jLabel12.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella1.png")
                    .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
            jLabel13.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella1.png")
                    .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
            jLabel15.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella1.png")
                    .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popUp = new javax.swing.JPopupMenu();
        itemGuardar = new javax.swing.JMenuItem();
        itemBorrar = new javax.swing.JMenuItem();
        itemCargar = new javax.swing.JMenuItem();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaPopUp = new javax.swing.JList<String>();
        ventanaGuardar = new javax.swing.JDialog();
        labelTitulo = new javax.swing.JLabel();
        textTitulo = new javax.swing.JTextField();
        labelComentario = new javax.swing.JLabel();
        botonGuardar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        textComentario = new javax.swing.JTextArea();
        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        botonGuardarFicha = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        botonGuardarFicha1 = new javax.swing.JButton();
        botonGuardarFicha4 = new javax.swing.JButton();
        textTituloFicha = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        textDescripcion = new javax.swing.JTextArea();
        jSplitPane3 = new javax.swing.JSplitPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        textCodigo = new javax.swing.JTextPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        botonGuardarFicha3 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        botonGuardarFicha2 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        listaIngredientes = new javax.swing.JList<String>();
        jPanel8 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();

        popUp.setLightWeightPopupEnabled(false);
        popUp.setRequestFocusEnabled(false);
        popUp.setVerifyInputWhenFocusTarget(false);
        popUp.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
                popUpMenuKeyPressed(evt);
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
        });
        popUp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                popUpFocusLost(evt);
            }
        });
        popUp.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                popUpPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                popUpPopupMenuWillBecomeVisible(evt);
            }
        });
        popUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                popUpMousePressed(evt);
            }
        });
        popUp.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                popUpComponentHidden(evt);
            }
        });
        popUp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                popUpKeyPressed(evt);
            }
        });

        itemGuardar.setText("Guardar");
        itemGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGuardarActionPerformed(evt);
            }
        });
        popUp.add(itemGuardar);

        itemBorrar.setText("Borrar elemento");
        itemBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBorrarActionPerformed(evt);
            }
        });
        popUp.add(itemBorrar);

        itemCargar.setText("Cargar");
        popUp.add(itemCargar);

        jScrollPane3.setMinimumSize(new java.awt.Dimension(50, 50));

        listaPopUp.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listaPopUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                listaPopUpMouseEntered(evt);
            }
        });
        jScrollPane3.setViewportView(listaPopUp);

        ventanaGuardar.setTitle("Añadir ingrediente");
        ventanaGuardar.setBackground(new java.awt.Color(193, 247, 160));
        ventanaGuardar.setLocation(new java.awt.Point(0, 0));
        ventanaGuardar.setMinimumSize(new java.awt.Dimension(20, 10));
        ventanaGuardar.setModal(true);

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelTitulo.setText("Titulo:");

        textTitulo.setToolTipText("Ingrese un titulo para la lista de ingredientes");
        textTitulo.setName(""); // NOI18N
        textTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textTituloActionPerformed(evt);
            }
        });

        labelComentario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelComentario.setText("Comentario:");

        botonGuardar.setText("Guardar");
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        jScrollPane4.setBackground(new java.awt.Color(102, 255, 51));

        textComentario.setColumns(20);
        textComentario.setLineWrap(true);
        textComentario.setRows(5);
        textComentario.setToolTipText("mostrar algo");
        textComentario.setWrapStyleWord(true);
        textComentario.setBorder(null);
        textComentario.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textComentario.setDragEnabled(true);
        jScrollPane4.setViewportView(textComentario);
        textComentario.getAccessibleContext().setAccessibleDescription("Descripcion del codigo seleccionado");

        javax.swing.GroupLayout ventanaGuardarLayout = new javax.swing.GroupLayout(ventanaGuardar.getContentPane());
        ventanaGuardar.getContentPane().setLayout(ventanaGuardarLayout);
        ventanaGuardarLayout.setHorizontalGroup(
            ventanaGuardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventanaGuardarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ventanaGuardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ventanaGuardarLayout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addContainerGap())
                    .addGroup(ventanaGuardarLayout.createSequentialGroup()
                        .addGroup(ventanaGuardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ventanaGuardarLayout.createSequentialGroup()
                                .addGap(0, 122, Short.MAX_VALUE)
                                .addComponent(botonGuardar)
                                .addGap(18, 18, 18)
                                .addComponent(botonCancelar))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ventanaGuardarLayout.createSequentialGroup()
                                .addComponent(labelTitulo)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ventanaGuardarLayout.createSequentialGroup()
                        .addComponent(textTitulo)
                        .addContainerGap())
                    .addGroup(ventanaGuardarLayout.createSequentialGroup()
                        .addComponent(labelComentario)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        ventanaGuardarLayout.setVerticalGroup(
            ventanaGuardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventanaGuardarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo)
                .addGap(11, 11, 11)
                .addComponent(textTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelComentario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ventanaGuardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonGuardar)
                    .addComponent(botonCancelar))
                .addContainerGap())
        );

        jMenuItem1.setText("jMenuItem1");

        setOpaque(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        setLayout(new java.awt.CardLayout());

        jScrollPane1.setOpaque(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setOpaque(false);

        jButton12.setText("v");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jButton12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton12KeyPressed(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jButton13.setText("Cerrar sesion");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("FindCode");

        jButton8.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jButton8.setText("Usuario");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        botonGuardarFicha.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        botonGuardarFicha.setText("Crear ficha");
        botonGuardarFicha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarFichaActionPerformed(evt);
            }
        });
        botonGuardarFicha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botonGuardarFichaKeyPressed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jComboBox1.setFocusable(false);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        botonGuardarFicha1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        botonGuardarFicha1.setText("Guardar ficha");
        botonGuardarFicha1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarFicha1ActionPerformed(evt);
            }
        });
        botonGuardarFicha1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botonGuardarFicha1KeyPressed(evt);
            }
        });

        botonGuardarFicha4.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        botonGuardarFicha4.setText("Borrar ficha");
        botonGuardarFicha4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarFicha4ActionPerformed(evt);
            }
        });
        botonGuardarFicha4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botonGuardarFicha4KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton13))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(botonGuardarFicha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonGuardarFicha1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonGuardarFicha4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jComboBox1)
                        .addComponent(botonGuardarFicha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botonGuardarFicha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonGuardarFicha4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jButton8)
                        .addGap(1, 1, 1)))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        textTituloFicha.setEditable(false);
        textTituloFicha.setBackground(new java.awt.Color(255, 255, 255));
        textTituloFicha.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        textTituloFicha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textTituloFicha.setText("Titulo");
        textTituloFicha.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        textTituloFicha.setMinimumSize(new java.awt.Dimension(0, 0));
        textTituloFicha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textTituloFichaActionPerformed(evt);
            }
        });
        textTituloFicha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textTituloFichaKeyPressed(evt);
            }
        });

        jScrollPane6.setOpaque(false);

        textDescripcion.setEditable(false);
        textDescripcion.setColumns(20);
        textDescripcion.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        textDescripcion.setRows(2);
        textDescripcion.setText("Descripcion");
        textDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textDescripcionKeyPressed(evt);
            }
        });
        jScrollPane6.setViewportView(textDescripcion);

        jSplitPane3.setBackground(new java.awt.Color(255, 255, 255));
        jSplitPane3.setDividerLocation(500);
        jSplitPane3.setDividerSize(10);
        jSplitPane3.setToolTipText("");
        jSplitPane3.setContinuousLayout(true);

        jSplitPane1.setBackground(new java.awt.Color(255, 255, 255));
        jSplitPane1.setDividerLocation(200);
        jSplitPane1.setDividerSize(10);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setContinuousLayout(true);

        jScrollPane2.setOpaque(false);

        textCodigo.setEditable(false);
        textCodigo.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        textCodigo.setText("Sintaxis");
        textCodigo.setMargin(new java.awt.Insets(3, 30, 3, 3));
        textCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textCodigoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textCodigoMouseReleased(evt);
            }
        });
        textCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textCodigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textCodigoKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(textCodigo);

        jSplitPane1.setTopComponent(jScrollPane2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(0, 0));

        botonGuardarFicha3.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        botonGuardarFicha3.setText("Calificar");
        botonGuardarFicha3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarFicha3ActionPerformed(evt);
            }
        });
        botonGuardarFicha3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botonGuardarFicha3KeyPressed(evt);
            }
        });

        jTextField3.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        botonGuardarFicha2.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        botonGuardarFicha2.setText("Comentar");
        botonGuardarFicha2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarFicha2ActionPerformed(evt);
            }
        });
        botonGuardarFicha2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botonGuardarFicha2KeyPressed(evt);
            }
        });

        jPanel11.setOpaque(false);

        jPanel10.setOpaque(false);
        jPanel10.setLayout(new java.awt.GridLayout(1, 0));

        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
        });
        jPanel10.add(jLabel8);

        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel11MousePressed(evt);
            }
        });
        jPanel10.add(jLabel11);

        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel12MousePressed(evt);
            }
        });
        jPanel10.add(jLabel12);

        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel13MousePressed(evt);
            }
        });
        jPanel10.add(jLabel13);

        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel15MousePressed(evt);
            }
        });
        jPanel10.add(jLabel15);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
        );

        jScrollPane9.setOpaque(false);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.PAGE_AXIS));

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel9.setForeground(java.awt.Color.decode("#D8D8D8"));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("No hay comentarios");
        jPanel7.add(jLabel9);

        jScrollPane9.setViewportView(jPanel7);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonGuardarFicha2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonGuardarFicha3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonGuardarFicha3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonGuardarFicha2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane7.setViewportView(jPanel5);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(jPanel4);

        jSplitPane3.setLeftComponent(jSplitPane1);

        jSplitPane2.setBackground(new java.awt.Color(255, 255, 255));
        jSplitPane2.setBorder(null);
        jSplitPane2.setDividerLocation(200);
        jSplitPane2.setDividerSize(10);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setContinuousLayout(true);
        jSplitPane2.setOpaque(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Lista de ingredientes");

        jScrollPane5.setOpaque(false);

        listaIngredientes.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        listaIngredientes.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        listaIngredientes.setSelectionBackground(new java.awt.Color(51, 204, 0));
        listaIngredientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                listaIngredientesMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                listaIngredientesMouseReleased(evt);
            }
        });
        listaIngredientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                listaIngredientesKeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(listaIngredientes);

        jPanel8.setOpaque(false);

        jPanel12.setOpaque(false);
        jPanel12.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel12.add(jLabel1);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel12.add(jLabel2);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel12.add(jLabel3);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel12.add(jLabel6);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel12.add(jLabel7);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane2.setTopComponent(jPanel2);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Codigos relacionados");

        jScrollPane8.setOpaque(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.PAGE_AXIS));

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel10.setForeground(java.awt.Color.decode("#D8D8D8"));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("No hay relaciones");
        jPanel3.add(jLabel10);

        jScrollPane8.setViewportView(jPanel3);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane2.setRightComponent(jPanel6);

        jSplitPane3.setRightComponent(jSplitPane2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSplitPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textTituloFicha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textTituloFicha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSplitPane3)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        add(jScrollPane1, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

        findcode.controladores.Utilidades.cambiarPantalla(this, contenedor);

    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed

        findcode.controladores.Utilidades.cambiarPantalla(this, new Inicio());

    }//GEN-LAST:event_jButton13ActionPerformed

    private void textCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textCodigoKeyReleased

        gestorFicha.textCodigoKeyReleased(evt);

    }//GEN-LAST:event_textCodigoKeyReleased

    private void textCodigoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textCodigoMouseReleased

        gestorFicha.textCodigoMouseReleased(evt);

    }//GEN-LAST:event_textCodigoMouseReleased

    private void textCodigoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textCodigoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCodigoMousePressed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        findcode.controladores.Utilidades.cambiarPantalla(this, new Usuario(this, this.usuario));

    }//GEN-LAST:event_jButton8ActionPerformed

    private void listaPopUpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaPopUpMouseEntered

    }//GEN-LAST:event_listaPopUpMouseEntered

    private void itemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGuardarActionPerformed

        gestorFicha.itemGuardarActionPerformed(evt);

    }//GEN-LAST:event_itemGuardarActionPerformed

    private void textTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textTituloActionPerformed

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        gestorFicha.botonGuardarActionPerformed(evt);
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        gestorFicha.botonCancelarActionPerformed(evt);
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void itemBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBorrarActionPerformed
        gestorFicha.itemBorrarActionPerformed(evt);
    }//GEN-LAST:event_itemBorrarActionPerformed

    private void textTituloFichaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTituloFichaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textTituloFichaActionPerformed

    private void botonGuardarFichaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarFichaActionPerformed

        if (Utilidades.validarCampo(textTituloFicha)
                && Utilidades.validarCampo(textDescripcion)
                && Utilidades.validarCampo(textCodigo)) {

            ficha.setTitulo(textTituloFicha.getText());
            ficha.setDescripcion(textDescripcion.getText());
            ficha.setEjemplo(textCodigo.getText());

            if (jTextField1.getForeground().equals(Color.decode("#D8D8D8"))) {
                ficha.setiDLenguaje(jComboBox1.getSelectedItem().toString());
            } else {
                findcode.clases.Lenguaje lenguaje = new findcode.clases.Lenguaje();
                lenguaje.setNombre(jTextField1.getText());
                lenguaje.crear();
                ficha.setiDLenguaje(jTextField1.getText());
            }

            ficha.setiDUsuario(usuario.getCorreo());
            ficha.crear();
            ficha.cargarIDIngresado();

            for (findcode.clases.Ingrediente ingrediente : ingredientes.values()) {
                ingrediente.setiDFicha(ficha.getiD());
                ingrediente.crear();
            }

            findcode.controladores.Utilidades.cambiarPantalla(this, new Usuario(this, usuario));

        }

    }//GEN-LAST:event_botonGuardarFichaActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed


    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void botonGuardarFicha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarFicha1ActionPerformed

        if (Utilidades.validarCampo(textTituloFicha)
                && Utilidades.validarCampo(textDescripcion)
                && Utilidades.validarCampo(textCodigo)) {

            ficha.setTitulo(textTituloFicha.getText());
            ficha.setDescripcion(textDescripcion.getText());
            ficha.setEjemplo(textCodigo.getText());

            if (jTextField1.getForeground().equals(Color.decode("#D8D8D8"))) {
                ficha.setiDLenguaje(jComboBox1.getSelectedItem().toString());
            } else {
                findcode.clases.Lenguaje lenguaje = new findcode.clases.Lenguaje();
                lenguaje.setNombre(jTextField1.getText());
                lenguaje.crear();
                ficha.setiDLenguaje(jTextField1.getText());
            }

            ficha.editar();

            for (findcode.clases.Ingrediente ingrediente : ingredientes.values()) {

                ingrediente.setiDFicha(ficha.getiD());
                if (ingrediente.getiD() == 0) {
                    ingrediente.crear();
                } else {
                    ingrediente.editar();
                }

            }
            
            for (findcode.clases.Ingrediente ingrediente : ingredientesIniciales.values()) {
                if(!ingredientes.containsValue(ingrediente)){
                    ingrediente.borrar();
                }
            }
            

            try {

                ((Usuario) contenedor).busqueda();
                findcode.controladores.Utilidades.cambiarPantalla(this, contenedor);

            } catch (ClassCastException ex) {

                ((Resultados) contenedor).busqueda();
                findcode.controladores.Utilidades.cambiarPantalla(this, contenedor);

            }

        }

    }//GEN-LAST:event_botonGuardarFicha1ActionPerformed

    private void botonGuardarFicha2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarFicha2ActionPerformed

        if (Utilidades.validarCampo(jTextField3)) {

            findcode.clases.Comentario comentario = new findcode.clases.Comentario();
            comentario.setiDFicha(ficha.getiD());
            comentario.setiDUsuario(usuario.getCorreo());
            comentario.setMensaje(jTextField3.getText());
            comentario.crear();

            jTextField3.setText("");
            busquedaComentarios();
            jPanel7.revalidate();

        }

    }//GEN-LAST:event_botonGuardarFicha2ActionPerformed

    private void listaIngredientesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaIngredientesMouseReleased

    }//GEN-LAST:event_listaIngredientesMouseReleased

    private void botonGuardarFicha3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarFicha3ActionPerformed

        if (usuario != null && ficha.getiD() != 0) {

            if (calificacion.getiD() == 0) {
                calificacion.crear();
                calificacion.cargarPorFichaYUsuario();
            } else {
                calificacion.editar();
            }

            busquedaPromedioCalificacion();

        }

    }//GEN-LAST:event_botonGuardarFicha3ActionPerformed

    private void botonGuardarFicha4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarFicha4ActionPerformed

        ficha.borrar();

        try {

            ((Usuario) contenedor).busqueda();
            findcode.controladores.Utilidades.cambiarPantalla(this, contenedor);

        } catch (ClassCastException ex) {

            ((Resultados) contenedor).busqueda();
            findcode.controladores.Utilidades.cambiarPantalla(this, contenedor);

        }

    }//GEN-LAST:event_botonGuardarFicha4ActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized

        jSplitPane1.setDividerLocation(this.getHeight() - 450);
        jSplitPane2.setDividerLocation(this.getHeight() - 400);
        jSplitPane3.setDividerLocation(this.getWidth() - 300);

    }//GEN-LAST:event_formComponentResized

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked

        if (usuario != null && ficha.getiD() != 0) {

            calificacion.setiDFicha(ficha.getiD());
            calificacion.setiDUsuario(usuario.getCorreo());
            calificacion.setCalificacion(1);

            jLabel8.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella2.png")
                    .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
            jLabel11.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella1.png")
                    .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
            jLabel12.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella1.png")
                    .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
            jLabel13.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella1.png")
                    .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
            jLabel15.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella1.png")
                    .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

        }

    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked

        if (usuario != null && ficha.getiD() != 0) {

            jLabel8MouseClicked(evt);
            calificacion.setCalificacion(2);

            jLabel11.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella2.png")
                    .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
            jLabel12.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella1.png")
                    .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
            jLabel13.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella1.png")
                    .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
            jLabel15.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella1.png")
                    .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

        }

    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked

        if (usuario != null && ficha.getiD() != 0) {

            jLabel11MouseClicked(evt);
            calificacion.setCalificacion(3);

            jLabel12.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella2.png")
                    .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
            jLabel13.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella1.png")
                    .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
            jLabel15.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella1.png")
                    .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

        }

    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked

        if (usuario != null && ficha.getiD() != 0) {

            jLabel12MouseClicked(evt);
            calificacion.setCalificacion(4);

            jLabel13.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella2.png")
                    .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
            jLabel15.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella1.png")
                    .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

        }

    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked

        if (usuario != null && ficha.getiD() != 0) {

            jLabel13MouseClicked(evt);
            calificacion.setCalificacion(5);

            jLabel15.setIcon(new ImageIcon(new ImageIcon("imagenes/estrella2.png")
                    .getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

        }

    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
        jLabel8MouseClicked(evt);
    }//GEN-LAST:event_jLabel8MousePressed

    private void jLabel11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MousePressed
        jLabel11MouseClicked(evt);
    }//GEN-LAST:event_jLabel11MousePressed

    private void jLabel12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MousePressed
        jLabel12MouseClicked(evt);
    }//GEN-LAST:event_jLabel12MousePressed

    private void jLabel13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MousePressed
        jLabel13MouseClicked(evt);
    }//GEN-LAST:event_jLabel13MousePressed

    private void jLabel15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MousePressed
        jLabel15MouseClicked(evt);
    }//GEN-LAST:event_jLabel15MousePressed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained

        textTituloFicha.requestFocus();

    }//GEN-LAST:event_formFocusGained

    private void jButton12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton12KeyPressed

        try {

            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

                jButton12ActionPerformed(null);

            }

        } catch (java.lang.NullPointerException ex) {
        }

    }//GEN-LAST:event_jButton12KeyPressed

    private void botonGuardarFichaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_botonGuardarFichaKeyPressed

        jButton12KeyPressed(evt);

    }//GEN-LAST:event_botonGuardarFichaKeyPressed

    private void botonGuardarFicha1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_botonGuardarFicha1KeyPressed

        jButton12KeyPressed(evt);

    }//GEN-LAST:event_botonGuardarFicha1KeyPressed

    private void botonGuardarFicha4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_botonGuardarFicha4KeyPressed

        jButton12KeyPressed(evt);

    }//GEN-LAST:event_botonGuardarFicha4KeyPressed

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed

        jButton12KeyPressed(evt);

    }//GEN-LAST:event_jComboBox1KeyPressed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed

        jButton12KeyPressed(evt);

    }//GEN-LAST:event_jTextField1KeyPressed

    private void textTituloFichaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textTituloFichaKeyPressed

        jButton12KeyPressed(evt);

    }//GEN-LAST:event_textTituloFichaKeyPressed

    private void textDescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textDescripcionKeyPressed
        jButton12KeyPressed(evt);
    }//GEN-LAST:event_textDescripcionKeyPressed

    private void textCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textCodigoKeyPressed

        jButton12KeyPressed(evt);

    }//GEN-LAST:event_textCodigoKeyPressed

    private void listaIngredientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_listaIngredientesKeyPressed

        jButton12KeyPressed(evt);

    }//GEN-LAST:event_listaIngredientesKeyPressed

    private void botonGuardarFicha3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_botonGuardarFicha3KeyPressed

        jButton12KeyPressed(evt);

    }//GEN-LAST:event_botonGuardarFicha3KeyPressed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed

        jButton12KeyPressed(evt);

    }//GEN-LAST:event_jTextField3KeyPressed

    private void botonGuardarFicha2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_botonGuardarFicha2KeyPressed

        jButton12KeyPressed(evt);

    }//GEN-LAST:event_botonGuardarFicha2KeyPressed

    private void listaIngredientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaIngredientesMousePressed

        try {
            gestorFicha.listaIngredientesMouseReleased(evt);
        } catch (BadLocationException ex) {
            Logger.getLogger(Ficha.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_listaIngredientesMousePressed

    private void popUpMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popUpMousePressed


    }//GEN-LAST:event_popUpMousePressed

    private void popUpComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_popUpComponentHidden

    }//GEN-LAST:event_popUpComponentHidden

    private void popUpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_popUpFocusLost

    }//GEN-LAST:event_popUpFocusLost

    private void popUpPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_popUpPopupMenuWillBecomeInvisible

        Point punto = MouseInfo.getPointerInfo().getLocation();
        int x = punto.x;
        int y = punto.y;

        try {
            Robot bot = new Robot();
            bot.mouseMove(x, y);
            bot.mousePress(InputEvent.BUTTON1_MASK);
            bot.mouseRelease(InputEvent.BUTTON1_MASK);
        } catch (AWTException ex) {
            Logger.getLogger(Ficha.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_popUpPopupMenuWillBecomeInvisible

    private void popUpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_popUpKeyPressed


    }//GEN-LAST:event_popUpKeyPressed

    private void popUpPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_popUpPopupMenuWillBecomeVisible


    }//GEN-LAST:event_popUpPopupMenuWillBecomeVisible

    private void popUpMenuKeyPressed(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_popUpMenuKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

            if (listaIngredientes.getSelectedIndex() < listaIngredientes.getModel().getSize() - 1) {

                PopupMenuListener[] p = popUp.getPopupMenuListeners();

                for (PopupMenuListener p1 : p) {
                    popUp.removePopupMenuListener(p1);
                }

                popUp.setVisible(false);

                for (PopupMenuListener p1 : p) {
                    popUp.addPopupMenuListener(p1);
                }

                listaIngredientes.setSelectedIndex(listaIngredientes.getSelectedIndex() + 1);
                try {
                    gestorFicha.mostrarElemento();
                } catch (BadLocationException ex) {
                    Logger.getLogger(Ficha.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

        if (evt.getKeyCode() == KeyEvent.VK_UP) {

            if (listaIngredientes.getSelectedIndex() > 0) {

                PopupMenuListener[] p = popUp.getPopupMenuListeners();

                for (PopupMenuListener p1 : p) {
                    popUp.removePopupMenuListener(p1);
                }

                popUp.setVisible(false);

                for (PopupMenuListener p1 : p) {
                    popUp.addPopupMenuListener(p1);
                }
                
                listaIngredientes.setSelectedIndex(listaIngredientes.getSelectedIndex() - 1);
                try {
                    gestorFicha.mostrarElemento();
                } catch (BadLocationException ex) {
                    Logger.getLogger(Ficha.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

    }//GEN-LAST:event_popUpMenuKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonGuardarFicha;
    private javax.swing.JButton botonGuardarFicha1;
    private javax.swing.JButton botonGuardarFicha2;
    private javax.swing.JButton botonGuardarFicha3;
    private javax.swing.JButton botonGuardarFicha4;
    private javax.swing.JMenuItem itemBorrar;
    private javax.swing.JMenuItem itemCargar;
    private javax.swing.JMenuItem itemGuardar;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel labelComentario;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JList<String> listaIngredientes;
    private javax.swing.JList<String> listaPopUp;
    private javax.swing.JPopupMenu popUp;
    private javax.swing.JTextPane textCodigo;
    private javax.swing.JTextArea textComentario;
    private javax.swing.JTextArea textDescripcion;
    private javax.swing.JTextField textTitulo;
    private javax.swing.JTextField textTituloFicha;
    private javax.swing.JDialog ventanaGuardar;
    // End of variables declaration//GEN-END:variables

}

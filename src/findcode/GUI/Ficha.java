package findcode.GUI;

import findcode.clases.Lenguaje;
import findcode.controladores.Utilidades;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Ficha extends javax.swing.JPanel {

    private JPanel contenedor;
    private findcode.clases.Usuario usuario;
    findcode.clases.Ficha ficha;
    private findcode.controladores.GestorFicha gestorFicha;
    private ArrayList<findcode.clases.Comentario> comentarios;
    private ArrayList<findcode.clases.Ficha> relacionados;

    // Consultar ficha sin usuario
    public Ficha(JPanel contenedor, findcode.clases.Ficha ficha) {

        initComponents();
        this.contenedor = contenedor;
        this.ficha = ficha;
        jButton8.setVisible(false);
        jButton13.setVisible(false);
        botonGuardarFicha.setVisible(false);
        botonGuardarFicha1.setVisible(false);
        jTextField1.setVisible(false);
        jTextField3.setVisible(false);
        botonGuardarFicha2.setVisible(false);
        String[] s = {ficha.getiDLenguaje()};
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(s));
        jComboBox1.setSelectedItem(ficha.getiDLenguaje());
        Utilidades.personalizarCampo(textTituloFicha, "Titulo", ficha.getTitulo());
        Utilidades.personalizarCampo(textDescripcion, "Descripcion del codigo", ficha.getDescripcion());
        Utilidades.personalizarCampo(textCodigo, "Sintaxis", ficha.getEjemplo());
        Utilidades.personalizarCampo(jTextField1, "Nuevo lenguaje", "");
        Utilidades.personalizarCampo(jTextField3, "Nuevo comentario", "");
        busquedaComentarios();
        busquedaFichasRelacionadas();

        //ficha = new findcode.clases.Ficha(popUp, listaIngredientes, textCodigo, listaPopUp);
        gestorFicha = new findcode.controladores.GestorFicha(popUp, listaIngredientes, textCodigo,
                listaPopUp, itemCargar, itemGuardar, itemBorrar, ventanaGuardar, textComentario, textTitulo,
                textDescripcion, textTituloFicha);

    }

    // Consultar ficha con usuario
    public Ficha(JPanel contenedor, findcode.clases.Usuario usuario, findcode.clases.Ficha ficha) {

        initComponents();
        this.contenedor = contenedor;
        this.ficha = ficha;
        this.usuario = usuario;
        botonGuardarFicha.setVisible(false);
        botonGuardarFicha1.setVisible(false);
        jTextField1.setVisible(false);
        jButton8.setVisible(true);
        jButton13.setVisible(true);
        jTextField3.setVisible(true);
        botonGuardarFicha2.setVisible(true);
        jButton8.setText(usuario.getNickname());
        String[] s = {ficha.getiDLenguaje()};
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(s));
        jComboBox1.setSelectedItem(ficha.getiDLenguaje());
        Utilidades.personalizarCampo(textTituloFicha, "Titulo", ficha.getTitulo());
        Utilidades.personalizarCampo(textDescripcion, "Descripcion del codigo", ficha.getDescripcion());
        Utilidades.personalizarCampo(textCodigo, "Sintaxis", ficha.getEjemplo());
        Utilidades.personalizarCampo(jTextField1, "Nuevo lenguaje", "");
        Utilidades.personalizarCampo(jTextField3, "Nuevo comentario", "");
        busquedaComentarios();
        busquedaFichasRelacionadas();

        if(usuario.getCorreo().equals(ficha.getiDUsuario())){
            textTituloFicha.setEditable(true);
            textDescripcion.setEditable(true);
            textCodigo.setEditable(true);
            botonGuardarFicha1.setVisible(true);
            jTextField1.setVisible(true);
        }
        
        //ficha = new findcode.clases.Ficha(popUp, listaIngredientes, textCodigo, listaPopUp);
        gestorFicha = new findcode.controladores.GestorFicha(popUp, listaIngredientes, textCodigo,
                listaPopUp, itemCargar, itemGuardar, itemBorrar, ventanaGuardar, textComentario, textTitulo,
                textDescripcion, textTituloFicha);

    }

    // Crear ficha
    public Ficha(JPanel contenedor, findcode.clases.Usuario usuario) {

        initComponents();
        this.contenedor = contenedor;
        this.usuario = usuario;
        this.ficha = new findcode.clases.Ficha();
        jButton8.setVisible(true);
        jButton13.setVisible(true);
        jTextField3.setVisible(true);
        jButton8.setText(usuario.getNickname());
        Utilidades.personalizarCampo(textTituloFicha, "Titulo", "");
        Utilidades.personalizarCampo(textDescripcion, "Descripcion del codigo", "");
        Utilidades.personalizarCampo(textCodigo, "Sintaxis", "");
        Utilidades.personalizarCampo(jTextField1, "Nuevo lenguaje", "");
        Utilidades.personalizarCampo(jTextField3, "Nuevo comentario", "");
        busquedaComentarios();
        busquedaFichasRelacionadas();
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(Lenguaje.cargarTodos().toArray()));
        textTituloFicha.setEditable(true);
        textDescripcion.setEditable(true);
        textCodigo.setEditable(true);
        botonGuardarFicha.setVisible(true);
        botonGuardarFicha.setVisible(false);
        jTextField1.setVisible(true);
        gestorFicha = new findcode.controladores.GestorFicha(popUp, listaIngredientes, textCodigo,
                listaPopUp, itemCargar, itemGuardar, itemBorrar, ventanaGuardar, textComentario, textTitulo,
                textDescripcion, textTituloFicha);


    }

    public final void busquedaComentarios() {

        jPanel7.removeAll();
        ArrayList<Comentario> panelesComentario = new ArrayList<>();
        comentarios = findcode.clases.Comentario.cargarPorFicha(ficha.getiD());

        for (findcode.clases.Comentario comentario : comentarios) {
            panelesComentario.add(new Comentario(contenedor, comentario));
        }

        for (Comentario comentario : panelesComentario) {
            jPanel7.add(comentario);
        }

    }
    
    public final void busquedaFichasRelacionadas(){
        
        jPanel3.removeAll();
        ArrayList<Resultado> panelesResultado = new ArrayList<>();
        relacionados = ficha.buscarFichasRelacionadas();
        
        System.out.println("sdasda"+usuario);
        for (findcode.clases.Ficha ficha1 : relacionados) {
            panelesResultado.add(new Resultado(this, usuario, ficha1));
        }
        
        for (Resultado resultado : panelesResultado) {
            jPanel3.add(resultado);
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
        jPanel7 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        botonGuardarFicha2 = new javax.swing.JButton();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        listaIngredientes = new javax.swing.JList<String>();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();

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

        setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jButton12.setText("v");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
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

        jComboBox1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jComboBox1.setFocusable(false);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        botonGuardarFicha1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        botonGuardarFicha1.setText("Guardar ficha");
        botonGuardarFicha1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarFicha1ActionPerformed(evt);
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
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1)
                            .addComponent(botonGuardarFicha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1)
                            .addComponent(botonGuardarFicha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
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

        textDescripcion.setEditable(false);
        textDescripcion.setColumns(20);
        textDescripcion.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        textDescripcion.setRows(2);
        textDescripcion.setText("Descripcion");
        jScrollPane6.setViewportView(textDescripcion);

        jSplitPane3.setBackground(new java.awt.Color(255, 255, 255));
        jSplitPane3.setDividerLocation(500);

        jSplitPane1.setBackground(new java.awt.Color(255, 255, 255));
        jSplitPane1.setDividerLocation(200);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        textCodigo.setEditable(false);
        textCodigo.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        textCodigo.setText("Sintaxis");
        textCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                textCodigoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                textCodigoMouseReleased(evt);
            }
        });
        textCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textCodigoKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(textCodigo);

        jSplitPane1.setTopComponent(jScrollPane2);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.PAGE_AXIS));

        jTextField3.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        botonGuardarFicha2.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        botonGuardarFicha2.setText("Comentar");
        botonGuardarFicha2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarFicha2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonGuardarFicha2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonGuardarFicha2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jSplitPane1.setRightComponent(jPanel4);

        jSplitPane3.setLeftComponent(jSplitPane1);

        jSplitPane2.setBackground(new java.awt.Color(255, 255, 255));
        jSplitPane2.setBorder(null);
        jSplitPane2.setDividerLocation(200);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Calificación");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Lista de ingredientes");

        listaIngredientes.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        listaIngredientes.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        listaIngredientes.setSelectionBackground(new java.awt.Color(51, 204, 0));
        listaIngredientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                listaIngredientesMouseReleased(evt);
            }
        });
        jScrollPane5.setViewportView(listaIngredientes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane2.setTopComponent(jPanel2);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Codigos relacionados");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane8.setViewportView(jPanel3);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane8))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane2.setRightComponent(jPanel6);

        jSplitPane3.setRightComponent(jSplitPane2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSplitPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textTituloFicha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

    private void listaIngredientesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaIngredientesMouseReleased
        gestorFicha.listaIngredientesMouseReleased(evt);
    }//GEN-LAST:event_listaIngredientesMouseReleased

    private void itemBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBorrarActionPerformed
        gestorFicha.itemBorrarActionPerformed(evt);
    }//GEN-LAST:event_itemBorrarActionPerformed

    private void textTituloFichaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTituloFichaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textTituloFichaActionPerformed

    private void botonGuardarFichaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarFichaActionPerformed

        ficha.setTitulo(textTituloFicha.getText());
        ficha.setDescripcion(textDescripcion.getText());
        ficha.setEjemplo(textCodigo.getText());
        ficha.setiDLenguaje(jComboBox1.getSelectedItem().toString());
        ficha.setiDUsuario(usuario.getCorreo());
        ficha.crear();
        
        findcode.controladores.Utilidades.cambiarPantalla(this, new Usuario(this, usuario));
        
    }//GEN-LAST:event_botonGuardarFichaActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        
        
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void botonGuardarFicha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarFicha1ActionPerformed
        
        ficha.setTitulo(textTituloFicha.getText());
        ficha.setDescripcion(textDescripcion.getText());
        ficha.setEjemplo(textCodigo.getText());
        ficha.setiDLenguaje(jComboBox1.getSelectedItem().toString());
        ficha.editar();
        
        try {
            
            ((Usuario) contenedor).busqueda();
            findcode.controladores.Utilidades.cambiarPantalla(this, contenedor);
            
        }catch(ClassCastException ex){
            
            ((Resultados) contenedor).busqueda();
            findcode.controladores.Utilidades.cambiarPantalla(this, contenedor);
            
        }
            
        
    }//GEN-LAST:event_botonGuardarFicha1ActionPerformed

    private void botonGuardarFicha2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarFicha2ActionPerformed
        
        if(Utilidades.validarCampo(jTextField3)){
            
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonGuardarFicha;
    private javax.swing.JButton botonGuardarFicha1;
    private javax.swing.JButton botonGuardarFicha2;
    private javax.swing.JMenuItem itemBorrar;
    private javax.swing.JMenuItem itemCargar;
    private javax.swing.JMenuItem itemGuardar;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
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
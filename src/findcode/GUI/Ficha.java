package findcode.GUI;

import javax.swing.JPanel;

public class Ficha extends javax.swing.JPanel {

    private JPanel contenedor;
    private findcode.clases.Usuario usuario;
    findcode.clases.Ficha ficha;
    private findcode.controladores.GestorFicha gestorFicha;

    // Consultar ficha sin usuario
    public Ficha(JPanel contenedor, findcode.clases.Ficha ficha) {

        initComponents();
        this.contenedor = contenedor;
        this.ficha = ficha;
        jButton8.setVisible(false);
        jButton13.setVisible(false);

        //ficha = new findcode.clases.Ficha(popUp, listaIngredientes, textCodigo, listaPopUp);
        gestorFicha = new findcode.controladores.GestorFicha(popUp, listaIngredientes, textCodigo,
                listaPopUp, itemCargar, itemGuardar, itemBorrar, ventanaGuardar, textComentario, textTitulo);

    }
    
    // Consultar ficha con usuario
    public Ficha(JPanel contenedor, findcode.clases.Usuario usuario, findcode.clases.Ficha ficha) {

        this(contenedor, ficha);
        this.usuario = usuario;
        jButton8.setVisible(true);
        jButton13.setVisible(true);
        
    }
    
    // Crear ficha
    public Ficha(JPanel contenedor, findcode.clases.Usuario usuario) {

        this(contenedor, usuario, new findcode.clases.Ficha());

    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popUp = new javax.swing.JPopupMenu();
        itemGuardar = new javax.swing.JMenuItem();
        itemBorrar = new javax.swing.JMenuItem();
        itemCargar = new javax.swing.JMenuItem();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaPopUp = new javax.swing.JList<>();
        ventanaGuardar = new javax.swing.JDialog();
        labelTitulo = new javax.swing.JLabel();
        textTitulo = new javax.swing.JTextField();
        labelComentario = new javax.swing.JLabel();
        botonGuardar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        textComentario = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textCodigo = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        listaIngredientes = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();

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

        listaPopUp.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
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
        ventanaGuardar.setPreferredSize(new java.awt.Dimension(330, 260));

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
                .addGroup(ventanaGuardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonGuardar)
                    .addComponent(botonCancelar))
                .addContainerGap())
        );

        setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Titulo");

        textCodigo.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
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

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Calificación");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel5.setText("Codigos relacionados");

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel4.setText("Lista de ingredientes");

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

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 482, Short.MAX_VALUE)
                        .addComponent(jButton13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
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
                .addGap(9, 9, 9)
                .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        listaIngredientes.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        listaIngredientes.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        listaIngredientes.setSelectionBackground(new java.awt.Color(51, 204, 0));
        listaIngredientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                listaIngredientesMouseReleased(evt);
            }
        });
        jScrollPane5.setViewportView(listaIngredientes);

        jButton1.setText("Guardar ficha");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane5)))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JMenuItem itemBorrar;
    private javax.swing.JMenuItem itemCargar;
    private javax.swing.JMenuItem itemGuardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelComentario;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JList<String> listaIngredientes;
    private javax.swing.JList<String> listaPopUp;
    private javax.swing.JPopupMenu popUp;
    private javax.swing.JTextPane textCodigo;
    private javax.swing.JTextArea textComentario;
    private javax.swing.JTextField textTitulo;
    private javax.swing.JDialog ventanaGuardar;
    // End of variables declaration//GEN-END:variables

}

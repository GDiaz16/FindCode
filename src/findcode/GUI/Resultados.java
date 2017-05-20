package findcode.GUI;

import findcode.controladores.Utilidades;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JPanel;

public class Resultados extends javax.swing.JPanel {

    private JPanel contenedor;
    private findcode.model.Usuario usuario;
    private ArrayList<findcode.model.Ficha> fichas;
    private ArrayList<Resultado> resultados;
    private String busqueda;
    private String lenguaje;

    public Resultados(JPanel contenedor, String busqueda, String lenguaje) {

        initComponents();
        jButton7.setVisible(false);
        jButton8.setVisible(false);
        jLabel8.setVisible(false);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane2.getViewport().setOpaque(false);
        this.contenedor = contenedor;
        this.busqueda = busqueda;
        this.lenguaje = lenguaje;
        personalizar();
        busqueda();

    }

    public Resultados(JPanel contenedor, findcode.model.Usuario usuario, String busqueda, String lenguaje) {

        initComponents();
        jButton7.setVisible(true);
        jButton8.setVisible(true);
        jLabel8.setVisible(false);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane2.getViewport().setOpaque(false);
        this.contenedor = contenedor;
        this.busqueda = busqueda;
        this.lenguaje = lenguaje;
        jButton8.setText(usuario.getNickname());
        this.usuario = usuario;
        personalizar();
        busqueda();

    }
    
    public final void personalizar(){
        
        Utilidades.personalizarBotonBack(jButton6);
        Utilidades.personalizarBotones(jButton7);
        Utilidades.personalizarBotones(jButton8);
        
    }

    public final void busqueda() {

        jPanel2.removeAll();
        resultados = new ArrayList<>();
        fichas = findcode.model.Ficha.buscarTextoPorLenguaje(busqueda, lenguaje);

        if (fichas.isEmpty()) {
            jPanel2.setLayout(new java.awt.GridLayout());
            jPanel2.add(jLabel8);
            jLabel8.setVisible(true);
        }

        if (usuario == null) {
            for (findcode.model.Ficha ficha : fichas) {
                resultados.add(new Resultado(this, ficha));
            }
        } else {
            for (findcode.model.Ficha ficha : fichas) {
                resultados.add(new Resultado(this, usuario, ficha));
            }
        }

        for (Resultado resultado : resultados) {
            jPanel2.add(resultado);
        }
        
        for (int i = 0; i < resultados.size(); i++) {
            
            final int i2 = i;
            
            resultados.get(i).addKeyListener(new java.awt.event.KeyAdapter() {
                
                @Override
                public void keyPressed(java.awt.event.KeyEvent evt) {

                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                        if (i2 > 0) {
                            resultados.get(i2 - 1).requestFocus();
                        }

                    }
                    
                    if (evt.getKeyCode() == KeyEvent.VK_UP) {

                        if (i2 > 0) {
                            resultados.get(i2 - 1).requestFocus();
                        }

                    }
                    
                    if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

                        if (i2 < resultados.size() - 1) {
                            resultados.get(i2 + 1).requestFocus();
                        }

                    }

                }
            });
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();

        setOpaque(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        setLayout(new java.awt.CardLayout());

        jScrollPane1.setOpaque(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        jScrollPane2.setOpaque(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(0, 153, 153));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.PAGE_AXIS));

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 153));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("No hay fichas");
        jPanel2.add(jLabel8);

        jScrollPane2.setViewportView(jPanel2);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setOpaque(false);

        jButton6.setText("v");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jButton6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton6KeyPressed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jButton7.setText("Cerrar sesion");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jButton7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton7KeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 153));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("FindCode");

        jButton8.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jButton8.setText("Usuario");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jButton8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton8KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(0, 0, 0))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        add(jScrollPane1, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        findcode.controladores.Utilidades.cambiarPantalla(this, contenedor);

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        findcode.controladores.Utilidades.cambiarPantalla(this, new Inicio());

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        findcode.controladores.Utilidades.cambiarPantalla(this, new Usuario(this, this.usuario));

    }//GEN-LAST:event_jButton8ActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            jButton6ActionPerformed(null);

        }

    }//GEN-LAST:event_formKeyPressed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained

        jPanel2.getComponent(0).requestFocus();

    }//GEN-LAST:event_formFocusGained

    private void jButton6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton6KeyPressed
        
        try {
            
            if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

                jButton6ActionPerformed(null);

            }
            
            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {

                jPanel2.getComponent(0).requestFocus();

            }
            
        } catch (java.lang.NullPointerException ex) {}
        
    }//GEN-LAST:event_jButton6KeyPressed

    private void jButton7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyPressed
        
        jButton6KeyPressed(evt);
        
    }//GEN-LAST:event_jButton7KeyPressed

    private void jButton8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton8KeyPressed
        
        jButton6KeyPressed(evt);
        
    }//GEN-LAST:event_jButton8KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}

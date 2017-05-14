package findcode.controladores;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;

public class Utilidades {

    public static Color COLOR_INICIAL = new java.awt.Color(0, 102, 102);
    public static Color COLOR_DEFECTO = Color.GRAY;
    public static Color COLOR_ERROR = new java.awt.Color(153, 0, 0);
    
    public static void personalizarCampo(JTextComponent jTextField, String textoDefault, String texto) {

        

        jTextField.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        jTextField.setForeground(COLOR_INICIAL);
        jTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, COLOR_INICIAL));
        jTextField.setDisabledTextColor(COLOR_INICIAL);
        jTextField.setOpaque(false);
        
        try{
            ((JTextField)jTextField).setHorizontalAlignment(javax.swing.JTextField.CENTER);
        } catch(java.lang.ClassCastException ex){}
        

        // Dar color gris
        jTextField.setForeground(COLOR_DEFECTO);
        jTextField.setText(textoDefault);

        if (texto.trim().equals("")) {

            // Borrar texto al hacer click una vez
            jTextField.addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusGained(java.awt.event.FocusEvent evt) {
                    jTextField.setBackground(Color.white);
                    jTextField.setForeground(COLOR_INICIAL);
                    jTextField.setText("");
                    jTextField.removeFocusListener(this);
                }
            });

        } else {

            jTextField.setBackground(Color.white);
            jTextField.setForeground(COLOR_INICIAL);
            jTextField.setText(texto);

        }

        // Mostrar texto por defecto si el campo esta vacio
        jTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {

                if (jTextField.getText().trim().equals("")) {
                    jTextField.setBackground(Color.white);
                    jTextField.setForeground(COLOR_DEFECTO);
                    jTextField.setText(textoDefault);
                    jTextField.addFocusListener(new java.awt.event.FocusAdapter() {
                        @Override
                        public void focusGained(java.awt.event.FocusEvent evt) {
                            jTextField.setBackground(Color.white);
                            jTextField.setForeground(COLOR_INICIAL);
                            jTextField.setText("");
                            jTextField.removeFocusListener(this);
                        }
                    });
                }

            }
        });

    }
    
    public static void personalizarBotones(JButton botton) {
        
        int height = 40;
        int width = botton.getText().length() * 10;
        
        if (width < height) {
            width = height;
        }
        
        ImageIcon imagen1 = new ImageIcon("imagenes/boton1.png");
        ImageIcon imagen2 = new ImageIcon("imagenes/boton2.png");
        ImageIcon imagen3 = new ImageIcon("imagenes/boton3.png");

        imagen1 = new ImageIcon(imagen1.getImage().getScaledInstance(width,height,
                Image.SCALE_DEFAULT));

        imagen2 = new ImageIcon(imagen2.getImage().getScaledInstance(width,height,
                Image.SCALE_DEFAULT));
        
        imagen3 = new ImageIcon(imagen3.getImage().getScaledInstance(width,height,
                Image.SCALE_DEFAULT));
        
        
        botton.setBorderPainted(false);
        botton.setContentAreaFilled(false);
        botton.setRolloverEnabled(true);
        botton.setBorder(null);
        
        botton.setIcon(imagen1);
        botton.setRolloverIcon(imagen2);
        botton.setSelectedIcon(imagen3);
        botton.setHorizontalTextPosition(SwingConstants.CENTER);
        botton.setVerticalTextPosition(SwingConstants.CENTER);

    }

    public static boolean validarCampo(JTextComponent jTextField) {
        if (jTextField.getText().trim().equals("")
                || jTextField.getForeground().equals(COLOR_DEFECTO)) {
            jTextField.setBackground(COLOR_ERROR);
            return false;
        }
        return true;
    }

    public static boolean compararCampos(JTextComponent jTextField1, JTextComponent jTextField2) {
        if (!jTextField1.getText().equals(jTextField2.getText())) {
            jTextField1.setBackground(COLOR_ERROR);
            jTextField2.setBackground(COLOR_ERROR);

            jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusGained(java.awt.event.FocusEvent evt) {
                    jTextField1.setBackground(Color.white);
                }
            });

            jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusGained(java.awt.event.FocusEvent evt) {
                    jTextField2.setBackground(Color.white);
                }
            });

            return false;
        }
        return true;
    }

    public static void cambiarPantalla(JComponent anterior, JComponent nuevo) {

        anterior.getParent().add(nuevo);
        nuevo.getParent().remove(anterior);
        nuevo.getParent().validate();
        nuevo.updateUI();
        nuevo.requestFocus();

    }

    public static void asignarFondoFijo(JFrame frame, File rutaImagen) {

        // Fondo inicial
        ((JPanel) frame.getContentPane()).setOpaque(false);
        ImageIcon imagen = null;
        try {
            imagen = new ImageIcon(rutaImagen.getCanonicalPath());
        } catch (IOException ex) {
        }

        imagen = new ImageIcon(imagen.getImage().getScaledInstance(frame.getWidth(),
                frame.getHeight(),
                Image.SCALE_DEFAULT));

        JLabel fondo = new JLabel();
        fondo.setIcon(imagen);
        fondo.setHorizontalAlignment(JLabel.RIGHT);
        fondo.setVerticalAlignment(JLabel.BOTTOM);
        frame.getLayeredPane().add(fondo, JLayeredPane.FRAME_CONTENT_LAYER);
        fondo.setBounds(0, 0, frame.getWidth(), frame.getHeight());

        // Fondo al redimencionar
        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {

                Thread hilo = new Thread("") {

                    @Override
                    public void run() {

                        ImageIcon imagen = null;
                        try {
                            imagen = new ImageIcon(rutaImagen.getCanonicalPath());
                        } catch (IOException ex) {
                        }
                        imagen = new ImageIcon(imagen.getImage().getScaledInstance(frame.getWidth(),
                                frame.getHeight(),
                                Image.SCALE_DEFAULT));
                        fondo.setIcon(imagen);
                        fondo.setBounds(0, 0, frame.getWidth(), frame.getHeight());

                    }

                };
                hilo.start();

            }
        });

    }

    public static void asignarFondoAnimado(JFrame frame, File carpeta) {

        // Fondo inicial
        ((JPanel) frame.getContentPane()).setOpaque(false);
        ImageIcon imagen = null;
        try {
            imagen = new ImageIcon(carpeta.listFiles()[0].getCanonicalPath());
        } catch (IOException ex) {
        }

        imagen = new ImageIcon(imagen.getImage().getScaledInstance(frame.getWidth(),
                frame.getHeight(),
                Image.SCALE_DEFAULT));

        JLabel fondo = new JLabel();
        fondo.setIcon(imagen);
        fondo.setHorizontalAlignment(JLabel.RIGHT);
        fondo.setVerticalAlignment(JLabel.BOTTOM);
        frame.getLayeredPane().add(fondo, JLayeredPane.FRAME_CONTENT_LAYER);
        fondo.setBounds(0, 0, frame.getWidth(), frame.getHeight());

        // Fondo al redimencionar
        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {

                Thread hilo = new Thread("") {

                    @Override
                    public void run() {

                        ImageIcon imagen = null;
                        try {
                            imagen = new ImageIcon(carpeta.listFiles()[0].getCanonicalPath());
                        } catch (IOException ex) {
                        }
                        imagen = new ImageIcon(imagen.getImage().getScaledInstance(frame.getWidth(),
                                frame.getHeight(),
                                Image.SCALE_DEFAULT));
                        fondo.setIcon(imagen);
                        fondo.setBounds(0, 0, frame.getWidth(), frame.getHeight());

                    }

                };
                hilo.start();

            }
        });

        Thread hilo = new Thread("") {

            @Override
            public void run() {

                while (true) {

                    for (int i = 0; i < carpeta.listFiles().length; i++) {

                        ImageIcon imagen = null;
                        try {
                            imagen = new ImageIcon(carpeta.listFiles()[i].getCanonicalPath());
                        } catch (IOException ex) {
                        }
                        imagen = new ImageIcon(imagen.getImage().getScaledInstance(frame.getWidth(),
                                frame.getHeight(),
                                Image.SCALE_DEFAULT));
                        fondo.setIcon(imagen);
                        fondo.setBounds(0, 0, frame.getWidth(), frame.getHeight());

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException ex) {
                        }

                    }

                }

            }

        };
        hilo.start();

    }

}

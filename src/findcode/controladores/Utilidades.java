package findcode.controladores;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

public class Utilidades {

    public static Color COLOR_INICIAL = new java.awt.Color(0, 102, 102);
    public static Color COLOR_DEFECTO = Color.GRAY;
    public static Color COLOR_ERROR = Color.red;

    public static void personalizarCampo(JTextComponent jTextField, String textoDefault, String texto) {

        jTextField.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        jTextField.setForeground(COLOR_INICIAL);
        jTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, COLOR_INICIAL));
        jTextField.setDisabledTextColor(COLOR_INICIAL);
        jTextField.setOpaque(false);

        try {
            ((JTextField) jTextField).setHorizontalAlignment(javax.swing.JTextField.CENTER);
        } catch (java.lang.ClassCastException ex) {
        }

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

        int height = 30;
        int width = botton.getText().length() * 10;

        if (width < height) {
            width = height;
        }

        final ImageIcon imagen1;
        final ImageIcon imagen2;
        final ImageIcon imagen3;

        imagen1 = new ImageIcon(new ImageIcon("imagenes/boton1.png").getImage().getScaledInstance(width, height,
                Image.SCALE_DEFAULT));

        imagen2 = new ImageIcon(new ImageIcon("imagenes/boton2.png").getImage().getScaledInstance(width, height,
                Image.SCALE_DEFAULT));

        imagen3 = new ImageIcon(new ImageIcon("imagenes/boton3.png").getImage().getScaledInstance(width, height,
                Image.SCALE_DEFAULT));

        botton.setBorderPainted(false);
        botton.setContentAreaFilled(false);
        botton.setRolloverEnabled(true);
        botton.setBorder(null);
        botton.setForeground(COLOR_INICIAL);
        botton.setFont(new java.awt.Font("Comic Sans MS", 1, 14));

        botton.setIcon(imagen1);
        botton.setRolloverIcon(imagen2);
        botton.setSelectedIcon(imagen3);
        botton.setHorizontalTextPosition(SwingConstants.CENTER);
        botton.setVerticalTextPosition(SwingConstants.CENTER);
        
        botton.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                botton.setIcon(imagen2);
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                botton.setIcon(imagen1);
            }
        });
        
        botton.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    botton.setIcon(imagen2);
                }
            }
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    botton.setIcon(imagen1);
                }
            }
        });

    }
    
    public static void personalizarBotonBack(JButton botton) {

        int height = 30;
        int width = 30;

        final ImageIcon imagen1;
        final ImageIcon imagen2;
        final ImageIcon imagen3;

        imagen1 = new ImageIcon(new ImageIcon("imagenes/atras1.png").getImage().getScaledInstance(width, height,
                Image.SCALE_DEFAULT));

        imagen2 = new ImageIcon(new ImageIcon("imagenes/atras2.png").getImage().getScaledInstance(width, height,
                Image.SCALE_DEFAULT));

        imagen3 = new ImageIcon(new ImageIcon("imagenes/atras3.png").getImage().getScaledInstance(width, height,
                Image.SCALE_DEFAULT));

        botton.setBorderPainted(false);
        botton.setContentAreaFilled(false);
        botton.setRolloverEnabled(true);
        botton.setBorder(null);
        botton.setText(null);

        botton.setIcon(imagen1);
        botton.setRolloverIcon(imagen2);
        botton.setSelectedIcon(imagen3);
        
        botton.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                botton.setIcon(imagen2);
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                botton.setIcon(imagen1);
            }
        });
        
        botton.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    botton.setIcon(imagen2);
                }
            }
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    botton.setIcon(imagen1);
                }
            }
        });

    }

    public static void personalizarComboBox(JComboBox jComboBox, String textoDefault, String texto) {

        String[] datos = new String[jComboBox.getItemCount()];

        for (int i = 0; i < datos.length; i++) {
            datos[i] = (String) jComboBox.getItemAt(i);
        }

        jComboBox.setEditable(true);
        jComboBox.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        jComboBox.setForeground(COLOR_INICIAL);
        jComboBox.setBorder(null);
        jComboBox.setOpaque(false);

        for (Component child : jComboBox.getComponents()) {
            if (child instanceof JButton) {
                jComboBox.remove(child);
            }
        }

        ((JTextField) jComboBox.getEditor().getEditorComponent()).setEditable(true);
        ((JTextField) jComboBox.getEditor().getEditorComponent()).setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        ((JTextField) jComboBox.getEditor().getEditorComponent()).setForeground(COLOR_INICIAL);
        ((JTextField) jComboBox.getEditor().getEditorComponent()).setBorder(null);
        ((JTextField) jComboBox.getEditor().getEditorComponent()).setOpaque(false);
        ((JTextField) jComboBox.getEditor().getEditorComponent()).setHorizontalAlignment(javax.swing.JTextField.CENTER);
        personalizarCampo(((JTextField) jComboBox.getEditor().getEditorComponent()), textoDefault, texto);
        jComboBox.setSelectedItem(texto);
        
        ((JTextField) jComboBox.getEditor().getEditorComponent()).addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                
                if (evt.getKeyCode() != KeyEvent.VK_ENTER
                        && evt.getKeyCode() != KeyEvent.VK_DOWN
                        && evt.getKeyCode() != KeyEvent.VK_UP) {

                    String texto = ((JTextField) jComboBox.getEditor().getEditorComponent()).getText();

                    ArrayList<Object> datosTemporales = new ArrayList<>();
                    for (String dato : datos) {
                        if (dato.toUpperCase().contains(texto.toUpperCase())) {
                            datosTemporales.add(dato);
                        }
                    }
                    jComboBox.setModel(new javax.swing.DefaultComboBoxModel(datosTemporales.toArray()));
                    ((JTextField) jComboBox.getEditor().getEditorComponent()).setText(texto);
                    jComboBox.showPopup();

                } 

            }
        });
        
        ((JTextField) jComboBox.getEditor().getEditorComponent()).addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                    ((JTextField) jComboBox.getEditor().getEditorComponent()).setText(jComboBox.getSelectedItem().toString());

                } 

            }
        });

    }

    public static boolean validarCampo(JTextComponent jTextField) {
        if (jTextField.getText().trim().equals("")
                || jTextField.getForeground().equals(COLOR_DEFECTO)
                || jTextField.getForeground().equals(COLOR_ERROR)) {
            jTextField.setForeground(COLOR_ERROR);
            return false;
        }
        return true;
    }

    public static boolean compararCampos(JTextComponent jTextField1, JTextComponent jTextField2) {
        if (!jTextField1.getText().equals(jTextField2.getText())) {
            jTextField1.setForeground(COLOR_ERROR);
            jTextField2.setForeground(COLOR_ERROR);

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
    
    public static void asignarFondoFijo(JDialog frame, File rutaImagen) {

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

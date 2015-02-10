package gestionuniversidad.Paneles;

import gestionuniversidad.GestionUniversidad;
import java.awt.CardLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Clase PanelOtros. Hereda de la clase padre JPanel para incluir mediante componentes
 * gráficos las funciones extras que no tenían cabida en otro panel, de momento en la
 * primera versión se incluye la opción de añadir más edificios donde realizar los cursos.
 * 
 * @auhtor: Carlos Marin Fernandez
 * @date: 10-feb-2015
 * @version: 1.0 
 */
public class PanelOtros  extends JPanel {
    
    private final JPanel Menu, Contenido;
    
    //Panel Menu
    private JButton MenuButtonEdificio;
    
    //Panel Contenido Añadir Nuevo Edificio
    private JPanel NuevoEdificioPanel;
    private JTextField NuevoEdificioTextField;
    private JButton NuevoEdificioButtonCrear;
    
    /**
     * Constuctor del panel Curso
     */
    public PanelOtros() {
        //Declaracion de los paneles que se mostraran en toda la aplicacion
        Menu = new JPanel();
        Contenido = new JPanel();
        Contenido.setLayout(new CardLayout());
        
        initPanelMenu();
        initPanelNuevoEdificio();
        
        this.setLayout(null);

        //Colocacion de los paneles en el JFrame
        Menu.setBounds(12, 15, 205, 450);
        this.add(Menu);
        Contenido.setBounds(230, 25, 600, 450);
        this.add(Contenido);

        Contenido.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);   
    }
    
    private void initPanelMenu() {
        //Iconos de los botones
        ImageIcon Edificio = new ImageIcon("./images/Edificio.png");
        ImageIcon Universidad = new ImageIcon("./images/Universidad.png");

        //Botones Menu
        MenuButtonEdificio = new JButton("  Añadir Edificio   ", Edificio);

        //Menu Acciones
        Menu.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
        Menu.add(MenuButtonEdificio);
        Menu.add(new JLabel(Universidad));
        
        //Para cada boton del Menu, inicializamos sus componentes, al hacer click
        MenuButtonEdificio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                NuevoEdificioTextField.setText("");
                CardLayout cl = (CardLayout) (Contenido.getLayout());
                cl.show(Contenido, "Panel NuevoEdificio");
            }
        });
    }

    private void initPanelNuevoEdificio() {
        NuevoEdificioPanel = new JPanel();
        NuevoEdificioPanel.setLayout(new GridLayout(1,2));
        NuevoEdificioPanel.setBounds(0, 0, 200, 150);
        
        JPanel PanelIzquierdaEtiquetas = new JPanel(new GridLayout(15, 1));
        PanelIzquierdaEtiquetas.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        JPanel PanelDerechaEntradaDatos = new JPanel(new GridLayout(15, 1));

        //Inserción de Datos
        PanelIzquierdaEtiquetas.add(new JLabel("Inserir el nombre del edificio"));
        PanelIzquierdaEtiquetas.add(new JLabel("· Nombre del edificio: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        NuevoEdificioTextField = new JTextField();
        PanelDerechaEntradaDatos.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(NuevoEdificioTextField);
        PanelDerechaEntradaDatos.add(new JLabel(""));

        NuevoEdificioButtonCrear = new JButton("Añadir Edificio");
        PanelDerechaEntradaDatos.add(NuevoEdificioButtonCrear);
       
        NuevoEdificioButtonCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Comprobacion de que se han rellenado todos los campos
                    if (NuevoEdificioTextField.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "ERROR: Has dejado "
                                + "algun campo sin rellenar", "Crear Profesor", JOptionPane.CANCEL_OPTION);
                    } else añadirEdificio(NuevoEdificioTextField.getText());
                } catch (Exception error) {
                }
            }
        });
        
        NuevoEdificioPanel.add(PanelIzquierdaEtiquetas);
        NuevoEdificioPanel.add(PanelDerechaEntradaDatos);
        
        Contenido.add(NuevoEdificioPanel, "Panel NuevoEdificio");
    }
    
    /**
     * De manera órdenada, se añaden los edificios en el la base de datos de edificios
     * 
     * @param nombre El nombre del nuevo edificio, objeto String.
     */
    private void añadirEdificio(String nombre) {
        int cantidadEdificios = GestionUniversidad.Edificios.size(), numeroEdificio = 0;
        if (cantidadEdificios == 0) GestionUniversidad.Edificios.add(nombre);
        else {
            int comparacion = compararDNI(nombre,GestionUniversidad.Edificios.get(numeroEdificio));
            while (numeroEdificio < cantidadEdificios && comparacion >= 0 ) {
                    if (comparacion == 0) {
                        JOptionPane.showMessageDialog(null, "El edificio "+nombre
                            +" ya existe", "Añadir Edificio", JOptionPane.OK_OPTION);
                        return;
                    }
                    numeroEdificio++;
                    if (numeroEdificio == cantidadEdificios) break;
                    comparacion = compararDNI(nombre,GestionUniversidad.Edificios.get(numeroEdificio));
                }
                GestionUniversidad.Edificios.add(numeroEdificio, nombre);
            }
        JOptionPane.showMessageDialog(null, "Edificio "+nombre
                +" añadido satisfactoriamente", "Edificio Añadido", JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * @see PanelAlumno#compararDNI(gestionuniversidad.Alumno, gestionuniversidad.Alumno) 
     */
    public int compararDNI(String edificio1, String edificio2) {
        return edificio1.compareTo(edificio2);
    }   

}

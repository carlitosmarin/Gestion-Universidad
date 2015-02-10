package gestionuniversidad.Paneles;

import gestionuniversidad.*;
import java.awt.CardLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Clase PanelAsignatura. Hereda de la clase padre JPanel para incluir mediante componentes
 * gráficos las funciones típicas de una base de datos: añadir, eliminar y buscar elementos.
 * En este caso se trata de asignaturas.
 * @see Asignatura
 * 
 * @auhtor: Carlos Marin Fernandez
 * @date: 10-feb-2015
 * @version: 1.0 
 */
public class PanelAsignatura  extends JPanel {
    
    private final JPanel Menu, Contenido;
    
    //Panel Menu
    private JButton MenuButtonCrear, MenuButtonAñadir, MenuButtonEliminar, MenuButtonBuscar;
    
    //Panel Contenido Crear Nueva Asignatura
    private JPanel NuevaAsignaturaPanel;
    private JTextField NuevaAsignaturaTextFieldID, NuevaAsignaturaTextFieldNombre;
    private JComboBox NuevaAsignaturaCombo;
    private JButton NuevaAsignaturaButtonCrear;
    
    //Panel Contenido Añadir Asignatura
    private JPanel AñadirAsignaturaPanel;
    private JTextField AñadirAsignaturaTextFieldDNIProfesor;
    private JComboBox AñadirAsignaturaCurso, AñadirAsignaturaAsignatura;
    private JButton AñadirAsignaturaButton;
    
    //Panel Contenido Eliminar Asignatura
    private JPanel EliminarAsignaturaPanel;
    private JTextField EliminarAsignaturaTextFieldID, EliminarAsignaturaTextFieldNombre;
    private JButton EliminarAsignaturaButtonID, EliminarAsignaturaButtonNombre;
    
    //Panel Contenido Buscar Asignatura
    private JPanel BuscarAsignaturaPanel;
    private JTextField BuscarAsignaturaTextFieldID, BuscarAsignaturaTextFieldNombre;
    private JButton BuscarAsignaturaButtonID, BuscarAsignaturaButtonNombre;
    private JTextArea AsignaturaInformacionGeneral, AsignaturaInformacionAlumnos;
    private JScrollPane BuscarAsignaturaScroll;
    
    /**
     * Constuctor del panel Curso
     */
    public PanelAsignatura() {
        //Declaracion de los paneles que se mostraran en toda la aplicacion
        Menu = new JPanel();
        Contenido = new JPanel();
        Contenido.setLayout(new CardLayout());
        
        initPanelMenu();
        initPanelNuevaAsignatura();
        initPanelAñadirAsignatura();
        initPanelEliminarAsignatura();
        initPanelBuscarAsignatura();
        
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
        ImageIcon Crear = new ImageIcon("./images/CrearAsignatura.png");
        ImageIcon Eliminar = new ImageIcon("./images/EliminarAsignatura.png");
        ImageIcon Buscar = new ImageIcon("./images/Buscar.png");
        ImageIcon Añadir = new ImageIcon("./images/Añadir.png");
        ImageIcon Universidad = new ImageIcon("./images/Universidad.png");

        //Botones Menu
        MenuButtonCrear = new JButton("  Crear Asignatura   ", Crear);
        MenuButtonAñadir = new JButton("   Añadir a Cursos   ", Añadir);
        MenuButtonEliminar = new JButton("Eliminar Asignatura", Eliminar);
        MenuButtonBuscar = new JButton(" Buscar Asignatura ", Buscar);

        //Menu Acciones
        Menu.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
        Menu.add(MenuButtonCrear);
        Menu.add(MenuButtonAñadir);
        Menu.add(MenuButtonEliminar);
        Menu.add(MenuButtonBuscar);
        Menu.add(new JLabel(Universidad));
        
        //Para cada boton del Menu, inicializamos sus componentes, al hacer click
        MenuButtonCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cargarProfesores();
                NuevaAsignaturaTextFieldID.setText("");
                NuevaAsignaturaTextFieldNombre.setText("");
                CardLayout cl = (CardLayout) (Contenido.getLayout());
                cl.show(Contenido, "Panel CrearAsignatura");
            }
        });
        
        MenuButtonAñadir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cargarCursos();
                cargarAsignaturas();
                AñadirAsignaturaTextFieldDNIProfesor.setText("");
                CardLayout cl = (CardLayout) (Contenido.getLayout());
                cl.show(Contenido, "Panel AñadirAsignatura");
            }
        });
        
        MenuButtonEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                EliminarAsignaturaTextFieldID.setText("");
                EliminarAsignaturaTextFieldNombre.setText("");
                CardLayout cl = (CardLayout) (Contenido.getLayout());
                cl.show(Contenido, "Panel EliminarAsignatura");
            }
        });
        
        MenuButtonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BuscarAsignaturaTextFieldID.setText("");
                BuscarAsignaturaTextFieldNombre.setText("");
                AsignaturaInformacionGeneral.setText("");
                AsignaturaInformacionGeneral.setVisible(false);
                AsignaturaInformacionAlumnos.setText("");
                BuscarAsignaturaScroll.setVisible(false);
                CardLayout cl = (CardLayout) (Contenido.getLayout());
                cl.show(Contenido, "Panel BuscarAsignatura");
            }
        });
    }
    
    private void initPanelNuevaAsignatura() {
        NuevaAsignaturaPanel = new JPanel();
        NuevaAsignaturaPanel.setLayout(new GridLayout(1,2));
        NuevaAsignaturaPanel.setBounds(0, 0, 200, 150);
        
        JPanel PanelIzquierdaEtiquetas = new JPanel(new GridLayout(13, 1));
        PanelIzquierdaEtiquetas.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        JPanel PanelDerechaEntradaDatos = new JPanel(new GridLayout(13, 1));

        //Inserción de Datos
        PanelIzquierdaEtiquetas.add(new JLabel("Inserir los datos de la asignatura"));
        PanelIzquierdaEtiquetas.add(new JLabel("· Código de la Asignatura: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        NuevaAsignaturaTextFieldID = new JTextField();
        PanelDerechaEntradaDatos.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(NuevaAsignaturaTextFieldID);
        PanelDerechaEntradaDatos.add(new JLabel(""));
        
        PanelIzquierdaEtiquetas.add(new JLabel("· Nombre de la Asignatura: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        NuevaAsignaturaTextFieldNombre = new JTextField();
        PanelDerechaEntradaDatos.add(NuevaAsignaturaTextFieldNombre);
        PanelDerechaEntradaDatos.add(new JLabel(""));

        //Cambiar el textfield por una lista de profesores
        PanelIzquierdaEtiquetas.add(new JLabel("· Profesor que la imparte: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        NuevaAsignaturaCombo = new JComboBox();
        PanelDerechaEntradaDatos.add(NuevaAsignaturaCombo);
        PanelDerechaEntradaDatos.add(new JLabel(""));

        NuevaAsignaturaButtonCrear = new JButton("Crear Asignatura");

        PanelDerechaEntradaDatos.add(NuevaAsignaturaButtonCrear);

        NuevaAsignaturaButtonCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Comprobacion de que se han rellenado todos los campos
                    if ((NuevaAsignaturaTextFieldID.getText().equals("")) ||
                            (NuevaAsignaturaTextFieldNombre.getText().equals("")) ||
                            (NuevaAsignaturaCombo.getSelectedItem().equals(""))) {
                        JOptionPane.showMessageDialog(null, "ERROR: Has dejado "
                                + "algun campo sin rellenar", "Crear Asignatura", JOptionPane.CANCEL_OPTION);
                    } else crearAsignatura();
                } catch (Exception error) {
                }
            }
        });
        
        NuevaAsignaturaPanel.add(PanelIzquierdaEtiquetas);
        NuevaAsignaturaPanel.add(PanelDerechaEntradaDatos);
        
        Contenido.add(NuevaAsignaturaPanel, "Panel CrearAsignatura");
    }
    
    /**
     * Ídem para todos los Cargar[...]
     * @see PanelAlumno#cargarCursos() 
     */
    private void cargarProfesores() {
        NuevaAsignaturaCombo.removeAllItems();
        int totalProfesores = GestionUniversidad.Profesores.size(), numeroProfesor = 0;
        while(numeroProfesor < totalProfesores) {
            if(GestionUniversidad.Profesores.get(numeroProfesor).getDNI() > 0) {
                NuevaAsignaturaCombo.addItem(GestionUniversidad.Profesores.get(numeroProfesor));
            }
            numeroProfesor++;
        }
    }
    
    /**
     * De manera ordenada, inserimos las asignaturas a la base de datos de Asignaturas.
     */
    private void crearAsignatura() {
        try {
            //Creamos la nueva Asigatura con los datos inseridos
            int IDNuevaAsignatura = Integer.parseInt(NuevaAsignaturaTextFieldID.getText());
            String NombreNuevaAsignatura = NuevaAsignaturaTextFieldNombre.getText();
            Profesor profesorElegido = (Profesor) NuevaAsignaturaCombo.getSelectedItem();
            int DNIProfesorNuevaAsignatura = profesorElegido.getDNI();
            Asignatura nuevaAsignatura = new Asignatura(IDNuevaAsignatura,
                    NombreNuevaAsignatura,DNIProfesorNuevaAsignatura);
            int cantidadAsignaturas = GestionUniversidad.Asignaturas.size(), numeroAsignatura = 0;
            if (cantidadAsignaturas == 0) GestionUniversidad.Asignaturas.add(nuevaAsignatura);
            else {
                int comparacion = compararID(nuevaAsignatura,GestionUniversidad.Asignaturas.get(numeroAsignatura));
                while (numeroAsignatura < cantidadAsignaturas && comparacion >= 0 ) {
                    if (comparacion == 0) {
                        JOptionPane.showMessageDialog(null, "Asignatura "+nuevaAsignatura.getIdentificador()
                            +" ya existe", "Crear Asignatura", JOptionPane.OK_OPTION);
                        NuevaAsignaturaTextFieldID.setText("");
                        return;
                    }
                    numeroAsignatura++;
                    if (numeroAsignatura == cantidadAsignaturas) break;
                    comparacion = compararID(nuevaAsignatura,GestionUniversidad.Asignaturas.get(numeroAsignatura));
                }
                GestionUniversidad.Asignaturas.add(numeroAsignatura, nuevaAsignatura);
            }
            
            JOptionPane.showMessageDialog(null, "Asignatura de "+NuevaAsignaturaTextFieldNombre.getText()
                +" creada satisfactoriamente", "Crear Asignatura", JOptionPane.WARNING_MESSAGE);
            
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "ERROR: Has rellenado algún campo erróneamente:"
                    + "\n · Para el código use carácteres numéricos."
                    + "\n · Para el nombre use carácteres alfabéticos.",
                    "Crear Asignatura", JOptionPane.CANCEL_OPTION);
        }    
        NuevaAsignaturaTextFieldID.setText("");
        NuevaAsignaturaTextFieldNombre.setText("");
    }
    
    /**
     * @see Alumno#compararID(gestionuniversidad.Asignatura, gestionuniversidad.Asignatura) 
     */
    private int compararID(Asignatura curso1, Asignatura curso2) {
        return String.valueOf(curso1.getIdentificador()).compareTo(String.valueOf(curso2.getIdentificador()));
    }
    
    private void initPanelAñadirAsignatura() {
        AñadirAsignaturaPanel = new JPanel();
        AñadirAsignaturaPanel.setLayout(new GridLayout(1,2));
        AñadirAsignaturaPanel.setBounds(0, 0, 200, 150);
        
        JPanel PanelIzquierdaEtiquetas = new JPanel(new GridLayout(13, 1));
        PanelIzquierdaEtiquetas.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        JPanel PanelDerechaEntradaDatos = new JPanel(new GridLayout(13, 1));

        //Inserción de Datos
        PanelIzquierdaEtiquetas.add(new JLabel("Añadir asignaturas a un curso:"));
        PanelIzquierdaEtiquetas.add(new JLabel(" · Curso a añadir: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(new JLabel(""));
        AñadirAsignaturaCurso = new JComboBox();
        PanelDerechaEntradaDatos.add(AñadirAsignaturaCurso);
        
        PanelIzquierdaEtiquetas.add(new JLabel(" · Profesor que la impartirá: "));
        PanelIzquierdaEtiquetas.add(new JLabel(" * Para añadir una asignatura, debe ser"
                + " quien"));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(new JLabel(""));
        AñadirAsignaturaTextFieldDNIProfesor = new JTextField();
        PanelDerechaEntradaDatos.add(AñadirAsignaturaTextFieldDNIProfesor);
        PanelDerechaEntradaDatos.add(new JLabel("la imparta."));
        
        PanelIzquierdaEtiquetas.add(new JLabel(" · Asignatura a añadir: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(new JLabel(""));
        AñadirAsignaturaAsignatura = new JComboBox();
        PanelDerechaEntradaDatos.add(AñadirAsignaturaAsignatura);
        PanelDerechaEntradaDatos.add(new JLabel(""));
        
        AñadirAsignaturaButton = new JButton("Añadir asignatura");

        PanelDerechaEntradaDatos.add(AñadirAsignaturaButton);

        AñadirAsignaturaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Comprobacion de que se han rellenado todos los campos
                    if ((AñadirAsignaturaCurso.getSelectedItem().equals("")) ||
                            (AñadirAsignaturaTextFieldDNIProfesor.getText().equals("")) ||
                            (AñadirAsignaturaAsignatura.getSelectedItem().equals(""))) {
                        JOptionPane.showMessageDialog(null, "ERROR: Has dejado "
                                + "algun campo sin rellenar", "Añadir Asignatura", JOptionPane.CANCEL_OPTION);
                    } else añadirAsignatura();
                } catch (Exception error) {
                }
            }
        });
        
        AñadirAsignaturaPanel.add(PanelIzquierdaEtiquetas);
        AñadirAsignaturaPanel.add(PanelDerechaEntradaDatos);
        
        Contenido.add(AñadirAsignaturaPanel, "Panel AñadirAsignatura");
    }
    
    private void cargarCursos() {
        AñadirAsignaturaCurso.removeAllItems();
        int totalCursos = GestionUniversidad.Cursos.size(), numeroCurso = 0;
        while(numeroCurso < totalCursos) {
            AñadirAsignaturaCurso.addItem(GestionUniversidad.Cursos.get(numeroCurso));
            numeroCurso++;
        }
    }
    
    private void cargarAsignaturas() {
        AñadirAsignaturaAsignatura.removeAllItems();
        int totalAsignaturas = GestionUniversidad.Asignaturas.size(), numeroAsignatura = 0;
        while(numeroAsignatura < totalAsignaturas) {
            if (GestionUniversidad.Asignaturas.get(numeroAsignatura).getDNIprofesor() > 0)
            AñadirAsignaturaAsignatura.addItem(GestionUniversidad.Asignaturas.get(numeroAsignatura));
            numeroAsignatura++;
        }
    }
    
    /**
     * Para añadir una asignatura dada a un curso que indica el usuario en la GUI.
     */
    private void añadirAsignatura() {
        try {
            Asignatura asignaturaPretenciosa = (Asignatura) AñadirAsignaturaAsignatura.getSelectedItem();
            int dniProfesor = Integer.parseInt(AñadirAsignaturaTextFieldDNIProfesor.getText());
            if (asignaturaPretenciosa.getDNIprofesor() == dniProfesor) {
                Curso cursoPretencioso = (Curso) AñadirAsignaturaCurso.getSelectedItem();
                int numeroCurso = GestionUniversidad.Cursos.indexOf(cursoPretencioso);
                boolean existeAsignaturaEnCurso = GestionUniversidad.Cursos.get(numeroCurso).asignaturasImpartidas.contains(asignaturaPretenciosa);
                if (existeAsignaturaEnCurso == true){
                    JOptionPane.showMessageDialog(null, "La asignatura "+asignaturaPretenciosa.getIdentificador()
                            +" ya existe", "Añadir Asignatura", JOptionPane.OK_OPTION);
                    return;
                }
                GestionUniversidad.Cursos.get(numeroCurso).addAsignatura(asignaturaPretenciosa);
                JOptionPane.showMessageDialog(null, "Asignatura de "+asignaturaPretenciosa.getNombre()
                    +" añadida satisfactoriamente", "Añadir Asignatura", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: Para poder añadir una "
                        + "asignatura a un curso debes ser, el profesor que la "
                        + "imparta.", "Añadir Asignatura", JOptionPane.CANCEL_OPTION);
                AñadirAsignaturaTextFieldDNIProfesor.setText("");
            }
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "ERROR: Has rellenado algún campo erróneamente:"
                    + "\n · Para el código use carácteres numéricos."
                    + "\n · Para el nombre use carácteres alfabéticos.",
                    "Añadir Asignatura", JOptionPane.CANCEL_OPTION);
        }
    }
    
    private void initPanelEliminarAsignatura() {
        EliminarAsignaturaPanel = new JPanel();
        EliminarAsignaturaPanel.setLayout(new GridLayout(1,2));
        EliminarAsignaturaPanel.setBounds(0, 0, 200, 150);
        
        JPanel PanelIzquierdaEtiquetas = new JPanel(new GridLayout(13, 1));
        PanelIzquierdaEtiquetas.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        JPanel PanelDerechaEntradaDatos = new JPanel(new GridLayout(13, 1));
        
        //Imagen de busqueda
        ImageIcon logoEliminar = new ImageIcon("./images/EliminarContenido.png");

        //Inserción de Datos
        PanelIzquierdaEtiquetas.add(new JLabel("Eliminar por código de la asignatura"));
        PanelIzquierdaEtiquetas.add(new JLabel("· Código de la asignatura: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(new JLabel(""));
        EliminarAsignaturaTextFieldID = new JTextField();
        PanelDerechaEntradaDatos.add(EliminarAsignaturaTextFieldID);
        EliminarAsignaturaButtonID = new JButton("Eliminar por código", logoEliminar);
        PanelDerechaEntradaDatos.add(EliminarAsignaturaButtonID);
        PanelDerechaEntradaDatos.add(new JLabel(""));
        
        PanelIzquierdaEtiquetas.add(new JLabel("Eliminar por nombre de la asignatura"));
        PanelIzquierdaEtiquetas.add(new JLabel("· Nombre de la asignatura: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(new JLabel(""));
        EliminarAsignaturaTextFieldNombre = new JTextField();
        PanelDerechaEntradaDatos.add(EliminarAsignaturaTextFieldNombre);
        EliminarAsignaturaButtonNombre = new JButton("Eliminar por nombre", logoEliminar);
        PanelDerechaEntradaDatos.add(EliminarAsignaturaButtonNombre);
        PanelDerechaEntradaDatos.add(new JLabel(""));

        EliminarAsignaturaButtonID.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Comprobacion de que se han rellenado todos los campos
                    if (EliminarAsignaturaTextFieldID.getText().equals("")) 
                        JOptionPane.showMessageDialog(null, "ERROR: Has dejado "
                                + "algun campo sin rellenar", "Eliminar Asignatura", JOptionPane.CANCEL_OPTION);
                     else eliminarAsignatura(0);
                } catch (Exception error) {}
            }
        });
        
        EliminarAsignaturaButtonNombre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Comprobacion de que se han rellenado todos los campos
                    if (EliminarAsignaturaTextFieldNombre.getText().equals("")) 
                        JOptionPane.showMessageDialog(null, "ERROR: Has dejado "
                                + "algun campo sin rellenar", "Eliminar Asignatura", JOptionPane.CANCEL_OPTION);
                     else eliminarAsignatura(1);
                } catch (Exception error) {
                }
            }
        });
        
        EliminarAsignaturaPanel.add(PanelIzquierdaEtiquetas);
        EliminarAsignaturaPanel.add(PanelDerechaEntradaDatos);
        
        Contenido.add(EliminarAsignaturaPanel, "Panel EliminarAsignatura");    
    }
    
    private void eliminarAsignatura(int tipoEliminar) {
        //tipoEliminar = 0 si es por ID, o = 1 si es por Nombre
        int cantidadAsignaturas = GestionUniversidad.Asignaturas.size(), numeroAsignatura = 0;
        if (tipoEliminar == 0) {
            int IDEliminar = Integer.parseInt(EliminarAsignaturaTextFieldID.getText());
            while (numeroAsignatura < cantidadAsignaturas) {
                if(IDEliminar == GestionUniversidad.Asignaturas.get(numeroAsignatura).getIdentificador()) {
                    eliminarEnCurso(GestionUniversidad.Asignaturas.get(numeroAsignatura));
                    GestionUniversidad.Asignaturas.remove(numeroAsignatura);
                    JOptionPane.showMessageDialog(null, "Asignatura "+EliminarAsignaturaTextFieldID.getText()
                    +" eliminado satisfactoriamente", "Eliminar Asignatura por ID", JOptionPane.WARNING_MESSAGE);
                    EliminarAsignaturaTextFieldID.setText("");
                    return;
                }
                numeroAsignatura++;
            }
        } else {
            String NombreEliminar = EliminarAsignaturaTextFieldNombre.getText();
            while (numeroAsignatura < cantidadAsignaturas) {
                if(NombreEliminar.equals(GestionUniversidad.Asignaturas.get(numeroAsignatura).getNombre())) {
                    eliminarEnCurso(GestionUniversidad.Asignaturas.get(numeroAsignatura));
                    GestionUniversidad.Asignaturas.remove(numeroAsignatura);
                    JOptionPane.showMessageDialog(null, "Asignatura de "+EliminarAsignaturaTextFieldNombre.getText()
                    +" eliminado satisfactoriamente", "Eliminar Asignatura por Nombre", JOptionPane.WARNING_MESSAGE);
                    EliminarAsignaturaTextFieldNombre.setText("");
                    return;
                }
                numeroAsignatura++;
            }
        }
        if (cantidadAsignaturas == numeroAsignatura) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado la asignatura que ha indicado."
                    , "Eliminar Asignatura ", JOptionPane.OK_OPTION);
                    EliminarAsignaturaTextFieldNombre.setText("");
                    EliminarAsignaturaTextFieldID.setText("");
        }
    }
    
    private void eliminarEnCurso(Asignatura asignaturaDelete) {
        int cantidadCursos = GestionUniversidad.Cursos.size(), numeroCurso = 0;
        while (numeroCurso < cantidadCursos) {
            GestionUniversidad.Cursos.get(numeroCurso).eliminarAsignatura(asignaturaDelete);
            numeroCurso++;
        }
    }
    
    private void initPanelBuscarAsignatura() {
        BuscarAsignaturaPanel = new JPanel();
        BuscarAsignaturaPanel.setLayout(new GridLayout(2,2));
        BuscarAsignaturaPanel.setBounds(0, 0, 200, 150);
        
        JPanel PanelIzquierdaEtiquetas = new JPanel(new GridLayout(8, 1));
        PanelIzquierdaEtiquetas.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        JPanel PanelIzquierdaCurso = new JPanel(new GridLayout(2, 1));
        PanelIzquierdaCurso.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        JPanel PanelDerechaEntradaDatos = new JPanel(new GridLayout(8, 1));
        JPanel PanelDerechaAsignaturas = new JPanel(new GridLayout(2, 1));
        
        //Imagen de busqueda
        ImageIcon logoBuscar = new ImageIcon("./images/BuscarContenido.png");

        //Inserción de Datos
        PanelIzquierdaEtiquetas.add(new JLabel("Búsqueda por código de la asignatura"));
        PanelIzquierdaEtiquetas.add(new JLabel("· Código de la asignatura: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(new JLabel(""));
        BuscarAsignaturaTextFieldID = new JTextField();
        PanelDerechaEntradaDatos.add(BuscarAsignaturaTextFieldID);
        BuscarAsignaturaButtonID = new JButton("Buscar por código", logoBuscar);
        PanelDerechaEntradaDatos.add(BuscarAsignaturaButtonID);
        PanelDerechaEntradaDatos.add(new JLabel(""));
        
        PanelIzquierdaCurso.add(new JLabel("Información de la asignatura:"));
        AsignaturaInformacionGeneral = new JTextArea();
        AsignaturaInformacionGeneral.setBounds(20,20,20,20);
        AsignaturaInformacionGeneral.setVisible(false);
        PanelIzquierdaCurso.add(AsignaturaInformacionGeneral);
        
        PanelIzquierdaEtiquetas.add(new JLabel("Búsqueda por nombre de la asignatura"));
        PanelIzquierdaEtiquetas.add(new JLabel("· Nombre de la asignatura: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(new JLabel(""));
        BuscarAsignaturaTextFieldNombre = new JTextField();
        PanelDerechaEntradaDatos.add(BuscarAsignaturaTextFieldNombre);
        BuscarAsignaturaButtonNombre = new JButton("Buscar por nombre", logoBuscar);
        PanelDerechaEntradaDatos.add(BuscarAsignaturaButtonNombre);
        PanelDerechaEntradaDatos.add(new JLabel(""));
        
        PanelDerechaAsignaturas.add(new JLabel("Lista de alumnos:"));
        AsignaturaInformacionAlumnos = new JTextArea();
        AsignaturaInformacionAlumnos.setEditable(false);
        BuscarAsignaturaScroll = new JScrollPane(AsignaturaInformacionAlumnos);
        BuscarAsignaturaScroll.setBounds(20, 20, 400, 200);
        BuscarAsignaturaScroll.setVisible(false);
        
        PanelDerechaAsignaturas.add(BuscarAsignaturaScroll);

        BuscarAsignaturaButtonID.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Comprobacion de que se han rellenado todos los campos
                    if (BuscarAsignaturaTextFieldID.getText().equals("")) 
                        JOptionPane.showMessageDialog(null, "ERROR: Has dejado "
                                + "algun campo sin rellenar", "Buscar Asignatura", JOptionPane.CANCEL_OPTION);
                     else buscarAsignatura(0);
                } catch (Exception error) {}
            }
        });
        
        BuscarAsignaturaButtonNombre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Comprobacion de que se han rellenado todos los campos
                    if (BuscarAsignaturaTextFieldNombre.getText().equals("")) 
                        JOptionPane.showMessageDialog(null, "ERROR: Has dejado "
                                + "algún campo sin rellenar", "Buscar Asignatura", JOptionPane.CANCEL_OPTION);
                     else buscarAsignatura(1);
                } catch (Exception error) {
                }
            }
        });
        
        BuscarAsignaturaPanel.add(PanelIzquierdaEtiquetas);
        BuscarAsignaturaPanel.add(PanelDerechaEntradaDatos);
        BuscarAsignaturaPanel.add(PanelIzquierdaCurso);
        BuscarAsignaturaPanel.add(PanelDerechaAsignaturas);

        Contenido.add(BuscarAsignaturaPanel, "Panel BuscarAsignatura");
        
    }   
    
    private void buscarAsignatura(int tipoBuscar) {
        //tipoEliminar = 0 si es por ID, o = 1 si es por Nombre
        boolean existeAsignatura = false;
        String texto = "";
        int cantidadAsignaturas = GestionUniversidad.Asignaturas.size(), numeroAsignatura = 0;
        if (tipoBuscar == 0) {
            int IDEliminar = Integer.parseInt(BuscarAsignaturaTextFieldID.getText());
            while (numeroAsignatura < cantidadAsignaturas) {
                if(IDEliminar == GestionUniversidad.Asignaturas.get(numeroAsignatura).getIdentificador()) {
                    BuscarAsignaturaTextFieldID.setText("");
                    existeAsignatura = true;
                    texto += GestionUniversidad.Asignaturas.get(numeroAsignatura).mostrarAlumnos();
                    texto += "-----------------\n";
                    break;
                }
                numeroAsignatura++;
            }
        } else {
            String NombreEliminar = BuscarAsignaturaTextFieldNombre.getText();
            while (numeroAsignatura < cantidadAsignaturas) {
                if(NombreEliminar.equals(GestionUniversidad.Asignaturas.get(numeroAsignatura).getNombre())) {
                    BuscarAsignaturaTextFieldNombre.setText("");
                    existeAsignatura = true;
                    texto += GestionUniversidad.Asignaturas.get(numeroAsignatura).mostrarAlumnos();
                    texto += "-----------------\n";
                    break;
                }
                numeroAsignatura++;
            }
        }
        if (existeAsignatura == true) {
            AsignaturaInformacionGeneral.setText(GestionUniversidad.Asignaturas.get(numeroAsignatura).toString());
            AsignaturaInformacionGeneral.setVisible(true);
            AsignaturaInformacionGeneral.setEditable(false);
            AsignaturaInformacionAlumnos.setText(texto);
            AsignaturaInformacionAlumnos.setCaretPosition(0); 
            BuscarAsignaturaScroll.setVisible(true);
            BuscarAsignaturaPanel.repaint();
        } else {
            JOptionPane.showMessageDialog(null, "No se ha encontrado la asignatura que ha indicado."
                    , "Buscar Asignatura", JOptionPane.OK_OPTION);
                    BuscarAsignaturaTextFieldNombre.setText("");
                    BuscarAsignaturaTextFieldID.setText("");
                    AsignaturaInformacionGeneral.setVisible(false);
                    BuscarAsignaturaScroll.setVisible(false);
        }
    }
}

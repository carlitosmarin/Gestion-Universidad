package gestionuniversidad.Paneles;

import gestionuniversidad.Asignatura;
import gestionuniversidad.Curso;
import gestionuniversidad.GestionUniversidad;
import gestionuniversidad.Profesor;
import java.awt.CardLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Clase PanelProfesor. Hereda de la clase padre JPanel para incluir mediante componentes
 * gráficos las funciones típicas de una base de datos: añadir, eliminar y buscar elementos.
 * En este caso se trata de profesor.
 * @see Profesor
 * 
 * @auhtor: Carlos Marin Fernandez
 * @date: 10-feb-2015
 * @version: 1.0 
 */
public class PanelProfesor extends JPanel {

    private final JPanel Menu, Contenido;
    
    //Panel Menu
    private JButton MenuButtonCrear, MenuButtonEliminar, MenuButtonBuscar, 
            MenuButtonCambiar;
    
    //Panel Contenido Añadir Nuevo Profesor
    private JPanel NuevoProfesorPanel;
    private JTextField NuevoProfesorTextFieldDNI, NuevoProfesorTextFieldNombre, 
            NuevoProfesorTextFieldDNIAsignatura;
    private JComboBox NuevoProfesorCursos, NuevoProfesorAsignaturas;
    private JButton NuevoProfesorButtonCrear, NuevoProfesorButtonAsignatura;
    
    //Panel Contenido Eliminar Profesor
    private JPanel EliminarProfesorPanel;
    private JTextField EliminarProfesorTextFieldDNI;
    private JButton EliminarProfesorButtonDNI;
    
    //Panel Contenido Buscar Profesor
    private JPanel BuscarProfesorPanel;
    private JTextField BuscarProfesorTextFieldDNI;
    private JButton BuscarProfesorButtonDNI;
    private JTextArea ProfesorInformacionGeneral, ProfesorInformacionAsignatura;
    private JScrollPane BuscarProfesorScroll;
    
    //Panel Contenido Añadir Nuevo Profesor
    private JPanel CambiarProfesorPanel;
    private JTextField CambiarProfesorTextFieldDNIAntiguo, CambiarProfesorTextFieldDNINuevo;
    private JComboBox CambiarProfesorCursos, CambiarProfesorAsignaturas;
    private JButton CambiarProfesorButton;
    
    /**
     * Constuctor del panel Profesor
     */
    public PanelProfesor() {
        //Declaracion de los paneles que se mostraran en toda la aplicacion
        Menu = new JPanel();
        Contenido = new JPanel();
        Contenido.setLayout(new CardLayout());
        
        initPanelMenu();
        initPanelNuevoProfesor();
        initPanelEliminarProfesor();
        initPanelBuscarProfesor();
        initPanelCambiarProfesor();
        
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
        ImageIcon Crear = new ImageIcon("./images/CrearPersona.png");
        ImageIcon Eliminar = new ImageIcon("./images/EliminarPersona.png");
        ImageIcon Buscar = new ImageIcon("./images/BuscarPersona.png");
        ImageIcon Cambiar = new ImageIcon("./images/CambiarPersona.png");
        ImageIcon Universidad = new ImageIcon("./images/Universidad.png");

        //Botones Menu
        MenuButtonCrear = new JButton("     Añadir Profesor    ", Crear);
        MenuButtonEliminar = new JButton("   Eliminar Profesor    ", Eliminar);
        MenuButtonBuscar = new JButton("    Buscar Profesor    ", Buscar);
        MenuButtonCambiar = new JButton(" Cambiar Profesores ", Cambiar);

        //Menu Acciones
        Menu.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
        Menu.add(MenuButtonCrear);
        Menu.add(MenuButtonEliminar);
        Menu.add(MenuButtonBuscar);
        Menu.add(MenuButtonCambiar);
        Menu.add(new JLabel(Universidad));
        
        //Para cada boton del Menu, inicializamos sus componentes, al hacer click
        MenuButtonCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                NuevoProfesorTextFieldDNI.setText("");
                NuevoProfesorTextFieldNombre.setText("");
                NuevoProfesorTextFieldDNIAsignatura.setText("");
                cargarCursos(NuevoProfesorCursos);
                cargarAsignaturas(NuevoProfesorAsignaturas);
                CardLayout cl = (CardLayout) (Contenido.getLayout());
                cl.show(Contenido, "Panel CrearProfesor");
            }
        });
        
        MenuButtonEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                EliminarProfesorTextFieldDNI.setText("");
                CardLayout cl = (CardLayout) (Contenido.getLayout());
                cl.show(Contenido, "Panel EliminarProfesor");
            }
        });
        
        MenuButtonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                EliminarProfesorTextFieldDNI.setText("");
                ProfesorInformacionGeneral.setText("");
                ProfesorInformacionGeneral.setVisible(false);
                ProfesorInformacionAsignatura.setText("");
                BuscarProfesorScroll.setVisible(false);
                CardLayout cl = (CardLayout) (Contenido.getLayout());
                cl.show(Contenido, "Panel BuscarProfesor");
            }
        });
        
        MenuButtonCambiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                CambiarProfesorTextFieldDNIAntiguo.setText("");
                CambiarProfesorTextFieldDNINuevo.setText("");
                cargarCursos(CambiarProfesorCursos);
                cargarAsignaturas(CambiarProfesorAsignaturas);
                CardLayout cl = (CardLayout) (Contenido.getLayout());
                cl.show(Contenido, "Panel CambiarProfesor");
            }
        });
    }
    
    private void initPanelNuevoProfesor() {
        NuevoProfesorPanel = new JPanel();
        NuevoProfesorPanel.setLayout(new GridLayout(1,2));
        NuevoProfesorPanel.setBounds(0, 0, 200, 150);
        
        JPanel PanelIzquierdaEtiquetas = new JPanel(new GridLayout(15, 1));
        PanelIzquierdaEtiquetas.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        JPanel PanelDerechaEntradaDatos = new JPanel(new GridLayout(15, 1));

        //Inserción de Datos
        PanelIzquierdaEtiquetas.add(new JLabel("Inserir los datos del profesor"));
        PanelIzquierdaEtiquetas.add(new JLabel("· DNI del profesor: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        NuevoProfesorTextFieldDNI = new JTextField();
        PanelDerechaEntradaDatos.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(NuevoProfesorTextFieldDNI);
        PanelDerechaEntradaDatos.add(new JLabel(""));
        
        PanelIzquierdaEtiquetas.add(new JLabel("· Nombre del profesor: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        NuevoProfesorTextFieldNombre = new JTextField();
        PanelDerechaEntradaDatos.add(NuevoProfesorTextFieldNombre);
        PanelDerechaEntradaDatos.add(new JLabel(""));

        NuevoProfesorButtonCrear = new JButton("Crear Profesor");
        PanelDerechaEntradaDatos.add(NuevoProfesorButtonCrear);
        
        PanelIzquierdaEtiquetas.add(new JLabel("Inserir los datos del curso y asignatura:"));
        PanelIzquierdaEtiquetas.add(new JLabel("· Identificador del Curso: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        NuevoProfesorCursos = new JComboBox();
        PanelDerechaEntradaDatos.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(NuevoProfesorCursos);
        PanelDerechaEntradaDatos.add(new JLabel(""));
        
        PanelIzquierdaEtiquetas.add(new JLabel("· Asignatura que impartirá: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        NuevoProfesorAsignaturas = new JComboBox();
        PanelDerechaEntradaDatos.add(NuevoProfesorAsignaturas);
        PanelDerechaEntradaDatos.add(new JLabel(""));
        
        PanelIzquierdaEtiquetas.add(new JLabel("· DNI Profesor que impartirá: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        NuevoProfesorTextFieldDNIAsignatura = new JTextField();
        PanelDerechaEntradaDatos.add(NuevoProfesorTextFieldDNIAsignatura);
        PanelDerechaEntradaDatos.add(new JLabel(""));
        
        NuevoProfesorButtonAsignatura = new JButton("Añadir profesor a asignatura");
        PanelDerechaEntradaDatos.add(NuevoProfesorButtonAsignatura);        

        NuevoProfesorButtonCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Comprobacion de que se han rellenado todos los campos
                    if ((NuevoProfesorTextFieldDNI.getText().equals("")) ||
                            (NuevoProfesorTextFieldNombre.getText().equals(""))) {
                        JOptionPane.showMessageDialog(null, "ERROR: Has dejado "
                                + "algun campo sin rellenar", "Crear Profesor", JOptionPane.CANCEL_OPTION);
                    } else crearProfesor();
                } catch (Exception error) {
                }
            }
        });
        
        NuevoProfesorButtonAsignatura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Comprobacion de que se han rellenado todos los campos
                    if ((NuevoProfesorTextFieldDNIAsignatura.getText().equals("")) ||
                            (NuevoProfesorCursos.getSelectedItem().equals("")) ||
                            (NuevoProfesorAsignaturas.getSelectedItem().equals("")) ||
                            (NuevoProfesorAsignaturas.getSelectedItem().equals("No existen asignaturas disponibles"))) {
                        JOptionPane.showMessageDialog(null, "ERROR: Has dejado "
                                + "algun campo sin rellenar o no exsiten asignaturas sin pofesores"
                                , "Añadir Profesor a Asignatura", JOptionPane.CANCEL_OPTION);
                    } else crearProfesorAsignatura();
                } catch (Exception error) {
                }
            }
        });
        
        NuevoProfesorPanel.add(PanelIzquierdaEtiquetas);
        NuevoProfesorPanel.add(PanelDerechaEntradaDatos);
        
        Contenido.add(NuevoProfesorPanel, "Panel CrearProfesor");
    }
    
    /**
     * Insertar de manera ordenada un nuevo profesor a su base de datos.
     */
    private void crearProfesor() {
        try {
            //Creamos el nuevo profesor con los datos inseridos
            int DNIProfesor = Integer.parseInt(NuevoProfesorTextFieldDNI.getText());
            
            //Por el uso de la base de datos no puede haber un DNI como nuestro centinela
            if (DNIProfesor < 1) {
                  JOptionPane.showMessageDialog(null, "No puede existir un profesor con "
                          + " el DNI = 0. Inserte otro", "Crear Profesor", JOptionPane.OK_OPTION);
                        NuevoProfesorTextFieldDNI.setText("");
                        return;
            }
            String NombreNuevoProfesor = NuevoProfesorTextFieldNombre.getText();
            Profesor nuevoProfesor = new Profesor(DNIProfesor,NombreNuevoProfesor);
            int cantidadProfesores = GestionUniversidad.Profesores.size(), numeroProfesor = 0;
            if (cantidadProfesores == 0) GestionUniversidad.Profesores.add(nuevoProfesor);
            else {
                int comparacion = compararDNI(nuevoProfesor,GestionUniversidad.Profesores.get(numeroProfesor));
                while (numeroProfesor < cantidadProfesores && comparacion >= 0 ) {
                    if (comparacion == 0) {
                        JOptionPane.showMessageDialog(null, "Profesor "+nuevoProfesor.getNombre()
                            +" ya existe", "Crear Profesor", JOptionPane.OK_OPTION);
                        NuevoProfesorTextFieldDNI.setText("");
                        return;
                    }
                    numeroProfesor++;
                    if (numeroProfesor == cantidadProfesores) break;
                    comparacion = compararDNI(nuevoProfesor,GestionUniversidad.Profesores.get(numeroProfesor));
                }
                GestionUniversidad.Profesores.add(numeroProfesor, nuevoProfesor);
            }
            
            JOptionPane.showMessageDialog(null, "Profesor "+NuevoProfesorTextFieldNombre.getText()
                +" añadido satisfactoriamente", "Crear Profesor", JOptionPane.WARNING_MESSAGE);
            
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "ERROR: Has rellenado algún campo erróneamente:"
                    + "\n · Para el DNI use carácteres numéricos."
                    + "\n · Para el nombre use carácteres alfabéticos.",
                    "Crear Profesor", JOptionPane.CANCEL_OPTION);
        }    
        NuevoProfesorTextFieldDNI.setText("");
        NuevoProfesorTextFieldNombre.setText("");
    }
    
    /**
     * @see Profesor#compararDNI(gestionuniversidad.Asignatura, gestionuniversidad.Asignatura) 
     */
    private int compararDNI(Profesor profesor1, Profesor profesor2) {
        return String.valueOf(profesor1.getDNI()).compareTo(String.valueOf(profesor2.getDNI()));
    }
    
    /**
     * Para añadir un profesor concreto a la asignatura y curso que el usuario indica, con 
     * las restricciones de que no pueden ser el mismo, o que la asignatura exista.
     */
    private void crearProfesorAsignatura() {
        try {
            int DNIprofesor = Integer.parseInt(NuevoProfesorTextFieldDNIAsignatura.getText());
            Curso curso = (Curso) NuevoProfesorCursos.getSelectedItem();
            int posicionCurso = GestionUniversidad.Cursos.indexOf(curso);
            Asignatura asignatura = (Asignatura) NuevoProfesorAsignaturas.getSelectedItem();
            if (!GestionUniversidad.Cursos.get(posicionCurso).asignaturasImpartidas.contains(asignatura)){
                JOptionPane.showMessageDialog(null, "La asignatura "+asignatura.getNombre()
                            +" no existe en el curso" + curso.getNombre(),
                        "Añadir Profesor a Asignatura", JOptionPane.OK_OPTION);
                return;
            }
            int posicionAsignatura = GestionUniversidad.Cursos.get(posicionCurso).asignaturasImpartidas.indexOf(asignatura);
            GestionUniversidad.Cursos.get(posicionCurso).asignaturasImpartidas.get(posicionAsignatura).setDNIprofesor(DNIprofesor);
            
            String nombre = "";
            for (Profesor Profesor : GestionUniversidad.Profesores) {
                if (GestionUniversidad.Profesores.get(GestionUniversidad.Profesores.indexOf(Profesor)).getDNI() == DNIprofesor) {
                    GestionUniversidad.Profesores.get(GestionUniversidad.Profesores.indexOf(Profesor)).addAsignaturaToProfesor(asignatura);
                    nombre = GestionUniversidad.Profesores.get(GestionUniversidad.Profesores.indexOf(Profesor)).getNombre();
                }
            }
            
            JOptionPane.showMessageDialog(null, "Profesor "+nombre
                +" añadido satisfactoriamente a la asignatura " +asignatura.getNombre(),
                    "Añadir Profesor a Asignatura", JOptionPane.WARNING_MESSAGE);
            
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "ERROR: Has rellenado el campo erróneamente:"
                    + "\n · Para el DNI use carácteres numéricos.",
                    "Crear Profesor", JOptionPane.CANCEL_OPTION);
            System.out.println(e.getMessage());
        }    
    }
    
    private void initPanelEliminarProfesor() {
        EliminarProfesorPanel = new JPanel();
        EliminarProfesorPanel.setLayout(new GridLayout(1,2));
        EliminarProfesorPanel.setBounds(0, 0, 200, 150);
        
        JPanel PanelIzquierdaEtiquetas = new JPanel(new GridLayout(13, 1));
        PanelIzquierdaEtiquetas.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        JPanel PanelDerechaEntradaDatos = new JPanel(new GridLayout(13, 1));
        
        //Imagen de busqueda
        ImageIcon logoEliminar = new ImageIcon("./images/EliminarContenido.png");

        //Inserción de Datos
        PanelIzquierdaEtiquetas.add(new JLabel("Eliminar por DNI del profesor"));
        PanelIzquierdaEtiquetas.add(new JLabel("· DNI del profesor: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(new JLabel(""));
        EliminarProfesorTextFieldDNI = new JTextField();
        PanelDerechaEntradaDatos.add(EliminarProfesorTextFieldDNI);
        EliminarProfesorButtonDNI = new JButton("Eliminar por DNI", logoEliminar);
        PanelDerechaEntradaDatos.add(EliminarProfesorButtonDNI);
        
        EliminarProfesorButtonDNI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Comprobacion de que se han rellenado todos los campos
                    if (EliminarProfesorTextFieldDNI.getText().equals("")) 
                        JOptionPane.showMessageDialog(null, "ERROR: Has dejado "
                                + "el campo sin rellenar", "Eliminar Profesor", JOptionPane.CANCEL_OPTION);
                     else eliminarProfesor();
                } catch (Exception error) {}
            }
        });
        
        EliminarProfesorPanel.add(PanelIzquierdaEtiquetas);
        EliminarProfesorPanel.add(PanelDerechaEntradaDatos);
        
        Contenido.add(EliminarProfesorPanel, "Panel EliminarProfesor");    
    }
    
    //Cosas raras 
    private void eliminarProfesor() {
        try {
            int cantidadProfesores = GestionUniversidad.Profesores.size(), numeroProfesor = 0;
            int IDEliminar = Integer.parseInt(EliminarProfesorTextFieldDNI.getText());
            while (numeroProfesor < cantidadProfesores) {
                if(IDEliminar == GestionUniversidad.Profesores.get(numeroProfesor).getDNI()) {
                    JOptionPane.showMessageDialog(null, "Profesor "
                    + GestionUniversidad.Profesores.get(numeroProfesor).getNombre()
                    +" eliminado satisfactoriamente", "Eliminar Profesor", JOptionPane.WARNING_MESSAGE);
                    eliminarEnAsignaturas(GestionUniversidad.Profesores.get(numeroProfesor));
                    GestionUniversidad.Profesores.get(numeroProfesor).setDNI(0);
                    EliminarProfesorTextFieldDNI.setText("");
                    return;
                }
                numeroProfesor++;
            }
            if (cantidadProfesores == numeroProfesor) {
                JOptionPane.showMessageDialog(null, "No se ha encontrado el profesor que ha indicado."
                        , "Eliminar Profesor ", JOptionPane.OK_OPTION);
                        EliminarProfesorTextFieldDNI.setText("");
            }
        } catch (NumberFormatException | HeadlessException e) {
            System.out.println(e.getMessage());
        }
    }
     /**
      * Asegurarnos la eliminacion de las asignaturas que tienen el DNI del profesor a eliminar
      * y poner el DNI de estos profesores a 0.
      * 
      * @param profesorDelete El profesor que va a ser eliminado de la base de datos.
      */
    private void eliminarEnAsignaturas(Profesor profesorDelete) {
        int cantidadCursos = GestionUniversidad.Cursos.size(), numeroCurso = 0;
        int cantidadAsignaturas, numeroAsignatura = 0;
        while (numeroCurso < cantidadCursos) {
            cantidadAsignaturas = GestionUniversidad.Cursos.get(numeroCurso).asignaturasImpartidas.size();
            while (numeroAsignatura < cantidadAsignaturas) {
                int DNIprofesores = GestionUniversidad.Cursos.get(numeroCurso)
                        .asignaturasImpartidas.get(numeroAsignatura).getDNIprofesor();
                if (DNIprofesores == profesorDelete.getDNI()) {
                    GestionUniversidad.Cursos.get(numeroCurso).asignaturasImpartidas.get(numeroAsignatura).setDNIprofesor(0);
                }
                numeroAsignatura++;
            }
            numeroAsignatura = 0;
            numeroCurso++;
        }
    }
    
    private void initPanelBuscarProfesor() {
        BuscarProfesorPanel = new JPanel();
        BuscarProfesorPanel.setLayout(new GridLayout(2,2));
        BuscarProfesorPanel.setBounds(0, 0, 200, 150);
        
        JPanel PanelIzquierdaEtiquetas = new JPanel(new GridLayout(8, 1));
        PanelIzquierdaEtiquetas.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        JPanel PanelIzquierdaProfesor = new JPanel(new GridLayout(2, 1));
        PanelIzquierdaProfesor.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        JPanel PanelDerechaEntradaDatos = new JPanel(new GridLayout(8, 1));
        JPanel PanelDerechaAsignaturas = new JPanel(new GridLayout(2, 1));
        
        //Imagen de busqueda
        ImageIcon logoBuscar = new ImageIcon("./images/BuscarContenido.png");

        //Inserción de Datos
        PanelIzquierdaEtiquetas.add(new JLabel("Búsqueda por DNI del profesor"));
        PanelIzquierdaEtiquetas.add(new JLabel("· DNI del profesor: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(new JLabel(""));
        BuscarProfesorTextFieldDNI = new JTextField();
        PanelDerechaEntradaDatos.add(BuscarProfesorTextFieldDNI);
        BuscarProfesorButtonDNI = new JButton("Buscar por DNI", logoBuscar);
        PanelDerechaEntradaDatos.add(BuscarProfesorButtonDNI);
        PanelDerechaEntradaDatos.add(new JLabel(""));
        
        PanelIzquierdaProfesor.add(new JLabel("Información del profesor:"));
        ProfesorInformacionGeneral = new JTextArea();
        ProfesorInformacionGeneral.setBounds(20,20,20,20);
        ProfesorInformacionGeneral.setVisible(false);
        PanelIzquierdaProfesor.add(ProfesorInformacionGeneral);
        
        PanelDerechaAsignaturas.add(new JLabel("Lista de asignaturas que imparte:"));
        ProfesorInformacionAsignatura = new JTextArea();
        ProfesorInformacionAsignatura.setEditable(false);
        BuscarProfesorScroll = new JScrollPane(ProfesorInformacionAsignatura);
        BuscarProfesorScroll.setBounds(20, 20, 400, 200);
        BuscarProfesorScroll.setVisible(false);
        
        PanelDerechaAsignaturas.add(BuscarProfesorScroll);

        BuscarProfesorButtonDNI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Comprobacion de que se han rellenado todos los campos
                    if (BuscarProfesorTextFieldDNI.getText().equals("")) 
                        JOptionPane.showMessageDialog(null, "ERROR: Has dejado "
                                + "algun campo sin rellenar", "Buscar Profesor", JOptionPane.CANCEL_OPTION);
                     else buscarProfesor();
                } catch (Exception error) {}
            }
        });
        
        BuscarProfesorPanel.add(PanelIzquierdaEtiquetas);
        BuscarProfesorPanel.add(PanelDerechaEntradaDatos);
        BuscarProfesorPanel.add(PanelIzquierdaProfesor);
        BuscarProfesorPanel.add(PanelDerechaAsignaturas);

        Contenido.add(BuscarProfesorPanel, "Panel BuscarProfesor");
    }   
    
    /**
     * Dado un profesor en concreto, listamos todas las asignaturas que tiene en su 
     * ArrayList de "asignaturasImpartidas", con la ayuda de un método complementario.
     */
    private void buscarProfesor() {
        boolean existeProfesor = false;
        String texto = "";
        int cantidadProfesores = GestionUniversidad.Profesores.size(), numeroProfesor = 0;
        int IDBuscar = Integer.parseInt(BuscarProfesorTextFieldDNI.getText());
        while (numeroProfesor < cantidadProfesores) {
            if(IDBuscar == GestionUniversidad.Profesores.get(numeroProfesor).getDNI()) {
                BuscarProfesorTextFieldDNI.setText("");
                existeProfesor = true;
                texto += mostrarAsignaturasImpartidas(IDBuscar);
                break;
            }
            numeroProfesor++;
        }
        if (existeProfesor == true) {
            ProfesorInformacionGeneral.setText(GestionUniversidad.Profesores.get(numeroProfesor).toString());
            ProfesorInformacionGeneral.setVisible(true);
            ProfesorInformacionGeneral.setEditable(false);
            ProfesorInformacionAsignatura.setText(texto);
            ProfesorInformacionAsignatura.setCaretPosition(0); 
            BuscarProfesorScroll.setVisible(true);
            BuscarProfesorPanel.repaint();
        } else {
            JOptionPane.showMessageDialog(null, "No se ha encontrado el profesor que ha indicado."
                    , "Buscar Profesor", JOptionPane.OK_OPTION);
                    BuscarProfesorTextFieldDNI.setText("");
                    ProfesorInformacionGeneral.setVisible(false);
                    BuscarProfesorScroll.setVisible(false);
        }
    }
    
    /**
     * Dado el parmetro, se recorrerán todos los cursos y sus asignaturas, para encontrar
     * asignaturas, cuyo profesor que la imparta, coinciden con el profesor buscado.
     * 
     * @param DNIprofesor
     * @return La lista de asignaturas impartidas por el profesor que se ha inserido 
     * en el entorno gráfico.
     */
    private String mostrarAsignaturasImpartidas(int DNIprofesor) {
        boolean existe = false;
        String asignaturas = "";
        int cantidadCursos = GestionUniversidad.Cursos.size(), numeroCurso = 0;
        int cantidadAsignaturas, numeroAsignatura = 0;
        while (numeroCurso < cantidadCursos) {
            cantidadAsignaturas = GestionUniversidad.Cursos.get(numeroCurso).asignaturasImpartidas.size();
            while (numeroAsignatura < cantidadAsignaturas) {
                int DNIprofesores = GestionUniversidad.Cursos.get(numeroCurso)
                        .asignaturasImpartidas.get(numeroAsignatura).getDNIprofesor();
                if (DNIprofesor == DNIprofesores) {
                    asignaturas += "("+GestionUniversidad.Cursos.get(numeroCurso).getNombre()+") "+
                            GestionUniversidad.Cursos.get(numeroCurso)
                        .asignaturasImpartidas.get(numeroAsignatura).toString() +"\n";
                    existe = true;
                }
                numeroAsignatura++;
            }
            if (existe) asignaturas += "\n";
            numeroAsignatura = 0;
            numeroCurso++;
        }
        return asignaturas;
    }
    
    private void initPanelCambiarProfesor() {
        CambiarProfesorPanel = new JPanel();
        CambiarProfesorPanel.setLayout(new GridLayout(1,2));
        CambiarProfesorPanel.setBounds(0, 0, 200, 150);
        
        JPanel PanelIzquierdaEtiquetas = new JPanel(new GridLayout(13, 1));
        PanelIzquierdaEtiquetas.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        JPanel PanelDerechaEntradaDatos = new JPanel(new GridLayout(13, 1));

        //Inserción de Datos
        PanelIzquierdaEtiquetas.add(new JLabel("Inserir los DNIs de los profesores:"));
        PanelIzquierdaEtiquetas.add(new JLabel("· DNI del profesor antiguo: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        CambiarProfesorTextFieldDNIAntiguo = new JTextField();
        PanelDerechaEntradaDatos.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(CambiarProfesorTextFieldDNIAntiguo);
        PanelDerechaEntradaDatos.add(new JLabel(""));
        
        PanelIzquierdaEtiquetas.add(new JLabel("· DNI del nuevo profesor: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        CambiarProfesorTextFieldDNINuevo = new JTextField();
        PanelDerechaEntradaDatos.add(CambiarProfesorTextFieldDNINuevo);
        PanelDerechaEntradaDatos.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(new JLabel(""));

        PanelIzquierdaEtiquetas.add(new JLabel("Indicar el curso y asignatura:"));
        PanelIzquierdaEtiquetas.add(new JLabel("· Lista de Cursos: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        CambiarProfesorCursos = new JComboBox();
        PanelDerechaEntradaDatos.add(CambiarProfesorCursos);
        PanelDerechaEntradaDatos.add(new JLabel(""));
        PanelIzquierdaEtiquetas.add(new JLabel("· Lista de Asignaturas: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        CambiarProfesorAsignaturas = new JComboBox();
        PanelDerechaEntradaDatos.add(CambiarProfesorAsignaturas);
        PanelDerechaEntradaDatos.add(new JLabel(""));
        
                
        CambiarProfesorButton = new JButton("Cambiar Profesores");

        PanelDerechaEntradaDatos.add(CambiarProfesorButton);

        CambiarProfesorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Comprobacion de que se han rellenado todos los campos
                    if ((CambiarProfesorTextFieldDNIAntiguo.getText().equals("")) ||
                            (CambiarProfesorTextFieldDNINuevo.getText().equals("")) ||
                            (CambiarProfesorCursos.getSelectedItem().equals("")) ||
                            (CambiarProfesorAsignaturas.getSelectedItem().equals("")) ||
                            (CambiarProfesorAsignaturas.getSelectedItem().equals("No existen asignaturas disponibles"))) {
                        JOptionPane.showMessageDialog(null, "ERROR: Has dejado "
                                + "algun campo sin rellenar o no existen asignaturas disponibles para el camnbio"
                                , "Cambiar Profesores", JOptionPane.CANCEL_OPTION);
                    } else actualizarProfesor();
                } catch (Exception error) {
                }
            }
        });
        
        CambiarProfesorPanel.add(PanelIzquierdaEtiquetas);
        CambiarProfesorPanel.add(PanelDerechaEntradaDatos);
        
        Contenido.add(CambiarProfesorPanel, "Panel CambiarProfesor");
    }
    
    /**
     * @see PanelAlumno#cargarCursos(JComboBox opcion)
     * @param opcion Dependiendo del ComboBox que llame al método
     */
    private void cargarCursos(JComboBox opcion) {
        opcion.removeAllItems();
        int totalCursos = GestionUniversidad.Cursos.size(), numeroCurso = 0;
        while(numeroCurso < totalCursos) {
            opcion.addItem(GestionUniversidad.Cursos.get(numeroCurso));
            numeroCurso++;
        }
    }
    
    /**
     * @see #cargarCursos
     */
    private void cargarAsignaturas(JComboBox opcion) {
        opcion.removeAllItems();
        int totalAsignaturas = GestionUniversidad.Asignaturas.size(), numeroAsignatura = 0;
        while(numeroAsignatura < totalAsignaturas) {
            if (opcion ==  NuevoProfesorAsignaturas) {  
                if (GestionUniversidad.Asignaturas.get(numeroAsignatura).getDNIprofesor() == 0) {
                    opcion.addItem(GestionUniversidad.Asignaturas.get(numeroAsignatura));
                }
            } else {  
                if (GestionUniversidad.Asignaturas.get(numeroAsignatura).getDNIprofesor() > 0) {
                    opcion.addItem(GestionUniversidad.Asignaturas.get(numeroAsignatura));
                }
            }
            numeroAsignatura++;
        }
        
        if (opcion.getItemCount() == 0) opcion.addItem("No existen asignaturas disponibles");
    }
    
    /**
     * Dados un DNI1 y un DNI2, y una asignatura de un curso, ingresado por interfaz,
     * si el profesor que impartía la asignatura es el poseedor del DNI1, el DNI2, 
     * pasará a ser el nuevo profesor que la imparta.
     */
    private void actualizarProfesor() {
        try {
            int DNIantiguo = Integer.parseInt(CambiarProfesorTextFieldDNIAntiguo.getText());
            int DNInuevo = Integer.parseInt(CambiarProfesorTextFieldDNINuevo.getText());
            if (DNIantiguo == 0 || DNInuevo == 0 ) {
                JOptionPane.showMessageDialog(null, "Debe existir el profesor que la impartía "
                        + " para poder realizar el cambio.",
                        "Cambiar Profesores", JOptionPane.CANCEL_OPTION);
                return;
            }
            
            Curso CursoPretencioso = (Curso) CambiarProfesorCursos.getSelectedItem();
            Asignatura AsignaturaPretenciosa = (Asignatura) CambiarProfesorAsignaturas.getSelectedItem();
            
            boolean existe = false;
            for (Profesor Profesor : GestionUniversidad.Profesores) {
                if (Profesor.getDNI() == DNInuevo) {
                    existe = true;
                }
            }
            
            int posicionCurso = GestionUniversidad.Cursos.indexOf(CursoPretencioso);
            int posicionAsignatura = GestionUniversidad.Cursos.
                    get(posicionCurso).asignaturasImpartidas.indexOf(AsignaturaPretenciosa);
            int DNIAsignaturaPretenciosa = GestionUniversidad.Cursos.
                    get(posicionCurso).asignaturasImpartidas.get(posicionAsignatura).getDNIprofesor();
            
            if (DNIantiguo == DNIAsignaturaPretenciosa && existe && DNIantiguo != DNInuevo) {
                GestionUniversidad.Cursos.
                    get(posicionCurso).asignaturasImpartidas.get(posicionAsignatura).setDNIprofesor(DNInuevo);
                JOptionPane.showMessageDialog(null, "Profesor con DNI " + DNInuevo
                    +" es el nuevo profesor de " +CambiarProfesorAsignaturas.getSelectedItem()
                        , "Cambiar Profesores", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Debes ser el profesor titular "
                        + "de la asignatura para poder editar tu sustitución o que el "
                        + "nuevo profesor exista.",
                        "Cambiar Profesores", JOptionPane.CANCEL_OPTION);
            }
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "ERROR: Has rellenado algún campo erróneamente:"
                    + "\n · Para los DNI use carácteres numéricos.",
                    "Cambiar Profesores", JOptionPane.CANCEL_OPTION);
        }
    }
}
package gestionuniversidad.Paneles;

import gestionuniversidad.Alumno;
import gestionuniversidad.Asignatura;
import gestionuniversidad.Curso;
import gestionuniversidad.GestionUniversidad;
import java.awt.CardLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Clase PanelAlumno. Hereda de la clase padre JPanel para incluir mediante componentes
 * gráficos las funciones típicas de una base de datos: añadir, eliminar y buscar elementos.
 * En este caso se trata de alumnos.
 * @see Alumno 
 * 
 * @auhtor: Carlos Marin Fernandez
 * @date: 10-feb-2015
 * @version: 1.0 
 */
public class PanelAlumno extends JPanel {
    
    private final JPanel Menu, Contenido;
    
    //Panel Menu
    private JButton MenuButtonCrear, MenuButtonEliminar, MenuButtonBuscar, 
            MenuButtonAñadir;
    
    //Panel Contenido Añadir Nuevo Alumno
    private JPanel NuevoAlumnoPanel;
    private JTextField NuevoAlumnoTextFieldDNI, NuevoAlumnoTextFieldNombre;
    private JButton NuevoAlumnoButtonCrear;
    
    //Panel Contenido Eliminar Nuevo Alumno
    private JPanel EliminarAlumnoPanel;
    private JComboBox EliminarAlumnoTextFieldDNI;
    private JButton EliminarAlumnoButton;
    
    //Panel Contenido Buscar Alumno
    private JPanel BuscarAlumnoPanel;
    private JTextField BuscarAlumnoTextFieldDNI;
    private JButton BuscarAlumnoButtonDNI;
    private JTextArea AlumnoInformacionGeneral, AlumnoInformacionAsignaturas;
    private JScrollPane BuscarAlumnoScroll;
    
    //Panel Contenido Añadir Asignatura
    private JPanel AñadirAlumnoPanel;
    private JComboBox AñadirAlumnoCurso, AñadirAlumnoAlumno, AñadirAlumnoAsignatura;
    private JButton AñadirAlumnoButton;
    
    /**
     * Constuctor del panel Curso.
     */
    public PanelAlumno() {
        //Declaracion de los paneles que se mostraran en toda la aplicacion
        Menu = new JPanel();
        Contenido = new JPanel();
        Contenido.setLayout(new CardLayout());
        
        //Inicializacion de los componentes de los paneles de cada funcion
        initPanelMenu();
        initPanelNuevoAlumno();
        initPanelEliminarAlumno();
        initPanelBuscarAlumno();
        initPanelAñadirAlumno();
        
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
        ImageIcon Listar = new ImageIcon("./images/Listar.png");
        ImageIcon Universidad = new ImageIcon("./images/Universidad.png");

        //Botones Menu
        MenuButtonCrear = new JButton("     Añadir Alumno     ", Crear);
        MenuButtonAñadir = new JButton("     Añadir Alumno     ", Listar);
        MenuButtonEliminar = new JButton("    Eliminar Alumno    ", Eliminar);
        MenuButtonBuscar = new JButton("      Buscar Alumno    ", Buscar);

        //Menu Acciones
        Menu.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
        Menu.add(MenuButtonCrear);
        Menu.add(MenuButtonEliminar);
        Menu.add(MenuButtonBuscar);
        Menu.add(MenuButtonAñadir);
        Menu.add(new JLabel(Universidad));
        
        //Para cada boton del Menu, inicializamos sus componentes, al hacer click
        MenuButtonCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                NuevoAlumnoTextFieldDNI.setText("");
                NuevoAlumnoTextFieldNombre.setText("");
                CardLayout cl = (CardLayout) (Contenido.getLayout());
                cl.show(Contenido, "Panel CrearAlumno");
            }
        });
        
        MenuButtonAñadir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cargarCursos();
                cargarAsignaturas();
                cargarAlumnos(AñadirAlumnoAlumno);
                CardLayout cl = (CardLayout) (Contenido.getLayout());
                cl.show(Contenido, "Panel AñadirAlumno");
            }
        });
        
        MenuButtonEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cargarAlumnos(EliminarAlumnoTextFieldDNI);
                CardLayout cl = (CardLayout) (Contenido.getLayout());
                cl.show(Contenido, "Panel EliminarAlumno");
            }
        });
        
        MenuButtonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BuscarAlumnoTextFieldDNI.setText("");
                AlumnoInformacionGeneral.setText("");
                AlumnoInformacionGeneral.setVisible(false);
                AlumnoInformacionAsignaturas.setText("");
                BuscarAlumnoScroll.setVisible(false);
                CardLayout cl = (CardLayout) (Contenido.getLayout());
                cl.show(Contenido, "Panel BuscarAlumno");
            }
        });
    }
    
    private  void initPanelNuevoAlumno() {
        NuevoAlumnoPanel = new JPanel();
        NuevoAlumnoPanel.setLayout(new GridLayout(1,2));
        NuevoAlumnoPanel.setBounds(0, 0, 200, 150);
        
        JPanel PanelIzquierdaEtiquetas = new JPanel(new GridLayout(15, 1));
        PanelIzquierdaEtiquetas.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        JPanel PanelDerechaEntradaDatos = new JPanel(new GridLayout(15, 1));

        //Inserción de Datos
        PanelIzquierdaEtiquetas.add(new JLabel("Inserir los datos del alumno"));
        PanelIzquierdaEtiquetas.add(new JLabel("· DNI del alumno: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        NuevoAlumnoTextFieldDNI = new JTextField();
        PanelDerechaEntradaDatos.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(NuevoAlumnoTextFieldDNI);
        PanelDerechaEntradaDatos.add(new JLabel(""));
        
        PanelIzquierdaEtiquetas.add(new JLabel("· Nombre del alumno: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        NuevoAlumnoTextFieldNombre = new JTextField();
        PanelDerechaEntradaDatos.add(NuevoAlumnoTextFieldNombre);
        PanelDerechaEntradaDatos.add(new JLabel(""));

        NuevoAlumnoButtonCrear = new JButton("Crear Alumno");
        PanelDerechaEntradaDatos.add(NuevoAlumnoButtonCrear);
          

        NuevoAlumnoButtonCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Comprobacion de que se han rellenado todos los campos
                    if ((NuevoAlumnoTextFieldDNI.getText().equals("")) ||
                            (NuevoAlumnoTextFieldNombre.getText().equals(""))) {
                        JOptionPane.showMessageDialog(null, "ERROR: Has dejado "
                                + "algun campo sin rellenar", "Crear Alumno", JOptionPane.CANCEL_OPTION);
                    } else crearAlumno();
                } catch (Exception error) {
                }
            }
        });
        
        NuevoAlumnoPanel.add(PanelIzquierdaEtiquetas);
        NuevoAlumnoPanel.add(PanelDerechaEntradaDatos);
        
        Contenido.add(NuevoAlumnoPanel, "Panel CrearAlumno");
    }
    
    private void crearAlumno() {
        int DNIalumno = Integer.parseInt(NuevoAlumnoTextFieldDNI.getText());
        String NombreAlumno = NuevoAlumnoTextFieldNombre.getText();
        Alumno alumno1 = new Alumno(DNIalumno, NombreAlumno);
        
        int cantidadAlumnos = GestionUniversidad.Alumnos.size(), numeroAlumno = 0;
        if (cantidadAlumnos == 0) GestionUniversidad.Alumnos.add(alumno1);
        else {
            int comparacion = compararDNI(alumno1,GestionUniversidad.Alumnos.get(numeroAlumno));
            while (numeroAlumno < cantidadAlumnos && comparacion >= 0 ) {
                    if (comparacion == 0) {
                        JOptionPane.showMessageDialog(null, "El alumno "+alumno1.getDNI()
                            +" ya existe", "Añadir Alumno", JOptionPane.OK_OPTION);
                        return;
                    }
                    numeroAlumno++;
                    if (numeroAlumno == cantidadAlumnos) break;
                    comparacion = compararDNI(alumno1,GestionUniversidad.Alumnos.get(numeroAlumno));
                }
                GestionUniversidad.Alumnos.add(numeroAlumno, alumno1);
                JOptionPane.showMessageDialog(null, "Alumno "+NuevoAlumnoTextFieldNombre.getText()
                +" añadido satisfactoriamente", "Añadir Alumno", JOptionPane.WARNING_MESSAGE);
            }
    }
    
    /**
     * @see Alumno#compararID(gestionuniversidad.Asignatura, gestionuniversidad.Asignatura) 
     */
    private int compararDNI(Alumno alumno1, Alumno alumno2) {
        return String.valueOf(alumno1.getDNI()).compareTo(String.valueOf(alumno2.getDNI()));
    }
        
    /**
     * Rellenar los JComboBox con la informacion de los ArrayList de informacion.
     * @param opcion Especifica que JComboBox ha llamado al método, para rellenarlo.
     */
    private void cargarAlumnos(JComboBox opcion) {
        opcion.removeAllItems();
        int totalAlumnos = GestionUniversidad.Alumnos.size(), numeroAlumno = 0;
        while(numeroAlumno < totalAlumnos) {
            opcion.addItem(GestionUniversidad.Alumnos.get(numeroAlumno));
            numeroAlumno++;
        }
    }
    
    private void initPanelEliminarAlumno() {
        EliminarAlumnoPanel = new JPanel();
        EliminarAlumnoPanel.setLayout(new GridLayout(1,2));
        EliminarAlumnoPanel.setBounds(0, 0, 200, 150);
        
        JPanel PanelIzquierdaEtiquetas = new JPanel(new GridLayout(13, 1));
        PanelIzquierdaEtiquetas.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        JPanel PanelDerechaEntradaDatos = new JPanel(new GridLayout(13, 1));
        
        //Imagen de busqueda
        ImageIcon logoEliminar = new ImageIcon("./images/EliminarContenido.png");

        //Inserción de Datos
        PanelIzquierdaEtiquetas.add(new JLabel("Eliminar por DNI del alumno"));
        PanelIzquierdaEtiquetas.add(new JLabel("· DNI del alumno: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(new JLabel(""));
        EliminarAlumnoTextFieldDNI = new JComboBox();
        PanelDerechaEntradaDatos.add(EliminarAlumnoTextFieldDNI);
        EliminarAlumnoButton = new JButton("Eliminar por DNI", logoEliminar);
        PanelDerechaEntradaDatos.add(EliminarAlumnoButton);
        
        EliminarAlumnoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Comprobacion de que se han rellenado todos los campos
                    if (EliminarAlumnoTextFieldDNI.getSelectedItem().equals("")) 
                        JOptionPane.showMessageDialog(null, "ERROR: Has dejado "
                                + "el campo sin rellenar", "Eliminar Alumno", JOptionPane.CANCEL_OPTION);
                     else eliminarAlumno();
                } catch (Exception error) {}
            }
        });
        
        EliminarAlumnoPanel.add(PanelIzquierdaEtiquetas);
        EliminarAlumnoPanel.add(PanelDerechaEntradaDatos);
        
        Contenido.add(EliminarAlumnoPanel, "Panel EliminarAlumno");   
    }
    
    /**
     * Recorre todos los cursos y todas las asignaturas para eliminar todos los alumnos
     * matriculados en ella y finalmente, elimina el alumno de la base de datos.
     */
    private void eliminarAlumno() {
        int cantidadCursos = GestionUniversidad.Cursos.size(), numeroCurso = 0;
        int cantidadAsignaturas, numeroAsignatura = 0;
        int posicionAlumno = GestionUniversidad.Alumnos.indexOf(EliminarAlumnoTextFieldDNI.getSelectedItem());
        Alumno alumno = GestionUniversidad.Alumnos.get(posicionAlumno);
        String nombreAlumno = alumno.getNombre();
        ArrayList <Asignatura> AsignaturasAlumno = alumno.getAsignaturasMatriculadas();
        while (numeroCurso < cantidadCursos) {
            cantidadAsignaturas = GestionUniversidad.Cursos.get(numeroCurso).asignaturasImpartidas.size();
            while (numeroAsignatura < cantidadAsignaturas) {
                if (AsignaturasAlumno.contains(GestionUniversidad.Cursos.get(numeroCurso).asignaturasImpartidas.get(numeroAsignatura))) {
                    GestionUniversidad.Cursos.get(numeroCurso).asignaturasImpartidas.get(numeroAsignatura).eliminarAlumno(alumno);
                }
                numeroAsignatura++;
            }
            numeroAsignatura = 0;
            numeroCurso++;
        }
        GestionUniversidad.Alumnos.remove(alumno);
        JOptionPane.showMessageDialog(null, "Alumno "+nombreAlumno
                +" eliminado satisfactoriamente", "Eliminar Alumno", JOptionPane.WARNING_MESSAGE);
    }
    
    private void initPanelBuscarAlumno()  {
        BuscarAlumnoPanel = new JPanel();
        BuscarAlumnoPanel.setLayout(new GridLayout(2,2));
        BuscarAlumnoPanel.setBounds(0, 0, 200, 150);
        
        JPanel PanelIzquierdaEtiquetas = new JPanel(new GridLayout(8, 1));
        PanelIzquierdaEtiquetas.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        JPanel PanelIzquierdaProfesor = new JPanel(new GridLayout(2, 1));
        PanelIzquierdaProfesor.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        JPanel PanelDerechaEntradaDatos = new JPanel(new GridLayout(8, 1));
        JPanel PanelDerechaAsignaturas = new JPanel(new GridLayout(2, 1));
        
        //Imagen de busqueda
        ImageIcon logoBuscar = new ImageIcon("./images/BuscarContenido.png");

        //Inserción de Datos
        PanelIzquierdaEtiquetas.add(new JLabel("Búsqueda por DNI del alumno"));
        PanelIzquierdaEtiquetas.add(new JLabel("· DNI del alumno: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(new JLabel(""));
        BuscarAlumnoTextFieldDNI = new JTextField();
        PanelDerechaEntradaDatos.add(BuscarAlumnoTextFieldDNI);
        BuscarAlumnoButtonDNI = new JButton("Buscar por DNI", logoBuscar);
        PanelDerechaEntradaDatos.add(BuscarAlumnoButtonDNI);
        PanelDerechaEntradaDatos.add(new JLabel(""));
        
        PanelIzquierdaProfesor.add(new JLabel("Información del alumno:"));
        AlumnoInformacionGeneral = new JTextArea();
        AlumnoInformacionGeneral.setBounds(20,20,20,20);
        AlumnoInformacionGeneral.setVisible(false);
        PanelIzquierdaProfesor.add(AlumnoInformacionGeneral);
        
        PanelDerechaAsignaturas.add(new JLabel("Lista de asignaturas matriculadas:"));
        AlumnoInformacionAsignaturas = new JTextArea();
        AlumnoInformacionAsignaturas.setEditable(false);
        BuscarAlumnoScroll = new JScrollPane(AlumnoInformacionAsignaturas);
        BuscarAlumnoScroll.setBounds(20, 20, 400, 200);
        BuscarAlumnoScroll.setVisible(false);
        
        PanelDerechaAsignaturas.add(BuscarAlumnoScroll);

        BuscarAlumnoButtonDNI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Comprobacion de que se han rellenado todos los campos
                    if (BuscarAlumnoTextFieldDNI.getText().equals("")) 
                        JOptionPane.showMessageDialog(null, "ERROR: Has dejado "
                                + "algun campo sin rellenar", "Buscar Alumno", JOptionPane.CANCEL_OPTION);
                     else buscarAlumno();
                } catch (Exception error) {}
            }
        });
        
        BuscarAlumnoPanel.add(PanelIzquierdaEtiquetas);
        BuscarAlumnoPanel.add(PanelDerechaEntradaDatos);
        BuscarAlumnoPanel.add(PanelIzquierdaProfesor);
        BuscarAlumnoPanel.add(PanelDerechaAsignaturas);

        Contenido.add(BuscarAlumnoPanel, "Panel BuscarAlumno");      
    }
    
    private void buscarAlumno() {
        boolean existeAlumno = false;
        String texto = "";
        int cantidadAlumnos = GestionUniversidad.Alumnos.size(), numeroAlumno = 0;
        int DNIBuscar = Integer.parseInt(BuscarAlumnoTextFieldDNI.getText());
        while (numeroAlumno < cantidadAlumnos) {
            Alumno alumno = GestionUniversidad.Alumnos.get(numeroAlumno);
            if(DNIBuscar == alumno.getDNI()) {
                BuscarAlumnoTextFieldDNI.setText("");
                existeAlumno = true;
                texto += GestionUniversidad.Alumnos.get(numeroAlumno).mostrarAsignaturas();
                break;
            }
            numeroAlumno++;
        }
        if (existeAlumno == true) {
            AlumnoInformacionGeneral.setText(GestionUniversidad.Alumnos.get(numeroAlumno).toString());
            AlumnoInformacionGeneral.setVisible(true);
            AlumnoInformacionGeneral.setEditable(false);
            AlumnoInformacionAsignaturas.setText(texto);
            AlumnoInformacionAsignaturas.setCaretPosition(0); 
            BuscarAlumnoScroll.setVisible(true);
            BuscarAlumnoPanel.repaint();
        } else {
            JOptionPane.showMessageDialog(null, "No se ha encontrado el profesor que ha indicado."
                    , "Buscar Profesor", JOptionPane.OK_OPTION);
                    BuscarAlumnoTextFieldDNI.setText("");
                    AlumnoInformacionGeneral.setVisible(false);
                    BuscarAlumnoScroll.setVisible(false);
        }
    }
    
    private void initPanelAñadirAlumno() {
        AñadirAlumnoPanel = new JPanel();
        AñadirAlumnoPanel.setLayout(new GridLayout(1,2));
        AñadirAlumnoPanel.setBounds(0, 0, 200, 150);
        
        JPanel PanelIzquierdaEtiquetas = new JPanel(new GridLayout(13, 1));
        PanelIzquierdaEtiquetas.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        JPanel PanelDerechaEntradaDatos = new JPanel(new GridLayout(13, 1));

        //Inserción de Datos
        PanelIzquierdaEtiquetas.add(new JLabel("Añadir alumno a una asignatura:"));
        PanelIzquierdaEtiquetas.add(new JLabel(" · Curso a añadir: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(new JLabel(""));
        AñadirAlumnoCurso = new JComboBox();
        PanelDerechaEntradaDatos.add(AñadirAlumnoCurso);
        
        PanelIzquierdaEtiquetas.add(new JLabel(" · Alumno a matricular: "));
        
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(new JLabel(""));
        AñadirAlumnoAlumno = new JComboBox();
        PanelDerechaEntradaDatos.add(AñadirAlumnoAlumno);
        
        PanelIzquierdaEtiquetas.add(new JLabel(" · Asignatura donde matricular: "));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(new JLabel(""));
        AñadirAlumnoAsignatura = new JComboBox();
        PanelDerechaEntradaDatos.add(AñadirAlumnoAsignatura);
        PanelDerechaEntradaDatos.add(new JLabel(""));
        
        AñadirAlumnoButton = new JButton("Añadir Alumno");

        PanelDerechaEntradaDatos.add(AñadirAlumnoButton);

        AñadirAlumnoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Comprobacion de que se han rellenado todos los campos
                    if ((AñadirAlumnoCurso.getSelectedItem().equals("")) ||
                            (AñadirAlumnoAlumno.getSelectedItem().equals("")) ||
                            (AñadirAlumnoAsignatura.getSelectedItem().equals(""))) {
                        JOptionPane.showMessageDialog(null, "ERROR: Has dejado "
                                + "algun campo sin rellenar", "Añadir Alumno", JOptionPane.CANCEL_OPTION);
                    } else añadirAlumno();
                } catch (Exception error) {
                }
            }
        });
        
        AñadirAlumnoPanel.add(PanelIzquierdaEtiquetas);
        AñadirAlumnoPanel.add(PanelDerechaEntradaDatos);
        
        Contenido.add(AñadirAlumnoPanel, "Panel AñadirAlumno");
    }
    
    /**
     * @see #cargarAlumnos(javax.swing.JComboBox) 
     */
    private void cargarCursos() {
        AñadirAlumnoCurso.removeAllItems();
        int totalCursos = GestionUniversidad.Cursos.size(), numeroCurso = 0;
        while(numeroCurso < totalCursos) {
            AñadirAlumnoCurso.addItem(GestionUniversidad.Cursos.get(numeroCurso));
            numeroCurso++;
        }
    }
    
    /**
     * @see #cargarCursos()  
     */
    private void cargarAsignaturas() {
        AñadirAlumnoAsignatura.removeAllItems();
        int totalAsignaturas = GestionUniversidad.Asignaturas.size(), numeroAsignatura = 0;
        while(numeroAsignatura < totalAsignaturas) {
            if (GestionUniversidad.Asignaturas.get(numeroAsignatura).getDNIprofesor() > 0)
            AñadirAlumnoAsignatura.addItem(GestionUniversidad.Asignaturas.get(numeroAsignatura));
            numeroAsignatura++;
        }
    }
    
    /**
     * Añade un alumno en el curso y la asignatura que se la ha indicado en el panel.
     */
    private void añadirAlumno() {
        try {
            Asignatura asignaturaPretenciosa = (Asignatura) AñadirAlumnoAsignatura.getSelectedItem();
            Alumno Alumno1 = (Alumno) AñadirAlumnoAlumno.getSelectedItem();
            Curso cursoPretencioso = (Curso) AñadirAlumnoCurso.getSelectedItem();
            int numeroCurso = GestionUniversidad.Cursos.indexOf(cursoPretencioso);
            boolean existeAsignaturaEnCurso = GestionUniversidad.Cursos.get(numeroCurso).asignaturasImpartidas.contains(asignaturaPretenciosa);
            if (existeAsignaturaEnCurso != true){
                JOptionPane.showMessageDialog(null, "La asignatura "+asignaturaPretenciosa.getIdentificador()
                        +" no existe", "Añadir Alumno", JOptionPane.OK_OPTION);
                return;
            }
            int posicionAsignatura = GestionUniversidad.Cursos.get(numeroCurso).asignaturasImpartidas.indexOf(asignaturaPretenciosa);
            GestionUniversidad.Cursos.get(numeroCurso).asignaturasImpartidas.get(posicionAsignatura).addAlumno(Alumno1);
            JOptionPane.showMessageDialog(null, "Alumno "+Alumno1.getNombre()
                +" añadido satisfactoriamente a " +asignaturaPretenciosa.getNombre()
                    , "Añadir Alumno", JOptionPane.WARNING_MESSAGE);
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "ERROR: Has rellenado algún campo erróneamente:",
                    "Añadir Alumno", JOptionPane.CANCEL_OPTION);
        }
    }
}

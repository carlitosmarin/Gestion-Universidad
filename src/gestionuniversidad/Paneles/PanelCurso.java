package gestionuniversidad.Paneles;

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
 * Clase PanelCurso. Hereda de la clase padre JPanel para incluir mediante componentes
 * gráficos las funciones típicas de una base de datos: añadir, eliminar y buscar elementos.
 * En este caso se trata de cursos.
 * @see Curso
 * 
 * @auhtor: Carlos Marin Fernandez
 * @date: 10-feb-2015
 * @version: 1.0 
 */
public class PanelCurso extends JPanel {
    
    private final JPanel Menu, Contenido;
    
    //Panel Menu
    private JButton MenuButtonCrear, MenuButtonEliminar, MenuButtonBuscar, 
            MenuButtonListar;
    
    //Panel Contenido Crear Nuevo Curso
    private JPanel NuevoCursoPanel;
    private JLabel NuevoCursoLabelID, NuevoCursoLabelNombre, NuevoCursoLabelEdificio;
    private JTextField NuevoCursoTextFieldID, NuevoCursoTextFieldNombre;
    private JComboBox NuevoCursoCombo;
    private JButton NuevoCursoButtonCrear;
    
    //Panel Contenido Eliminar Curso
    private JPanel EliminarCursoPanel;
    private JLabel EliminarCursoLabelID, EliminarCursoLabelNombre;
    private JTextField EliminarCursoTextFieldID, EliminarCursoTextFieldNombre;
    private JButton EliminarCursoButtonID, EliminarCursoButtonNombre;
    
    //Panel Contenido Buscar Curso
    private JPanel BuscarCursoPanel;
    private JLabel BuscarCursoLabelID, BuscarCursoLabelNombre;
    private JTextField BuscarCursoTextFieldID, BuscarCursoTextFieldNombre;
    private JButton BuscarCursoButtonID, BuscarCursoButtonNombre;
    private JTextArea CursoInformacionGeneral, CursoInformacionAsignaturas;
    private JScrollPane BuscarCursoScroll;
    
    //Panel Contenido Listar Cursos
    private JPanel ListarCursoPanel;
    private JLabel totalCursos, totalAsignaturas, totalAlumnos, totalProfesores; 
    private JTextArea CursoListar;
    private JScrollPane ListarCursoScroll;
    
    /**
     * Constuctor del panel Curso
     */
    public PanelCurso() {
        //Declaracion de los paneles que se mostraran en toda la aplicacion
        Menu = new JPanel();
        Contenido = new JPanel();
        Contenido.setLayout(new CardLayout());
        
        initPanelMenu();
        initPanelNuevoCurso();
        initPanelEliminarCurso();
        initPanelBuscarCurso();
        initPanelListarCurso();
        
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
        ImageIcon Crear = new ImageIcon("./images/Crear.png");
        ImageIcon Eliminar = new ImageIcon("./images/Eliminar.png");
        ImageIcon Buscar = new ImageIcon("./images/Buscar.png");
        ImageIcon Listar = new ImageIcon("./images/Listar.png");
        ImageIcon Universidad = new ImageIcon("./images/Universidad.png");

        //Botones Menu
        MenuButtonCrear = new JButton("       Crear Curso      ", Crear);
        MenuButtonEliminar = new JButton("     Eliminar Curso     ", Eliminar);
        MenuButtonBuscar = new JButton("      Buscar Curso      ", Buscar);
        MenuButtonListar = new JButton("      Listar Cursos     ", Listar);

        //Menu Acciones
        Menu.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
        Menu.add(MenuButtonCrear);
        Menu.add(MenuButtonEliminar);
        Menu.add(MenuButtonBuscar);
        Menu.add(MenuButtonListar);
        Menu.add(new JLabel(Universidad));
        
        //Para cada boton del Menu, inicializamos sus componentes, al hacer click
        MenuButtonCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cargarEdificios();
                NuevoCursoTextFieldID.setText("");
                NuevoCursoTextFieldNombre.setText("");
                CardLayout cl = (CardLayout) (Contenido.getLayout());
                cl.show(Contenido, "Panel CrearCurso");
            }
        });
        
        MenuButtonEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                EliminarCursoTextFieldID.setText("");
                EliminarCursoTextFieldNombre.setText("");
                CardLayout cl = (CardLayout) (Contenido.getLayout());
                cl.show(Contenido, "Panel EliminarCurso");
            }
        });
        
        MenuButtonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BuscarCursoTextFieldID.setText("");
                BuscarCursoTextFieldNombre.setText("");
                CursoInformacionGeneral.setText("");
                CursoInformacionGeneral.setVisible(false);
                CursoInformacionAsignaturas.setText("");
                BuscarCursoScroll.setVisible(false);
                CardLayout cl = (CardLayout) (Contenido.getLayout());
                cl.show(Contenido, "Panel BuscarCurso");
            }
        });
        
        MenuButtonListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                totalCursos.setText(" · Cantidad total de cursos: " + GestionUniversidad.Cursos.size());
                totalAsignaturas.setText(" · Cantidad total de asignaturas: " + GestionUniversidad.Asignaturas.size());
                totalProfesores.setText(" · Cantidad total de profesores: " + totalRealProfesores());
                totalAlumnos.setText(" · Cantidad total de alumnos: " + GestionUniversidad.Alumnos.size()); 
                String texto = listarCursosBoton();
                CursoListar.setText(texto);
                CursoListar.setCaretPosition(0);
                ListarCursoScroll.setVisible(true);
                CardLayout cl = (CardLayout) (Contenido.getLayout());
                cl.show(Contenido, "Panel ListarCurso");
            }
        });
    }
    
    private void initPanelNuevoCurso() {
        NuevoCursoPanel = new JPanel();
        NuevoCursoPanel.setLayout(new GridLayout(1,2));
        NuevoCursoPanel.setBounds(0, 0, 200, 150);
        
        JPanel PanelIzquierdaEtiquetas = new JPanel(new GridLayout(13, 1));
        PanelIzquierdaEtiquetas.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        JPanel PanelDerechaEntradaDatos = new JPanel(new GridLayout(13, 1));

        //Inserción de Datos
        PanelIzquierdaEtiquetas.add(new JLabel("Inserir los datos del curso"));
        NuevoCursoLabelID = new JLabel("· Código del Curso: ");
        PanelIzquierdaEtiquetas.add(NuevoCursoLabelID);
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        NuevoCursoTextFieldID = new JTextField();
        PanelDerechaEntradaDatos.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(NuevoCursoTextFieldID);
        PanelDerechaEntradaDatos.add(new JLabel(""));
        
        NuevoCursoLabelNombre = new JLabel("· Nombre del Curso: ");
        PanelIzquierdaEtiquetas.add(NuevoCursoLabelNombre);
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        NuevoCursoTextFieldNombre = new JTextField();
        PanelDerechaEntradaDatos.add(NuevoCursoTextFieldNombre);
        PanelDerechaEntradaDatos.add(new JLabel(""));

        //Cambiar el textfield por una lista de edificio
        NuevoCursoLabelEdificio = new JLabel("· Edificio donde se imparte: ");
        PanelIzquierdaEtiquetas.add(NuevoCursoLabelEdificio);
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        NuevoCursoCombo = new JComboBox();
        PanelDerechaEntradaDatos.add(NuevoCursoCombo);
        PanelDerechaEntradaDatos.add(new JLabel(""));

        NuevoCursoButtonCrear = new JButton("Crear Curso");

        PanelDerechaEntradaDatos.add(NuevoCursoButtonCrear);

        NuevoCursoButtonCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Comprobacion de que se han rellenado todos los campos
                    if ((NuevoCursoTextFieldID.getText().equals("")) ||
                            (NuevoCursoTextFieldNombre.getText().equals("")) ||
                            (NuevoCursoCombo.getSelectedItem().equals(""))) {
                        JOptionPane.showMessageDialog(null, "ERROR: Has dejado "
                                + "algun campo sin rellenar", "Crear Curso", JOptionPane.CANCEL_OPTION);
                    } else crearCurso();
                } catch (Exception error) {
                }
            }
        });
        
        NuevoCursoPanel.add(PanelIzquierdaEtiquetas);
        NuevoCursoPanel.add(PanelDerechaEntradaDatos);
        
        Contenido.add(NuevoCursoPanel, "Panel CrearCurso");
    }
    
    /**
     * @see PanelAlumno#cargarCursos() 
     */
    private void cargarEdificios() {
        NuevoCursoCombo.removeAllItems();
        int totalEdificios = GestionUniversidad.Edificios.size(), numeroEdificio = 0;
        while(numeroEdificio < totalEdificios) {
            NuevoCursoCombo.addItem(GestionUniversidad.Edificios.get(numeroEdificio));
            numeroEdificio++;
        }
    }
    
    /**
     * Inserimos de manera ordenada el curso que ha indicado el usuario.
     */
    private void crearCurso() {
        try {
            //Creamos el nuevo Curso con los datos inseridos
            int IDNuevoCurso = Integer.parseInt(NuevoCursoTextFieldID.getText());
            String NombreNuevoCurso = NuevoCursoTextFieldNombre.getText();            
            String EdificioNuevoCurso = NuevoCursoCombo.getSelectedItem().toString();
            Curso nuevoCurso = new Curso(IDNuevoCurso,NombreNuevoCurso,EdificioNuevoCurso);
            
            int cantidadCursos = GestionUniversidad.Cursos.size(), numeroCurso = 0;
            if (cantidadCursos == 0) GestionUniversidad.Cursos.add(nuevoCurso);
            else {
                int comparacion = compararID(nuevoCurso,GestionUniversidad.Cursos.get(numeroCurso));
                while (numeroCurso < cantidadCursos && comparacion >= 0 ) {
                    if (comparacion == 0) {
                        JOptionPane.showMessageDialog(null, "Curso "+nuevoCurso.getIdentificador()
                            +" ya existe", "Crear Curso", JOptionPane.OK_OPTION);
                        NuevoCursoTextFieldID.setText("");
                        return;
                    }
                    numeroCurso++;
                    if (numeroCurso == cantidadCursos) break;
                    comparacion = compararID(nuevoCurso,GestionUniversidad.Cursos.get(numeroCurso));
                }
                GestionUniversidad.Cursos.add(numeroCurso, nuevoCurso);
            }
            
            JOptionPane.showMessageDialog(null, "Curso de "+NuevoCursoTextFieldNombre.getText()
                +" creado satisfactoriamente", "Crear Curso", JOptionPane.WARNING_MESSAGE);
            
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "ERROR: Has rellenado algún campo erróneamente:"
                    + "\n · Para el código use carácteres numéricos."
                    + "\n · Para el nombre use carácteres alfabéticos."
                    + "\n · Para el edificio use carácteres alfabéticos.",
                    "Crear Curso", JOptionPane.CANCEL_OPTION);
        }    
        NuevoCursoTextFieldID.setText("");
        NuevoCursoTextFieldNombre.setText("");
        NuevoCursoCombo.setSelectedIndex(0);
    }
    
    /**
     * @see PanelAlumno#compararDNI(gestionuniversidad.Alumno, gestionuniversidad.Alumno) 
     */
    public int compararID(Curso curso1, Curso curso2) {
        return String.valueOf(curso1.getIdentificador()).compareTo(String.valueOf(curso2.getIdentificador()));
    }

    private void initPanelEliminarCurso() {
        EliminarCursoPanel = new JPanel();
        EliminarCursoPanel.setLayout(new GridLayout(1,2));
        EliminarCursoPanel.setBounds(0, 0, 200, 150);
        
        JPanel PanelIzquierdaEtiquetas = new JPanel(new GridLayout(13, 1));
        PanelIzquierdaEtiquetas.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        JPanel PanelDerechaEntradaDatos = new JPanel(new GridLayout(13, 1));
        
        //Imagen de busqueda
        ImageIcon logoEliminar = new ImageIcon("./images/EliminarContenido.png");

        //Inserción de Datos
        PanelIzquierdaEtiquetas.add(new JLabel("Eliminar por código del curso"));
        EliminarCursoLabelID = new JLabel("· Código del Curso: ");
        PanelIzquierdaEtiquetas.add(EliminarCursoLabelID);
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(new JLabel(""));
        EliminarCursoTextFieldID = new JTextField();
        PanelDerechaEntradaDatos.add(EliminarCursoTextFieldID);
        EliminarCursoButtonID = new JButton("Eliminar por código", logoEliminar);
        PanelDerechaEntradaDatos.add(EliminarCursoButtonID);
        PanelDerechaEntradaDatos.add(new JLabel(""));
        
        PanelIzquierdaEtiquetas.add(new JLabel("Eliminar por nombre del curso"));
        EliminarCursoLabelNombre = new JLabel("· Nombre del Curso: ");
        PanelIzquierdaEtiquetas.add(EliminarCursoLabelNombre);
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(new JLabel(""));
        EliminarCursoTextFieldNombre = new JTextField();
        PanelDerechaEntradaDatos.add(EliminarCursoTextFieldNombre);
        EliminarCursoButtonNombre = new JButton("Eliminar por nombre", logoEliminar);
        PanelDerechaEntradaDatos.add(EliminarCursoButtonNombre);
        PanelDerechaEntradaDatos.add(new JLabel(""));

        EliminarCursoButtonID.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Comprobacion de que se han rellenado todos los campos
                    if (EliminarCursoTextFieldID.getText().equals("")) 
                        JOptionPane.showMessageDialog(null, "ERROR: Has dejado "
                                + "algun campo sin rellenar", "Eliminar Curso", JOptionPane.CANCEL_OPTION);
                     else eliminarCurso(0);
                } catch (Exception error) {}
            }
        });
        
        EliminarCursoButtonNombre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Comprobacion de que se han rellenado todos los campos
                    if (EliminarCursoTextFieldNombre.getText().equals("")) 
                        JOptionPane.showMessageDialog(null, "ERROR: Has dejado "
                                + "algun campo sin rellenar", "Eliminar Curso", JOptionPane.CANCEL_OPTION);
                     else eliminarCurso(1);
                } catch (Exception error) {
                }
            }
        });
        
        EliminarCursoPanel.add(PanelIzquierdaEtiquetas);
        EliminarCursoPanel.add(PanelDerechaEntradaDatos);
        
        Contenido.add(EliminarCursoPanel, "Panel EliminarCurso");    
    }
    
    private void eliminarCurso(int tipoEliminar) {
        //tipoEliminar = 0 si es por ID, o = 1 si es por Nombre
        int cantidadCursos = GestionUniversidad.Cursos.size(), numeroCurso = 0;
        if (tipoEliminar == 0) {
            int IDEliminar = Integer.parseInt(EliminarCursoTextFieldID.getText());
            while (numeroCurso < cantidadCursos) {
                if(IDEliminar == GestionUniversidad.Cursos.get(numeroCurso).getIdentificador()) {
                    GestionUniversidad.Cursos.remove(numeroCurso);
                    JOptionPane.showMessageDialog(null, "Curso "+EliminarCursoTextFieldID.getText()
                    +" eliminado satisfactoriamente", "Eliminar Curso por ID", JOptionPane.WARNING_MESSAGE);
                    EliminarCursoTextFieldID.setText("");
                    return;
                }
                numeroCurso++;
            }
        } else {
            String NombreEliminar = EliminarCursoTextFieldNombre.getText();
            while (numeroCurso < cantidadCursos) {
                if(NombreEliminar.equals(GestionUniversidad.Cursos.get(numeroCurso).getNombre())) {
                    GestionUniversidad.Cursos.remove(numeroCurso);
                    JOptionPane.showMessageDialog(null, "Curso de "+EliminarCursoTextFieldNombre.getText()
                    +" eliminado satisfactoriamente", "Eliminar Curso por Nombre", JOptionPane.WARNING_MESSAGE);
                    EliminarCursoTextFieldNombre.setText("");
                    return;
                }
                numeroCurso++;
            }
        }
        if (cantidadCursos == numeroCurso) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado el curso que ha indicado."
                    , "Eliminar Curso ", JOptionPane.OK_OPTION);
                    EliminarCursoTextFieldNombre.setText("");
                    EliminarCursoTextFieldID.setText("");
        }
    }
    
    private void initPanelBuscarCurso() {
        BuscarCursoPanel = new JPanel();
        BuscarCursoPanel.setLayout(new GridLayout(2,2));
        BuscarCursoPanel.setBounds(0, 0, 200, 150);
        
        JPanel PanelIzquierdaEtiquetas = new JPanel(new GridLayout(8, 1));
        PanelIzquierdaEtiquetas.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        JPanel PanelIzquierdaCurso = new JPanel(new GridLayout(2, 1));
        PanelIzquierdaCurso.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        JPanel PanelDerechaEntradaDatos = new JPanel(new GridLayout(8, 1));
        JPanel PanelDerechaAsignaturas = new JPanel(new GridLayout(2, 1));
        
        //Imagen de busqueda
        ImageIcon logoBuscar = new ImageIcon("./images/BuscarContenido.png");

        //Inserción de Datos
        PanelIzquierdaEtiquetas.add(new JLabel("Búsqueda por código del curso"));
        BuscarCursoLabelID = new JLabel("· Código del Curso: ");
        PanelIzquierdaEtiquetas.add(BuscarCursoLabelID);
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(new JLabel(""));
        BuscarCursoTextFieldID = new JTextField();
        PanelDerechaEntradaDatos.add(BuscarCursoTextFieldID);
        BuscarCursoButtonID = new JButton("Buscar por código", logoBuscar);
        PanelDerechaEntradaDatos.add(BuscarCursoButtonID);
        PanelDerechaEntradaDatos.add(new JLabel(""));
        
        PanelIzquierdaCurso.add(new JLabel("Información del curso:"));
        CursoInformacionGeneral = new JTextArea();
        CursoInformacionGeneral.setBounds(20,20,20,20);
        CursoInformacionGeneral.setVisible(false);
        PanelIzquierdaCurso.add(CursoInformacionGeneral);
        
        PanelIzquierdaEtiquetas.add(new JLabel("Búsqueda por nombre del curso"));
        BuscarCursoLabelNombre = new JLabel("· Nombre del Curso: ");
        PanelIzquierdaEtiquetas.add(BuscarCursoLabelNombre);
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelIzquierdaEtiquetas.add(new JLabel(""));
        PanelDerechaEntradaDatos.add(new JLabel(""));
        BuscarCursoTextFieldNombre = new JTextField();
        PanelDerechaEntradaDatos.add(BuscarCursoTextFieldNombre);
        BuscarCursoButtonNombre = new JButton("Buscar por nombre", logoBuscar);
        PanelDerechaEntradaDatos.add(BuscarCursoButtonNombre);
        PanelDerechaEntradaDatos.add(new JLabel(""));
        
        PanelDerechaAsignaturas.add(new JLabel("Lista de asignaturas:"));
        CursoInformacionAsignaturas = new JTextArea();
        CursoInformacionAsignaturas.setEditable(false);
        BuscarCursoScroll = new JScrollPane(CursoInformacionAsignaturas);
        BuscarCursoScroll.setBounds(20, 20, 400, 200);
        BuscarCursoScroll.setVisible(false);
        
        PanelDerechaAsignaturas.add(BuscarCursoScroll);

        BuscarCursoButtonID.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Comprobacion de que se han rellenado todos los campos
                    if (BuscarCursoTextFieldID.getText().equals("")) 
                        JOptionPane.showMessageDialog(null, "ERROR: Has dejado "
                                + "algun campo sin rellenar", "Buscar Curso", JOptionPane.CANCEL_OPTION);
                     else buscarCurso(0);
                } catch (Exception error) {}
            }
        });
        
        BuscarCursoButtonNombre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //Comprobacion de que se han rellenado todos los campos
                    if (BuscarCursoTextFieldNombre.getText().equals("")) 
                        JOptionPane.showMessageDialog(null, "ERROR: Has dejado "
                                + "algún campo sin rellenar", "Buscar Curso", JOptionPane.CANCEL_OPTION);
                     else buscarCurso(1);
                } catch (Exception error) {
                }
            }
        });
        
        BuscarCursoPanel.add(PanelIzquierdaEtiquetas);
        BuscarCursoPanel.add(PanelDerechaEntradaDatos);
        BuscarCursoPanel.add(PanelIzquierdaCurso);
        BuscarCursoPanel.add(PanelDerechaAsignaturas);

        Contenido.add(BuscarCursoPanel, "Panel BuscarCurso");
        
    }   
    
    private void buscarCurso(int tipoBuscar) {
        //tipoEliminar = 0 si es por ID, o = 1 si es por Nombre
        boolean existeCurso = false;
        int cantidadCursos = GestionUniversidad.Cursos.size(), numeroCurso = 0;
        if (tipoBuscar == 0) {
            int IDEliminar = Integer.parseInt(BuscarCursoTextFieldID.getText());
            while (numeroCurso < cantidadCursos) {
                if(IDEliminar == GestionUniversidad.Cursos.get(numeroCurso).getIdentificador()) {
                    BuscarCursoTextFieldID.setText("");
                    existeCurso = true;
                    break;
                }
                numeroCurso++;
            }
        } else {
            String NombreEliminar = BuscarCursoTextFieldNombre.getText();
            while (numeroCurso < cantidadCursos) {
                if(NombreEliminar.equals(GestionUniversidad.Cursos.get(numeroCurso).getNombre())) {
                    BuscarCursoTextFieldNombre.setText("");
                    existeCurso = true;
                    break;
                }
                numeroCurso++;
            }
        }
        if (existeCurso == true) {
            CursoInformacionGeneral.setText(GestionUniversidad.Cursos.get(numeroCurso).toString());
            CursoInformacionGeneral.setVisible(true);
            CursoInformacionGeneral.setEditable(false);
            CursoInformacionAsignaturas.setText(GestionUniversidad.Cursos.get(numeroCurso).mostrarAsignaturas());
            CursoInformacionAsignaturas.setCaretPosition(0); 
            BuscarCursoScroll.setVisible(true);
            BuscarCursoPanel.repaint();
        } else {
            JOptionPane.showMessageDialog(null, "No se ha encontrado el curso que ha indicado."
                    , "Buscar Curso", JOptionPane.OK_OPTION);
                    BuscarCursoTextFieldNombre.setText("");
                    BuscarCursoTextFieldID.setText("");
                    CursoInformacionGeneral.setVisible(false);
                    BuscarCursoScroll.setVisible(false);
        }
    }
    
    private void initPanelListarCurso() {
        ListarCursoPanel = new JPanel();
        ListarCursoPanel.setLayout(new GridLayout(2,1));
        ListarCursoPanel.setBounds(0, 0, 100, 30);
        
        JPanel PanelCentralSuperior = new JPanel(new GridLayout(7, 1));
        PanelCentralSuperior.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        JPanel PanelCentralInferior = new JPanel(new GridLayout(1, 1));
        
        //Inserción de Datos
        totalCursos = new JLabel();
        totalAsignaturas = new JLabel();
        totalAlumnos = new JLabel();
        totalProfesores = new JLabel();
        PanelCentralSuperior.add(new JLabel("Listar todos los cursos de la Universidad: "));
        PanelCentralSuperior.add(totalCursos);
        PanelCentralSuperior.add(totalAsignaturas);
        PanelCentralSuperior.add(totalProfesores);
        PanelCentralSuperior.add(totalAlumnos);
        
        CursoListar = new JTextArea();
        CursoListar.setEditable(false);
        ListarCursoScroll = new JScrollPane(CursoListar);
        ListarCursoScroll.setBounds(20, 20, 400, 50);
        ListarCursoScroll.setVisible(true);
        
        PanelCentralInferior.add(ListarCursoScroll);
        
        ListarCursoPanel.add(PanelCentralSuperior);
        ListarCursoPanel.add(PanelCentralInferior);
        
        Contenido.add(ListarCursoPanel, "Panel ListarCurso");
    }
    
    /**
     * Cuando se le da a la opción de listar los cursos, llamamos a este método para
     * "rellenar" que devuelve la lista completa de todos los cursos, con sus respectivas
     * asignaturas. 
     * 
     * @return El texto final para introducirlo en la JTextArea
     */
    private String listarCursosBoton() {
        String texto = "";
        for (Curso Curso : GestionUniversidad.Cursos) {
            texto += Curso.toString();
            texto += "\n---------------------------\n";
            texto += Curso.mostrarAsignaturas() + "\n\n";
        }
        return texto;
    }
    
    /**
     * Para calcular relamente la cantidad de profesores reales, los eliminados únicamente
     * se les hace setDNI(0) pero no son eliminados de la lista para futuras creaciones.
     * 
     * @return Devuelve el numero de profesores reales, cuyo DNI no es 0.
     */
    private int totalRealProfesores() {
        int valor = 0;
        for (Profesor Profesor : GestionUniversidad.Profesores) {
            if (Profesor.getDNI() > 0) {
                valor++;
            }
        }
        return valor;
    }
}

package gestionuniversidad;

import gestionuniversidad.Paneles.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 * Clase main GestionUniversidad. Clase principal del proyecto que hereda de la clase
 * JFrame, es decir, es la clase que representa la GUI (Interfaz Gráfica de Usuario),
 * en la que se crean las "BBDD" que contendrán todos los objetos que hemos definido
 * anteriormente. Además de incluir ejemplos propios para no trabajar sin datos.
 * 
 * @auhtor: Carlos Marin Fernandez
 * @date: 10-feb-2015
 * @version: 1.0 
 */
public class GestionUniversidad extends JFrame {

    //Listas para la gestion
    public static ArrayList <Curso> Cursos;
    public static ArrayList <Profesor> Profesores;
    public static ArrayList <Asignatura> Asignaturas;
    public static ArrayList <Alumno> Alumnos;
    public static ArrayList <String> Edificios;
    
    //Definicion de los paneles del frame principal
    public static PanelCurso PanelCurso = new PanelCurso();
    public static PanelAsignatura PanelAsignatura = new PanelAsignatura();
    public static PanelProfesor PanelProfesor = new PanelProfesor();
    public static PanelAlumno PanelAlumno = new PanelAlumno();
    public static PanelOtros PanelOtros = new PanelOtros();
    
    //Elementos interfaz grafica de usuario (GUI)
    public static JTabbedPane Tabs;
    
    //Tamaño de la pantalla de tu ordenador para centralizar las ventanas
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Constructor de la clase GU que situa la ventana, la dimensiona y inicializa sus componentes.
     */
    public GestionUniversidad() {
        super("Gestion Universidad -- Cursos, Asignaturas y Estudiantes");
        this.setBounds((screenSize.width/2)-450, (screenSize.height/2)-300, 900, 600);
        initComponents();
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cursos = new ArrayList();
        Profesores = new ArrayList();
        Asignaturas = new ArrayList();
        Alumnos = new ArrayList();
        Edificios = new ArrayList();
        valoresDefecto();
        GestionUniversidad interfaz = new GestionUniversidad();
    }
    
    public void initComponents() {
        Tabs = new JTabbedPane();
        Tabs.setAlignmentY(CENTER_ALIGNMENT);

        //Iconos Pestañas
        ImageIcon iconoCurso = new ImageIcon("./images/Tab/Curso.png");
        ImageIcon iconoAsignatura = new ImageIcon("./images/Tab/Asignatura.png");
        ImageIcon iconoProfesor = new ImageIcon("./images/Tab/Profesor.png");
        ImageIcon iconoAlumno = new ImageIcon("./images/Tab/Alumno.png");
        ImageIcon iconoOtros = new ImageIcon("./images/Tab/Otros.png");

        //Configuracion de las tabs con iconos
        Tabs.addTab("  Cursos   ", iconoCurso, PanelCurso);
        Tabs.addTab("Asignaturas", iconoAsignatura, PanelAsignatura);
        Tabs.addTab(" Profesores", iconoProfesor, PanelProfesor);
        Tabs.addTab("  Alumnos  ", iconoAlumno, PanelAlumno);
        Tabs.addTab("   Otros   ", iconoOtros, PanelOtros);

        this.add(Tabs);
    }
    /**
     * Valores que se toman como ejemplo para tener datos de base.
     */
    public static void valoresDefecto() {
        Curso Curso1 = new Curso(1,"GEIN","Anselm Turmeda");
        Curso Curso2 = new Curso(2,"GMAT","Anselm Turmeda");
        Curso Curso3 = new Curso(3,"GADE","Jovellanos");
        Cursos.add(Curso1);
        Cursos.add(Curso2);
        Cursos.add(Curso3);
        Profesor Profesor1 = new Profesor(1, "Ricardo Galli");
        Profesor Profesor2 = new Profesor(2, "Isaac Lera");
        Profesor Profesor3 = new Profesor(3, "Margaret Miro");
        Profesor Profesor4 = new Profesor(4, "Carlos Guerrero");
        Profesores.add(Profesor1);
        Profesores.add(Profesor2);
        Profesores.add(Profesor3);
        Profesores.add(Profesor4);
        Asignatura Asignatura1 = new Asignatura(21720,"Concurrente",1);
        Asignatura Asignatura2 = new Asignatura(21719,"ACSI",2);
        Asignatura Asignatura3 = new Asignatura(21721,"Inteligencia Artificial",3);
        Asignatura Asignatura4 = new Asignatura(21345,"Empresa I",4);
        Asignatura Asignatura5 = new Asignatura(21903,"Estadistica",3);
        Curso1.addAsignatura(Asignatura1);
        Curso1.addAsignatura(Asignatura2);
        Curso1.addAsignatura(Asignatura3);
        Curso1.addAsignatura(Asignatura5);
        Curso2.addAsignatura(Asignatura3);
        Curso2.addAsignatura(Asignatura5);
        Curso3.addAsignatura(Asignatura4);
        Curso3.addAsignatura(Asignatura5);
        Asignaturas.add(Asignatura1);
        Asignaturas.add(Asignatura2);
        Asignaturas.add(Asignatura3);
        Asignaturas.add(Asignatura4);
        Asignaturas.add(Asignatura5);
        Alumno Alumno1 = new Alumno(1,"Carlos");
        Alumno Alumno2 = new Alumno(2,"Berto");
        Alumno Alumno3 = new Alumno(3,"Mariano");
        Alumno Alumno4 = new Alumno(4,"Juanjo");
        Alumno Alumno5 = new Alumno(5,"Maria");
        Asignatura1.addAlumno(Alumno1);
        Asignatura1.addAlumno(Alumno2);
        Asignatura1.addAlumno(Alumno3);
        Asignatura2.addAlumno(Alumno4);
        Asignatura2.addAlumno(Alumno5);
        Asignatura2.addAlumno(Alumno2);
        Asignatura3.addAlumno(Alumno5);
        Asignatura4.addAlumno(Alumno2);
        Asignatura5.addAlumno(Alumno4);
        Asignatura5.addAlumno(Alumno3);
        Alumnos.add(Alumno1);
        Alumnos.add(Alumno2);
        Alumnos.add(Alumno3);
        Alumnos.add(Alumno4);
        Alumnos.add(Alumno5);
        Edificios.add("Anselm Turmeda");
        Edificios.add("Jovellanos");
        Edificios.add("Ramon LLull");
        Edificios.add("Mateu Orfila");
    }  
}

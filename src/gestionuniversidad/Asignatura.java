package gestionuniversidad;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Clase Asignatura. Contiene un identificador propio, un
 * idenificador del profesor que la imparte, una lista de alumnos matriculados
 * y el nombre de la asignatura.
 * 
 * @auhtor: Carlos Marin Fernandez
 * @date: 10-feb-2015
 * @version: 1.0 
 */
public class Asignatura implements Serializable {

    private int Identificador;
    private String Nombre;
    private int DNIprofesor;
    private ArrayList <Alumno> alumnosMatriculados;
    
    public Asignatura(int Identificador, String Nombre, int Profesor) {
        this.Identificador = Identificador;
        this.Nombre = Nombre;
        this.DNIprofesor = Profesor;
        this.alumnosMatriculados = new ArrayList();
    }

    /**
     * Muestra los alumnos matriculados en una asignatura concreta.
     * 
     * @return La lista de alumnos matriculados
     */
    public String mostrarAlumnos() {
        String alumnos = "";
        int cantidadAlumnos = this.alumnosMatriculados.size();
        for (int i = 0; i < cantidadAlumnos; i++){
            alumnos += this.alumnosMatriculados.get(i).toString();
            alumnos += "\n";
        }
        return alumnos;
    }
    
    /**
     * @see Alumno#mostrarAsignaturas() 
     */
    public void addAlumno(Alumno alumno1) {
        int cantidadAlumnos = this.alumnosMatriculados.size(), numeroAlumno = 0;
        if (cantidadAlumnos == 0) {
            this.alumnosMatriculados.add(alumno1);
            alumno1.addAsignatura(this);
        }
        else {
            int comparacion = compararDNI(alumno1,this.alumnosMatriculados.get(numeroAlumno));
            while (numeroAlumno < cantidadAlumnos && comparacion >= 0 ) {
                    if (comparacion == 0) {
                        JOptionPane.showMessageDialog(null, "El alumno "+alumno1.getDNI()
                            +" ya existe", "Añadir Alumno", JOptionPane.OK_OPTION);
                        return;
                    }
                    numeroAlumno++;
                    if (numeroAlumno == cantidadAlumnos) break;
                    comparacion = compararDNI(alumno1,this.alumnosMatriculados.get(numeroAlumno));
                }
                this.alumnosMatriculados.add(numeroAlumno, alumno1);
                alumno1.addAsignatura(this);
            }
    }
    /**
     * @see Alumno#compararID(gestionuniversidad.Asignatura, gestionuniversidad.Asignatura) 
     */
    public int compararDNI(Alumno alumno1, Alumno alumno2) {
        return String.valueOf(alumno1.getDNI()).compareTo(String.valueOf(alumno2.getDNI()));
    }
    
    public boolean eliminarAlumno(Alumno alumno1) {
        int cantidadAlumnos = this.alumnosMatriculados.size(), numeroAlumno = 0;
        while (numeroAlumno < cantidadAlumnos) {
            if(this.alumnosMatriculados.get(numeroAlumno).getDNI()== alumno1.getDNI()){
                this.alumnosMatriculados.remove(numeroAlumno);
                return true;
            }
            numeroAlumno++;
        }
        return false;
    }
    
    public Alumno buscarAlumno(Alumno alumno1) {
        int cantidadAlumnos = this.alumnosMatriculados.size(), numeroAlumno = 0;
        while (numeroAlumno < cantidadAlumnos) {
            if(this.alumnosMatriculados.get(numeroAlumno).getDNI()== alumno1.getDNI()){
                return this.alumnosMatriculados.get(numeroAlumno);
            }
            numeroAlumno++;
        }
        return null;
    }
    
    /*
     * Getters and Setters 
     */
    public int getIdentificador() {
        return Identificador;
    }

    public void setIdentificador(int Identificador) {
        this.Identificador = Identificador;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }    

    public int getDNIprofesor() {
        return DNIprofesor;
    }

    public void setDNIprofesor(int DNIprofesor) {
        this.DNIprofesor = DNIprofesor;
    }
    
    public int getAlumnosMatriculados() {
        return this.alumnosMatriculados.size();
    }

    /**
     * Para mostrar de manera legible los datos, el profesor que "llama" a valorLista(),
     * devuelve la posicion de este en la lista de profesores.
     * 
     * @return La posicion del profesor
     */
    public int valorLista () {
        int cantidadProfesores = GestionUniversidad.Profesores.size(), numeroProfesor = 0;
        while (numeroProfesor < cantidadProfesores) {
            if (GestionUniversidad.Profesores.get(numeroProfesor).getDNI() == this.DNIprofesor) {
                return numeroProfesor; 
            }
            numeroProfesor++;
        }
        return 0;
    }
    
    /**
     * Si no existe profesor que da cierta asignatura, lo indicamos con NombreProfesor
     * 
     * @see Alumno#toString() 
     */
    @Override
    public String toString() {
        int numeroProfesor = valorLista();
        String NombreProfesor;
        if(GestionUniversidad.Profesores.get(numeroProfesor).getDNI() >= 1) 
             NombreProfesor = GestionUniversidad.Profesores.get(numeroProfesor).getNombre();
        else NombreProfesor = "~ No profesor ~";
        return Nombre + " ("+ Identificador +") - "+ NombreProfesor;
    }    
}

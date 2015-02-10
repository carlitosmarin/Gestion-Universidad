package gestionuniversidad;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Clase Profesor. Definimos el objeto profesor, hijo de una clase padre Persona, de 
 * la que hereda el DNI y el nombre.
 * 
 * @auhtor: Carlos Marin Fernandez
 * @date: 10-feb-2015
 * @version: 1.0 
 */
public class Profesor extends Persona {
    
    private ArrayList <Asignatura> asignaturasImpartidas; 
    
    public Profesor(int DNI, String Nombre) {
        super(DNI, Nombre);
        this.asignaturasImpartidas = new ArrayList();
    }
    
    /**
     * Ídem a las clases Curso, Alumno y Asignatura, tienen métodos de añadir, eliminar y buscar
     * en sus respectivos ArrayList.
     * 
     * @see Alumno#mostrarAsignaturas() 
     */
    public String mostrarAsignaturas() {
        String texto = "";
        int cantidadAsignaturas = this.asignaturasImpartidas.size();
        for (int i = 0; i < cantidadAsignaturas; i++){
            texto += this.asignaturasImpartidas.get(i).toString();
        }
        return texto;
    }

    public void addAsignaturaToProfesor(Asignatura asignatura) {
        int cantidadAlumnos = this.asignaturasImpartidas.size(), numeroAlumno = 0;
        if (cantidadAlumnos == 0) this.asignaturasImpartidas.add(asignatura);
        else {
            int comparacion = compararDNI(asignatura,this.asignaturasImpartidas.get(numeroAlumno));
            while (numeroAlumno < cantidadAlumnos && comparacion >= 0 ) {
                    if (comparacion == 0) {
                        JOptionPane.showMessageDialog(null, "La asignatura "+asignatura.getIdentificador()
                            +" ya existe", "Añadir Asignatura al Profesor " + this.getNombre(), JOptionPane.OK_OPTION);
                        return;
                    }
                    numeroAlumno++;
                    if (numeroAlumno == cantidadAlumnos) break;
                    comparacion = compararDNI(asignatura,this.asignaturasImpartidas.get(numeroAlumno));
                }
                this.asignaturasImpartidas.add(numeroAlumno, asignatura);
            }        
    }
    
    public int compararDNI(Asignatura asignatura1, Asignatura asignatura2) {
        return String.valueOf(asignatura1.getIdentificador()).compareTo(String.valueOf(asignatura2.getIdentificador()));
    }
    
    public String toString(int privilegios){
        if (privilegios == 1) return super.getNombre() + " (" + super.getDNI() + ")\n";
        return super.getNombre();
    }
}

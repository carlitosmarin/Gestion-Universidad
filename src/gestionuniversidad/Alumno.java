package gestionuniversidad;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Clase Alumno. Definimos el objeto alumno, hijo de una clase padre Persona, de 
 * la que hereda el DNI y el nombre.
 * 
 * @auhtor: Carlos Marin Fernandez
 * @date: 10-feb-2015
 * @version: 1.0 
 */
public class Alumno extends Persona {

    private ArrayList <Asignatura> asignaturasMatriculadas; 
    
    public Alumno(int DNI, String Nombre) {
        super(DNI, Nombre);
        this.asignaturasMatriculadas = new ArrayList();
    }
    
    /**
     * Muestra las asignaturas en las que está matriculado el alumno que "llama"
     * al método.
     * 
     * @return La lista de asignaturas matriculadas.
     */
    public String mostrarAsignaturas() {
        String asignaturas = "";
        int cantidadAsignaturas = this.asignaturasMatriculadas.size();
        for (int i = 0; i < cantidadAsignaturas; i++){
            asignaturas += this.asignaturasMatriculadas.get(i) + "\n";
        }
        return asignaturas;
    }

    public ArrayList<Asignatura> getAsignaturasMatriculadas() {
        return asignaturasMatriculadas;
    }
    
    /**
     * Añade por orden alfabético una nueva asignatura a la lista de asignaturas
     * matriculadas del alumno.
     * 
     * @param asignatura La asignatura que se matricula el alumno.
     */
    public void addAsignatura(Asignatura asignatura) {
        int cantidadAsignaturas = this.asignaturasMatriculadas.size(), numeroAsignatura = 0;
        if (cantidadAsignaturas == 0) this.asignaturasMatriculadas.add(asignatura);
        else {
            int comparacion = compararID(asignatura,this.asignaturasMatriculadas.get(numeroAsignatura));
            while (numeroAsignatura < cantidadAsignaturas && comparacion >= 0 ) {
                    if (comparacion == 0) {
                        JOptionPane.showMessageDialog(null, "El alumno " + this.getNombre() +
                                " ya esta matriculado de la asignatura "+asignatura.getIdentificador(),
                                "Añadir Asignatura", JOptionPane.OK_OPTION);
                        return;
                    }
                    numeroAsignatura++;
                    if (numeroAsignatura == cantidadAsignaturas) break;
                    comparacion = compararID(asignatura,this.asignaturasMatriculadas.get(numeroAsignatura));
                }
                this.asignaturasMatriculadas.add(numeroAsignatura, asignatura);
            }
    }
        
    /**
     * Comparador de valores, para ordenar alfabéticamente.
     * 
     * @param asignatura1 Sacaremos el identificador de la asignatura.
     * @param asignatura2 Ídem a asignatura1.
     * @return Devuelve el valor de la comparación (0: si son iguales, >0: si el ID de la primera es mayor).
     */
    public int compararID(Asignatura asignatura1, Asignatura asignatura2) {
        return String.valueOf(asignatura1.getIdentificador()).compareTo(String.valueOf(asignatura2.getIdentificador()));
    }
    
    /**
     * Para poder mostrar un objeto de manera legible para el usuario
     * 
     * @return El nombre del alumno y su DNI en un String
     */
    public String toString(){
        return super.getNombre() + " (" + super.getDNI() + ")";
    }
}
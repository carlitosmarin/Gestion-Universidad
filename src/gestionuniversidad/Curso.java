package gestionuniversidad;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Clase Curso. Definimos el objeto Curso y sus métodos.
 * 
 * @auhtor: Carlos Marin Fernandez
 * @date: 10-feb-2015
 * @version: 1.0 
 */
public class Curso implements Serializable{

    private int Identificador;
    private String Nombre;
    private String Edificio;
    public ArrayList <Asignatura> asignaturasImpartidas;

    public Curso(int Identificador, String Nombre, String Edificio) {
        this.Identificador = Identificador;
        this.Nombre = Nombre;
        this.Edificio = Edificio;
        this.asignaturasImpartidas = new ArrayList();
    }

    /**
     * Ídem a las clases Curso, Alumno y Profesor, tienen métodos de añadir, eliminar y buscar
     * en sus respectivos ArrayList.
     * 
     * @see Alumno#mostrarAsignaturas() 
     */
    public String mostrarAsignaturas() {
        String asignaturas = "";
        int cantidadAsignaturas = this.asignaturasImpartidas.size();
        for (int i = 0; i < cantidadAsignaturas; i++){
            asignaturas += this.asignaturasImpartidas.get(i).toString();
            asignaturas += "\n";
        }
        return asignaturas;
    }
    
    public void addAsignatura(Asignatura asignatura1) {
        int cantidadAsignaturas = this.asignaturasImpartidas.size(), numeroAsignatura = 0;
        if (cantidadAsignaturas == 0) this.asignaturasImpartidas.add(asignatura1);
        else {
            int comparacion = compararID(asignatura1,this.asignaturasImpartidas.get(numeroAsignatura));
            while (numeroAsignatura < cantidadAsignaturas && comparacion >= 0 ) {
                if (comparacion == 0) {
                    JOptionPane.showMessageDialog(null, "Asignatura "+asignatura1.getIdentificador()
                            +" ya existe", "Añadir Asignatura", JOptionPane.OK_OPTION);
                    return;
                }
                numeroAsignatura++;
                if (numeroAsignatura == cantidadAsignaturas) break;
                comparacion = compararID(asignatura1,this.asignaturasImpartidas.get(numeroAsignatura));
            }
            this.asignaturasImpartidas.add(numeroAsignatura, asignatura1);
        }
    }
    
    public int compararID(Asignatura asignatura1, Asignatura asignatura2) {
        return String.valueOf(asignatura1.getIdentificador()).compareTo(String.valueOf(asignatura2.getIdentificador()));
    }
    
    public boolean eliminarAsignatura(Asignatura asignatura1) {
        int cantidadAsignaturas = this.asignaturasImpartidas.size(), numeroAsignatura = 0;
        while (numeroAsignatura < cantidadAsignaturas) {
            if(this.asignaturasImpartidas.get(numeroAsignatura).getIdentificador() == asignatura1.getIdentificador()){
                this.asignaturasImpartidas.remove(numeroAsignatura);
                return true;
            }
            numeroAsignatura++;
        }
        return false;
    }
    
    public Asignatura buscarAsignatura(Asignatura asignatura1) {
        int cantidadAsignaturas = this.asignaturasImpartidas.size(), numeroAsignatura = 0;
        while (numeroAsignatura < cantidadAsignaturas) {
            if(this.asignaturasImpartidas.get(numeroAsignatura).getIdentificador() == asignatura1.getIdentificador()){
                return this.asignaturasImpartidas.get(numeroAsignatura);
            }
            numeroAsignatura++;
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

    public String getEdificio() {
        return Edificio;
    }

    public void setEdificio(String Edificio) {
        this.Edificio = Edificio;
    }   

    /**
     * @see Alumno#toString() 
     */
    @Override
    public String toString() {
        return Nombre + " ("+ Identificador +") - "+ Edificio;
    }
}

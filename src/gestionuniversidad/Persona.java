package gestionuniversidad;

import java.io.*;

/**
 * Clase Persona. Definimos el objeto persona, clase padre de Alumno y de Profesor
 * en la que declaramos los atributos que contendrán ambas clases y su constructor.
 * 
 * @auhtor: Carlos Marin Fernandez
 * @date: 10-feb-2015
 * @version: 1.0 
 */
public class Persona implements Serializable {
    
    private int DNI;
    private String Nombre;

    public Persona(int DNI, String Nombre) {
        this.DNI = DNI;
        this.Nombre = Nombre;
    }

    /*
     * Getters and Setters 
     */
    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }   

    @Override
    public String toString() {
        return Nombre + " (" + DNI + ")";
    }
}

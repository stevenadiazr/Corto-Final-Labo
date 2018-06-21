/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Steven Diaz
 */
public class Inscripciones {
    private int numeroAFP;
    private String nombre;
    private String apellidos;
    private String profesion;
    private int edad;
    private boolean estado;
    
    public Inscripciones(){}

    public Inscripciones(int numeroAFP, String nombre, String apellidos, String profesion, int edad, boolean estado) {
        this.numeroAFP = numeroAFP;
        this.nombre = nombre;
    }

    public Inscripciones(String nombre, String apellidos, int numeroAFP, String profesion, int edad, boolean estado) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.numeroAFP = numeroAFP;
        this.edad = edad;
        this.estado = estado;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumeroAFP() {
        return numeroAFP;
    }

    public String getProfesion() {
        return profesion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumeroAFP(int numeroAFP) {
        this.numeroAFP = numeroAFP;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
    
    
}


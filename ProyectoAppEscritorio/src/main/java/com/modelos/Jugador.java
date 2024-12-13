
package com.modelos;

public class Jugador {
    private String nif;
    private String nombre;
    private String apellidos;
    private int edad;
    private String categoria;
    private boolean fichado;

    public Jugador(String nif, String nombre, String apellidos, int edad, String categoria, boolean fichado) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.categoria = categoria;
        this.fichado = fichado;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isFichado() {
        return fichado;
    }

    public void setFichado(boolean fichado) {
        this.fichado = fichado;
    }

    @Override
    public String toString() {
        return this.nombre+" "+this.apellidos;
    } 
}

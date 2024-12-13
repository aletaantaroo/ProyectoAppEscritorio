package com.modelos;

public class Equipo {
    private int idEquipo;
    private String nombre;
    private String categoria;
    private String urlEscudo;
    private String localidad;
    private String director;

    public Equipo(int idEquipo, String nombre, String categoria, String urlEscudo, String localidad, String director) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.urlEscudo = urlEscudo;
        this.localidad = localidad;
        this.director = director;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUrlEscudo() {
        return urlEscudo;
    }

    public void setUrlEscudo(String urlEscudo) {
        this.urlEscudo = urlEscudo;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
    
    
}

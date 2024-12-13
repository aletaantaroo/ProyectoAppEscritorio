package com.modelos;

import java.time.LocalDate;

public class Fichaje {
    private String nifJugador;
    private String jugador;
    private int idEquipo;
    private String equipo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
  
    public Fichaje(String nifJugador, String jugador,int idEquipo, String equipo, LocalDate fechaInicio, LocalDate fechaFin) {
        this.nifJugador = nifJugador;
        this.jugador = jugador;
        this.equipo = equipo;
        this.idEquipo = idEquipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    
    public String getNifJugador() {
        return nifJugador;
    }

    public void setNifJugador(String nifJugador) {
        this.nifJugador = nifJugador;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }
    
    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "("+ this.nifJugador + ")" + this.jugador +" de "+ "("+ this.idEquipo + ")"+ this.equipo;
    }
    
}

package com.nprofesiones.dam.pmdm.teamquiniela;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class MensajeQuiniela implements Serializable {

    String propietario;
    String quiniela;
    String comentario;
    String equipo;
    String jornada;
    GregorianCalendar fecha;

    public MensajeQuiniela(String propietario, String quiniela, String comentario, String equipo, GregorianCalendar fecha) {
        this.propietario = propietario;
        this.quiniela = quiniela;
        this.comentario = comentario;
        this.equipo = equipo;
        this.fecha = fecha;
    }

    public MensajeQuiniela(String propietario, String quiniela, String jornada) {
        this.propietario = propietario;
        this.quiniela = quiniela;
        this.jornada = jornada;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getQuiniela() {
        return quiniela;
    }

    public void setQuiniela(String quiniela) {
        this.quiniela = quiniela;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public GregorianCalendar getFecha() {
        return fecha;
    }

    public void setFecha(GregorianCalendar fecha) {
        this.fecha = fecha;
    }

}
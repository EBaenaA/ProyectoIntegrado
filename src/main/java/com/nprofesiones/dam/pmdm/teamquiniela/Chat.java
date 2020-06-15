package com.nprofesiones.dam.pmdm.teamquiniela;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Chat implements Serializable {

    int id;
    String correo;
    String quiniela;
    String comentario;
    int jornada;

    public Chat(int id, String correo, String quiniela, String comentario, int jornada) {
        this.id = id;
        this.correo = correo;
        this.quiniela = quiniela;
        this.comentario = comentario;
        this.jornada = jornada;
    }

    public Chat(String correo, String quiniela, String comentario, int jornada) {

        this.correo = correo;
        this.quiniela = quiniela;
        this.comentario = comentario;
        this.jornada = jornada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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
}
package com.nprofesiones.dam.pmdm.teamquiniela;

import java.io.Serializable;

public class Quiniela implements Serializable {

    String correo;
    String quiniela;
    String comentario;
    int jornada;


    public Quiniela(String correo, String quiniela, String comentario, int jornada) {

        this.correo = correo;
        this.quiniela = quiniela;
        this.comentario = comentario;
        this.jornada = jornada;
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

    public  int getJornada(){
        return jornada;
    }

    public void setJornada(int jornada){
        this.jornada = jornada;
    }

    @Override
    public String toString() {
        return "Correo: " + correo + "\n"
                + "Comentario:" + comentario + "\n"
                + "Jornada: " + jornada + "\n"
                + "Quiniela: " + quiniela;
    }

}
package com.nprofesiones.dam.pmdm.teamquiniela;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Usuario implements Serializable{

    String nombre;
    String pass;
    String correo;

    public Usuario(String nombre, String pass, String correo) {
        this.nombre = nombre;
        this.pass = pass;
        this.correo = correo;

    }

    public Usuario(String pass, String correo) {
        this.pass = pass;
        this.correo = correo;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }



}

package com.nprofesiones.dam.pmdm.teamquiniela;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.xml.transform.Result;

public class EnviarDatos extends AsyncTask<String , Void, Void> {

    public static final int PUERTO = 6000;
    public static final String SERVIDOR = "192.168.1.32";

    public String nombre, pass, email;

    public EnviarDatos(String nombre, String pass, String mail){
        this.nombre = nombre;
        this.pass = pass;
        this.email = mail;
    }

    @Override
    protected Void doInBackground(String... Void) {

        //String mensajes = Void[0];

        Socket socket;
        //InetSocketAddress isa = new InetSocketAddress(SERVIDOR, PUERTO);

        try{
            socket = new Socket(SERVIDOR, PUERTO);

            DataOutputStream flujoSalida = new DataOutputStream(socket.getOutputStream());
            flujoSalida.writeUTF("Nuevo usuario -> " + this.nombre + ";" + this.pass + ";" + this.email);

            socket.close();
            flujoSalida.close();

        }catch (Exception e){

        }


        return null;
    }
}

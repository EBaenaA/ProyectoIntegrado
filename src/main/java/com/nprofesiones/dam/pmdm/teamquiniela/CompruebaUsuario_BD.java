package com.nprofesiones.dam.pmdm.teamquiniela;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;

public class CompruebaUsuario_BD extends AsyncTask<Usuario, Void, String> {

    public static final int PUERTO = 6000;
    public static final String SERVIDOR = "cerratolabs.me";

    public Usuario usuario;
    public String msg;

    public CompruebaUsuario_BD(Usuario usuario){
        this.usuario = usuario;
        this.msg = "";
    }

    @Override
    protected String doInBackground(Usuario... Void) {

        //String mensajes = Void[0];

        Socket socket = null;
        String aux = "";
        //InetSocketAddress isa = new InetSocketAddress(SERVIDOR, PUERTO);

        try{
            //socket = new Socket(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("cerratolabs.me", 6000)));
            socket = new Socket(SERVIDOR, PUERTO);
            ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream recibeMensaje = new ObjectInputStream(socket.getInputStream());
            flujoSalida.writeUTF("2");

            flujoSalida.flush();


            aux = recibeMensaje.readUTF();


            if(aux.trim().equals("2")){
                flujoSalida.writeUTF(this.usuario.getPass());
                flujoSalida.flush();
                flujoSalida.writeUTF(this.usuario.getCorreo());
                flujoSalida.flush();


                aux = recibeMensaje.readUTF();


            }


            socket.close();
            flujoSalida.close();
            recibeMensaje.close();

        }catch (Exception e){
            Log.e("SERVIDOR", e.getMessage());
        }


        return aux;
    }

    public String getMsg(){
        return this.msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

}
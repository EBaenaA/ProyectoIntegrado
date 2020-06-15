package com.nprofesiones.dam.pmdm.teamquiniela;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;

import javax.xml.transform.Result;

public class RegistrarUsuario_BD extends AsyncTask<Usuario , Void, String> {

    public static final int PUERTO = 6000;
    public static final String SERVIDOR = "cerratolabs.me";

    //private Usuario usuario;
    private String nombre, pasw, email;
    //private String msg;

    public RegistrarUsuario_BD(String nombre, String passw, String email){
        this.nombre = nombre;
        this.pasw = passw;
        this.email = email;
        //this.msg = "";
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
            flujoSalida.writeUTF("1");

            flujoSalida.flush();


            aux = recibeMensaje.readUTF();


            if(aux.trim().equals("1")){
                flujoSalida.writeUTF(nombre);
                flujoSalida.flush();
                flujoSalida.writeUTF(pasw);
                flujoSalida.flush();
                flujoSalida.writeUTF(email);
                flujoSalida.flush();

                //mensaje.writeUTF("1");

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

    /*public String getMsg(){
        return this.msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }*/

}

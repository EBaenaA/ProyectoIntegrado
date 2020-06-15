package com.nprofesiones.dam.pmdm.teamquiniela;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RegistrarQuiniela_BD extends AsyncTask<Quiniela, Void, String> {

    public static final int PUERTO = 6000;
    public static final String SERVIDOR = "cerratolabs.me";

    public Quiniela quiniela;
    public String msg;

    public RegistrarQuiniela_BD(Quiniela quiniela){
        this.quiniela = quiniela;
        this.msg = "";
    }

    @Override
    protected String doInBackground(Quiniela... Void) {

        //String mensajes = Void[0];

        Socket socket = null;
        String aux = "";
        //InetSocketAddress isa = new InetSocketAddress(SERVIDOR, PUERTO);

        try{
            //socket = new Socket(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("cerratolabs.me", 6000)));
            socket = new Socket(SERVIDOR, PUERTO);
            ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream recibeMensaje = new ObjectInputStream(socket.getInputStream());
            flujoSalida.writeUTF("3");

            flujoSalida.flush();


            aux = recibeMensaje.readUTF();


            if(aux.trim().equals("3")){
                flujoSalida.writeUTF(this.quiniela.correo);
                flujoSalida.flush();
                flujoSalida.writeUTF(this.quiniela.quiniela);
                flujoSalida.flush();
                flujoSalida.writeUTF(this.quiniela.comentario);
                flujoSalida.flush();
                flujoSalida.writeUTF(String.valueOf(this.quiniela.jornada));
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

    public String getMsg(){
        return this.msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

}
package com.nprofesiones.dam.pmdm.teamquiniela;

import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

public class ConsultaLista_BD extends AsyncTask<Void, Void, LinkedList<Quiniela>> {

    public static final int PUERTO = 6000;
    public static final String SERVIDOR = "cerratolabs.me";



    public ConsultaLista_BD(){

    }

    @Override
    protected LinkedList<Quiniela> doInBackground(Void... Void) {

        //String mensajes = Void[0];

        LinkedList<Quiniela> listaQ = new LinkedList<Quiniela>();

        Socket socket = null;
        String aux= "";
        //InetSocketAddress isa = new InetSocketAddress(SERVIDOR, PUERTO);

        try{
            socket = new Socket(SERVIDOR, PUERTO);
            ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());

            flujoSalida.writeUTF("4");
            flujoSalida.flush();


            aux = flujoEntrada.readUTF();

            if(aux.equals("4")){

                while (flujoEntrada.readUTF().equals("AOM")) {
                    listaQ.add(new Quiniela(flujoEntrada.readUTF(), flujoEntrada.readUTF(), flujoEntrada.readUTF(), Integer.parseInt(flujoEntrada.readUTF())));
                }


                /*flujoSalida.writeUTF("4");
                flujoSalida.flush();*/

            }


            socket.close();
            flujoEntrada.close();
            flujoSalida.close();

        }catch (Exception e){

        }


        return listaQ;
    }

}
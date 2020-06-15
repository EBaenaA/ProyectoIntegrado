package com.nprofesiones.dam.pmdm.teamquiniela;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuinielaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuinielaFragment extends Fragment {

    CheckBox cB1,cB2,cB3,cB4,cB5,cB6,cB7,cB8,cB9,
            cB10,cB11,cB12,cB13,cB14,cB15,cB16,cB17,cB18,cB19,
            cB20,cB21,cB22,cB23,cB24,cB25,cB26,cB27,cB28,cB29,
            cB30,cB31,cB32,cB33,cB34,cB35,cB36,cB37,cB38,cB39,
            cB40,cB41,cB42,cB43,cB44,cB45;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuinielaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuinielaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuinielaFragment newInstance(String param1, String param2) {
        QuinielaFragment fragment = new QuinielaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_quiniela, container, false);

        cB1= v.findViewById(R.id.CheckBox1);
        cB2= v.findViewById(R.id.CheckBox2);
        cB3= v.findViewById(R.id.CheckBox3);
        cB4= v.findViewById(R.id.CheckBox4);
        cB5= v.findViewById(R.id.CheckBox5);
        cB6= v.findViewById(R.id.CheckBox6);
        cB7= v.findViewById(R.id.CheckBox7);
        cB8= v.findViewById(R.id.CheckBox8);
        cB9= v.findViewById(R.id.CheckBox9);
        cB10= v.findViewById(R.id.CheckBox10);
        cB11= v.findViewById(R.id.CheckBox11);
        cB12= v.findViewById(R.id.CheckBox12);
        cB13= v.findViewById(R.id.CheckBox13);
        cB14= v.findViewById(R.id.CheckBox14);
        cB15= v.findViewById(R.id.CheckBox15);
        cB16= v.findViewById(R.id.CheckBox16);
        cB17= v.findViewById(R.id.CheckBox17);
        cB18= v.findViewById(R.id.CheckBox18);
        cB19= v.findViewById(R.id.CheckBox19);
        cB20= v.findViewById(R.id.CheckBox20);
        cB21= v.findViewById(R.id.CheckBox21);
        cB22= v.findViewById(R.id.CheckBox22);
        cB23= v.findViewById(R.id.CheckBox23);
        cB24= v.findViewById(R.id.CheckBox24);
        cB25= v.findViewById(R.id.CheckBox25);
        cB26= v.findViewById(R.id.CheckBox26);
        cB27= v.findViewById(R.id.CheckBox27);
        cB28= v.findViewById(R.id.CheckBox28);
        cB29= v.findViewById(R.id.CheckBox29);
        cB30= v.findViewById(R.id.CheckBox30);
        cB31= v.findViewById(R.id.CheckBox31);
        cB32= v.findViewById(R.id.CheckBox32);
        cB33= v.findViewById(R.id.CheckBox33);
        cB34= v.findViewById(R.id.CheckBox34);
        cB35= v.findViewById(R.id.CheckBox35);
        cB36= v.findViewById(R.id.CheckBox36);
        cB37= v.findViewById(R.id.CheckBox37);
        cB38= v.findViewById(R.id.CheckBox38);
        cB39= v.findViewById(R.id.CheckBox39);
        cB40= v.findViewById(R.id.CheckBox40);
        cB41= v.findViewById(R.id.CheckBox41);
        cB42= v.findViewById(R.id.CheckBox42);
        cB43= v.findViewById(R.id.CheckBox43);
        cB44= v.findViewById(R.id.CheckBox44);
        cB45= v.findViewById(R.id.CheckBox45);

        Button guardarQuiniela = v.findViewById(R.id.guardar_quiniela);
        final EditText comentario =  v.findViewById(R.id.comentario);
        final EditText jornada = v.findViewById(R.id.jornada);

        guardarQuiniela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] campos = compruebaUsuarios();

                String contenidoQuiniela = escribirQuiniela();

                String email = campos[2];

                int j = Integer.parseInt(jornada.getText().toString());
                String c = comentario.getText().toString();

                Quiniela quiniela = new Quiniela(email, contenidoQuiniela, c, j);

                RegistrarQuiniela_BD registrarQuiniela_bd = new RegistrarQuiniela_BD(quiniela);
                registrarQuiniela_bd.execute();

                String valMsg = null;
                try {
                    valMsg = registrarQuiniela_bd.get();

                    if(valMsg.equals("3")){
                        Toast.makeText(getContext(), "Se ha guardado la quiniela correctamente.", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getContext(), "Error al guardar la quiniela.", Toast.LENGTH_LONG).show();
                    }

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }




            }
        });


        return v;
    }

    public String[] compruebaUsuarios(){
        String cadena[] = new String[3];
        String linea = "";

        try {

            FileInputStream fis = getContext().openFileInput("usuarios.txt");
            DataInputStream dis = new DataInputStream(fis);


            linea =dis.readUTF();

            if(linea != null){
                cadena = linea.split(";");
            }

            dis.close();
            fis.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cadena;

    }

    public String escribirQuiniela(){
        String quiniela="";

        //1º Partido
        if(cB1.isChecked()){quiniela= quiniela+1;}else{quiniela= quiniela+"_";}
        if(cB2.isChecked()){quiniela= quiniela+"x";}else{quiniela= quiniela+"_";}
        if(cB3.isChecked()){quiniela= quiniela+2;}else{quiniela= quiniela+"_";}
        quiniela= quiniela+",";
        //2º Partido
        if(cB4.isChecked()){quiniela= quiniela+1;}else{quiniela= quiniela+"_";}
        if(cB5.isChecked()){quiniela= quiniela+"x";}else{quiniela= quiniela+"_";}
        if(cB6.isChecked()){quiniela= quiniela+2;}else{quiniela= quiniela+"_";}
        quiniela= quiniela+",";
        //3º Partido
        if(cB7.isChecked()){quiniela= quiniela+1;}else{quiniela= quiniela+"_";}
        if(cB8.isChecked()){quiniela= quiniela+"x";}else{quiniela= quiniela+"_";}
        if(cB9.isChecked()){quiniela= quiniela+2;}else{quiniela= quiniela+"_";}
        quiniela= quiniela+",";
        //4º Partido
        if(cB10.isChecked()){quiniela= quiniela+1;}else{quiniela= quiniela+"_";}
        if(cB11.isChecked()){quiniela= quiniela+"x";}else{quiniela= quiniela+"_";}
        if(cB12.isChecked()){quiniela= quiniela+2;}else{quiniela= quiniela+"_";}
        quiniela= quiniela+",";
        //5º Partido
        if(cB13.isChecked()){quiniela= quiniela+1;}else{quiniela= quiniela+"_";}
        if(cB14.isChecked()){quiniela= quiniela+"x";}else{quiniela= quiniela+"_";}
        if(cB15.isChecked()){quiniela= quiniela+2;}else{quiniela= quiniela+"_";}
        quiniela= quiniela+",";
        //6º Partido
        if(cB16.isChecked()){quiniela= quiniela+1;}else{quiniela= quiniela+"_";}
        if(cB17.isChecked()){quiniela= quiniela+"x";}else{quiniela= quiniela+"_";}
        if(cB18.isChecked()){quiniela= quiniela+2;}else{quiniela= quiniela+"_";}
        quiniela= quiniela+",";
        //7º Partido
        if(cB19.isChecked()){quiniela= quiniela+1;}else{quiniela= quiniela+"_";}
        if(cB20.isChecked()){quiniela= quiniela+"x";}else{quiniela= quiniela+"_";}
        if(cB21.isChecked()){quiniela= quiniela+2;}else{quiniela= quiniela+"_";}
        quiniela= quiniela+",";
        //8º Partido
        if(cB22.isChecked()){quiniela= quiniela+1;}else{quiniela= quiniela+"_";}
        if(cB23.isChecked()){quiniela= quiniela+"x";}else{quiniela= quiniela+"_";}
        if(cB24.isChecked()){quiniela= quiniela+2;} else{quiniela= quiniela+"_";}
        quiniela= quiniela+",";
        //9º Partido
        if(cB25.isChecked()){quiniela= quiniela+1;}else{quiniela= quiniela+"_";}
        if(cB26.isChecked()){quiniela= quiniela+"x";}else{quiniela= quiniela+"_";}
        if(cB27.isChecked()){quiniela= quiniela+2;}else{quiniela= quiniela+"_";}
        quiniela= quiniela+",";
        //10º Partido
        if(cB28.isChecked()){quiniela= quiniela+1;}else{quiniela= quiniela+"_";}
        if(cB29.isChecked()){quiniela= quiniela+"x";}else{quiniela= quiniela+"_";}
        if(cB30.isChecked()){quiniela= quiniela+2;}else{quiniela= quiniela+"_";}
        quiniela= quiniela+",";
        //11º Partido
        if(cB31.isChecked()){quiniela= quiniela+1;}else{quiniela= quiniela+"_";}
        if(cB32.isChecked()){quiniela= quiniela+"x";}else{quiniela= quiniela+"_";}
        if(cB33.isChecked()){quiniela= quiniela+2;}else{quiniela= quiniela+"_";}
        quiniela= quiniela+",";
        //12º Partido
        if(cB34.isChecked()){quiniela= quiniela+1;}else{quiniela= quiniela+"_";}
        if(cB35.isChecked()){quiniela= quiniela+"x";}else{quiniela= quiniela+"_";}
        if(cB36.isChecked()){quiniela= quiniela+2;}else{quiniela= quiniela+"_";}
        quiniela= quiniela+",";
        //13º Partido
        if(cB37.isChecked()){quiniela= quiniela+1;}else{quiniela= quiniela+"_";}
        if(cB38.isChecked()){quiniela= quiniela+"x";}else{quiniela= quiniela+"_";}
        if(cB39.isChecked()){quiniela= quiniela+2;}else{quiniela= quiniela+"_";}
        //14º Partido
        quiniela= quiniela+",";
        if(cB40.isChecked()){quiniela= quiniela+1;}else{quiniela= quiniela+"_";}
        if(cB41.isChecked()){quiniela= quiniela+"x";}else{quiniela= quiniela+"_";}
        if(cB42.isChecked()){quiniela= quiniela+2;}else{quiniela= quiniela+"_";}
        //15º Partido
        quiniela= quiniela+",";
        if(cB43.isChecked()){quiniela= quiniela+1;}else{quiniela= quiniela+"_";}
        if(cB44.isChecked()){quiniela= quiniela+"x";}else{quiniela= quiniela+"_";}
        if(cB45.isChecked()){quiniela= quiniela+2;}else{quiniela= quiniela+"_";}

        return quiniela;
    }

}
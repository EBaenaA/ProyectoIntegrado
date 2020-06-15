package com.nprofesiones.dam.pmdm.teamquiniela;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaQuinielaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaQuinielaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListaQuinielaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaQuinielaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaQuinielaFragment newInstance(String param1, String param2) {
        ListaQuinielaFragment fragment = new ListaQuinielaFragment();
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
        View v =  inflater.inflate(R.layout.fragment_lista_quiniela, container, false);

        ArrayAdapter<Quiniela> arrayAdapter;
        ArrayList<Quiniela> listaBasica = new ArrayList<>();
        LinkedList<Quiniela> listaQuiniela = new LinkedList<>();
        LinkedList<Quiniela> recibido;

        ConsultaLista_BD consultaLista_bd = new ConsultaLista_BD();
        consultaLista_bd.execute();



        try {
            recibido = consultaLista_bd.get();
            if(recibido != null){
                //listaBasica.addAll(consultaLista_bd.getLista());
                listaQuiniela.addAll(recibido);

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ListView lista = v.findViewById(R.id.lista_grupos);

        /*for(int i = 0; i < 100; i++){
            listaBasica.add("Item"+i);
        }*/


        arrayAdapter = new ArrayAdapter<Quiniela>(requireActivity(), android.R.layout.simple_dropdown_item_1line, listaQuiniela);
        lista.setAdapter(arrayAdapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Bundle bundle = new Bundle();

                bundle.putSerializable("itemQuiniela", (Serializable) parent.getAdapter().getItem(position));*/

                Intent i = new Intent(getContext(), VistaItemQuiniela.class);
                Quiniela quiniela = (Quiniela) parent.getAdapter().getItem(position);
                i.putExtra("comentario", quiniela.getComentario()+ "");
                i.putExtra("correo", quiniela.getCorreo()+ "");
                i.putExtra("jornada", quiniela.getJornada() + "");
                i.putExtra("quiniela", quiniela.getQuiniela()+ "");
                startActivity(i);
            }
        });

        return v;
    }
}
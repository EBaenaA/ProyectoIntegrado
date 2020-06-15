package com.nprofesiones.dam.pmdm.teamquiniela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class VistaItemQuiniela extends AppCompatActivity {

    TextView autor, quiniela, comentario, jornada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_item_quiniela);

        autor = findViewById(R.id.autorQuiniela);
        quiniela = findViewById(R.id.valorQuiniela);
        comentario = findViewById(R.id.comentarioQuiniela);
        jornada = findViewById(R.id.jornadaQuiniela);

        Intent i = getIntent();


        autor.setText(i.getStringExtra("correo"));
        quiniela.setText(i.getStringExtra("quiniela"));
        comentario.setText(i.getStringExtra("comentario"));
        jornada.setText(i.getStringExtra("jornada"));


    }
}
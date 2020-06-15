package com.nprofesiones.dam.pmdm.teamquiniela;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class RegistrarUsuario extends AppCompatActivity {

    private EditText nombre, email, contrasenia;
    private Button botonRegistrarse;

    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        nombre = (EditText) findViewById(R.id.regUs);
        email = (EditText) findViewById(R.id.regEmail);
        contrasenia = (EditText) findViewById(R.id.regPass);
        botonRegistrarse = (Button) findViewById(R.id.buttonReg);

        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registro(nombre, email, contrasenia);
            }
        });

    }

    public void registro(EditText nombre, EditText email, EditText passw) {
        if (email.getText().toString().equals("") || nombre.getText().toString().equals("") || passw.getText().toString().equals("")) {
            Toast.makeText(this, "Debe rellenar todos los campos obligatorios", Toast.LENGTH_LONG).show();
        } else {
            if (!email.getText().toString().contains("@")) {
                Toast.makeText(this, "El campo email debe ser valido", Toast.LENGTH_LONG).show();
            } else {
                try {


                    /*Usuario usuario = new Usuario(nombre.getText().toString(), passw.getText().toString(),
                            email.getText().toString());*/

                    RegistrarUsuario_BD registrarUsuarioBD = new RegistrarUsuario_BD(nombre.getText().toString(), passw.getText().toString(),
                            email.getText().toString());
                    registrarUsuarioBD.execute();

                    String valMsg = registrarUsuarioBD.get();

                    if(valMsg.equals("1")){

                        FileOutputStream fos = openFileOutput("usuarios.txt", Context.MODE_PRIVATE);
                        DataOutputStream dos = new DataOutputStream(fos);
                        dos.writeUTF(nombre.getText().toString()+";"+passw.getText().toString()+";"+
                                email.getText().toString());

                        dos.close();
                        fos.close();

                        Toast.makeText(getBaseContext(), "Se ha registrado al usuario correctamente",
                                Toast.LENGTH_SHORT).show();
                   }else{
                        Toast.makeText(getBaseContext(), "ERROR AL CREAR USUARIO",
                                Toast.LENGTH_SHORT).show();
                   }


                } catch (Exception ex) {
                    Log.e("Ficheros", "Error al escribir fichero a memoria interna");
                }
                finish();
            }
        }
    }


}
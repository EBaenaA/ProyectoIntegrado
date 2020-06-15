package com.nprofesiones.dam.pmdm.teamquiniela;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nprofesiones.dam.pmdm.teamquiniela.ui.main.SectionsPagerAdapter;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private EditText email, contrasenia;
    private Button botonIniciarSesion, botonRegistarse;

    String mail="", pass="";

    String campos[] = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.regEmail);
        contrasenia = (EditText) findViewById(R.id.regPass);
        botonIniciarSesion = (Button) findViewById(R.id.button_isesion);
        botonRegistarse = (Button) findViewById(R.id.button_registrarse);

        mail = email.getText().toString();
        pass = contrasenia.getText().toString();

        campos = compruebaUsuarios();

        email.setText(campos[2]);
        contrasenia.setText(campos[1]);

        isStoragePermissionGranted();
        isInternetPermissionGranted();

        botonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals("") || contrasenia.getText().toString().equals("")) {
                    AlertDialog.Builder mensajeOfline = new AlertDialog.Builder(MainActivity.this);
                    mensajeOfline.setTitle("TeamQuiniela infroma:");
                    mensajeOfline.setMessage("Si no inicia sesión accederá a la aplicación sin conexión. ¿Está seguro?");
                    mensajeOfline.setCancelable(false);
                    mensajeOfline.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(MainActivity.this, MenuPrincipal.class);
                            startActivity(i);
                        }
                    });
                    mensajeOfline.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface mensajeOfline, int id) {
                        }
                    });
                    mensajeOfline.show();
                    }else{
                        Usuario usuario = new Usuario(contrasenia.getText().toString(), email.getText().toString());

                        CompruebaUsuario_BD compruebaUsuario_bd = new CompruebaUsuario_BD(usuario);
                        compruebaUsuario_bd.execute();

                        String valMsg = null;
                        try {
                            valMsg = compruebaUsuario_bd.get();

                            if(valMsg.equals("2")){
                                Intent i = new Intent(MainActivity.this, MenuPrincipal.class);
                                startActivity(i);
                            }else{
                                Toast.makeText(MainActivity.this, "Error al iniciar sesión.", Toast.LENGTH_LONG).show();
                            }

                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                }
            }
        });

        botonRegistarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegistrarUsuario.class);
                startActivity(i);
            }
        });


    }

    public String[] compruebaUsuarios(){
        String cadena[] = new String[3];
        String linea = "";

        try {

            FileInputStream fis = openFileInput("usuarios.txt");
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


        /*SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    public  boolean isInternetPermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.INTERNET)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        campos = compruebaUsuarios();

        email.setText(campos[2]);
        contrasenia.setText(campos[1]);

    }

}
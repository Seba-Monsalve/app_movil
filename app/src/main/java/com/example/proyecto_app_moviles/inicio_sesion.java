package com.example.proyecto_app_moviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.List;

import clases.Util;
import codigo.Servicio;
import clases.Usuario;

public class inicio_sesion extends AppCompatActivity {

    EditText et_user;
    EditText et_pass;
    Servicio serv;
    Context con;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        serv = new Servicio(this);
        serv.insertarUsuario(new Usuario("q", "erre", "1-9", "q"));
        Button ingresar = (Button) findViewById(R.id.btn_ingresar);
        Button registrar = (Button) findViewById(R.id.btn_registrar);
        et_user = (EditText) findViewById(R.id.et_user);
        et_pass = (EditText) findViewById(R.id.et_pass);
        con = getApplicationContext();

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag =0;

                List<Usuario> usuarios = serv.getUsuarios();
                for(Usuario u : usuarios){
                    if(et_user.getText().toString().equals(u.getNombre()) && (et_pass.getText().toString().equals(u.getContrasena()))){
                    flag = 1;
                    Intent inicio = new Intent(inicio_sesion.this, inicio.class);
                    startActivity(inicio);}
                }
                if(et_user.length()<5){
                    Util.mostrar(con,"Contraseña menor a 5 caracteres");

                }
                if(flag==0){
                    Util.mostrar(con,"No existe ese usuario registrado");
                }

            }
        });


        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = new Intent(inicio_sesion.this, Registrar_usuario.class);
                startActivity(registrar);
            }
        });


    }


}

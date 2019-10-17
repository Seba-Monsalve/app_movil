package com.example.proyecto_app_moviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import clases.Util;
import codigo.Servicio;
import clases.Usuario;

public class Registrar_usuario extends AppCompatActivity {
    EditText nombre ;
    EditText apellido;
    EditText rut ;
    EditText contrasena ;
    EditText conf_contrasena ;
    Button btn_ingresar ;
    Servicio serv;
    Context con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        nombre = (EditText) findViewById(R.id.et_nombre_user);
        apellido = (EditText) findViewById(R.id.et_apellido_user);
        rut = (EditText) findViewById(R.id.et_rut_user);
        contrasena = (EditText) findViewById(R.id.et_contrasena_user);
        conf_contrasena = (EditText) findViewById(R.id.et_conf_contrasena_user);
        btn_ingresar = (Button) findViewById(R.id.btn_ingresar);
        serv = new Servicio(this);
        con = getApplicationContext();

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (conf_contrasena.getText().toString().isEmpty() || contrasena.getText().toString().isEmpty() ){
                Util.mostrar(con, "La contraseña no puede ser nula");
                }

                //REDUNDANCIA EN SU MAXIMA EXPRESION AJAJ
                else if(!contrasena.getText().toString().equals(conf_contrasena.getText().toString())){
                    Util.mostrar(con, "Las contraseñas no coinciden");
                }

                else if(contrasena.getText().toString().equals(conf_contrasena.getText().toString())){

                serv.insertarUsuario(new Usuario(nombre.getText().toString(),apellido.getText().toString(),rut.getText().toString(),contrasena.getText().toString()));

                Intent inicio = new Intent(Registrar_usuario.this, inicio.class);
                startActivity(inicio);
            }



            }
        });
    }
}

package com.example.proyecto_app_moviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import codigo.Servicio;

public class inicio extends AppCompatActivity {
    Servicio serv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        serv = new Servicio(this);


        Button btn_carrito = (Button) findViewById(R.id.btn_carrito);
        Button btn_productos= (Button) findViewById(R.id.btn_productos);
        Button btn_ver= (Button) findViewById(R.id.btn_ver);


        btn_carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inicio.this, carrito.class);
                startActivity(intent);
            }
        });

        btn_productos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inicio.this, productos.class);
                startActivity(intent);
            }
        });

        btn_ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inicio.this, Ver_Usuarios.class);
                startActivity(intent);
            }
        });

    }
}

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


        Button btn_stock = (Button) findViewById(R.id.btn_stock);
        Button btn_productos= (Button) findViewById(R.id.btn_productos);
        Button btn_ver= (Button) findViewById(R.id.btn_ver);
        Button btn_agregar_producto= (Button) findViewById(R.id.btn_agregar_producto);



        btn_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inicio.this, agregar_productos_tienda.class);
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

        btn_agregar_producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inicio.this, agregar_producto_carrito.class);
                startActivity(intent);
            }
        });


    }
}

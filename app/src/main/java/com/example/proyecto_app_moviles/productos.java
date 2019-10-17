package com.example.proyecto_app_moviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class productos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        Button btn_agregar = (Button) findViewById(R.id.btn_agregar);
        Button btn_seccion1 = (Button) findViewById(R.id.btn_seccion1);

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(productos.this, agregar_productos_tienda.class);
                startActivity(intent);
            }
        });

        btn_seccion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(productos.this, ver_producto.class);
                startActivity(intent);
            }
        });


    }
}

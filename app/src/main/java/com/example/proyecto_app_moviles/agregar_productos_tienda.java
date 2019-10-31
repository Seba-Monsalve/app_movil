package com.example.proyecto_app_moviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.nio.file.Files;

import clases.Util;

import static clases.Util.getPHP;
import static clases.Util.jProductos;

public class agregar_productos_tienda extends AppCompatActivity {



    Spinner spn_categoria;
    Spinner spn_proveedor;
    Spinner spn_nombre;

    TextView prueba ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_productos_tienda);



        spn_categoria = findViewById(R.id.spn_categoria);
        spn_proveedor = findViewById(R.id.spn_proveedor);
        spn_nombre = findViewById(R.id.spn_nombre);
        prueba = findViewById(R.id.prueba);



        spn_categoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Log.d("POÑO","ACA BRO");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Util.getPHP(this,spn_nombre, "producto");
        Util.getPHP(this, spn_categoria, "categoria");
        Util.getPHP(this, spn_proveedor, "proveedor");


    }
}

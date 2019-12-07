package com.example.proyecto_app_moviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import clases.Util;

import static clases.Util.getPHP;

public class agregar_productos_tienda extends AppCompatActivity {



    Spinner spn_categoria;
    Spinner spn_proveedor;
    Spinner spn_producto;
    Button btn_aceptar;
    TextView prueba ;
    EditText et_precio;
    EditText et_cantidad;

    String valor_producto_spinner;
    int stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_productos_tienda);

       final Context cont = getApplicationContext();



        spn_categoria = findViewById(R.id.spn_categoria);
        spn_producto = findViewById(R.id.spn_producto);
        prueba = findViewById(R.id.prueba);

        btn_aceptar = findViewById(R.id.btn_aceptar);

        et_precio = findViewById(R.id.et_precio);
        et_cantidad = findViewById(R.id.et_cantidad);





        Util.getPHP(this, spn_categoria, "categoria","","");

        final String producto_seleccionado;

        spn_categoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            String select = String.valueOf(spn_categoria.getSelectedItemPosition());
            if (!select.equalsIgnoreCase("0")) {
                Util.getPHP(cont, spn_producto, "producto", "categoria", String.valueOf(spn_categoria.getSelectedItemPosition()));

            } else {
                Util.getPHP(cont, spn_producto, "", "", String.valueOf(spn_categoria.getSelectedItemPosition()));
            }
        }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spn_producto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                valor_producto_spinner = spn_producto.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.postPHP_stock(cont,valor_producto_spinner,et_cantidad.getText().toString(),prueba);
            }
        });

    }



}



package com.example.proyecto_app_moviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import clases.Util;

public class agregar_producto_carrito extends AppCompatActivity {

    EditText et_precio;
    EditText et_nombre;
    EditText et_stock;
    Spinner spn_categoria;
    String valor_producto_spinner;
    Button btn_agregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        spn_categoria = findViewById(R.id.spn_categoria);
        btn_agregar = findViewById(R.id.btn_aceptar);

        et_nombre = findViewById(R.id.et_nombre);
        et_precio = findViewById(R.id.et_precio);
        et_stock = findViewById(R.id.et_stock);
        Util.getPHP(this, spn_categoria, "categoria","","");

        spn_categoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                valor_producto_spinner = spn_categoria.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(et_nombre.getText().toString().isEmpty()||et_precio.getText().toString().isEmpty()||et_stock.getText().toString().isEmpty()){
                   Util.mostrar(getApplicationContext(), "Debees llenar todos los campos");
               }
               else{
                   Util.postPHP_agregar_producto(getApplicationContext(),et_nombre.getText().toString(),et_stock.getText().toString(),et_precio.getText().toString(), spn_categoria.getSelectedItemPosition());
               }
            }
        });
    }
}

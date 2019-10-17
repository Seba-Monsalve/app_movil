package com.example.proyecto_app_moviles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import codigo.Servicio;
import clases.Usuario;

public class Ver_Usuarios extends AppCompatActivity {

    private ListView lista;
    private Servicio servicio;
    private Button btn_eliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__usuarios);

        lista = (ListView) findViewById(R.id.lv_usuarios);
        servicio = new Servicio(this);
        btn_eliminar = findViewById(R.id.btn_eliminar);

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicio.eliminarTabla();
                cargarUsuarios(lista);
            }
        });

        cargarUsuarios(lista);



    }
    private void cargarUsuarios(ListView lista){
        ArrayAdapter<Usuario> adapter = new ArrayAdapter<Usuario>(
                this,android.R.layout.simple_list_item_1,servicio.getUsuarios()

        );
        lista.setAdapter(adapter);


    }
}

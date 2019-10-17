package com.example.proyecto_app_moviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import clases.Producto;

public class detalle_producto extends AppCompatActivity {


    // Variables desde bundle
    private String id;
    private String precio;
    private String nombre;
    private String desc;
    private int tipo;

    // ELementos de la vista

    private TextView tv_id;
    private TextView tv_nombre;
    private TextView tv_precio;
    private TextView tv_desc;
    private TextView tv_tipo;
    private ImageView imagen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();



        if (bundle != null) {
            id = String.valueOf(bundle.get("id"));
            precio = (String)bundle.get("precio");
            nombre = (String)bundle.get("nombre");
            desc = (String)bundle.get("desc");
            tipo = (int)bundle.get("tipo");
        }

        try{

            tv_id = findViewById(R.id.tv_id);
            tv_nombre = findViewById(R.id.tv_nombre);
            tv_precio = findViewById(R.id.tx_precio);
            tv_desc = findViewById(R.id.tx_desc);
            tv_tipo = findViewById(R.id.tx_tipo);

            tv_id.setText(id);
            tv_nombre.setText(nombre);
            tv_precio.setText(precio);
            tv_desc.setText(desc);
            tv_tipo.setText(Producto.tipo_producto[tipo]);
            imagen = findViewById(R.id.imagen);


            Log.d("INt", "tipo: " +tipo);

            switch (tipo) {
                case 0:
                    imagen.setImageResource(R.drawable.can);
                    break;
                case 1:
                    imagen.setImageResource(R.drawable.postre);
                    break;
                case 2:
                    imagen.setImageResource(R.drawable.sandwich);
                    break;
                case 3:
                    imagen.setImageResource(R.drawable.comida);
                    break;
                default:
                    imagen.setImageResource(R.drawable.ic_launcher_foreground);
                    break;
            }





        }catch (Exception e){}


    }
}

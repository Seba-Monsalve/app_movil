package com.example.proyecto_app_moviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import clases.Producto;

public class carrito extends AppCompatActivity {


    private ArrayList<Producto> lista_producto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        lista_producto  = new ArrayList<Producto>();


        AdaptadorProducto adaptador = new AdaptadorProducto(this);

        ListView lv = findViewById(R.id.lv_producto);
        lv.setAdapter(adaptador);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(carrito.this,detalle_producto.class);
                intent.putExtra("id", String.valueOf(lista_producto.get(position).getId()));
                intent.putExtra("nombre", lista_producto.get(position).getNombre());
                intent.putExtra("precio", String.valueOf(lista_producto.get(position).getStock()));

                startActivity(intent);
            }
        });



    }


    class AdaptadorProducto extends ArrayAdapter<Producto> {

        AppCompatActivity appCompatActivity;

        AdaptadorProducto(AppCompatActivity context) {
            super(context, R.layout.producto, lista_producto);
            appCompatActivity = context;
        }

        public View getView(int pos, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.producto, null);

            TextView nombre = item.findViewById(R.id.nombre_producto);
            TextView precio = item.findViewById(R.id.precio_producto);
            ImageView imagen = item.findViewById(R.id.imagen);


            // OJO CON EL PARAMETRO DE INT A STRING!!
            nombre.setText(String.valueOf(lista_producto.get(pos).getNombre()));
            precio.setText(String.valueOf(lista_producto.get(pos).getStock()));


            return item;
        }
    }
}





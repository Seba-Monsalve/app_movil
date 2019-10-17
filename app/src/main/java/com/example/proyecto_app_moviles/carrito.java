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

        lista_producto.add(new Producto(1, "Fanta", 1500, "Toma el sabor", 0));
        lista_producto.add(new Producto(2, "Mousse", 22222, "222222r", 1));
        lista_producto.add(new Producto(3, "Palta mayo", 153333300, "333333 el sabor", 2));
        lista_producto.add(new Producto(4, "PIzza", 44444, "T44444oma el sabor", 3));
        lista_producto.add(new Producto(5, "Coca-cola", 5555, "Tom55555a el sabor", 0));
        lista_producto.add(new Producto(6, "Flan", 66666, "Toma el s66666abor", 1));
        lista_producto.add(new Producto(7, "Churrasco", 777, "Toma el s77777abor", 2));
        lista_producto.add(new Producto(8, "Pure con huevo", 888, "Toma e8888l sabor", 3));

        AdaptadorProducto adaptador = new AdaptadorProducto(this);

        ListView lv = findViewById(R.id.lv_producto);
        lv.setAdapter(adaptador);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(carrito.this,detalle_producto.class);
                intent.putExtra("id", String.valueOf(lista_producto.get(position).getId()));
                intent.putExtra("nombre", lista_producto.get(position).getNombre());
                intent.putExtra("precio", String.valueOf(lista_producto.get(position).getPrecio()));
                intent.putExtra("desc", lista_producto.get(position).getDescrp());
                intent.putExtra("tipo", lista_producto.get(position).getTipo());
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
            precio.setText(String.valueOf(lista_producto.get(pos).getPrecio()));

            switch (lista_producto.get(pos).getTipo()) {
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
            return item;
        }
    }
}





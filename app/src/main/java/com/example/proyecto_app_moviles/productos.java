package com.example.proyecto_app_moviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BlendMode;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import clases.Producto;
import clases.Usuario;
import clases.Util;
import codigo.Servicio;

import static androidx.core.content.ContextCompat.getSystemService;

public class productos extends AppCompatActivity {


    final ArrayList<Producto> datos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_productos);


        final ListView lv = findViewById(R.id.lv_categorias);
        getPhpProductos(getApplicationContext(), lv, this);

    }

        void getPhpProductos(final Context cont,final ListView lv,final AppCompatActivity a) {

            String url = Util.ip +"server/conexion.php?peticion=producto_";
            RequestQueue queue = Volley.newRequestQueue(cont);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray jarr = new JSONArray(response);
                                datos.clear();
                                for (int i = 0; i < jarr.length(); i++) {
                                    JSONObject job = jarr.getJSONObject(i);
                                    datos.add( new Producto(job.optInt("id"),job.optString("nombre"),job.optInt("stock"),job.optInt("precio")));



                                }

                                AdaptadorProd arr = new AdaptadorProd( a,datos,cont);
                                lv.setAdapter(arr);


                            } catch (JSONException ex) {
                                Util.mostrar(cont,"Error al parsear el JSON");
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Util.mostrar(cont,"Error: "+error.toString());

                }
            });
            queue.add(stringRequest);
        }

     class AdaptadorProd extends ArrayAdapter<Producto> {

            AppCompatActivity appCompatActivity;
            Context cont;

         AdaptadorProd(AppCompatActivity context, final ArrayList<Producto> prod, Context cont) {
                super(context, R.layout.producto, prod);
              cont = cont;
                appCompatActivity = context;
            }

            public View getView(final int pos, View convertView, ViewGroup parent) {
                LayoutInflater inflater = appCompatActivity.getLayoutInflater();
                View item = inflater.inflate(R.layout.producto, null);

                TextView nombre = item.findViewById(R.id.nombre_producto);
                nombre.setText(datos.get(pos).toString());


                item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonShowPopupWindowClick(v,pos);
                    }
                });



                return item;
            }
            public void onButtonShowPopupWindowClick(View view,final int pos) {
                final Servicio serv = new Servicio(cont);
                // inflate the layout of the popup window
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View popupView = inflater.inflate(R.layout.modificar_prod, null);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                // dismiss the popup window when touched


                Button btn_aceptar_pop = popupView.findViewById(R.id.btn_aceptar);

                btn_aceptar_pop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {


                            EditText pass1 = popupView.findViewById(R.id.et_nombre);
                            EditText pass2 = popupView.findViewById(R.id.et_precio);

                            final String url = Util.ip +"server/conexion.php?peticion=update_producto&id_producto="+datos.get(pos).getId()+"&nombre_producto="+pass1.getText().toString()+"&precio_producto="+pass2.getText().toString();
                            // Request a string response from the provided URL.
                            //Util.mostrar(cont,url);
                            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Util.mostrar(getContext(),response);
                                            getPhpProductos(getContext(),(ListView) findViewById(R.id.lv_categorias),appCompatActivity);

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Util.mostrar(getContext(),"Error: "+ error.getMessage());
                                }
                            });
                            queue.add(stringRequest);
                                popupWindow.dismiss();
                        }
                        catch (Exception e){
                            Util.mostrar(getContext(),"lkError: "+e.getMessage() );
                        }
                    }
                });


            }

        }







    }
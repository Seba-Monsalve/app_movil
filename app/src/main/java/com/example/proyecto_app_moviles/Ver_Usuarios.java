package com.example.proyecto_app_moviles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;


import java.util.ArrayList;

import clases.Util;
import codigo.Servicio;
import clases.Usuario;

public class Ver_Usuarios extends AppCompatActivity {

    private ArrayList<Usuario> lista_usuario;
    private Servicio     servicio = new Servicio(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__usuarios);
        refrescarLista();







    }


    class AdaptadorUsuario extends ArrayAdapter<Usuario> {

        AppCompatActivity appCompatActivity;

        AdaptadorUsuario(AppCompatActivity context) {
            super(context, R.layout.usuarios,lista_usuario );
            appCompatActivity = context;
        }

        public View getView(final int pos, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.usuarios, null);

            TextView nombre = item.findViewById(R.id.tv_username);
            Button btn_modificar = item.findViewById(R.id.btn_modUser);
            btn_modificar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonShowPopupWindowClick(v,pos);
                }
            });
            Button btn_eliminar = item.findViewById(R.id.btn_delUser);
            btn_eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                    servicio.eliminar("usuario",lista_usuario.get(pos).getId());
                    Util.mostrar(appCompatActivity,"Usuario eliminado");
                    refrescarLista();}
                    catch (Exception e){
                        Util.mostrar(appCompatActivity,"Error: "+e.getMessage() );
                    }
                }
            });


            // OJO CON EL PARAMETRO DE INT A STRING!!
            nombre.setText(servicio.getUsuarios().get(pos).toString());
            return item;
        }
    }
    public void onButtonShowPopupWindowClick(View view,final int pos) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.modificar_user_popup, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                popupWindow.dismiss();
                return true;
            }
        });
        Button btn_aceptar_pop = popupView.findViewById(R.id.btn_aceptar);
        btn_aceptar_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EditText pass1 = popupView.findViewById(R.id.et_contra1);
                    EditText pass2 = popupView.findViewById(R.id.et_contra2);
                    if(Util.verificar_pass(pass1, pass2, getApplicationContext())){
                        servicio.modificarPass(lista_usuario.get(pos).getId(),pass1.getText().toString());
                        Util.mostrar(getApplicationContext(),"Contraseña modificada" );
                        refrescarLista();
                        popupWindow.dismiss();


                    }
                }
                catch (Exception e){
                    Util.mostrar(getApplicationContext(),"Error: "+e.getMessage() );
                }
            }
        });


    }

    public void refrescarLista(){
        lista_usuario = (ArrayList)servicio.getUsuarios();
        AdaptadorUsuario adaptador =  new AdaptadorUsuario(this);

        ListView lv = findViewById(R.id.lv_usuarios);
        lv.setAdapter(adaptador);


    }
}
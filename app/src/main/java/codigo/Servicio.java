package codigo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import clases.Usuario;
import clases.Util;

public class Servicio {
    private Conexion conex;
    private Context cont ;

    public Servicio(Context context) {
        conex = new Conexion(context, "misdatos", null, 9);
    }

    public void insertarUsuario(Usuario user) {
        SQLiteDatabase db = conex.getWritableDatabase();
        try {
            ContentValues valores = new ContentValues();
            valores.put("nombre", user.getNombre());
            valores.put("contrasena", user.getContrasena());
            valores.put("rut", user.getRut());
            db.insert("usuario", null, valores);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void modificarPass(int id, String pass) {
        SQLiteDatabase db = conex.getWritableDatabase();
        try {
            db.execSQL("update usuario set contrasena='"+pass+"' where id="+id);

        } catch (Exception e) {
            Log.i("ERROR CAMBIAR PASS",e.getMessage());
        } finally {
            db.close();
        }
    }

    public void modificarProducto(String id, String nombre_actual, String precio_value) {

        String url = Util.ip +"server/conexion.php?peticion=update_producto&nombre_producto="+nombre_actual+"&precio="+precio_value;
        // Request a string response from the provided URL.
        //Util.mostrar(cont,url);
        RequestQueue queue = Volley.newRequestQueue(cont);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Util.mostrar(cont,response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Util.mostrar(cont,"Error: " + error.toString());
            }
        });
        queue.add(stringRequest);
    }



    public List<Usuario> getUsuarios() {
        SQLiteDatabase db = conex.getWritableDatabase();
        List<Usuario> lista = new ArrayList<>();
        try {
            Cursor cursor = db.rawQuery("select * from usuario", null);
            while (cursor.moveToNext()) {
                Usuario p = new Usuario(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                        );
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return lista;
    }


        public void eliminar (String tabla, int id) {
            SQLiteDatabase db = conex.getWritableDatabase();
            //db.execSQL("update usuario set habilitado = false  where habilitado is true");
            db.execSQL("delete from "+tabla+" where id="+id);
        }
    public void drop () {
        Util.mostrar(cont,"Funcion no implementada");
    }
}
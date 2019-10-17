package codigo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import clases.Usuario;
import clases.Util;

public class Servicio {
    private Conexion conex;
    private Context cont;

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

        public void eliminarTabla () {
            SQLiteDatabase db = conex.getWritableDatabase();
            //db.execSQL("update usuario set habilitado = false  where habilitado is true");
            db.execSQL("delete from usuario");
        }

    public void drop () {
        Util.mostrar(cont,"Funcion no implementada");

    }
}
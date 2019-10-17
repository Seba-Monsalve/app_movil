package clases;

import android.content.Context;
import android.widget.Toast;

public class Util {


    public static void mostrar(Context context,String msje){
        Toast.makeText(context,msje ,Toast.LENGTH_SHORT).show();
    }
}

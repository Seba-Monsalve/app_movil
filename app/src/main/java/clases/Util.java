package clases;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;


import java.util.ArrayList;

public class Util {

    final static String ip = "http://192.168.0.6/";

    public static final JSONArray jProductos = new JSONArray();


    public static void mostrar(Context context, String msje) {
        Toast.makeText(context, msje, Toast.LENGTH_SHORT).show();
    }


    public static boolean validarRut(String rut) {

        boolean validacion = false;
        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {

        }

        return validacion;
    }


    public static void getPHP(Context cont, final TextView textView, String req) {
        String url = ip +"server/conexion.php?peticion=" + req;
        // Request a string response from the provided URL.
        RequestQueue queue = Volley.newRequestQueue(cont);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView.setText("Response is: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);
    }

    public static void getPHP(final Context cont, final Spinner spinner, final String req) {
        String url = ip+"server/conexion.php?peticion="+req;
        // Request a string response from the provided URL.
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(cont);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<String> datos = new ArrayList<>();
                        datos.add("Seleccione una opcion");
                        try {
                            JSONArray jarr = new JSONArray(response);

                            for (int i = 0; i < jarr.length(); i++) {
                                JSONObject job = jarr.getJSONObject(i);
                                String v1 = job.optString("id");
                                String v2 = job.optString(req);
                                datos.add(v1 + ".- " + v2);
                            }


                            ArrayAdapter<String> adapter =
                                    new ArrayAdapter<String>(cont, android.R.layout.simple_spinner_dropdown_item, datos);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(adapter);
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }}

                , new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }

        });
        queue.add(stringRequest);

    }


    public static JSONArray getString(Context cont, String req) throws JSONException {
        String url = ip +"server/conexion.php?peticion=" + req;
        final String [] dato = new String[20];
        // Request a string response from the provided URL.
        RequestQueue queue = Volley.newRequestQueue(cont);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                       dato[0] =  response;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
        Log.d("AQUI","error en el volley gestring");
            }
        });
        queue.add(stringRequest);
        return new JSONArray(dato[0]);
    }

}
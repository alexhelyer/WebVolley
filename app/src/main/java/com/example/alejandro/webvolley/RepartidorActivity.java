package com.example.alejandro.webvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.alejandro.webvolley.dto.Repartidor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RepartidorActivity extends AppCompatActivity {

    //Arreglo de repartidores
    List<Repartidor> arrayRepartidores;

    //Adapter
    ListView listaRepartidores;
    RepartidorAdapter repartidorAdapter;

    public static final String IP = "192.168.1.29";
    String miURL;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repartidor);
        setTitle("Repartidores");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Inicializamos valores
        miURL = "http://"+IP+":8080/repartidor/repartidores";
        arrayRepartidores = new ArrayList<>();
        listaRepartidores = findViewById(R.id.listaRepartidores);
        repartidorAdapter = new RepartidorAdapter(this, R.layout.repartidor_row, arrayRepartidores);

        listaRepartidores.setAdapter(repartidorAdapter);

        //Añadimos datos;
        setRepartidores();
    }

    public void setRepartidores() {

        JsonArrayRequest request = new JsonArrayRequest(miURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    arrayRepartidores.clear();
                    for (int i=0; i<response.length(); i++) {
                        JSONObject repartidor = response.getJSONObject(i);

                        Long id = repartidor.getLong("id");
                        String nombre = repartidor.getString("nombre");
                        String placas = repartidor.getString("placas");
                        String direccion = repartidor.getString("direccion");

                        arrayRepartidores.add(new Repartidor(id,nombre,placas,direccion));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                repartidorAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(request);

    }
}

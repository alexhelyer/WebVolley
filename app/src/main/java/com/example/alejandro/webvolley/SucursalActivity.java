package com.example.alejandro.webvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.alejandro.webvolley.dto.Cliente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SucursalActivity extends AppCompatActivity {

    //Arreglo de datos
    List<Cliente> arraySucursales;

    //Adapter
    ListView listaSucursales;
    ClienteAdapter sucursalesAdapter;

    public static final String IP = "192.168.1.29";
    String miURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursal);
        setTitle("Sucursales");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Inicializamos las variables
        miURL = "http://"+IP+":8080/sucursal/sucursales";
        arraySucursales = new ArrayList<>();
        listaSucursales = findViewById(R.id.listaSucursales);
        sucursalesAdapter = new ClienteAdapter(this, R.layout.cliente_row, arraySucursales);

        listaSucursales.setAdapter(sucursalesAdapter);



        //Añadir Datos
        setSucursales();

    }

    public void setSucursales() {

        JsonArrayRequest request = new JsonArrayRequest(miURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    arraySucursales.clear();
                    for (int i=0; i<response.length(); i++) {
                        JSONObject sucursal = response.getJSONObject(i);

                        Long id = sucursal.getLong("id");
                        String nombre = sucursal.getString("nombre");
                        String direccion = sucursal.getString("direccion");

                        arraySucursales.add(new Cliente(id, nombre, direccion));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                sucursalesAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(request);

    }


}

package com.example.alejandro.webvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alejandro.webvolley.dto.Cliente;
import com.example.alejandro.webvolley.dto.Jugo;
import com.example.alejandro.webvolley.dto.Orden;
import com.google.gson.Gson;

import org.json.JSONObject;

public class OrdenActivity extends AppCompatActivity {

    TextView printOrden;
    TextView printCliente;
    TextView printJugos;
    TextView printSucursal;
    TextView printRepartidor;
    TextView printCobro;
    TextView printPago;
    TextView printEstatus;

    public static final String IP = "192.168.1.29";

    //Variable id
    private Long idOrden;

    Cliente clienteReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden);

        printOrden = findViewById(R.id.ordenOrden);
        printCliente = findViewById(R.id.ordenCliente);
        printJugos = findViewById(R.id.ordenJugos);
        printSucursal = findViewById(R.id.ordenSucursal);
        printRepartidor = findViewById(R.id.ordenRepartidor);
        printCobro = findViewById(R.id.ordenCobro);
        printPago = findViewById(R.id.ordenPago);
        printEstatus = findViewById(R.id.ordenEstatus);

        //Obtenemos el objeto cliente
        idOrden = 0l;
        clienteReceiver = (Cliente) getIntent().getSerializableExtra("ORDEN_KEY");

        Toast.makeText(this, ""+clienteReceiver.getNombre(), Toast.LENGTH_SHORT).show();


        //Paso 1.- Pedir la orden
        pedirOrden(clienteReceiver.getId());

    }

    public void pedirOrden(Long idCliente) {
        String url = "http://"+IP+":8080/sucursal/pedir?idCliente="+idCliente;

        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                Orden miOrden = gson.fromJson(response.toString(), Orden.class);
                idOrden = miOrden.getId();
                //Mostrar Cambios
                getOrdenById(idOrden);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(request);
    }

    public void getOrdenById(Long id) {

        String url = "http://"+IP+":8080/orden/ver?id="+id;

        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                Orden miOrden = gson.fromJson(response.toString(), Orden.class);
                imprimir(miOrden);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(request);

    }

    public void imprimir(Orden orden) {

        printOrden.setText("Id: "+orden.getId());

        if (orden.getCliente()==null) {
            printCliente.setText("NULL");
        } else {
            printCliente.setText("Id: " + orden.getCliente().getId()
                    + "   Nombre:" + orden.getCliente().getNombre()
                    + "   Direccion:"+ orden.getCliente().getDireccion());
        }

        if (orden.getSucursal()==null) {
            printSucursal.setText("NULL");
        } else {
            printSucursal.setText("Id: " + orden.getSucursal().getId()
                    + "   Nombre:" + orden.getSucursal().getNombre()
                    + "   Direccion:" + orden.getSucursal().getDireccion());
        }

        if (orden.getRepartidor()==null) {
            printRepartidor.setText("NULL");
        } else {
            printRepartidor.setText("Id:" + orden.getRepartidor().getId()
                    + "   Nombre:" + orden.getRepartidor().getNombre()
                    + "   Placas:" + orden.getRepartidor().getPlacas()
                    + "   Direccion:" + orden.getRepartidor().getDireccion());
        }

        if(orden.getJugos()==null) {
            printJugos.setText("NULL");
        } else {
            String buffer = "";
            for (Jugo jugo: orden.getJugos()) {
                buffer += "Sabor:" + jugo.getSabor()
                        + "   Tamaño:" + jugo.getTam()
                        + "   Precio" + jugo.getPrecio()
                        + "\n";
            }
            printJugos.setText(buffer);
        }

        if(orden.getCobro()==null) {
            printCobro.setText("NULL");
        } else {
            printCobro.setText("Id:" + orden.getCobro().getId()
                    + "   Total" + orden.getCobro().getTotal()
                    + "   Token:" + orden.getCobro().getToken_paypal()
                    + "   Completado" + orden.getCobro().getCompletado());
        }

        if(orden.getPago()==null) {
            printPago.setText("NULL");
        } else {
            printPago.setText("Id:" + orden.getPago().getId()
                    + "  Token:" + orden.getPago().getToken_paypal()
                    + "  Completado:" + orden.getPago().getCompletado());
        }

        if(orden.getEstatus()==null) {
            printEstatus.setText("NULL");
        } else {
            printEstatus.setText("Id:" + orden.getEstatus().getId()
                    + "   Estado:" + orden.getEstatus().getEstado());
        }

    }
}

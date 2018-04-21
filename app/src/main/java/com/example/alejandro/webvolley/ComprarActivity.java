package com.example.alejandro.webvolley;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.alejandro.webvolley.dto.Cliente;
import com.example.alejandro.webvolley.dto.Jugo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ComprarActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView cart;
    TextView count;
    TextView nombre;
    ListView listaJugos;
    Button btnBuy;

    //Adapter
    List<Jugo> arrayJugos;
    JugoAdapter jugoAdapter;

    //Lista de compra
    List<Jugo> arrayCompras;

    //URL
    public static final String IP = "192.168.1.29";
    String miURL;

    Cliente clienteReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar);

        cart = findViewById(R.id.cartButton);
        count = findViewById(R.id.cartCount);
        nombre = findViewById(R.id.cartNombre);
        btnBuy = findViewById(R.id.cartBtnBuy);
        listaJugos = findViewById(R.id.cartList);



        //Inicializar
        miURL = "http://"+IP+":8080/jugo/jugos";
        arrayJugos = new ArrayList<>();
        arrayCompras = new ArrayList<>();
        jugoAdapter = new JugoAdapter(this, R.layout.jugos_row, arrayJugos);
        btnBuy.setOnClickListener(this);

        //Obtenemos el objeto cliente
        clienteReceiver = (Cliente) getIntent().getSerializableExtra("CLI_KEY");

        nombre.setText(clienteReceiver.getNombre());

        listaJugos.setAdapter(jugoAdapter);

        setClientes();
    }

    public void setClientes() {

        JsonArrayRequest request = new JsonArrayRequest(miURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    arrayJugos.clear();
                    for (int i=0; i<response.length(); i++) {
                        JSONObject jugo = response.getJSONObject(i);

                        Long id = jugo.getLong("id");
                        String sabor = jugo.getString("sabor");
                        String tam = jugo.getString("tam");
                        int precio = jugo.getInt("precio");

                        arrayJugos.add(new Jugo(id, sabor, tam, precio));

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                jugoAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(request);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cartBtnBuy:
                Intent intent = new Intent(getApplicationContext(), OrdenActivity.class);
                intent.putExtra("ORDEN_KEY", clienteReceiver);
                startActivity(intent);
                break;
        }
    }


    public class JugoAdapter extends ArrayAdapter<Jugo> {

        List<Jugo> jugos;

        public JugoAdapter(Context context, int resource, List<Jugo> jugos) {
            super(context, resource, jugos);
            this.jugos = jugos;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            final Jugo jugo = jugos.get(position);

            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.jugos_row, parent, false);

            TextView sabor = view.findViewById(R.id.jugoNombre);
            TextView tam = view.findViewById(R.id.jugoTamano);
            TextView precio = view.findViewById(R.id.jugoPrecio);
            Button add = view.findViewById(R.id.jugoAdd);

            sabor.setText(jugo.getSabor());
            tam.setText("("+jugo.getTam()+")");
            precio.setText("$ "+jugo.getPrecio());

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    arrayCompras.add(jugo);
                    count.setText("("+arrayCompras.size()+")");
                    btnBuy.setEnabled(true);
                    btnBuy.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
            });

            return view;
        }
    }

}

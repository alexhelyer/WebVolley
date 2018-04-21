package com.example.alejandro.webvolley;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alejandro.webvolley.dto.Cliente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferenceController sharedPref;


    //Ver clientes
    List<Cliente> arrayClientes;
    //Adapter
    ListView listaClientes;
    ClienteAdapter clienteAdapter;

    LinearLayout layout1;

    public static final String IP = "192.168.1.29";
    String miURL;

    public static final int OPCION_UNO = 1;
    public static final int OPCION_DOS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = new SharedPreferenceController(this);
        //Inicializar variables
        miURL = "http://"+IP+":8080/cliente/clientes";
        arrayClientes = new ArrayList<>();
        layout1 = findViewById(R.id.layout_progress1);
        listaClientes = findViewById(R.id.listaClientes);

        clienteAdapter = new ClienteAdapter(this, R.layout.cliente_row,arrayClientes);

        listaClientes.setAdapter(clienteAdapter);

        //Añadir Datos
        setClientes();

        //String url = sharedPref.getSettingIp();
        //String urlC = url+":8080/cliente/clientes";

        listaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ComprarActivity.class);
                intent.putExtra("CLI_KEY",arrayClientes.get(i));
                startActivity(intent);
            }
        });

        registerForContextMenu(listaClientes);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_addCliente:
                //Toast.makeText(this, "AddCliente", Toast.LENGTH_SHORT).show();
                showAddCliente();
                break;
            case R.id.navigation_showSucursales:
                Intent intent = new Intent(this, SucursalActivity.class);
                startActivity(intent);
                break;
            case R.id.navigation_showRepartidores:
                Intent intent1 = new Intent(this, RepartidorActivity.class);
                startActivity(intent1);
                break;
            case R.id.navigation_setting:
                //Toast.makeText(this, "Ajustes", Toast.LENGTH_SHORT).show();
                showSetting();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(Menu.NONE, OPCION_UNO,Menu.NONE,"Editar");
        menu.add(Menu.NONE, OPCION_DOS,Menu.NONE,"Borrar");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo elemento = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Long id = arrayClientes.get(elemento.position).getId();

        switch (item.getItemId()) {
            case OPCION_UNO:
                Toast.makeText(this, "Touch ID:"+id, Toast.LENGTH_SHORT).show();
                break;
            case OPCION_DOS:
                deleteCliente(id);
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void showSetting() {

        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_setting, null);

        final EditText inputIP = view.findViewById(R.id.inputIP);
        inputIP.setText(sharedPref.getSettingIp());

        new AlertDialog.Builder(this)
                .setView(view)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String getIP = inputIP.getText().toString();
                        Toast.makeText(MainActivity.this, getIP, Toast.LENGTH_SHORT).show();
                        sharedPref.setSettingIp(getIP);
                    }
                })
                .show();
    }

    public void showAddCliente() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_add_cliente, null);

        final EditText inputCliNombre = view.findViewById(R.id.inputClienteNombre);
        final EditText inputCliDireccion = view.findViewById(R.id.inputClienteDireccion);

        new AlertDialog.Builder(this)
                .setTitle("Agregar Cliente")
                .setView(view)
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String txtNombre = inputCliNombre.getText().toString();
                        String txtDireccion = inputCliDireccion.getText().toString();

                        if (!txtNombre.isEmpty() && !txtDireccion.isEmpty()) {
                            addCliente(txtNombre, txtDireccion);
                        } else {
                            Toast.makeText(MainActivity.this, "Algunos campos vacios, no se guardó registro", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .show();
    }

    public void setClientes() {

        JsonArrayRequest request = new JsonArrayRequest(miURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    arrayClientes.clear();
                    for (int i=0; i<response.length(); i++) {
                        JSONObject cliente = response.getJSONObject(i);

                        Long id = cliente.getLong("id");
                        String nombre = cliente.getString("nombre");
                        String direccion = cliente.getString("direccion");

                        arrayClientes.add(new Cliente(id, nombre, direccion));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                clienteAdapter.notifyDataSetChanged();
                layout1.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(request);

    }

    public void addCliente(String nombre, String direccion) {
        String url = "http://"+IP+":8080/cliente/crear?nombre="+nombre+"&direccion="+direccion;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Toast.makeText(MainActivity.this, "Se creo cliente: "+response.getString("nombre"), Toast.LENGTH_SHORT).show();
                    setClientes();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(request);

    }

    public void deleteCliente(Long id) {

        String url = "http://"+IP+":8080/cliente/borrar?id="+id;

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Se borró usuario: "+response, Toast.LENGTH_SHORT).show();
                setClientes();
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

    }
}









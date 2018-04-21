package com.example.alejandro.webvolley;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.alejandro.webvolley.dto.Cliente;

import java.util.List;

/**
 * Created by alejandro on 19/04/18.
 */

public class ClienteAdapter extends ArrayAdapter<Cliente> {


    List<Cliente> clientes;

    public ClienteAdapter(Context context, int layout, List<Cliente> clientes) {
        super(context, layout, clientes);
        this.clientes = clientes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View root = inflater.inflate(R.layout.cliente_row,parent,false);

        TextView nombre = root.findViewById(R.id.clienteNombre);
        TextView direccion = root.findViewById(R.id.clienteDireccion);


        Cliente cliente = clientes.get(position);

        nombre.setText(cliente.getNombre());
        direccion.setText(cliente.getDireccion());

        return root;
    }
}

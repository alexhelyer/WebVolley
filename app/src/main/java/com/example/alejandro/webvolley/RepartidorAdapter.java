package com.example.alejandro.webvolley;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.alejandro.webvolley.dto.Repartidor;

import java.util.List;

/**
 * Created by alejandro on 19/04/18.
 */

public class RepartidorAdapter extends ArrayAdapter<Repartidor> {

    List<Repartidor> repartidores;

    public RepartidorAdapter(Context context, int layout ,List<Repartidor> repartidores) {
        super(context, layout, repartidores);

        this.repartidores = repartidores;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View root = inflater.inflate(R.layout.repartidor_row, parent, false);

        TextView inReNombre = root.findViewById(R.id.repartidorNombre);
        TextView inRePlacas = root.findViewById(R.id.repartidorPlacas);
        TextView inReDireccion = root.findViewById(R.id.repartidorDireccion);

        Repartidor repartidor = repartidores.get(position);

        inReNombre.setText(repartidor.getNombre());
        inRePlacas.setText(repartidor.getPlacas());
        inReDireccion.setText(repartidor.getDireccion());

        return root;
    }
}

package com.example.firebaseprueba;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DIDACT on 26/02/2018.
 */

public class Adaptador extends ArrayAdapter<CJugador> {

    ArrayList<CJugador> jugadores;
    Context c;

    public Adaptador(Context c,ArrayList<CJugador> jugadores){

        super(c, R.layout.item_listview, jugadores);
        this.c =c;
        this.jugadores = jugadores;



    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater= LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.item_listview,null);

        //TextView año
        TextView tv_nombre=(TextView)item.findViewById(R.id.item_nombre);
        tv_nombre.setText(""+jugadores.get(position).getNombre());

        //TextView equipo
        TextView tv_dorsal=(TextView)item.findViewById(R.id.item_dorsal);
        tv_dorsal.setText(jugadores.get(position).getDorsal()+"");

        TextView tv_posicion=(TextView)item.findViewById(R.id.item_posicion);
        tv_posicion.setText(jugadores.get(position).getPosicion());






        return item;
    }
}

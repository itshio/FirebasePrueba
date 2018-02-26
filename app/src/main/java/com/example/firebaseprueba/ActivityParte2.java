package com.example.firebaseprueba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityParte2 extends AppCompatActivity {

    TextView txtlist;
    ListView lvlistview;

    DatabaseReference dbRef;
    ValueEventListener valueEventListener;


    ArrayList<CJugador> lista_jugadores = new ArrayList<CJugador>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parte2);

        txtlist=(TextView)findViewById(R.id.txt_listviewprin);



        lvlistview=(ListView)findViewById(R.id.list_view);

        cargarDatosFirebase();



    }

    private void cargarjugador (){

        lista_jugadores.add(new CJugador("juanin",5,"delantero",15000.35));
        lista_jugadores.add(new CJugador("antoñete",33,"central",5000.89));
        lista_jugadores.add(new CJugador("abdul",1,"portero",55000.35));


    }

    private void cargarListview (DataSnapshot dataSnapshot){


        lista_jugadores.add(dataSnapshot.getValue(CJugador.class));
        Adaptador adaptador_jugadores = new Adaptador(this,lista_jugadores);
        lvlistview.setAdapter(adaptador_jugadores);




        lvlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CJugador cJugador = ((CJugador)parent.getItemAtPosition(position));
                Toast.makeText(getApplicationContext(),"el sueldo es: "+cJugador.getSueldo(),Toast.LENGTH_LONG).show();
            }
        });

    }

    private void cargarDatosFirebase (){

        dbRef = FirebaseDatabase.getInstance().getReference().child("jugadores");

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lista_jugadores.clear();
                for (DataSnapshot jugadoresdatasnapchot: dataSnapshot.getChildren()){
                    cargarListview(jugadoresdatasnapchot);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        dbRef.addValueEventListener(valueEventListener);
    }
}

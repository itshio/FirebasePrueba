package com.example.firebaseprueba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityEjercicioParte1 extends AppCompatActivity {

    Spinner sp_jugadores;
    EditText et_nombre, et_sueldo, et_dorsal, et_posicion;

    private DatabaseReference dbRef;
    private ValueEventListener valueEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_parte1);

        sp_jugadores=(Spinner)findViewById(R.id.spinner);
        et_nombre=(EditText)findViewById(R.id.txt_nombre);
        et_sueldo=(EditText)findViewById(R.id.txt_sueldo);
        et_dorsal=(EditText)findViewById(R.id.txt_dorsal);
        et_posicion=(EditText)findViewById(R.id.txt_posicion);

        String[] spinner_jugadores = {"j1","j2","j3","j4"};

        ArrayAdapter<String> spinneroso = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,spinner_jugadores);

        sp_jugadores.setAdapter(spinneroso);

    }

    public void click_mostrar (View view){

        String spjugadores = sp_jugadores.getSelectedItem().toString();

        dbRef = FirebaseDatabase.getInstance().getReference().child("jugadores/"+spjugadores);

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CJugador spjug = dataSnapshot.getValue(CJugador.class);
                et_nombre.setText("Nombre: "+spjug.getNombre());
                et_sueldo.setText("Sueldo: "+spjug.getSueldo());
                et_dorsal.setText("Dorsal: "+spjug.getDorsal());
                et_posicion.setText("Posicion: "+spjug.getPosicion());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        dbRef.addValueEventListener(valueEventListener);




    }
}

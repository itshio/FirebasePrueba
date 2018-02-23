package com.example.firebaseprueba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityParte1 extends AppCompatActivity {

    TextView txt_parte1;

    //VARIABLES OBJETOS FIREBASE
    private DatabaseReference dbRef;
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parte1);

        txt_parte1=(TextView)findViewById(R.id.parte1_txt);


        //RELLENAR EL DATA REFERENCE
        dbRef = FirebaseDatabase.getInstance().getReference().child("jugadores/j1");

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                CJugador jug = dataSnapshot.getValue(CJugador.class);
                txt_parte1.setText("Nombre: "+jug.getNombre()+"\n"+
                 "Dorsal: "+jug.getDorsal()+"\n"+
                "Posicion: "+jug.getPosicion()+"\n" +
                "Sueldo:    "+jug.getSueldo());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        dbRef.addValueEventListener(valueEventListener);//base de datos tiempo real//

        //dbRef.addListenerForSingleValueEvent(valueEventListener);//solo conecta una vez//

        //dbRef.removeEventListener(valueEventListener);//destruye la conexion//
    }
}

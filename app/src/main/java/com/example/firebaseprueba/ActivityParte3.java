package com.example.firebaseprueba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityParte3 extends AppCompatActivity {

    EditText et_nombre,et_dorsal,et_sueldo,et_posicion;
    Spinner spjugadores;
    private DatabaseReference dbRef;
    private ValueEventListener valueEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parte3);

        et_nombre=(EditText)findViewById(R.id.formu_nombre);
        et_dorsal=(EditText)findViewById(R.id.formu_dorsal);
        et_sueldo=(EditText)findViewById(R.id.formu_sueldo);
        et_posicion=(EditText)findViewById(R.id.formu_posicion);

        spjugadores=(Spinner)findViewById(R.id.formu_spin);

        String [] jugadores = {"selecciona","j1","j2","j3","j4"};
        ArrayAdapter<String> adaptadorjugadores = new ArrayAdapter<String>(this,android.R.layout.
                simple_list_item_1,jugadores);
        spjugadores.setAdapter(adaptadorjugadores);



    }

    public void click_lupa (View view){

        String spjugado= spjugadores.getSelectedItem().toString();

        dbRef= FirebaseDatabase.getInstance().getReference().child("jugadores/"+spjugado);

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CJugador spi_jug = dataSnapshot.getValue(CJugador.class);
                et_nombre.setText("Nombre: "+spi_jug.getNombre());
                et_dorsal.setText("Nombre: "+spi_jug.getDorsal()+"");
                et_posicion.setText("Nombre: "+spi_jug.getPosicion());
                et_sueldo.setText("Nombre: "+spi_jug.getSueldo()+"");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        dbRef.addValueEventListener(valueEventListener);






    }

    public void click_modificar(View view){

    }
    public void click_borrar (View vie ){

        et_nombre.setText("");
        et_dorsal.setText("");
        et_sueldo.setText("");
        et_posicion.setText("");

    }
    public void click_insertar (View view){


        String nombre = et_nombre.getText().toString();
        String dorsal = et_dorsal.getText().toString();
        String sueldo = et_sueldo.getText().toString();
        String posicion = et_posicion.getText().toString();

        if( nombre.equals("")||dorsal.equals("")||sueldo.equals("")||posicion.equals("")){

            Toast.makeText(this,"Debes rellenar todos los campos",Toast.LENGTH_LONG).show();


        }else{


        }


    }
}

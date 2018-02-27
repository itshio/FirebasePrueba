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
                et_nombre.setText(spi_jug.getNombre());
                et_dorsal.setText(spi_jug.getDorsal()+"");
                et_posicion.setText(spi_jug.getPosicion());
                et_sueldo.setText(spi_jug.getSueldo()+"");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        dbRef.addValueEventListener(valueEventListener);






    }

    public void click_modificar(View view){

        String nombre = et_nombre.getText().toString();
        String dorsal = et_dorsal.getText().toString();
        String sueldo = et_sueldo.getText().toString();
        String posicion = et_posicion.getText().toString();
        String idseleccionado= spjugadores.getSelectedItem().toString();

        if( nombre.equals("")||dorsal.equals("")||sueldo.equals("")||posicion.equals("")){

            Toast.makeText(this,"Debes rellenar todos los campos",Toast.LENGTH_LONG).show();


        }else{

            int dorsales = Integer.parseInt(dorsal);
            double sueldos = Double.parseDouble(sueldo);
            CJugador nuevoJugador = new CJugador(nombre,dorsales,posicion,sueldos);
            dbRef = FirebaseDatabase.getInstance().getReference().child("jugadores");

            // STRING NUEVA CLASE

            dbRef.child(idseleccionado).setValue(nuevoJugador, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                    if (databaseError == null){

                        Toast.makeText(getApplicationContext(),"cambiado correctamente",Toast.LENGTH_LONG).show();

                        limpiar();


                    }else{

                        Toast.makeText(getApplicationContext(),"no se pudo modificar",Toast.LENGTH_LONG).show();


                    }

                }
            });





        }

    }
    public void click_borrar (View view ){

        String idseleccionado= spjugadores.getSelectedItem().toString();

        dbRef.child(idseleccionado).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null){

                    Toast.makeText(getApplicationContext(),"eliminado correctamente",Toast.LENGTH_LONG).show();
                    limpiar();

                }else{

                    Toast.makeText(getApplicationContext(),"no se pudo eliminar correctamente",Toast.LENGTH_LONG).show();


                }
            }
        });

        dbRef.addValueEventListener(valueEventListener);



    }
    public void click_insertar (View view){


        String nombre = et_nombre.getText().toString();
        String dorsal = et_dorsal.getText().toString();
        String sueldo = et_sueldo.getText().toString();
        String posicion = et_posicion.getText().toString();

        if( nombre.equals("")||dorsal.equals("")||sueldo.equals("")||posicion.equals("")){

            Toast.makeText(this,"Debes rellenar todos los campos",Toast.LENGTH_LONG).show();


        }else{

            int dorsales = Integer.parseInt(dorsal);
            double sueldos = Double.parseDouble(sueldo);
            CJugador nuevoJugador = new CJugador(nombre,dorsales,posicion,sueldos);
            dbRef = FirebaseDatabase.getInstance().getReference().child("jugadores");

            // STRING NUEVA CLASE

            dbRef.child("j5").setValue(nuevoJugador, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                    if (databaseError == null){

                        Toast.makeText(getApplicationContext(),"agregado correctamente",Toast.LENGTH_LONG).show();

                        limpiar();


                    }else{

                        Toast.makeText(getApplicationContext(),"no se puede agregar",Toast.LENGTH_LONG).show();


                    }

                }
            });





        }


    }

    private void limpiar (){

        et_nombre.setText("");
        et_dorsal.setText("");
        et_sueldo.setText("");
        et_posicion.setText("");


    }
}

package com.example.firebaseprueba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void clcik_uno (View view ){

        Intent form_acti1 = new Intent(getApplicationContext(),ActivityParte1.class);
        startActivity(form_acti1);
    }

    public void click_dos (View view){

        Intent form_acti2 = new Intent(getApplicationContext(),ActivityParte2.class);
        startActivity(form_acti2);
    }

    public void click_ejercicio (View view){

        Intent form_ejercicio = new Intent(getApplicationContext(),ActivityEjercicioParte1.class);
        startActivity(form_ejercicio);

    }

    public void click_parte3 (View view){

        Intent form_tres = new Intent(getApplicationContext(),ActivityParte3.class);
        startActivity(form_tres);

    }
}

package com.example.dm2.examenev2;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class multimedia extends AppCompatActivity {
    private Spinner spn;
    MediaPlayer mpAudio;
    MediaPlayer mpDisparo;
    MediaPlayer mpExplosion;
    Button volver;
    int posicion = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);


        spn = (Spinner) findViewById(R.id.spinner);

        volver = (Button) findViewById(R.id.btnVolver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mpAudio = MediaPlayer.create(this, R.raw.audio);

        mpDisparo = MediaPlayer.create(this, R.raw.disparo);
        mpExplosion = MediaPlayer.create(this, R.raw.explosion);

        //Falta obtener los string por los recursos
        final String[] datos = new String[]{getResources().getString(R.string.audio),
                getResources().getString(R.string.disparo),
                    getResources().getString(R.string.explosion)};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adaptador);


        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int posSpn = spn.getSelectedItemPosition();
                if (posSpn == 0) {

                    mpAudio.start();
                } else if (posSpn == 1) {

                    mpDisparo.start();
                } else {

                    mpExplosion.start();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}

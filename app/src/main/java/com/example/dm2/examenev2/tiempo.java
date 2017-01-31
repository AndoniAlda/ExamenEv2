package com.example.dm2.examenev2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class tiempo extends AppCompatActivity {
    Button btnBil;
    Button btnVit;
    Button btnDon;
    Button volver;
    RssParserDom parserDom;
    TextView hora;
    TextView temp;
    TextView text;
    TextView tuTiemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiempo);
        btnVit = (Button) findViewById(R.id.button6);
        btnBil = (Button) findViewById(R.id.button4);
        btnDon = (Button) findViewById(R.id.button7);
        volver = (Button) findViewById(R.id.button9);
        hora =(TextView) findViewById(R.id.textView5);
        temp =(TextView) findViewById(R.id.textView6);
        text =(TextView) findViewById(R.id.textView7);
        tuTiemp = (TextView ) findViewById(R.id.textView3);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnVit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tuTiemp.setText("Tiempo actual en: Vitoria");
                parserDom = new RssParserDom("http://xml.tutiempo.net/xml/8043");
                List<TiempoJ> tiempos = parserDom.parse();
                TiempoJ tiempo = tiempos.get(0);
                hora.setText("HORA: "+ tiempo.getHora_datos());
                temp.setText("Temperatura: "+ tiempo.getTemperatura() );
                text.setText("Estado del cielo : " +tiempo.getTexto());
            }
        });
        btnBil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tuTiemp.setText("Tiempo actual en: Bilbao");
                parserDom = new RssParserDom("http://xml.tutiempo.net/xml/8050");
                List<TiempoJ> tiempos = parserDom.parse();
                TiempoJ tiempo = tiempos.get(0);
                hora.setText("HORA: "+ tiempo.getHora_datos());
                temp.setText("Temperatura: "+ tiempo.getTemperatura() );
                text.setText("Estado del cielo : " +tiempo.getTexto());
            }
        });
        btnDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tuTiemp.setText("Tiempo actual en: Donosti");
                parserDom = new RssParserDom("http://xml.tutiempo.net/xml/4917");
                List<TiempoJ> tiempos = parserDom.parse();
                TiempoJ tiempo = tiempos.get(0);
                hora.setText("HORA: "+ tiempo.getHora_datos());
                temp.setText("Temperatura: "+ tiempo.getTemperatura() );
                text.setText("Estado del cielo : " +tiempo.getTexto());
            }
        });








    }
}

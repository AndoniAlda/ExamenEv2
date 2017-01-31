package com.example.dm2.examenev2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button tiempo;
    private Button atom;
    private Button multimedia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tiempo = (Button) findViewById(R.id.tiempo);
        atom = (Button) findViewById(R.id.atom);
        multimedia = (Button) findViewById(R.id.multi);


        multimedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, multimedia.class);
                startActivity(intent);
            }
        });

        tiempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, tiempo.class);
                startActivity(intent);
            }
        });
        atom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, atomicos.class);
                startActivity(intent);
            }
        });



    }
}

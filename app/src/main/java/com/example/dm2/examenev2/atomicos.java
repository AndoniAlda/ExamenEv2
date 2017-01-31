package com.example.dm2.examenev2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class atomicos extends AppCompatActivity {
    private Button btnInfo;
    private EditText txtElemento;
    private String str;
    private TextView simbolo;
    private TextView numero;
    private TextView peso;
    private TextView punto;
    private TextView densidad;
    private Button volver;
    private String resultado;
    String an="";
    String sq ="";
    String pa="";
    String pe="";
    String de="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atomicos);
        btnInfo= (Button) findViewById(R.id.btnInfo);
        txtElemento= (EditText) findViewById(R.id.txtelemento);
        str=    txtElemento.getText().toString();
        simbolo= (TextView) findViewById(R.id.lblSimbolo);
        numero= (TextView) findViewById(R.id.lblNumero);
        peso= (TextView) findViewById(R.id.lblPeso);
        punto= (TextView) findViewById(R.id.lblPunto);
        densidad= (TextView) findViewById(R.id.lblDensidad);
        volver = (Button) findViewById(R.id.btnVolverAtom);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String elemento= txtElemento.getText().toString();
                AsyncPost tarea=new AsyncPost();
                tarea.execute(elemento);
                simbolo.setText("Simbolo quimico:");
                numero.setText("Numero atomico:");
                peso.setText("Peso atomico:");
                punto.setText("Punto de Ebullicion:");
                densidad.setText("Densidad:");
            }
        });

    }
    private class AsyncPost extends AsyncTask<String,Void,Void>{
        @Override
        protected Void doInBackground(String... params) {
            try {
                HttpURLConnection conn;
                URL url=new URL("http://www.webservicex.net/periodictable.asmx/GetAtomicNumber");
                String param="ElementName="+ URLEncoder.encode(params[0],"UTF-8");
                conn= (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                PrintWriter out=new PrintWriter((conn.getOutputStream()));
                out.print(param);
                out.close();
                String result="";
                Scanner inStream=new Scanner(conn.getInputStream());


                while(inStream.hasNextLine()){
                    result=inStream.nextLine();
                    if(result.indexOf("AtomicNumber")>0){
                       an =  result.replace("&lt;AtomicNumber&gt;", "").replace("&lt;/AtomicNumber&gt;", "");
                    }
                    if(result.indexOf("Symbol")>0){
                        sq = result.replace("&lt;Symbol&gt;", "").replace("&lt;/Symbol&gt;", "");
                    }
                    if(result.indexOf("AtomicWeight")>0){
                        pa = result.replace("&lt;AtomicWeight&gt;", "").replace("&lt;/AtomicWeight&gt;", "");
                    }
                    if(result.indexOf("BoilingPoint")>0){
                        pe = result.replace("&lt;BoilingPoint&gt;", "").replace("&lt;/BoilingPoint&gt;", "");
                    }
                    if(result.indexOf("Density")>0){
                        de = result.replace("&lt;Density&gt;", "").replace("&lt;/Density&gt;", "");
                    }
                    resultado+=result;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void result)
        {
            simbolo.setText(simbolo.getText().toString()+sq);
            numero.setText(numero.getText().toString()+an);
            peso.setText(peso.getText().toString()+pa);
            punto.setText(punto.getText().toString()+pe);
            densidad.setText(densidad.getText().toString()+de);

        }
    }
}


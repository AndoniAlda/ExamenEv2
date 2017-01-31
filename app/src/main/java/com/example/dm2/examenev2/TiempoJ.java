package com.example.dm2.examenev2;

/**
 * Created by dm2 on 31/01/2017.
 */

public class TiempoJ {
    private String texto;
    private String hora_datos;
    private String temperatura;

    public TiempoJ(){

    }

    public void setTexto(String text){
        this.texto = text;
    }
    public String getTexto(){
        return this.texto;
    }
    public void setHora_datos(String hora){
        this.hora_datos = hora;
    }
    public String getHora_datos(){
        return  this.hora_datos;
    }
    public void setTemperatura(String temperatura){
        this.temperatura = temperatura;
    }

    public String getTemperatura() {
        return temperatura;
    }
}

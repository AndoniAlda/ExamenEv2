package com.example.dm2.examenev2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by dm2 on 31/01/2017.
 */
public class RssParserDom {
    private URL rssUrl;

    public RssParserDom(String url){
        try {
            this.rssUrl=new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<TiempoJ> parse(){
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        ArrayList<TiempoJ> pronosticos=new ArrayList<TiempoJ>();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(this.getInputStream());
            Element root = dom.getDocumentElement();
            NodeList items = root.getElementsByTagName("hora");
            for (int i = 0; i < 1; i++) {
                TiempoJ tiempo = new TiempoJ();
                Node item = items.item(i);
                NodeList datospronostico = item.getChildNodes();
                for (int j = 0; j < datospronostico.getLength(); j++) {
                    Node dato = datospronostico.item(j);
                    String etiqueta = dato.getNodeName();
                    if (etiqueta.equals("hora_datos")) {
                        String texto = obtenerTexto(dato);
                        tiempo.setHora_datos(texto);
                    } else {
                        if (etiqueta.equals("temperatura")) {
                            String texto = obtenerTexto(dato);
                            tiempo.setTemperatura(texto);
                        } else {
                            if (etiqueta.equals("texto")) {
                                String texto = obtenerTexto(dato);
                                tiempo.setTexto(texto);
                            }
                        }
                    }
                }
                pronosticos.add(tiempo);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return pronosticos;
    }

    public String obtenerTexto(Node dato){
        StringBuilder texto=new StringBuilder();
        NodeList fragmentos=dato.getChildNodes();

        for(int k=0;k<fragmentos.getLength();k++)
        {
            texto.append(fragmentos.item(k).getNodeValue());
        }
        return texto.toString();
    }

    public InputStream getInputStream(){
        try {
            return rssUrl.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

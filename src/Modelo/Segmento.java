
package Modelo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.Serializable;

/**
 *
 * @author andre
 */
public class Segmento implements Serializable {
    
    private Coordenadas[] coordenadas;
    private Color color;
    private float grosorLinea;
    private String etiqueta;
    private int longitud;
    private boolean mostrarEtiqueta;
    
    
    public Segmento(Coordenadas[] coordenadas){
        // inicializando variables de instancia
        this.coordenadas = coordenadas;
        grosorLinea = 1f;
        color = Color.black;
        mostrarEtiqueta = true;
    }
    
    public void dibujarSegmento(Graphics g){
        ((Graphics2D)g).setColor(getColor());
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke stroke = new BasicStroke(grosorLinea);
        ((Graphics2D)g).setStroke(stroke);
        
        if(coordenadas != null){
            Coordenadas aux;
            
            for(int i = 0; i < coordenadas.length; i++){
                aux = coordenadas[i];
               
                if(i == 0){
                    ((Graphics2D)g).drawLine(aux.getX(), aux.getY(), aux.getX(), aux.getY());
                }
                else{
                    ((Graphics2D)g).drawLine(coordenadas[i-1].getX(), coordenadas[i-1].getY(), aux.getX(), aux.getY());
                }
            }
            
            if(mostrarEtiqueta){
                ((Graphics2D)g).setColor(Color.BLUE);
                Font fuente=new Font("Monospaced",Font.BOLD, 12);
                g.setFont(fuente);

                if(coordenadas.length == 2){
                    int xMenor = menor(coordenadas[0].getX(),coordenadas[1].getX());
                    int yMenor = menor(coordenadas[0].getY(),coordenadas[1].getY());

                    int xMayor = mayor(coordenadas[0].getX(),coordenadas[1].getX());
                    int yMayor = mayor(coordenadas[0].getY(),coordenadas[1].getY());                    
                    
                    ((Graphics2D)g).drawString(longitud+"",(xMayor + xMenor)/2,(yMayor + yMenor)/2);
                }else{
                    int pos = coordenadas.length / 2;

                    ((Graphics2D)g).drawString(longitud+"",coordenadas[pos].getX()+3,coordenadas[pos].getY()-8);
                }
            }  
        }
        
        stroke = new BasicStroke(1);
        ((Graphics2D)g).setStroke(stroke);   
    }
    
    
    private int mayor(int n1,int n2){
        return (n1 > n2) ? n1 : n2;
    }
    
    private int menor(int n1,int n2){
        return (n1 < n2) ? n1 : n2;
    }

    public Coordenadas[] getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Coordenadas[] coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getGrosorLinea() {
        return grosorLinea;
    }

    public void setGrosorLinea(float grosorLinea) {
        this.grosorLinea = grosorLinea;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public boolean isMostrarEtiqueta() {
        return mostrarEtiqueta;
    }

    public void setMostrarEtiqueta(boolean mostrarEtiqueta) {
        this.mostrarEtiqueta = mostrarEtiqueta;
    }
    
}

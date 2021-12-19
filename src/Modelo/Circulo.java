

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
public class Circulo implements Serializable {
    
    private final Coordenadas coordenadas;
    private int diametro;
    private String etiqueta;
    private Font fuente;
    private int izquierda;
    private Color color;
    
    public Circulo(Coordenadas coordenadas){
        // inicializando variables de instancia
        this.coordenadas = coordenadas;
        color = Color.yellow;
        diametro = 10;
        etiqueta = "";
        fuente = null;
        izquierda = 0;
    }
    
    public void dibujarCirculo(Graphics g){
        
        ((Graphics2D)g).setColor(color);
        ((Graphics2D)g).setStroke(new BasicStroke(2));

        ((Graphics2D)g).fillOval(coordenadas.getX(), coordenadas.getY(), diametro, diametro);
        ((Graphics2D)g).setColor(Color.black);
        ((Graphics2D)g).drawOval(coordenadas.getX(),coordenadas.getY(),diametro,diametro);
        if(!"".equals(etiqueta)){
            if(fuente != null){
                g.setFont(fuente);
            }

            ((Graphics2D)g).drawString(etiqueta,coordenadas.getX() - (izquierda),coordenadas.getY());


            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);
        }
    }
    
    public void setColor(Color color){
        this.color = color;
    }
    
    public Color getColor(){
        return color;
    }
    
    public int getX(){
        return coordenadas.getX();
    }
    
    public int getY(){
        return coordenadas.getY();
    }

    public int getDiametro() {
        return diametro;
    }

    public void setDiametro(int diametro) {
        this.diametro = diametro;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Font getFuente() {
        return fuente;
    }

    public void setFuente(Font fuente) {
        this.fuente = fuente;
    }

    public int getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(int izquierda) {
        this.izquierda = izquierda;
    }
    
}

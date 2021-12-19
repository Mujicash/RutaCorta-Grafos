
package Modelo;

import java.io.Serializable;
import javax.swing.text.Segment;

/**
 *
 * @author andre
 */
public class Arista implements Serializable {
    
    private int idArista;
    private String nombreArista;
    private Segmento segmento;//Ésta es la via representada graficamente    
    private boolean habilitado;
    private int distancia; //Distancia entre dos puntos
    private Vertice destino;
    

    public Arista(){
        // Inicializando variables de instancia
        this(-1,"");
    }
    
    public Arista(int idArista){
        // Inicializando variables de instancia
        this(-1,"");
    }
    
    public Arista(int idArista,String nombreArista){
        // Inicializando variables de instancia
        this(-1, "", 1, null);
    }
    
    public Arista(int idArista, String nombreArista, int peso, Vertice destino) {
        this.idArista = idArista;
        this.nombreArista = nombreArista;
        this.distancia = peso;
        segmento = null;
        habilitado = true;
        this.destino = destino;
    }
    
    public Arista(int peso, Segmento segmento, Vertice destino){
        this.idArista = -1;
        this.nombreArista = "";
        this.distancia = peso;
        //this.segmento = segmento;
        setSegemento(segmento);
        habilitado = true;
        this.destino = destino;
    }

    public void setIdArista(int idArista){
        this.idArista = idArista;
    }
    
    public int getIdArista(){
        return idArista;
    }
    
    public void setNombreArista(String nombreVia){
        this.nombreArista = nombreVia;
    }
    
    public String getNombreArista(){
        return nombreArista;
    }
    
    public int getPeso() {
        return distancia;
    }

    public void setPeso(int peso) {
        this.distancia = peso;
    }
    
    public void setSegemento(Segmento segmento){
        this.segmento = segmento;
        
        if(segmento != null){
            this.segmento.setLongitud(distancia);
        }
    }
    
    public Segmento getSegmento(){
        return segmento;
    }
    
    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    public Vertice getDestino() {
        return destino;
    }
    
}


package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author andre
 */
public class Vertice implements Serializable {
    
    private String nombre;
    private ArrayList<Arista> listaNodoAdyacente;
    private boolean visitado = false;    
    private Circulo circulo;
    private int  izquierda;

    private int longitudCamino; //Para uso de la clase Disjktra
    private Vertice nodoAntecesorDisjktra; //Para uso de la clase Disjktra
    private boolean marca;//Para uso de la clase Disjktra

    public Vertice(){
        this.nombre = "";
        circulo = null;
        izquierda = 0;
        inicializarDijkstra();
    }
    
    public Vertice(String nombre, Coordenadas coordenadas) {
        this.nombre = nombre;
        listaNodoAdyacente = new ArrayList<>();
        circulo = new Circulo(coordenadas);
        circulo.setIzquierda(izquierda);
        inicializarDijkstra();
    }
    
    private void inicializarDijkstra(){
        longitudCamino = -1;
        nodoAntecesorDisjktra = null;
        marca = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public ArrayList<Arista> getListaNodoAdyacente(){
        ArrayList<Arista> listAristaAux = null;
        if(!listaNodoAdyacente.isEmpty()){
            listAristaAux = new ArrayList<Arista>();
            for(Arista enlace:listaNodoAdyacente){
                if(enlace.isHabilitado()){
                    listAristaAux.add(enlace);
                }
            }
        }        
        return listAristaAux;
    }

    public void addNodoAdyacente(Arista arista){
        listaNodoAdyacente.add(arista);
    }
    
    public void setVisitado(boolean visitado){
        this.visitado = visitado;
    }
    
    public boolean isVisitado(){
        return visitado;
    }
    
    public Circulo getCirculo() {
        return circulo;
    }

    public void setCirculo(Circulo circulo) {
        this.circulo = circulo;
    }

    public int getLongitudCamino() {
        return longitudCamino;
    }

    public void setLongitudCamino(int longitudCamino) {
        this.longitudCamino = longitudCamino;
    }

    public boolean isMarca() {
        return marca;
    }

    public void setMarca(boolean marca) {
        this.marca = marca;
    }

    public Vertice getNodoAntecesorDisjktra() {
        return nodoAntecesorDisjktra;
    }

    public void setNodoAntecesorDisjktra(Vertice nodoAntecesorDisjktra) {
        this.nodoAntecesorDisjktra = nodoAntecesorDisjktra;
    }
    
}

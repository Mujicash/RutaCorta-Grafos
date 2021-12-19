
package Modelo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author andre
 */
public class Disjktra {
    
    private Grafo grafo;
    private ArrayList<Vertice> listaVerticesAdyacentes;
    private ArrayList<Arista> aux;
    
    public Disjktra(Grafo grafo){
        this.grafo = grafo;
        listaVerticesAdyacentes = new ArrayList<>();
        aux = new ArrayList<>();
    }
    
    public void calcular(Vertice origen, Vertice destino){
        origen.setLongitudCamino(0);
        
        if(origen != null && destino != null){
            System.out.println("estoy en calcular");
            listaVerticesAdyacentes = new ArrayList<>();
            listaVerticesAdyacentes.add(origen);
            
            while(!listaVerticesAdyacentes.isEmpty()){
                Vertice menor = buscarMenor();
                menor.setMarca(true);
                listaVerticesAdyacentes.remove(menor);
                llenarConAdyacente(menor);
            }
            
            marcarRutaCorta(destino);
        }
    }
    
    private void llenarConAdyacente(Vertice nodo){
        if(nodo != null){
            ArrayList<Arista> listaAux = nodo.getListaNodoAdyacente();
            System.out.println("estoy en llenarConAdyacente");
            if(listaAux != null){
                for(Arista enlace : listaAux){
                    Vertice aux2 = enlace.getDestino();
                    
                    if(!aux2.isMarca()){
                        if(contiene(aux2)){
                            int longitud = nodo.getLongitudCamino() + enlace.getPeso();
                            if(aux2.getLongitudCamino() > longitud){
                                aux2.setLongitudCamino(longitud);
                                aux2.setNodoAntecesorDisjktra(nodo);
                            }
                        }else{
                            aux2.setLongitudCamino(nodo.getLongitudCamino() + enlace.getPeso());
                            aux2.setNodoAntecesorDisjktra(nodo);
                            listaVerticesAdyacentes.add(aux2);
                        }
                    }
                }
            }
        }
    }
    
    private void rutaCorta(Vertice destino){
        aux.clear();
        Vertice nAux = destino;
        System.out.println("rutaCorta");
        while(nAux.getNodoAntecesorDisjktra() != null){
            System.out.println("1");
            aux.add(grafo.getArista(nAux, nAux.getNodoAntecesorDisjktra()));
            nAux = nAux.getNodoAntecesorDisjktra();
        }        
    }
    
    private void marcarRutaCorta(Vertice destino){
        if(destino != null){
            System.out.println("marcarRutacorta");
            rutaCorta(destino);
            for(int i = 0; i < aux.size(); i++){
                System.out.println("for");
                aux.get(i).getSegmento().setColor(Color.red);
                aux.get(i).getSegmento().setGrosorLinea(4);
            }
        }
    }
    
    private Vertice buscarMenor(){
        Vertice auxiliar = new Vertice();
        auxiliar.setLongitudCamino(9999999);
        
        for(Vertice i : listaVerticesAdyacentes){
            if(i.getLongitudCamino() < auxiliar.getLongitudCamino()){
                auxiliar = i;
            }
        }
        
        return auxiliar;
    }
    
    private boolean contiene(Vertice nodo){
        boolean encontrado = false;
        Iterator<Vertice> it = listaVerticesAdyacentes.iterator();
        
        while(it.hasNext() && !encontrado){
            if(it.next() == nodo){
                encontrado = true;
            }
        }
        
        return encontrado;
    }
    
    
}

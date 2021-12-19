
package Modelo;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;


/**
 *
 * @author andre
 */
public class Grafo implements Serializable {
    
    private ArrayList<Vertice> listaNodo;
    private boolean dirigido;

    public Grafo(boolean dirigido){
        listaNodo = new ArrayList<>();
        this.dirigido = dirigido;
    }
    
    public boolean adjuntarNodo(Vertice nodo){
        Vertice nodoTemp = buscarNodo(nodo.getNombre());
        
        if(nodoTemp == null){ //no existe el nodo
            listaNodo.add(nodo);
            return true;
        }else{
            return false;
        }
    }
    
    public void crearEnlaces(Vertice origen, int idArista, String nombreArista, int peso, Vertice destino){
        if(origen != null){
            origen.addNodoAdyacente(new Arista(idArista, nombreArista, peso, destino));
            
            if(!dirigido){ //no dirigido
                destino.addNodoAdyacente(new Arista(idArista, nombreArista, peso, origen));
            }
        }
    }
    
    public void crearEnlaces(Vertice origen, int peso, Segmento segmento, Vertice destino){
        if(origen != null){
            origen.addNodoAdyacente(new Arista(peso, segmento, destino));
            
            if(!dirigido){ //no dirigido
                destino.addNodoAdyacente(new Arista(peso, segmento, origen));
            }
        }
    }
    
    public void crearEnlaces(Vertice origen, Vertice destino, Arista arista){
        if(origen != null){
            arista.setDestino(destino);
            origen.addNodoAdyacente(arista);
            
            if(!dirigido){ //no dirigido
                arista.setDestino(origen);
                destino.addNodoAdyacente(arista);
            }
        }
    }
    
    public Vertice buscarNodo(Object dato){
        Vertice temp = null; 
        
        if(dato != null){
            for(Vertice nodo:listaNodo){
                if(dato.equals(nodo.getNombre())){
                    temp = nodo;              
                }
            }
        }
        
        return temp;
    }
    
    public Vertice buscarNodo(int x, int y){
        Vertice nodoAuxiliar = null;
        
        for(int i = 0; i < listaNodo.size(); i++){
            int xNodo = listaNodo.get(i).getCirculo().getX();
            int yNodo = listaNodo.get(i).getCirculo().getY();
            
            if(x > xNodo && x < (xNodo + listaNodo.get(i).getCirculo().getDiametro())){
                if(y > yNodo && y < (yNodo + listaNodo.get(i).getCirculo().getDiametro())){
                    nodoAuxiliar = listaNodo.get(i);
                    break;
                }
            }
        }
        
        return nodoAuxiliar;
    }
    
    public ArrayList<Vertice> getAdyacentes(Object dato){
        ArrayList<Vertice> lista = null;
        Vertice principal = buscarNodo(dato);
        ArrayList<Arista> aristas = principal.getListaNodoAdyacente();
        if(aristas != null){
            for(int i = 0; i < aristas.size();i++){
                lista.add(aristas.get(i).getDestino());
            }
        }
        return lista;
    }
    
    public ArrayList<Vertice> getListaNodos(){
        return listaNodo;
    }
    
    public boolean isAdyacente(Vertice n1, Vertice n2){
        boolean aux = false;
        ArrayList<Arista> listaAristas = n1.getListaNodoAdyacente();
        if(listaAristas != null){
            for(int i = 0;i < listaAristas.size();i++){
                if(listaAristas.get(i).getDestino() == n2){
                    aux = true;
                }
            }
        }
        return aux;
    }
    
    public boolean isAdyacente(Object datoNodoInicio,Object datoNodoDestino){
        boolean aux = false;
        Vertice n1 = buscarNodo(datoNodoInicio);
        Vertice n2 = buscarNodo(datoNodoDestino);
        ArrayList<Arista> listaAristas = n1.getListaNodoAdyacente();
        if(listaAristas != null){
            for(int i = 0;i < listaAristas.size();i++){
                if(listaAristas.get(i).getDestino() == n2){
                    aux = true;
                }
            }
        }
        return aux;
    }
    
    public Arista getArista(Object datoNodo1,Object datoNodo2){
        return getArista(buscarNodo(datoNodo1),buscarNodo(datoNodo2));
    }
    
    public Arista getArista(String nombreVia){
        Arista aux = null;
        if(nombreVia != null){
            ArrayList<Vertice> listaN = listaNodo;
            for(Vertice nd:listaN){
                ArrayList<Arista> lA = nd.getListaNodoAdyacente();
                for(Arista enlace:lA){
                    if(enlace.getNombreArista().equals(nombreVia)){
                        aux = enlace;
                    }
                }
            }
        }
        return aux;
    }
    
    public Arista getArista(Vertice n1, Vertice n2){
        Arista aux = null;
        if(isAdyacente(n1, n2)){
            ArrayList<Arista> listaAristas = n1.getListaNodoAdyacente();
            for(int i = 0;i < listaAristas.size();i++){
                if(listaAristas.get(i).getDestino() == n2){
                    aux = listaAristas.get(i);
                }
            }
        }else if(isAdyacente(n2, n1)){
            aux = getArista(n2, n1);
        }
        return aux;
    }
    
    public Arista getEnlace(Object datoNodo1, Object datoNodo2){
        Arista aux = null;
        if(isAdyacente(datoNodo1, datoNodo2)){
            Vertice n1 = buscarNodo(datoNodo1);
            Vertice n2 = buscarNodo(datoNodo2);
            ArrayList<Arista> listaAristas = n1.getListaNodoAdyacente();
            for(int i = 0;i < listaAristas.size();i++){
                if(listaAristas.get(i).getDestino()== n2){
                    aux = listaAristas.get(i);
                }
            }
        }
        else if(isAdyacente(datoNodo2, datoNodo1)){
            aux = getEnlace(datoNodo2, datoNodo1);
        }
        return aux;
    }
    
    public void reiniciarGrafoParaDisjktra(){
        for(Vertice n:listaNodo){
            n.setMarca(false);
            n.setNodoAntecesorDisjktra(null);
            n.setLongitudCamino(-1);
        }
    }

    public boolean eliminarNodo(Vertice nodo){
        boolean retornado = false;
        if(nodo != null){
            retornado = listaNodo.remove(nodo);
        }
        return retornado;
    }
    
    public void reiniciarColores() {
        if(listaNodo != null){
            for(Vertice nodo: listaNodo){
                nodo.getCirculo().setColor(Color.yellow);
            ArrayList<Arista> la = nodo.getListaNodoAdyacente();
                if(la != null){
                    for(Arista enlace:la){
                        if(enlace.isHabilitado()){
                            enlace.getSegmento().setColor(Color.black);
                            enlace.getSegmento().setGrosorLinea(1);
                        }
                    }
                }
            }
        }        
    }
    
    public ArrayList<Arista> aristasEntrante(Vertice nodo){
        ArrayList<Arista> listaArista = null;
        for(Vertice n:listaNodo){
            ArrayList<Arista> enlaces = n.getListaNodoAdyacente();
            if(enlaces != null){
                listaArista = new ArrayList<Arista>();
                for(Arista e:enlaces){
                    if(e.getDestino() == nodo){
                        listaArista.add(e);
                    }
                }
            }
        }
        return listaArista;
    }
    
    public ArrayList<Arista> aristasSaliente(Vertice nodo){
        ArrayList<Arista> listaArista = null;
        if(nodo != null){
            if(listaNodo.contains(nodo)){  
                ArrayList<Arista> listaEnlace = nodo.getListaNodoAdyacente();
                if(listaEnlace != null){ //listaArista
                    listaArista = listaEnlace;
                }
            }
        }
        return listaArista;
    }
    
    private void eliminarAristas(Vertice nodo){
        ArrayList<Arista> aristas = aristasSaliente(nodo);        
        for(Arista a:aristas){
            a = null;
        }
    }
    
    public void eliminarAristasSalientes(Vertice nodo){
        ArrayList<Arista> aristas = aristasSaliente(nodo);        
        eliminarAristas(nodo);
    }
    
    public void eliminarAristasEntrante(Vertice nodo){
        ArrayList<Arista> aristas = aristasEntrante(nodo);
        eliminarAristas(nodo);
    }
    
    
}

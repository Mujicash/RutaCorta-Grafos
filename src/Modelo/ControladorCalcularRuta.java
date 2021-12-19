
package Modelo;

import Vista.FrmCalcularRuta;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author andre
 */
public class ControladorCalcularRuta implements ActionListener {
    
    private FrmCalcularRuta vista;
    private Grafo grafo;
    private Vertice origen;
    private Vertice destino;
    
    public ControladorCalcularRuta(FrmCalcularRuta vista, Grafo grafo){
        this.vista = vista;
        this.grafo = grafo;
        origen = null;
        destino = null;
        this.vista.jbtnCalcularRuta.addActionListener(this);
    }
    
    public void iniciar(){
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        dibujarGrafo();
        llenarCampos();
    }
    
    private void llenarCampos(){
        
        for(Vertice i : grafo.getListaNodos()){
            vista.jcbOrigen.addItem(i.getNombre());
            vista.jcbDestino.addItem(i.getNombre());
        }
        
    }
    
    public void dibujarGrafo(){
        vista.jpnMapa.paint(vista.jpnMapa.getGraphics());
        dibujarArista();
        dibujarNodos();
    }
    
    public void dibujarNodos(){
        ArrayList<Vertice> listaNodo = grafo.getListaNodos();
        for(Vertice nodo : listaNodo){            
            nodo.getCirculo().dibujarCirculo(vista.jpnMapa.getGraphics());
        }
    }
    
    public void dibujarArista(){
        ArrayList<Vertice> listaNodo = grafo.getListaNodos();
        for(Vertice nodo : listaNodo){            
            ArrayList<Arista> listaEnlace = nodo.getListaNodoAdyacente();
            if(listaEnlace != null){
                for(Arista enlace : listaEnlace){
                    enlace.getSegmento().dibujarSegmento(vista.jpnMapa.getGraphics());
                }
            }            
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == vista.jbtnCalcularRuta){
            System.out.println("Se disparo el boton");
            String sOrigen, sDestino;
            
            sOrigen = (String) vista.jcbOrigen.getSelectedItem();
            sDestino = (String) vista.jcbDestino.getSelectedItem();
            
            if(origen == null){
                grafo.reiniciarGrafoParaDisjktra();
                grafo.reiniciarColores();
                origen = grafo.buscarNodo(sOrigen);
                System.out.println(origen);
                origen.getCirculo().setColor(Color.red);
                dibujarGrafo();
            }
            
            if(origen != null){
                destino = grafo.buscarNodo(sDestino);
                System.out.println(destino);
                Disjktra disjktra = new Disjktra(grafo);
                disjktra.calcular(origen, destino);

                origen = null;//null para poder crear mas arista
                destino = null;//null para poder crear mas arista       
                dibujarGrafo();
            }
            
        }
        
    }
    
}

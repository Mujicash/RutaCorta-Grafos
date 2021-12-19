
package Modelo;

import Vista.FrmMapa;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author andre
 */
public class ControladorMapa implements MouseListener, ActionListener {
    
    private FrmMapa vista;
    private Grafo grafo;
    private Vertice origen;
    private Vertice destino;
    
    public ControladorMapa(FrmMapa vista){
        this.vista = vista;
        grafo = new Grafo(false); //grafo no dirigido
        origen = null;
        destino = null;        
        vista.jpnMapa.addMouseListener(this);
        vista.jbtnGuardar.addActionListener(this);
        vista.jbtnRetroceder.addActionListener(this);
    }
    
    public void iniciar(){
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
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
    
    private int ingresarPeso(){
        String peso = JOptionPane.showInputDialog("Digite la distancia entre puntos");
        int intPeso = Integer.parseInt(peso);
        
        return intPeso;
    }
    
    private void eliminarNodo(int x,int y){
        if(grafo.buscarNodo(x, y)!=null){//si se hace clic sobre un nodo
            Vertice temp = grafo.buscarNodo(x, y);
            grafo.eliminarAristasEntrante(temp);
            if(grafo.eliminarNodo(temp)){
                JOptionPane.showMessageDialog(null,"Eliminado");
                dibujarGrafo();
            }
            
        }
    }
    
    private void crearArista(){
        int intPeso = ingresarPeso();
        //Arista arista = new Arista();
        //arista.setPeso(intPeso);
        Coordenadas[] c = new Coordenadas[2];
        c[0] = new Coordenadas(origen.getCirculo().getX() + (origen.getCirculo().getDiametro()/2), origen.getCirculo().getY() + (origen.getCirculo().getDiametro()/2));
        c[1] = new Coordenadas(destino.getCirculo().getX() + (origen.getCirculo().getDiametro()/2), destino.getCirculo().getY() + (origen.getCirculo().getDiametro()/2));
        Segmento lq = new Segmento(c);
        //arista.setSegemento(lq);               
        //grafo.crearEnlaces(origen, destino, arista);       
        grafo.crearEnlaces(origen, intPeso, lq, destino);
    }
    
    private void crearNodo(int x, int y){
        Coordenadas c = new Coordenadas(x, y);
        String dato = JOptionPane.showInputDialog("Digite un dato o Nombre de la coordenada");
        if(dato != null){
            dato = dato.toUpperCase();//por que lo quiero todo en mayusculas
            Vertice nodo = new Vertice(dato,c);
            nodo.getCirculo().setDiametro(12);
            nodo.getCirculo().setEtiqueta(nodo.getNombre() + "");
            
            if(grafo.adjuntarNodo(nodo)){ //Se agrego el nodo al grafo
                nodo.getCirculo().dibujarCirculo(vista.jpnMapa.getGraphics());
            }
            
            origen = null;
            destino = null; 
        }
    }

    @Override
    public void mouseClicked(MouseEvent evt) {

        int x = evt.getX();
        int y = evt.getY();
        
        if(!evt.isMetaDown()){ //crear nodos y aristas   
            if(grafo.buscarNodo(x, y)!=null){ //Se hace click sobre un nodo para crear una arista
                if(origen == null){
                    origen = grafo.buscarNodo(x, y);//nodoInicio lo pongo a apuntar al nodo donde hice clic
                    origen.getCirculo().setColor(Color.red);//Lo hago cambiar de color
                    
                }else{//si nodoInicio ya estaba apunto a un nodo, lo preparo para crear arista             
                    destino = grafo.buscarNodo(x, y);                
                    crearArista();            

                    origen.getCirculo().setColor(Color.yellow);//lo regreso a su color original

                    origen = null;//null para poder crear mas arista
                    destino = null;//null para poder crear mas arista
                }
            }else{//Si no he hecho clic sobre un nodo
                crearNodo(x, y);//creo un nodo apartir de unas coordenadas
            }                       
        }
        dibujarGrafo();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == vista.jbtnGuardar){
            
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Mapas.bin"));
                
                oos.writeObject(grafo);
                oos.close();
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControladorMapa.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ControladorMapa.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        if(e.getSource() == vista.jbtnRetroceder){
            
        }

    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }

    
}

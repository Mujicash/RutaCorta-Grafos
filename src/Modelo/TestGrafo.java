
package Modelo;

import Vista.FrmCalcularRuta;
import Vista.FrmMapa;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author andre
 */
public class TestGrafo {
    
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        /*
        FrmMapa vista = new FrmMapa();
        ControladorMapa controlador = new ControladorMapa(vista);
        controlador.iniciar();*/
        
        Grafo grafo;
        
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Mapas.bin"));
        
        grafo = (Grafo) ois.readObject();
        
        FrmCalcularRuta vista = new FrmCalcularRuta();
        ControladorCalcularRuta controlador = new ControladorCalcularRuta(vista, grafo);
        controlador.iniciar();
    }
    
}

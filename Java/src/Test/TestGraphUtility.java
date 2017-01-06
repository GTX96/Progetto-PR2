package Test;

import Grafo.Graph;
import Grafo.GraphUtility;
import MyException.NoVertexFound;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * OVERVIEW = Test per la classe GraphUtility<E>
 * 
 * @author Gionatha Sturba
 */
public class TestGraphUtility 
{
    /*
    public static void main(String[] args) 
    {
        //TEST 1: su grafo orientato
        
        /*
        //creo una lista di stringhe,che saranno vertici del mio grafo.
        List<String> lista = new ArrayList<String>(Arrays.asList("A","B","C","D","F","G"));
        //inizializzo il grafo,orientato,senza cappi
        Graph<String> g = new Graph<String>(true,lista);
        //aggiungo archi,tutti con peso 1
        g.addArco("A", "B");
        g.addArco("B", "C");
        g.addArco("D", "F");
        g.addArco("F", "G");
        g.addArco("G", "C");
        g.addArco("A", "D");
        
        
        g.stampaGrafo();
        
        //test cammino minimo da A a C
        System.out.println(GraphUtility.Dijistrka(g, "A", "C"));
        //test diametro grafo
        System.out.println(GraphUtility.Graph_Diameter(g));
        */
        
        /*
        //TEST 2: su grafo non orientato
        
        
        //creo una lista di stringhe,che saranno vertici del mio grafo.
        List<String> lista = new ArrayList<String>(Arrays.asList("A","B","C","D","F","G"));
        //inizializzo il grafo,orientato,senza cappi
        Graph<String> g = new Graph<String>(false,lista);
        
        //aggiungo archi,con peso diverso.
        g.addArco("A", "B");
        g.addArco("B", "C",5);
        g.addArco("C", "G",7);
        g.addArco("B", "D",4);
        g.addArco("D", "F",3);
        g.addArco("F", "G",2);
        
        g.stampaGrafo();
        
        //testiamo il caso in cui si cercano piu cammini
        System.out.println("Cammino 1: "+GraphUtility.Dijistrka(g, "A", "G"));
        System.out.println("Cammino 2:"+GraphUtility.Dijistrka(g, "B", "F"));
        System.out.println("Cammino 2:"+GraphUtility.Dijistrka(g, "C", "A"));
        System.out.println(GraphUtility.Graph_Diameter(g));
        */
        
        //TEST 3: possibile eccezioni
        
        /*
        
        //creo grafo semplice.
        Graph<Integer> g = new Graph<Integer>(true);
        g.addVertice(1);
        g.addVertice(2);
        
        g.addArco(1,2);
        
        // con un grafo null
        
        try{
            GraphUtility.Dijistrka(null, 1, 2);
        }catch(NullPointerException e){
            System.err.println("Grafo non esistente");
        }
        
        //con vertici non esistenti.
        try{
            System.out.println(GraphUtility.Dijistrka(g, 1, 3));
        }catch(NoVertexFound e){
            System.err.println("Uno o piu vertici non esistenti");
       }
       */
            
        
    }

}

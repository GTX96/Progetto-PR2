package Test;

import Grafo.Graph;
import MyException.NoVertexFound;
import SocialNetwork.Utente;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//NOTA: Eliminare i commenti volta per volta per effetuare i test.

/**
 * OVERVIEW = Test delle funzioni  della classe Graph<E>
 * 
 * DEBUG = Per vedere a video cosa fa effetivamente una funzione,
 *         e' consigliato l'uso della funzione stampaGrafo.
 * 
 * @author Gionatha Sturba
 */
public class TestGrafo1 
{
    
    public static void main(String[] args) 
    {
        /*
        //TEST 1: Creazione grafo
        
        //creo una lista di stringhe,che saranno vertici del mio grafo.
        List<String> lista = new ArrayList<String>(Arrays.asList("A","B","C","D","F","G"));
        //inizializzo il grafo,orientato,senza cappi
        Graph<String> g = new Graph<String>(true,false,lista);
        //aggiungo degli archi,senza peso
        g.addArco("A","B");
        g.addArco("B","C");
        g.addArco("D","C");
        g.addArco("B","D");
        g.addArco("F","A");
        g.addArco("G","F");
        
        */
        
        //TEST 2: aggiungi vertice
        
        /*aggiungo vertice con chiave null 
        try{
            g.addVertice(null);
        }
        catch(NullPointerException e)
        {
            System.err.println("la chiave non puo essere null");
        }
        //aggiungo un vertice gia' presente
        try
        {
            g.addVertice("A");
        }catch(VertexAlreadyExist e){
            System.err.println("Vertice gia' presente");
        }
        */
        
        //TEST 3: addArco
        
        /*
        //aggiungo un arco con vertici non presenti
        try{
            g.addArco("ciao", "miao");
        }catch(NoVertexFound e)
        {
            System.err.println("Uno o piu vertici non presenti");
        }
        */
        
        //TEST 4: removeVertice
        
        /*
        //rimuovo vertice C
        g.removeVertice("C");
        
        //rimuovo un vertice non presente
        try{
            g.removeVertice("ciao");
        }catch(NoVertexFound e)
        {
            System.err.println("Vertice non trovato");
        }
        */
        
        //TEST 5: removeArco
        
        /*
        //rimozione dell'arco A->B
        g.removeArco("A", "B");
        
        //rimozione di vertici non esistenti
        try{
            g.removeArco("A", "ciao");
        }catch(NoVertexFound e)
        {
            System.err.println("Uno o piu vertici non esistenti");
        }
        */
        
        //Test 6: HasArco
        /*
        //arco presente
        System.out.println(g.hasArco("F", "A"));
        
        //archi non presenti
        try{
            g.hasArco("Z", "A");
        }catch(NoVertexFound e)
        {
            System.err.println("Vertici non presenti");
        }
        */
        
        //TEST 7: getVertici
        
        /*
        //stampa tutti i vertici
        System.out.println(g.getVertici());
        */
        
        //TEST 8: numero di vertici e archi
        
        /*
        //stampa il numero di vertici e archi
        System.out.println(g.getNum_of_vertici()+" "+g.getNum_of_Archi());
        */
        
    }
    
}


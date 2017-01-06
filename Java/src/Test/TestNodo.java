package Test;

import Grafo.Arco;
import Grafo.Nodo;

//NOTA: Eliminare i commenti volta per volta per effetuare i test.

/**
 * OVERVIEW = Test per la classe Nodo
 * 
 * @author Gionatha Sturba
 */

public class TestNodo 
{
    /*
    public static void main(String[] args) 
    {
        //Creazione del nodo
        Nodo<Integer> nodo = new Nodo<>(1);
        
        //Archi del Nodo
        nodo.addArco(new Nodo<Integer>(2), 1);
        nodo.addArco(new Nodo<Integer>(3), 1);
        
        //TEST 1: hasArco
        
        /*
        System.out.println("Test hasArco");
        
        // 1)archi presenti,stampa la loro poszione
        System.out.println(nodo.hasArco(new Arco<Integer>(new Nodo<Integer>(1),new Nodo<Integer>(2))));
        System.out.println(nodo.hasArco(new Arco<Integer>(new Nodo<Integer>(1),new Nodo<Integer>(3))));
        
        // 2)arco non presente,stampa -1.
        System.out.println(nodo.hasArco(new Arco<Integer>(new Nodo<Integer>(1),new Nodo<Integer>(4))));
        
        // 3)arco con parametro null,solleva eccezione
        try
        {
            System.out.println(nodo.hasArco(new Arco<Integer>(new Nodo<Integer>(null),new Nodo<Integer>(3))));
        }
        catch(NullPointerException e)
        {
            System.out.println("Test passato");
        }
        */
        
        //TEST 2: addArco
        
        /*
        System.out.println("Test addArco");
        
        // 1)aggiungo arco non presente,stampa true
        System.out.println(nodo.addArco(new Nodo<Integer>(4), 0));
        
        // 2)aggiungo arco con peso < 0,solleva eccezione
        try
        {
            System.out.println(nodo.addArco(new Nodo<Integer>(4), -1));
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Test Passato");
        }
        
        // 3) aggiungo arco gia' presente,stampa false
        System.out.println(nodo.addArco(new Nodo<Integer>(2), 0));
        */
        
        //TEST 3: removeArco
        
        /*
        System.out.println("Test removeArco");
        
        // 1) rimuovo arco presente e poi lo cerco con hasArco.dovra stampare true e poi -1
        System.out.println(nodo.removeArco(new Nodo<Integer>(2)));
        System.out.println(nodo.hasArco((new Arco<Integer>(new Nodo<Integer>(1),new Nodo<Integer>(2)))));
        
        // 2) passo un nodo null,solleva eccezione
        try{
            nodo.removeArco(new Nodo<Integer>(null));
        }
        catch(NullPointerException e){
            System.out.println("Test Passato");
        }
        */
        
        /////////////////////////////////////////////////
        /*Versione stringhe.
        Nodo<String> nodo2 = new Nodo<>("Marco");g
        System.out.println(nodo2.equals(new Nodo<String>("ciao")));
        */
        
    }
    
}


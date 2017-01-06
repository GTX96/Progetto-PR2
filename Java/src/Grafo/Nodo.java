package Grafo;

import Grafo.Interfaccie.NodoInterf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * OVERVIEW = Rappresenta un nodo di un grafo G = (V,E), avente chiave di tipo generico E.
 *            Gli archi del nodo sono rappresentati tramite una lista di adiacenza.
 * 
 * 
 * AF = <key,archi,visited,parent,minDistance> dove:
 *      -archi = [archi.get(0)...archi.get(num_of_archi() -1)]
 *      -key /in E
 *      -visited /in boolean
 *      -parent /in Nodo<E>
 *      -minDistance /in double.
 *      
 * 
 * IR = key != null && forall i,j. 0 < i != j < num_of_archi() --> (!archi.get(i).equals(archi.get(j)))
 *      && minDistance >= 0 + IR(Arco<E>)
 * 
 * @author Gionatha Sturba.
 * @param <E> tipo generico della chaive nodo.
 */
public class Nodo<E> implements NodoInterf<E>
{
    private E key;
    private List<Arco<E>> archi;
    private boolean visited;
    private Nodo<E> parent;
    private double minDistance;
    
    /*COSTRUTTORI*/
    
    /**
        EFF:Crea e inizializza,con una chiave key,un nuovo nodo.
            La minDistance viene messa al un valore Infinito,per un
            eventuale operazione di ricerca del cammino minimo.
            
        @param key chiave del nodo.
        @throws NullPointerException se key == null.
    **/
    public Nodo(E key)throws NullPointerException
    {
        if(key == null)
        {
            throw new NullPointerException();
        }
        
        this.key = key;
        archi = new ArrayList<>();
        visited = false;
        parent = null;
        minDistance = Double.POSITIVE_INFINITY;
    }
    
    /*GETTERS & SETTERS*/
    
    public void setMinDistance(double val)throws IllegalArgumentException
    {
        if(val < 0)
        {
            throw new IllegalArgumentException();
        }
        
        minDistance = val;
    }
    
    public boolean IsVisited() {
        return visited;
    }

    public void setVisited(boolean bool) {
        this.visited = bool;
    }
    
    public E getKey() {
        return key;
    }

    public List<Arco<E>> getArchi() {
        return Collections.unmodifiableList(archi);
    }
    
    public Nodo<E> getParent() {
        return parent;
    }

    public double getMinDistance() {
        return minDistance;
    }

    public void setParent(Nodo<E> parent)
    {
        this.parent = parent;
    }
    
    /*OVERRIDE*/

    @Override
    public boolean equals(Object obj) 
    {
        if(obj == null)
            return false;
        if(!(obj instanceof Nodo))
            return false;
        
        Nodo<E> toMatch = (Nodo<E>) obj;
        
        return toMatch.getKey().equals(this.key);
    }
    
    
    /*METODI*/
    
    public int num_of_Archi()
    {
        return archi.size();
    }
    
    public boolean addArco(Nodo<E> dest,double w)
    {
        //creo l'arco da aggiungere
        Arco<E> toAdd = new Arco<E>(this,dest,w);
        
        //arco gia presente..
        if(hasArco(toAdd) != -1)
            return false;
        
        //agiungo arco..
        archi.add(toAdd);
        
        return true;
    }
    
    public int hasArco(Arco<E> toFind)throws NullPointerException
    {
        if(toFind == null)
            throw new NullPointerException();
        
        return archi.indexOf(toFind);
    }
    
    public boolean removeArco(Nodo<E> dest)
    {
        //creo l'arco da rimuovere.
        Arco<E> toRemove = new Arco<E>(this,dest);
        
        //cerco la posizione dell'arco da rimuovere.
        int pos = hasArco(toRemove);
        
        //arco presente
        if(pos != -1)
        {
            archi.remove(pos);
            return true;
        }
        //arco non presente
        else
        {
            return false;
        }       
    }  
}

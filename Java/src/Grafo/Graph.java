package Grafo;

import Grafo.Interfaccie.GraphInterf;
import MyException.NoVertexFound;
import MyException.VertexAlreadyExist;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OVERVIEW = Rappresenta un Grafo G = (V,E),dove V sono i vertici,E gli archi.
 * 
 * AF = (adj,isOriented),dove:
 *      1) adj = f: keys -> values ,dove keys = { x | x /in E } && values = { y| y /in Nodo<E>} tale che forall i. 0 < i < keys.size() -> f(keys.get(i)) = values.get(i) e' definito.
 *      2) isOriented /in boolean
 *      3) DEFAULT_WEIGHT /in const of int
 * 
 * IR = forall i,j. 0 < i != j < keys.size() -> keys.get(i).getKey() != keys.get(j).getKey && keys != null && values != null
 *      && keys.size() == values.size() && + IR(Nodo<E>) && +IR(Arco<E>)
 *
 * @author Gionatha Sturba.
 * @param <E> tipo generico delle chiave dei vertici.
 */
public final class Graph<E> implements GraphInterf<E>
{
    private Map<E,Nodo<E>> adj;
    private boolean isOriented;
    
    /**
     * per rappresentare un grafo 'unweighted',viene utilizzato questo peso di default.
     */
    public static final double DEFAULT_WEIGHT = 1; 
    
    /*COSTRUTTORI*/
    
    /**
     * EFF: Crea e inzializza un nuovo grafo,senza vertici.
     * @param orient valore dell'orientamento del grafo
     */
    public Graph(boolean orient)
    {
        adj = new HashMap<>();
        isOriented = orient;
    }
    
    /**
     * EFF: Crea e inizializza un nuovo grafo,con una lista di vertici.
     * @param orient valore dell'orientamento del grafo.
     * @param vertici lista di vertici da inserire.
     */
    public Graph(boolean orient,List<E> vertici) throws NullPointerException
    {
        if(vertici == null)
        {
            throw new NullPointerException();
        }
        
        adj = new HashMap<>();
        isOriented = orient;
        
        for(E i : vertici)
        {
            addVertice(i);
        }
    }
    
    /* METODI */
    
    public void stampaGrafo()
    {
        for(Nodo<E> i : adj.values())
        {
            System.out.print(i.getKey()+" ->[");
            for(Arco<E> j : i.getArchi())
            {
                System.out.print(j.getDst().getKey()+" ");
            }
            System.out.println("]"+'\n');
        }
    }
    
    public void resetGraph()
    {
        for(Nodo<E> i : adj.values())
        {
            i.setParent(null);
            i.setVisited(false);
            i.setMinDistance(Double.POSITIVE_INFINITY);
        }
    }
    
    public boolean addVertice(E vertice)throws VertexAlreadyExist
    {
        //se il vertice e' gia presente
        if(adj.containsKey(vertice))
            throw new VertexAlreadyExist();
        
        //altrimenti inserisco
        Nodo<E> toAdd = new Nodo<E>(vertice);
        adj.put(vertice, toAdd);
        
        return true;
    }
    
    public boolean addArco(E src,E dest,double w)
    {
        checkVertici(src, dest);
        
        //aggiungiamo l'arco
        if(adj.get(src).addArco(adj.get(dest), w))
        {
            //caso arco non orientato,aggiungo l'arco in senso opposto,contandolo sempre come un singolo arco.
            if(!isOriented)
            {
                adj.get(dest).addArco(adj.get(src), w);
            }
            
            return true;
        }
        
        //arco gia' presente
        return false;
    }
    
    //caso arco in cui non viene specificato un peso.
    public boolean addArco(E src,E dest)
    {
        return addArco(src,dest,DEFAULT_WEIGHT);
    }
    
    public boolean removeVertice(E vertice)
    {
        checkVertice(vertice);
        
        //se lo troviamo,rimuoviamo sia il vertice,sia i relativi archi che puntano a lui
        adj.remove(vertice);
        
        Nodo<E> toRemove = new Nodo<E>(vertice);
        
        //Controllo se ogni vertice ha l'arco da eliminare..
        for(Nodo<E> i : adj.values())
        {
            i.removeArco(toRemove);
        }
        
        return true;
    }
    
    public boolean removeArco(E src,E dest)
    {
        checkVertici(src, dest);
        
        //rimuovo l'arco,se c'e'
        if(hasArco(src,dest))
        {
            adj.get(src).removeArco(adj.get(dest));
            
            //caso non orientato,rimuovo anche l'arco opposto
            if(!isOriented)
            {
                adj.get(dest).removeArco(adj.get(src));
            }
            
            return true;
        }
        
        //arco non presente
        return false;
        
    }
    
    public boolean hasArco(E src,E dest)
    {
        checkVertici(src,dest);
        
        //arco da ricercare
        Arco<E> toFind = new Arco<E>(new Nodo<E>(src),new Nodo<E>(dest));
                
         return adj.get(src).hasArco(toFind) != -1;
    }
    
    public boolean containsVertice(E vertice)throws NullPointerException
    {
        if(vertice == null)
        {
            throw new NullPointerException();
        }
        
        return adj.containsKey(vertice);
    }
    
    /*METODI PRIVATI*/
    
    private void checkVertici(E src,E dest) throws NullPointerException,NoVertexFound
    {
        if(src == null || dest == null)
            throw new NullPointerException();
        if(!containsVertice(src)||!containsVertice(dest))
            throw new NoVertexFound();
    }
    
    private void checkVertice(E vertice)throws NullPointerException,NoVertexFound
    {
        if(vertice == null)
            throw new NullPointerException();
        
        //se non contiene il vertice il vertice
        if(!containsVertice(vertice))
            throw new NoVertexFound();
    }
    
    /*GETTERS & SETTERS*/
    
    public List<E> getVertici()
    {
        List<E> toReturn = new ArrayList<>();
        
        for(Nodo<E> i : adj.values())
        {
            toReturn.add(i.getKey());
        }
        
        return Collections.unmodifiableList(toReturn);
    }
    
    public Map<E, Nodo<E>> getAdj() {
        return Collections.unmodifiableMap(adj);
    }

    public boolean IsOriented() {
        return isOriented;
    }
    
    public int getNum_of_vertici()
    {
        return adj.values().size();
    }
    
    public int getNum_of_Archi()
    {
        int count = 0;
        
        for(Nodo<E> i :adj.values())
        {
            count += i.num_of_Archi();
        }
        
        return count;
            
    }
}

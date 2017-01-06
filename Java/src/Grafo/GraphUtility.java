package Grafo;

import MyException.NoVertexFound;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * OVERVIEW = Classe che contiene una serie di metodi utili 
 *            per operazioni sui grafi
 * 
 * AF = //
 * 
 * IR = //
 * 
 * @author Gionatha Sturba.
 */
public final class GraphUtility 
{
    /**
     * EFF: Trova il cammino minimo tra 2 nodi.Valido
     *      sia per grafi pesati e non.
     * 
     * @param <E> tipo dei valori dei vertici del grafo
     * @param g grafo su cui operare
     * @param src nodo sorgente
     * @param dst nodo destinazione
     * @return  //
     * @throws NoVertexFound se uno o entrambi i vertici non esistono nel grafo.
     * @throws NullPointerException se il grafo e' null
     */
    public static <E> List<E> Dijistrka(Graph<E> g,E src,E dst)throws NoVertexFound,NullPointerException
    {
        //controllo grafo
        if(g == null)
        {
            throw new NullPointerException();
        }
        //controllo i vertici
        if(!g.containsVertice(src) || !g.containsVertice(dst))
            throw new NoVertexFound();
        
        //resetto il grafo.
        g.resetGraph();
        
        //lancio l'algoritmo BFS
        bfs(g.getAdj().get(src));
        
        return Ottieni_Cammino(g.getAdj().get(dst));
    }
    
    /**
     * Algoritmo Bread-First-Search,per settare le componenti per trovare i
     * cammini minimi a partire da un nodo.Inoltre ritorna la distanza dal vertice
     * piu' lontano.
     * 
     * @param <E> tipo della chiave del nodo
     * @param src nodo sorgente
     */
    private static <E> double bfs(Nodo<E> src)
    {
        double max = 0;
        src.setMinDistance(0);
        
        //inizializzo coda di priorita'
        Queue<Nodo<E>> vertexQueue = new LinkedList<Nodo<E>>();
        vertexQueue.add(src);
        
        //esploro il grafo
        while(!vertexQueue.isEmpty())
        {
            Nodo<E> u = vertexQueue.poll();
            
            //visito gli archi
            for(Arco<E> e : u.getArchi())
            {
                Nodo<E> v = e.getDst();
                double weight = e.getWeight();
                double distanceToU = u.getMinDistance() + weight;
                
                //faccio dei controlli pre vedere quale nodo inserire nel cammino minimo.
                if(distanceToU < v.getMinDistance())
                {
                    vertexQueue.remove(v);
                    
                    if(distanceToU > max)
                        max = distanceToU;
                    
                    v.setMinDistance(distanceToU);
                    v.setParent(u);
                    vertexQueue.add(v);
                }
            }
        }
        
        return max;
    }
    
    private static <E> List<E> Ottieni_Cammino(Nodo<E> dst)
    {
        List<E> path = new ArrayList<E>();
        
        for(Nodo<E> vertex = dst; vertex != null ; vertex = vertex.getParent())
        {
            path.add(vertex.getKey());
        }
        
        Collections.reverse(path);
        
        return path;
    }
    
    /**
     * Algoritmo per calcolo del diametro di un Grafo.
     * @param <E> tipo delle chiavi dei vertici del grafo.
     * @param g grafo su cui operare
     * @return //
     * @throws NullPointerException se il grafo == null.
     */
    public static <E> double Graph_Diameter(Graph<E> g)throws NullPointerException
    {
        if(g == null)
            throw new NullPointerException();
        
        double diameter = 0;
        
        for(Nodo<E> i : g.getAdj().values())
        {
            double distance_of_i = bfs(i);
           
            if(distance_of_i > diameter)
                diameter = distance_of_i;
            
            g.resetGraph();
        }
        
        return diameter;
    }
    
    
    
    
}

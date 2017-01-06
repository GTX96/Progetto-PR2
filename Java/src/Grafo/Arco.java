package Grafo;

import Grafo.Interfaccie.ArcoInterf;

/**
 * OVERVIEW = Rappresenta un arco di un grafo G = (V,E).
 *            Un arco unisce un nodo sorgente(src),ad un nodo 
 *            destinazione(dest),e ha un relativo peso.
 * 
 * AF = (src,dest,weight),dove: 
 *          1)src e dest /in Nodo<E>
 *          2)weight /in double
 * 
 * IR = src != null && dest != null && weight >= 0 + IR(Nodo<E>)
 * 
 * @author Gionatha Sturba.
 * @param <E> tipo generico dei nodi src e dest.
 */
public class Arco<E> implements ArcoInterf<E>
{
    private Nodo<E> src;
    private Nodo<E> dst;
    private double weight;
   
    /*COSTRUTTORI*/
    
    /**
     * EFF: Crea ed inizializza un nuovo arco.
     * 
     * @param src nodo sorgente
     * @param dst nodo destinazione
     * @param w peso dell'arco
     * @throws NullPointerException se src o dest == null
     * @throws IllegalArgumentException se weight < 0
     */ 
    public Arco(Nodo<E> src,Nodo<E> dst,double w)throws NullPointerException,IllegalArgumentException
    {
        if(src == null || dst == null)
            throw new NullPointerException();
        
        if(w < 0)
            throw new IllegalArgumentException();
        
        this.src = src;
        this.dst = dst;
        this.weight = w;
    }
    
    /**
     * EFF: Crea ed inizializza un nuovo arco,con peso = 0.
     * 
     * @param src nodo sorgente
     * @param dst nodo destinazione
     * @throws NullPointerException se src o dst == null.
     */
    public Arco(Nodo<E> src,Nodo<E> dst)throws NullPointerException
    {
        if(src == null || dst == null)
            throw new NullPointerException();
        
        this.src = src;
        this.dst = dst;
        this.weight = 0;
    }
    
    /*GETTERS & SETTERS*/
    
    public Nodo<E> getSrc() {
        return src;
    }

    public Nodo<E> getDst() {
        return dst;
    }

    public double getWeight() {
        return weight;
    }
    
    /*OVERRIDE*/
    
    @Override
    public boolean equals(Object obj) 
    {
        if(obj == null || !(obj instanceof Arco))
            return false;
        
        Arco<E> toMatch = (Arco<E>) obj;
        
        return toMatch.getSrc().equals(this.src) && toMatch.getDst().equals(this.dst);
            
    }
    
    
}

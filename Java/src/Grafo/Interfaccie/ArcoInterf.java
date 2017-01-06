package Grafo.Interfaccie;

import Grafo.Nodo;

/**
 * OVERVIEW = Rappresenta un arco di un grafo G = (V,E),dove i nodi hanno chiave con tipo generico E.
 * 
 * Typical Element = (src,dest,weight) dove:
 *                   1) src & dest /in Nodo<E>
 *                   2) weight e' un double rappresentante il peso dell'arco.
 * 
 * @author Gionatha Sturba.
 * @param <E> tipo generico dei nodi src e dest.
 */
public interface ArcoInterf<E>
{
    /**
     * EFF: Ritorna il nodo sorgente dell'arco this.
     * @return //
     */
    public Nodo<E> getSrc();
    
    /**
     * EFF: Ritorna il nodo destinazione dell'arco this.
     * @return //
     */
    public Nodo<E> getDst();
    
    /**
     * EFF: Ritorna il peso dell'arco this.
     * @return //
     */
    public double getWeight();
}

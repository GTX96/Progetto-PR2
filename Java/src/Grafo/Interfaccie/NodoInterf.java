package Grafo.Interfaccie;

import Grafo.Arco;
import Grafo.Nodo;
import java.util.List;

/**
 * OVERVIEW = Rappresenta un nodo di un grafo G = (V,E),avente chiave con tipo generico E.
 *            Gli archi del nodo sono rappresentati tramite una lista di adiacenza.
 * 
 * Typical Element = (key,archi,visited,parent,minDistance),dove:
 *                   1) key = rappresenta la chiave,di tipo generico E, identificativa del nodo.
 *                   2) archi = lista di archi<E> uscenti dal nodo this.
 *                   3) visited = valore booleano, che rappresenta se il nodo e' stato visitato o meno. 
 *                   4) parent = il nodo padre nella visita del nodo.
 *                   5) minDistance = la distanza di un suo cammino minimo.
 *            
 * @author Gionatha Sturba.
 * @param <E> tipo generico della chiave nodo.
 */
public interface NodoInterf<E> 
{
    /**
     * EFF: Ritorna lo stato di visita del nodo.
     * @return //
     */
    public boolean IsVisited();
    
    /**
     * EFF: Setta lo stato di visita del nodo.
     * MOD: this
     * 
     * @param bool valore dello stato di visita.
     */
    public void setVisited(boolean bool);
    
    /**
     * EFF: Setta la distanza minima di un nodo.
     * MOD:this
     * 
     * @param val valore da assegnare alla distanza minima
     * @throws se il valore della distanza minima e' < 0.
     */
    public void setMinDistance(double val)throws IllegalArgumentException;
    
    /**
     * EFF: Ritorna il padre,in una visita,del nodo this
     * @return // 
     */
    public Nodo<E> getParent();
    
    /**
     * EFF: Ritorna la distanza minima,in un cammino minimo,del nodo this.
     * @return //
     */
    public double getMinDistance();
    
    /**
     * EFF: Setta il padre,in una visita,del nodo this.
     *      In questo caso e' ammesso un nodo null.
     * MOD:this
     * 
     * @param parent il nodo padre
     */
    public void setParent(Nodo<E> parent);
    
    /**
     * EFF: Ritorna il valore della chiave del nodo.
     * @return //
     */
    public E getKey();
    
    /**
     * EFF: Ritorna la lista degli archi uscenti di this.
     * @return //
     */
    public List<Arco<E>> getArchi();
    
    /**
     * EFF: Ritorna il numero degli archi uscenti del nodo this.
     * @return //
     */
    public int num_of_Archi();
    
    /**
     * EFF: Ritorna la posizione dell'arco toFind nella lista degli archi di this.
     *      Se non viene trovato ritorna -1,cio vuol dire che l'arco toFind non esiste.
     * 
     * @param toFind arco da ricercare,del tipo (this,dest)
     * @throws NullPointerException se l'arco da ricercare e' null.
     * @return //
     */
    public int hasArco(Arco<E> toFind)throws NullPointerException;
    
    /**
     * MOD: this
     * EFF: Aggiunge l'arco (this,dest),che non deve essere gia'
     *      presente nella lista degli archi di this.
     * 
     * @param dest nodo destinazione dell'arco (this,dest).
     * @param w peso dell'arco
     * @return esito operazione.
     * 
     */
    public boolean addArco(Nodo<E> dest,double w);
    
    /**
     * MOD: this
     * EFF: Rimuove un arco del tipo(this,dest),dalla lista degli
     *      archi di this.
     * 
     * @param dest nodo destinazione dell'arco (this,dest).
     * @return esito operazione.
     * @throws NullPointerException se dest == null
     * 
     * L'eccezione viene lanciata dalla classe Arco.
     */
    public boolean removeArco(Nodo<E> dest);
}

package Grafo.Interfaccie;

import Grafo.Nodo;
import MyException.NoVertexFound;
import MyException.VertexAlreadyExist;
import java.util.List;
import java.util.Map;

/**
 * OVERVIEW = Rappresenta un Grafo G = (V,E),dove V sono i vertici,E gli archi.
 *            Il Grafo e' rappresentato tramite una lista di adiacenza.
 *            Tutti gli archi hanno un peso e sono orientati.
 * 
 * Typical Element : (adj,isOriented,DEFAULT_WEIGHT),dove:
 *                  1) adj e' una funzione da K -> V,dove K e' l'insieme delle chiavi dei nodi e V e' l'insieme dei Vertici, e serve per rappresentare il grafo.
 *                  2)isOriented, indica se il grafo e' orientato o meno
 *                  3)DEFAULT_WEIGHT e' il peso specifico di un arco,se esso non viene dichiarato esplicitamente.
 * 
 * @author Gionatha Sturba.
 * @param <E> tipo generico delle chiave dei vertici.
 */
public interface GraphInterf<E> 
{
    /**
     * EFF: Stampa a video la lista di adiacenza del grafo.
     */
    public void stampaGrafo();
    
    /**
     * MOD: this
     * EFF: Resetta i dati di tutti i vertici del grafo.
     * 
     */
    public void resetGraph();
    
    /**
     * MOD: this
     * EFF: Aggiunge un vertice al grafo this,se questo non esiste
     * 
     * @param vertice vertice da aggiungere
     * @return esito operazione
     * @throws VertexAlreadyExist se il vertice e' gia presente nel grafo.
     */
    public boolean addVertice(E vertice)throws VertexAlreadyExist;
    
    /**
     * MOD: this
     * EFF: Aggiunge un arco al grafo this,se questo non esiste. 
     * 
     * @param src vertice sorgente dell'arco
     * @param dest vertice destinazione dell'arco
     * @param w peso dell'arco
     * @return esito operazione
     * @throws NullPointerException se src o dest == null
     * @throws NoVertexFound se src o dest sono vertici non esistenti nel grafo.
     */
    public boolean addArco(E src,E dest,double w)throws NullPointerException,NoVertexFound;
    
    /**
     * MOD: this
     * 
     * EFF: Rimuove un vertice dal grafo,se questo esiste.
     * 
     * @param vertice vertice da eliminare
     * @return esito operazione
     * @throws NullPointerException se vertice == null
     * @throws NoVertexFound se il vertice da rimuovere non esiste.
     */
    public boolean removeVertice(E vertice)throws NullPointerException,NoVertexFound;
    
    /**
     * MOD : this
     * 
     * EFF: RImuove un arco del tipo (src,dest) dal grafo this,se questo e' presente
     * 
     * @param src vertice sorgente dell'arco
     * @param dest vertice destinazione dell'arco
     * @return esito operazione
     * @throws NullPointerException se src o dest == null.
     * @throws NoVertexFound se i vertici src o dest non esitono.
     */
    public boolean removeArco(E src,E dest)throws NullPointerException,NoVertexFound;
    
    /**
     * EFF: RItorna se il vertice e' presente nel grafo,in valore booleano.
     * 
     * @param vertice vertice da ricercare
     * @return stato della presenza del vertice nel grafo
     * @throws NullPointerException se il vertice == null.
     */
    public boolean containsVertice(E vertice)throws NullPointerException;
    
    /**
     * EFF: Ritorna la presenza dell'arco (src,dest)
     * 
     * @param src vertice sorgente dell'arco
     * @param dest vertice destinazione dell'arco
     * @return valore booleano sulla presenza dell'arco
     * @throws NullPointerException se src o dest  == null
     * @throws NoVertexFound se i vertici src o dest non esistono nel grafo.
     */
    public boolean hasArco(E src,E dest)throws NullPointerException,NoVertexFound;
    
    /**
     * EFF: Ritorna il numero di vertici del grafo.
     * @return //
     */
    public int getNum_of_vertici();
    
    /**
     * EFF: Ritorna il numero di archi del grafo.
     * @return //
     */
    public int getNum_of_Archi();
    
    /**
     * EFF: Ritorna in una Lista non modificabile,tutte le chiavi dei vertici.
     * @return //
     */
    public List<E> getVertici();
    
    /**
     * EFF: Ritorna se il grafo e' orientato.
     * @return //
     */
    public boolean IsOriented();
    
    /**
     * EFF: Ritorna la lista di adiacenza del grafo G.
     *      La lista di adiacenza non e' modificabile.
     * @return //
     */
    public Map<E, Nodo<E>> getAdj();
}

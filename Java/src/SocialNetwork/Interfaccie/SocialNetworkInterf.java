package SocialNetwork.Interfaccie;

import SocialNetwork.Utente;
import java.util.List;

/**
 * OVERVIEW = Rappresenta la rete degli utenti iscritti ad un social network,dove
 *            i vertici sono gli utenti e gli archi rappresentano le amicizie tra
 *            gli utenti.
 * 
 * Typical Element: (rete), rappresenta il grafo,non orientato,del social network.
 * 
 * @author Gionatha Sturba
 */
public interface SocialNetworkInterf 
{
    /**
     * MOD: this
     * EFF: Inserisce un nuovo utente,non presente,nel social network.
     * 
     * @param u utente da aggiungere
     * @return esito operazione
     */
    public boolean iscriviUtente(Utente u);
    
    /**
     * MOD: this
     * EFF: Rimuove un utente,presente,dal social network
     * 
     * @param u utente da rimuovere
     * @return esito operazione
     */
    public boolean disiscriviUtente(Utente u);
    
    /**
     * MOD: this
     * EFF: Fa iniziare una nuova amicizia tra 2 utenti,se questi non sono gia amici.
     *      Piu formalmente li collega tramite un arco nel grafo,che non sia gia presente.
     * 
     * @param a primo utente coinvolto nella nuova amicizia
     * @param b secondo utente coinvolto nella nuova amicizia
     * @return esito operazione
     */
    public boolean inizioAmicizia(Utente a,Utente b);
    
    
    /**
     * MOD: this
     * EFF: Fimuove lo stato di amicizia tra 2 utenti,se questi sono amici.
     *      Piu formalmente rimuove rimuove l'arco che collega i 2 utenti,se questo esiste.
     * 
     * @param a primo utente coinvolto nella nuova amicizia
     * @param b secondo utente coinvolto nella nuova amicizia
     * @return esito operazione
     */
    public boolean fineAmicizia(Utente a,Utente b);
    
    /** 
     * EFF: Ritorna se due utenti sono amici
     * 
     * @param a primo utente sui cui analizzare l'amicizia con il secondo
     * @param b secondo utente su cui analizzare l'amicizia con il primo
     * @return esito amicizia
     */
    public boolean sonoAmici(Utente a,Utente b);
    
    /**
     * EFF: Ritorna il numero di iscritti al social network.
     * @return //
     */
    public int numeroIscritti();
    
    /**
     * EFF: Ritorna il numero totale di amicizie che ci sono tra gli utenti.
     * @return //
     */
    public int numeroRelazioni();
    
    /**
     * EFF: Ritorna il numero di intermediari tra 2 utenti.
     * @param a utente da cui si parte
     * @param b utente su cui arrivare
     * @return //
     */
    public int intermediariTra(Utente a,Utente b);
    
    /**
     * EFF: Ritorna la compatezza del social network
     * @return //
     */
    public double compattezzaSocialNetwork();
    
    /**
     * EFF: Ritorna una lista di utenti,che sono amici dell'utente ricercato.
     * 
     * @param a Utente su cui ricercare gli amici
     * @return //
     */
    public List<Utente> amiciDi(Utente a);
    
    /**
     * EFF: Stampa le persone iscritte al social network,con i suoi relativi amici.
     */
    public void stampaRete();
}

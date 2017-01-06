package SocialNetwork;

import SocialNetwork.Interfaccie.UtenteInterf;
import java.util.Objects;

/**
 * OVERVIEW = Rappresenta un utente iscritto al social network.
 *            L'eta' minima per iscriversi e' 13 e quella massima e' 85.
 * 
 * AF = (nome,cognome,eta,sesso), dove:
 *      1) nome /in String
 *      2) cognome /in String
 *      3) eta /in int
 *      3) sesso /in char
 * 
 * IR = nome != null && cognome != null && eta >= 13 && eta < 86 && sesso /in {'M','F'}
 * 
 * @author Gionatha Sturba
 */
public class Utente implements UtenteInterf
{
    private String nome;
    private String cognome;
    private int eta;
    private char sesso;
    public static final int MIN_ETA = 13;
    public static final int MAX_ETA = 85;
    
    /**
     * Crea e inizializza un nuovo utente,con i suoi relativi dati.
     * @param nome nome dell'utente
     * @param cognome cognome dell'utente   
     * @param eta eta' dell'utente
     * @param sesso sesso dell'utente
     * @throws NullPointerException se nome o cognome == null
     * @throws IllegalArgumentException se l'eta' e' < 0,oppure il sesso non e' un carattere valido
     */
    public Utente(String nome,String cognome,int eta,char sesso)throws NullPointerException,IllegalArgumentException
    {
        //nome o cognome non validi
        if(nome == null || cognome == null)
        {
            throw new NullPointerException();
        }
        //iscritti solo con eta maggiore di 13 e minore di 86
        if(eta < MIN_ETA || eta > MAX_ETA)
        {
            throw new IllegalArgumentException();
        }
        //carattere sesso non valido
        if(sesso != 'M' && sesso != 'F')
        {
            throw new IllegalArgumentException();
        }
        
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.sesso = sesso;
        
    }
    
    /*GETTERS & SETTERS*/
    
    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public int getEta() {
        return eta;
    }

    public char getSesso() {
        return sesso;
    }
    
    /* OVERRIDE */

    @Override
    public String toString() {
        return nome+" "+cognome;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.nome);
        hash = 31 * hash + Objects.hashCode(this.cognome);
        hash = 31 * hash + this.eta;
        hash = 31 * hash + this.sesso;
        return hash;
    }
    
    

    @Override
    public boolean equals(Object obj) 
    {
        if(obj == null)
            return false;
        if(!(obj instanceof Utente))
            return false;
        
        Utente toMatch = (Utente) obj;
        
        return toMatch.getNome().equalsIgnoreCase(nome) && toMatch.getCognome().equalsIgnoreCase(cognome) && toMatch.getEta() == eta && toMatch.getSesso() == sesso;
        
    }
    
    
}

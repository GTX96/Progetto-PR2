package SocialNetwork.Interfaccie;

/**
 * OVERVIEW = Rappresenta un utente iscritto al social network.
 *            L'eta' minima per iscriversi e' 13 e quella massima e' 85.
 * 
 * Typical Element = (nome,cognome,eta,sesso) : questa tupla rappresenta le informazioni riguardo un utente 
 *                                              iscritto ad un social network.
 * 
 * @author Gionatha Sturba
 */
public interface UtenteInterf 
{
    /**
     * EFF: Ritorna il nome dell'utente
     * @return //
     */
    public String getNome();
    
    /**
     * EFF: Ritorna il cognome dell'utente 
     * @return //
     */
    public String getCognome();
    
    /**
     * EFF: Ritorna l'eta dell'utente
     * @return //
     */
    public int getEta();
    
    /**
     * EFF: Ritorna il sesso dell'utente.
     * @return //
     */
    public char getSesso();
}

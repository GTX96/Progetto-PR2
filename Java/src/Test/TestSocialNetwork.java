
package Test;

import MyException.NoVertexFound;
import MyException.VertexAlreadyExist;
import SocialNetwork.SocialNetwork;
import SocialNetwork.Utente;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * OVERVIEW = Test per la classe SocialNetwork
 * 
 * @author Gionatha Sturba
 */
public class TestSocialNetwork 
{
    
    public static void main(String[] args) 
    {
    
        //Creo un nuovo social network,con 4 iscritti
        List<Utente> iscritti = new ArrayList<>(Arrays.asList(new Utente("Marco", "Rossi", 13, 'M'),new Utente("Fabio","Resi",18,'M'),new Utente("Sara","Verdi",21,'F'),new Utente("Elliot","Alderson",26,'M')));
    
        SocialNetwork Facebook = new SocialNetwork(iscritti);
        
        
        /*
        //TEST 1: iscrizioneutente
        
        //iscrizione semplice
        Facebook.iscriviUtente(new Utente("Chiara","Benedetti",19,'F'));
        
        //possibili eccezioni
        
        try{
            //utente gia presente
            Facebook.iscriviUtente(new Utente("Marco","Rossi",13,'M'));
        }catch(VertexAlreadyExist e){
            System.err.println("Utente gia' presente,se non sono gli stessi utenti,inserisci dati piu specifici per differenziarli");
        }
        
        try{
            //utente con eta < del minimo
            Facebook.iscriviUtente(new Utente("Marco","Rossi",12,'M'));
        }catch(IllegalArgumentException e){
            System.err.println("Eta minima necessaria per l'iscrizione 13");
        }
        
        //utente con nome non valido
        try{
            Facebook.iscriviUtente(new Utente(null,"Rossi",12,'M'));
        }catch(NullPointerException e){
            System.err.println("Nome non valido");
        }
        */
        
        
        //TEST 2: disiscrivi utente
        /*
        //disiscrizione normale
        Facebook.disiscriviUtente(new Utente("Fabio","Resi",18,'M'));
        
        //possibili eccezioni
        
        try{
            //utente non esistente
            Facebook.disiscriviUtente(new Utente("Fabione","Rossini",18,'M'));
        }catch(NoVertexFound e){
            System.err.println("Utente non esistente");
        }
        */
        
        
        //TEST 3: inizio amicizia
        
        /*
        //test normale
        Facebook.inizioAmicizia(new Utente("Marco", "Rossi", 13, 'M'),new Utente("Fabio","Resi",18,'M'));
        
        //possibili eccezioni
        
        try{
            Facebook.inizioAmicizia(new Utente("Marco", "Rossi", 13, 'M'),new Utente("Fabione","Ressss",40,'M'));
        }catch(NoVertexFound e)
        {
            System.err.println("Uno o piu' Utenti non esistenti");
        }
        */
        
        
        //TEST 4: fineAmicizia
        
        /*
        //test normale
        Facebook.fineAmicizia(new Utente("Marco", "Rossi", 13, 'M'),new Utente("Fabio","Resi",18,'M'));
        
        //possibili eccezioni
        try{
            Facebook.fineAmicizia(new Utente("Marco", "Rossi", 13, 'M'),new Utente("Fabione","Ressss",40,'M'));
        }catch(NoVertexFound e)
        {
            System.err.println("Uno o piu' Utenti non esistenti");
        }
        */
        
        
        //TEST 5: sonoAmici
        
        /*
        //test normale
        Facebook.inizioAmicizia(new Utente("Marco", "Rossi", 13, 'M'),new Utente("Fabio","Resi",18,'M'));
        System.out.println(Facebook.sonoAmici(new Utente("Marco", "Rossi", 13, 'M'), new Utente("Fabio","Resi",18,'M')));
        
        //possibili eccezioni
        try{
            Facebook.sonoAmici(new Utente("Marco", null, 13, 'M'), new Utente("Fabio","Resi",18,'M'));
        }catch(NullPointerException e){
            System.err.println("Uno o piu nomi non validi");
        }
        */
        
        
        
        //TEST 6: numero iscritti e numero relazioni e stampaRete
        
        /*
        Facebook.stampaRete();
        System.out.println("numero iscritti :"Facebook.numeroIscritti() +" numero relazioni: "+ Facebook.numeroRelazioni());
        */
        
        
        //TEST 7: intermediari e compattezzaRete
        /*
        //aggiungiamo un po di relazione e utenti
        Facebook.iscriviUtente(new Utente("Giacomo","Leopardi",80,'M'));
        Facebook.inizioAmicizia(new Utente("Marco", "Rossi", 13, 'M'),new Utente("Fabio","Resi",18,'M'));
        Facebook.inizioAmicizia(new Utente("Fabio","Resi",18,'M'),new Utente("Sara","Verdi",21,'F'));
        Facebook.inizioAmicizia(new Utente("Sara","Verdi",21,'F'),new Utente("Elliot","Alderson",26,'M'));
        Facebook.inizioAmicizia(new Utente("Elliot","Alderson",26,'M'), new Utente("Giacomo","Leopardi",80,'M'));
        
        System.out.println("intemediari tra fabio ed Elliot: "+Facebook.intermediariTra(new Utente("Fabio","Resi",18,'M'), new Utente("Elliot","Alderson",26,'M')));
        System.out.println("intemediari tra sara e marco: "+Facebook.intermediariTra(new Utente("Sara","Verdi",21,'F'), new Utente("Marco", "Rossi", 13, 'M')));
        System.out.println("Compattezza rete: "+ Facebook.compattezzaSocialNetwork());
        
        Facebook.stampaRete();
        */
        
        
        //TEST 8: amiciDi
        
        //test normale
        /*
        Facebook.inizioAmicizia(new Utente("Marco", "Rossi", 13, 'M'),new Utente("Fabio","Resi",18,'M'));
        Facebook.inizioAmicizia(new Utente("Fabio","Resi",18,'M'),new Utente("Sara","Verdi",21,'F'));
        Facebook.inizioAmicizia(new Utente("Sara","Verdi",21,'F'),new Utente("Elliot","Alderson",26,'M'));
        System.out.println(Facebook.amiciDi(new Utente("Sara","Verdi",21,'F')));
        */
    }
    
}

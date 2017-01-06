package SocialNetwork;

import Grafo.Graph;
import Grafo.GraphUtility;
import Grafo.Nodo;
import SocialNetwork.Interfaccie.SocialNetworkInterf;
import java.util.ArrayList;
import java.util.List;

/**
 * OVERVIEW = Rappresenta la rete degli utenti iscritti ad un social network,dove
 *            i vertici sono gli utenti e gli archi rappresentano le amicizie tra
 *            gli utenti.
 * 
 * AF = (rete), dove:
 *      rete /in {Graph<Utente>},dove per Graph<Utente>.IsOriented = false.
 * 
 * IR = rete != null + IR(Graph<E>)
 * 
 * @author Gionatha Sturba
 */
public final class SocialNetwork implements SocialNetworkInterf
{
   private Graph<Utente> rete;
   
   /**
    * EFF: Crea e inizializza una nuovo social network,senza iscritti.
    */
   public SocialNetwork()
   {
       rete = new Graph<>(false);
   }
   
   /**
    * EFF: Crea e inzializza una nuovo social network,con una lista di iscritti
     * @param iscritti lista di iscritti al social network
    */
   public SocialNetwork(List<Utente> iscritti)
   {
       rete = new Graph<>(false,iscritti);
   }
   
   public boolean iscriviUtente(Utente u)
   {
       return rete.addVertice(u);
   }
   
   public boolean disiscriviUtente(Utente u)
   {
       return rete.removeVertice(u);
   }
   
   public boolean inizioAmicizia(Utente a,Utente b)
   {
       return rete.addArco(a,b);
   }
   
   public boolean fineAmicizia(Utente a,Utente b)
   {
       return rete.removeArco(a, b);
   }
   
   public boolean sonoAmici(Utente a,Utente b)
   {
       return rete.hasArco(a,b);
   }
   
   public int numeroIscritti()
   {
       return rete.getNum_of_vertici();
   }
   
   public int numeroRelazioni()
   {
       return rete.getNum_of_Archi();
   }
   
   public int intermediariTra(Utente a,Utente b)
   {
       List<Utente> path;
       
       path = GraphUtility.Dijistrka(rete, a, b);
       
       return path.size()-2;
   }
   
   public double compattezzaSocialNetwork()
   {
       return GraphUtility.Graph_Diameter(rete);
   }
   
   public List<Utente> amiciDi(Utente a)
   {
       List<Utente> toReturn = new ArrayList<>();
       
       for(int i = 0; i < rete.getAdj().get(a).num_of_Archi();i++)
       {
           toReturn.add(rete.getAdj().get(a).getArchi().get(i).getDst().getKey());
       }
       
       return toReturn;
   }
   
   public void stampaRete()
   {
       for(Utente i : rete.getVertici())
       {
           System.out.print(i.toString().toUpperCase()+" -> amici: ");
           
           if(amiciDi(i).isEmpty())
           {
               System.out.println("Nessun amico trovato");
           }
           else
               System.out.println(amiciDi(i));
       }
   }
   
}

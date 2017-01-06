
package MyException;

/**
 * OVERVIEW = Eccezione,che viene lanciata se il vertice e' gia' presente nel grafo.
 * 
 * @author gio
 */
public class VertexAlreadyExist extends RuntimeException {

    public VertexAlreadyExist()
    {
        super();
    }
    
    public VertexAlreadyExist(String s)
    {
        super(s);
    }
}

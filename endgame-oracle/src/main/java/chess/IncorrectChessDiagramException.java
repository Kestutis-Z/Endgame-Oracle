package chess;

/** 
 * An exception that occurs when incorrect chess diagram
 * is provided as an argument. 
 * 
 * @author Kestutis
 *
 */
public class IncorrectChessDiagramException extends Exception {

    private static final long serialVersionUID = 1L;

    public IncorrectChessDiagramException(String message) {
        super("\n" + message);
    }
}

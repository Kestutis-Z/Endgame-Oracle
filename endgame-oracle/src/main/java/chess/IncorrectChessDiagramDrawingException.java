package chess;

/** 
 * An exception that occurs when incorrect chess diagram
 * is provided as an argument. 
 * 
 * @author Kestutis
 *
 */
public class IncorrectChessDiagramDrawingException extends Exception {

    private static final long serialVersionUID = 1L;

    public IncorrectChessDiagramDrawingException(String message) {
        super("\n" + message);
    }
}

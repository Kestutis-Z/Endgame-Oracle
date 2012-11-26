package chess;

/**
 * An exception that occurs when an incorrect chess diagram is provided as an
 * argument.
 * 
 * @author Kestutis
 * 
 */
public class IncorrectChessDiagramDrawingException extends Exception {

    /** For serialization. */
    private static final long serialVersionUID = -4715673830246718157L;

    /**
     * Instantiates a new incorrect chess diagram drawing exception.
     * 
     * @param message
     *            the detail message specifying the reason why the chess diagram
     *            is incorrect
     */
    public IncorrectChessDiagramDrawingException(String message) {
        super("\n" + message);
    }
    
}

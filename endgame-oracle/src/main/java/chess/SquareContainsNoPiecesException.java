package chess;

/**
 * An exception that occurs when the square is expected to contain some piece,
 * but does not.
 * 
 * @author Kestutis
 * 
 */
public class SquareContainsNoPiecesException extends Exception {

    /** For serialization. */
    private static final long serialVersionUID = -5331133672234425506L;

    /**
     * Instantiates a new exception concerned with squares that contains no
     * pieces.
     * 
     * @param message
     *            the detail message explaining why the exception was thrown
     */
    public SquareContainsNoPiecesException(String message) {
        super("\n" + message);
    }
}

package chess;

/** An exception that occurs when the square is expected to 
 * contain some piece, but does not. */
public class SquareContainsNoPiecesException extends Exception {

    private static final long serialVersionUID = -5331133672234425506L;

    public SquareContainsNoPiecesException(String message) {
        super("\n" + message);
    }
}

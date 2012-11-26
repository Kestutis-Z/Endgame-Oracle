package chess;

/** 
 * An exception occuring when a square is expected 
 * to have an adjacent square, but does not have. 
 * 
 * @author Kestutis
 *
 */
public class SquareHasNoAdjacentSquareException extends Exception {

    /** For serialization. */
    private static final long serialVersionUID = -3604688674460679824L;

    /**
     * Instantiates a new exception dealing with the square that has no adjacent
     * square.
     * 
     * @param message
     *            the detail message explaining why the exception was thrown
     */
    public SquareHasNoAdjacentSquareException(String message) {
        super("\n" + message);
    }

}

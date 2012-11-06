package chess;

/** Represents an exception occuring when a square is expected 
 * to have an adjacent square, but does not have. */
public class SquareHasNoAdjacentSquareException extends Exception {

    private static final long serialVersionUID = -3604688674460679824L;

    public SquareHasNoAdjacentSquareException(String message) {
        super("\n" + message);
    }

}

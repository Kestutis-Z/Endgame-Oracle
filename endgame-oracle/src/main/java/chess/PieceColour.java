package chess;

/**
 * Chess piece colour - either White, or Black.
 * 
 * @author Kestutis
 * 
 */
public enum PieceColour {
    
    WHITE('W'), BLACK('B');    
    
    /** The first letter of this piece colour - either 'W', or 'B'. */
    private final char pieceColourFirstLetter;
   
    /**
     * Instantiates a new piece colour.
     * 
     * @param pieceColourFirstLetter
     *            the first letter of this piece colour - either 'W', or 'B'
     */
    private PieceColour(char pieceColourFirstLetter) {
	this.pieceColourFirstLetter = pieceColourFirstLetter;
    }
    
    /**
     * Gets the first upper-case letter of this piece colour ('W' or 'B' for the
     * standard White and Black colours).
     * 
     * @return the first letter of this piece colour - either 'W', or 'B'
     */
    public char getPieceColourFirstLetter() {
        return pieceColourFirstLetter;
    }    
    
    /**
     * Gets the piece colour from the abbreviation (capital letter 'W' or 'B').
     * 
     * @param abbreviation
     *            the first upper-case letter of the piece colour ('W' or 'B'
     *            for the standard White and Black colours)
     * @return the chess piece colour - either White, or Black
     */
    public static PieceColour getPieceColourFromAbbreviation(char abbreviation) {
	switch(abbreviation) {
	case 'W': 
	    return WHITE;
	case 'B': 
	    return BLACK;	
	default:
	    throw new AssertionError("Not supported abbreviation: " + abbreviation);
	}
    }

}
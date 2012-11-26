package chess;

/**
 * Abstract chess piece type ("King", "Queen", etc.).
 * 
 * @author Kestutis
 * 
 */
public enum PieceType {
    
    KING('K'), QUEEN('Q'), ROOK('R'), BISHOP('B'), KNIGHT('N'), PAWN('P');
    
    /**
     * The upper-case letter representing this piece type in situations were the
     * full name would be too long (e.g., in move notation).
     */
    private final char pieceTypeAbbreviation;

    /**
     * Instantiates a new piece type.
     * 
     * @param pieceTypeAbbreviation
     *            the upper-case letter representing the piece type
     */
    private PieceType(char pieceTypeAbbreviation) {
	this.pieceTypeAbbreviation = pieceTypeAbbreviation;
    }
    
    /**
     * Gets the abbreviation (short form) of this piece type's name.
     * 
     * @return the upper-case letter representing this piece type
     */
    public char getPieceTypeAbbreviation() {
        return pieceTypeAbbreviation;
    }
    
    /**
     * Gets the piece type from the provided abbreviation (upper case
     * character).
     * 
     * @param abbreviation
     *            the upper-case letter representing the piece type
     * @return the abstract chess piece type ("King", "Queen", etc.)
     */
    public static PieceType getPieceTypeFromAbbreviation(char abbreviation) {
	switch(abbreviation) {
	case 'K': 
	    return KING;
	case 'Q': 
	    return QUEEN;
	case 'R': 
	    return ROOK;
	case 'B': 
	    return BISHOP;
	case 'N': 
	    return KNIGHT;
	case 'P': 
	    return PAWN;
	default:
	    throw new AssertionError("Not supported abbreviation: " + abbreviation);
	}
    }
    
}
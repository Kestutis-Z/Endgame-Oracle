package chess;

/**
 * Abstract chess piece type.
 * 
 * @author Kestutis
 *
 */
public enum PieceType {
    
    KING('K'), QUEEN('Q'), ROOK('R'), BISHOP('B'), KNIGHT('N'), PAWN('P');
    
    private final char pieceTypeAbbreviation;

    private PieceType(char pieceTypeAbbreviation) {
	this.pieceTypeAbbreviation = pieceTypeAbbreviation;
    }

    /** @return the first upper-case letter of the piece type */
    public char getPieceTypeAbbreviation() {
        return pieceTypeAbbreviation;
    }
    
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
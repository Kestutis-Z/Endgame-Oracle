package chess;

/**
 * Chess piece colour - each piece can be of 
 * one of the two listed colours. 
 * @author Kestutis
 *
 */
public enum PieceColour {
    
    WHITE('W'), BLACK('B');
    
    private final char pieceTypeColourFirstLetter;

    private PieceColour(char pieceColourAbbreviation) {
	this.pieceTypeColourFirstLetter = pieceColourAbbreviation;
    }

    /**
     * @return first upper-case letter of the piece colour 
     * ('W' or 'B' for standard White and Black colours)
     */
    public char getPieceColourAbbreviation() {
        return pieceTypeColourFirstLetter;
    }

}
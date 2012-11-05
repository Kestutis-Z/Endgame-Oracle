package tablebases;

import chess.PieceColour;

/**
 * Computerized database containing all possible legal chess positions 
 * and their evaluations, given the set of specific chess pieces. 
 * @author Kestutis
 *
 */
public enum Tablebase {
    
    KK,
    
    KRK, 
    
    KRPKR;

    /** 
     * @return number of pieces (White and Black) that 
     * a chess position from this tablebase contains.
     */
    public int chessPiecesCount() {
	return this.name().length();
    }

    /**
     * Tablebases' names consist of lists of upper-case letters, 
     * corresponding to the abbreviations of abstract pieces. White pieces 
     * are listed first, and followed by the black pieces; also, for 
     * both sides, pieces are listed from the relatively most valuable 
     * piece (King), to the least valuable - Pawn. Thus this method splits 
     * the tablebase's name into two substrings, and returns the one that 
     * corresponds to the specified piece colour.
     * 
     * @param pieceColour White/Black
     * @return array of chars, each char representing a piece 
     * type. E.g., 'K' for King, 'Q' for Queen, etc.
     */
    protected char[] getPiecesAsCharsFromTablebase(PieceColour pieceColour) {
	String tablebaseName = this.name();
	int blackKingsIndex = tablebaseName.lastIndexOf("K");
	return pieceColour == PieceColour.WHITE 
		? tablebaseName.substring(0, blackKingsIndex).toCharArray() 
		: tablebaseName.substring(blackKingsIndex).toCharArray();
    }
}

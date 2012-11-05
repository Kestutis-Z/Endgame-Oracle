package tablebases;

import java.util.ArrayList;
import java.util.List;

import chess.Piece;
import chess.PieceColour;
import chess.PieceType;

/**
 * Computerized database containing all possible legal chess positions 
 * and their evaluations, given the set of specific chess pieces. 
 * 
 * @author Kestutis
 *
 */
public enum Tablebase {
    
    KK(0, 0),
    KRK(175168, 223944),
    KPK(163328, 168024),
    KRKR(10780728, 10780728),
    KRKP(8100040, 9963008),    
    KRPK(7877172, 10249464),
    KRPKR(476609388, 490095548) 
    
    // TODO all the remaining tablebases
    
    ;

    private final long totalWhiteToMovePositions; 
    private final long totalBlackToMovePositions;
    
    private Tablebase(long wCount, long bCount) {
	totalWhiteToMovePositions = wCount;
	totalBlackToMovePositions = bCount;
    }

    /** @return number of white-to-move positions in tablebase */
    public long getWhiteToMovePositionCount() {
	return totalWhiteToMovePositions;
    }

    /** @return number of black-to-move positions in tablebase */
    public long getBlackToMovePositionCount() {
	return totalBlackToMovePositions;
    }  

    /** 
     * @return number of pieces (White and Black) that 
     * a chess position from this tablebase contains.
     */
    public int chessPiecesCount() {
	return this.name().length();
    }    

    /** @return list of White pieces for this tablebase */
    public List<Piece> getWhitePieces() {
	char[] whitePiecesAsChars = getPiecesAsCharsFromTablebase(PieceColour.WHITE);
	List<Piece> whitePieces = new ArrayList<Piece>();
	whitePieces = convertCharsToPieces(whitePiecesAsChars, PieceColour.WHITE);
	return whitePieces;
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

    protected static List<Piece> convertCharsToPieces(char[] piecesAsChars, PieceColour pieceColour) {
	List<Piece> pieces = new ArrayList<>();
	String pieceCol = pieceColour.name();
	
	for (int i = 0; i < piecesAsChars.length; i++) {
	    char ch = piecesAsChars[i];
	    String pieceName = pieceCol;
	    pieceName += "_";
	    pieceName += PieceType.getPieceTypeFromAbbreviation(ch).name();
	  
	    int consequtiveDuplicateCharsInArrayUpToIndex = 
		    getConsequtiveDuplicateCharsInArrayUpToIndex(piecesAsChars, i);
	    if (consequtiveDuplicateCharsInArrayUpToIndex > 1) {
		pieceName += "_";
		pieceName += consequtiveDuplicateCharsInArrayUpToIndex;
	    }
	    pieces.add(Piece.valueOf(pieceName));
	}
	
	return pieces;
    }

    protected static int getConsequtiveDuplicateCharsInArrayUpToIndex(
	    char[] piecesAsChars, int i) {
	if (i == 0)
	    return 1;
	int count = 1;
	for (int j = i-1; piecesAsChars[i] == piecesAsChars[j] && j >= 0; j--) {
	    count++;
	}
	return count;
    }
    
    /** @return list of Black pieces for this tablebase */
    public List<Piece> getBlackPieces() {
	char[] blackPiecesAsChars = getPiecesAsCharsFromTablebase(PieceColour.BLACK);
	List<Piece> blackPieces = new ArrayList<Piece>();
	blackPieces = convertCharsToPieces(blackPiecesAsChars, PieceColour.BLACK);
	return blackPieces;    
    }
}

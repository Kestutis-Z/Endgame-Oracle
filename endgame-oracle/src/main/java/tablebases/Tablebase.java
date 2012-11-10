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
	return extractCharsFromTablebaseName(pieceColour, tablebaseName);
    }

    private char[] extractCharsFromTablebaseName(PieceColour pieceColour,
	    String tablebaseName) {
	int blackKingsIndex = tablebaseName.lastIndexOf("K");
	return pieceColour == PieceColour.WHITE 
		? tablebaseName.substring(0, blackKingsIndex).toCharArray() 
		: tablebaseName.substring(blackKingsIndex).toCharArray();
    }

    protected static List<Piece> convertCharsToPieces(char[] piecesAsChars, PieceColour pieceColour) {
	List<Piece> pieces = new ArrayList<Piece>();
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
    
    /** @return list of all (White followed by Black) pieces for this tablebase */
    public List<Piece> getAllPieces() {
	List<Piece> allPieces = new ArrayList<Piece>();
	allPieces.addAll(getWhitePieces());
	allPieces.addAll(getBlackPieces());
	return allPieces;
    }
    
    /** 
     * @return tablebase that would be obtained by removing the 
     * specified piece from this tablebase 
     */
    public Tablebase removePiece(Piece piece) {
	if (piece.getPieceType() == PieceType.KING)
	    throw new IllegalArgumentException("Cannot remove " + piece);
	char ch = piece.getPieceType().getPieceTypeAbbreviation();
	char kingCh = PieceType.KING.getPieceTypeAbbreviation();
	String oldTB = this.name();
	String newTB = oldTB;

	if (piece.getPieceColour() == PieceColour.WHITE) {
	    for (int i = 1; oldTB.charAt(i) != kingCh; i++) {
		if (oldTB.charAt(i) == ch) {
		    newTB = deleteCharAt(oldTB, i);
		    break;
		}
	    }
	} else
	    for (int i = oldTB.length() - 1; oldTB.charAt(i) != kingCh; i--) {
		if (oldTB.charAt(i) == ch) {
		    newTB = deleteCharAt(oldTB, i);
		    break;
		}
	    }
	if (!whitePiecesAreRelativelyMoreValuableThanBlackPiecesIn(newTB))
		newTB = reverseWhiteAndBlackPieces(newTB);
	return valueOf(newTB);
    }

    private String deleteCharAt(String s, int index) {
	StringBuffer buf = new StringBuffer(s.length() - 1);
	buf.append(s.substring(0, index)).append(s.substring(index + 1));
	return buf.toString();
    }
    
    /**
     * By convention, White pieces are relatively more 
     * valuable in tablebases. For example, there is no 
     * tablebase KPKQ ("White King + White Pawn vs. 
     * Black King + Black Queen"), since the Queen is relatively more 
     * valuable than the Pawn - instead the KQKP tablebase 
     * is used and chessboard symmetry exploited. 
     */
    private boolean whitePiecesAreRelativelyMoreValuableThanBlackPiecesIn(
	    String tablebaseName) {
	char[] whitePiecesAsChars = extractCharsFromTablebaseName(
		PieceColour.WHITE, tablebaseName);	
	char[] blackPiecesAsChars = extractCharsFromTablebaseName(
		PieceColour.BLACK, tablebaseName);
	
	int whitePiecesCount = whitePiecesAsChars.length;
	int blackPiecesCount = blackPiecesAsChars.length;	
	int smallerOrEqualOfWhitePiecesCountAndBlackPiecesCount =
		whitePiecesCount < blackPiecesCount ? 
		whitePiecesCount : blackPiecesCount;
	
	for (int i = 1 /* both 0th elements are Kings */; 
		i < smallerOrEqualOfWhitePiecesCountAndBlackPiecesCount; i++) {
	    char whitePieceAbbreviation = whitePiecesAsChars[i];
	    char blackPieceAbbreviation = blackPiecesAsChars[i];
	    PieceType whitePc = PieceType.getPieceTypeFromAbbreviation(whitePieceAbbreviation);
	    PieceType blackPc = PieceType.getPieceTypeFromAbbreviation(blackPieceAbbreviation);
	    if (whitePc.ordinal() < blackPc.ordinal())
		return true;
	    if (whitePc.ordinal() > blackPc.ordinal())
		return false;	    
	}	
	
	return whitePiecesCount >= blackPiecesCount ? true : false;
    }
    
    private String reverseWhiteAndBlackPieces(String tablebaseName) {
	char[] whitePiecesAsChars = extractCharsFromTablebaseName(
		PieceColour.WHITE, tablebaseName);	
	char[] blackPiecesAsChars = extractCharsFromTablebaseName(
		PieceColour.BLACK, tablebaseName);
	return new String(blackPiecesAsChars) + new String (whitePiecesAsChars);
    }
    
    @Override
    public String toString() {
	List<Piece> whitePieces = getWhitePieces();
	List<Piece> blackPieces = getBlackPieces();

	String output = "";
	output += whitePieces.get(0).getPieceType().name();
	for (int i = 1; i < whitePieces.size(); i++) {
	    output += " + ";
	    output += whitePieces.get(i).getPieceType().name();
	}
	output += " vs. ";
	output += blackPieces.get(0).getPieceType().name();
	for (int i = 1; i < blackPieces.size(); i++) {
	    output += " + ";
	    output += blackPieces.get(i).getPieceType().name();
	}
	
	return output;
    }
    
}

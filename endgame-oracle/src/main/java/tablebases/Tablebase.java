package tablebases;

import java.util.ArrayList;
import java.util.List;

import chess.Piece;
import chess.PieceColour;
import chess.PieceType;

/**
 * Computerized database containing all possible legal chess positions and their
 * evaluations, given the set of specific chess pieces.
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
    
    /** The number of white-to-move positions in this tablebase. */
    private final long totalWhiteToMovePositions;
    /** The number of black-to-move positions in this tablebase. */
    private final long totalBlackToMovePositions;
    
    /**
     * Instantiates a new tablebase.
     * 
     * @param wCount
     *            the number of white-to-move positions in this tablebase
     * @param bCount
     *            the number of black-to-move positions in this tablebase
     */
    private Tablebase(long wCount, long bCount) {
	totalWhiteToMovePositions = wCount;
	totalBlackToMovePositions = bCount;
    }
    
    /**
     * Gets the total of white-to-move positions in this tablebase.
     * 
     * @return the number of white-to-move positions in the tablebase
     */
    public long getWhiteToMovePositionCount() {
	return totalWhiteToMovePositions;
    }

    /**
     * Gets the total of black-to-move positions in this tablebase.
     * 
     * @return the number of black-to-move positions in the tablebase
     */
    public long getBlackToMovePositionCount() {
	return totalBlackToMovePositions;
    }     

    /**
     * Gets all the White pieces that are present in the chess positions
     * contained in this tablebase.
     * 
     * @return the list of White pieces for this tablebase
     */
    public List<Piece> getWhitePieces() {
	char[] whitePiecesAsChars = getPiecesAsCharsFromTablebase(PieceColour.WHITE);
	List<Piece> whitePieces = new ArrayList<Piece>();
	whitePieces = convertCharsToPieces(whitePiecesAsChars, PieceColour.WHITE);
	return whitePieces;
    }    
    
    /**
     * Gets the pieces as characters from this tablebase. <br/> <br/>
     * 
     * Tablebases' names consist of lists of upper-case letters, corresponding
     * to the abbreviations of abstract pieces. The White pieces are listed
     * first, and followed by the Black pieces; also, for both sides, pieces are
     * listed from the relatively most valuable piece - King, to the least
     * valuable - Pawn. Thus this method splits the tablebase's name into two
     * substrings, and returns the one that corresponds to the specified piece
     * colour. 
     * 
     * @param pieceColour
     *            the chess piece colour - either White, or Black
     * @return the array of chars, each char representing a piece type. E.g.,
     *         'K' for King, 'Q' for Queen, etc.
     */
    protected char[] getPiecesAsCharsFromTablebase(PieceColour pieceColour) {
	String tablebaseName = this.name();
	return extractCharsFromTablebaseName(pieceColour, tablebaseName);
    }

    /**
     * Extracts characters from this tablebase's name, representing the pieces
     * of the specified colour.
     * 
     * @param pieceColour
     *            the chess piece colour - either White, or Black
     * @param tablebaseName
     *            the tablebase's name as a String object
     * @return the array of chars, each char representing a piece type. E.g.,
     *         'K' for King, 'Q' for Queen, etc.
     */
    private char[] extractCharsFromTablebaseName(PieceColour pieceColour,
	    String tablebaseName) {
	int blackKingsIndex = tablebaseName.lastIndexOf("K");
	return pieceColour == PieceColour.WHITE 
		? tablebaseName.substring(0, blackKingsIndex).toCharArray() 
		: tablebaseName.substring(blackKingsIndex).toCharArray();
    }

    /**
     * Converts characters, representing the pieces, to pieces of the specified
     * colour.
     * 
     * @param piecesAsChars
     *            the character array, representing the pieces
     * @param pieceColour
     *            the chess piece colour - either White, or Black
     * @return the list of pieces corresponding to the provided characters
     */
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

    /**
     * Gets the consequtive duplicate characters in the character array,
     * starting at the specified index, and going backwards.
     * 
     * @param chars
     *            the character array
     * @param index
     *            the index in the array
     * @return the number of the consequtive duplicate characters in the array
     *         up to (and including) the specified index
     */
    protected static int getConsequtiveDuplicateCharsInArrayUpToIndex(
	    char[] chars, int index) {
	if (index == 0)
	    return 1;
	int count = 1;
	for (int j = index-1; chars[index] == chars[j] && j >= 0; j--) {
	    count++;
	}
	return count;
    }
    
    /**
     * Gets all the Black pieces that are present in the chess positions
     * contained in this tablebase.
     * 
     * @return the list of Black pieces for this tablebase
     */
    public List<Piece> getBlackPieces() {
	char[] blackPiecesAsChars = getPiecesAsCharsFromTablebase(PieceColour.BLACK);
	List<Piece> blackPieces = new ArrayList<Piece>();
	blackPieces = convertCharsToPieces(blackPiecesAsChars, PieceColour.BLACK);
	return blackPieces;    
    }
    
    /**
     * Gets all the pieces (White followed by Black) that are present in the
     * chess positions contained in this tablebase.
     * 
     * @return the list of all the pieces for this tablebase
     */
    public List<Piece> getAllPieces() {
	List<Piece> allPieces = new ArrayList<Piece>();
	allPieces.addAll(getWhitePieces());
	allPieces.addAll(getBlackPieces());
	return allPieces;
    }
    
    /**
     * Removes the specified piece from this tablebase. This results in the new
     * tablebase, with the same pieces as the original tablebase, except the
     * removed piece.
     * 
     * @param piece
     *            the representation of the chess piece
     * @return the tablebase obtained by removing the specified piece from this
     *         tablebase
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

    /**
     * Deletes the character from the String at the specified position.
     * 
     * @param originalString
     *            the specified String
     * @param index
     *            the position in the originalString (the first character in the
     *            originalString is at position zero)
     * @return the new String object, obtained by removing one character from
     *         the originalString
     */
    private String deleteCharAt(String originalString, int index) {
	StringBuffer buf = new StringBuffer(originalString.length() - 1);
	buf.append(originalString.substring(0, index)).append(originalString.substring(index + 1));
	return buf.toString();
    }
    
    /**
     * Determines if the White pieces are relatively more valuable than the
     * Black pieces in the provided String, that represents the (potential)
     * tablebase name. <br/> <br/>
     * 
     * By convention, White pieces are relatively more valuable in tablebases.
     * For example, there is no tablebase KPKQ ("White King + White Pawn vs.
     * Black King + Black Queen"), since the Queen is relatively more valuable
     * than the Pawn - instead the KQKP tablebase is used and the chessboard
     * symmetry is exploited.
     * 
     * @param tablebaseName
     *            the name of the potential tablebase
     * @return true, if the White pieces <b>are</b> relatively more valuable
     *         than the Black ones
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
    
    /**
     * Reverses the substrings representing the White and Black pieces in the
     * specified String.
     * 
     * @param tablebaseName
     *            the String representing the tablebase name
     * @return the new String, obtained by switching the characters representing
     *         the White pieces, and the characters representing the Black
     *         pieces
     */
    private String reverseWhiteAndBlackPieces(String tablebaseName) {
	char[] whitePiecesAsChars = extractCharsFromTablebaseName(
		PieceColour.WHITE, tablebaseName);	
	char[] blackPiecesAsChars = extractCharsFromTablebaseName(
		PieceColour.BLACK, tablebaseName);
	return new String(blackPiecesAsChars) + new String (whitePiecesAsChars);
    }
    
    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
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
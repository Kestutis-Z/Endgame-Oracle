package chess;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Representation of the chess piece. A legal chess position can contain exactly
 * one King, up to 8 pawns, and up to 9 units of each other piece for each piece
 * colour. </br> </br> If there is more than one piece of the same type and
 * colour in the chess position (e.g., two Black pawns), then the pieces are
 * ordered according to the values of the squares they occupy (from square a1 to
 * square h8). The second, third, etc., duplicated pieces are assigned numbers
 * two, three, and so on, and are recognized as
 * "PieceColour_PieceType_PieceNumber", e.g., "BLACK_PAWN_2".
 * 
 * @author Kestutis
 * 
 */
public enum Piece {

    WHITE_KING(PieceType.KING, PieceColour.WHITE), 
    WHITE_QUEEN(PieceType.QUEEN, PieceColour.WHITE),    
    WHITE_QUEEN_2(PieceType.QUEEN, PieceColour.WHITE), 
    WHITE_ROOK(PieceType.ROOK, PieceColour.WHITE),
    WHITE_ROOK_2(PieceType.ROOK, PieceColour.WHITE),
    WHITE_BISHOP(PieceType.BISHOP, PieceColour.WHITE),
    WHITE_BISHOP_2(PieceType.BISHOP, PieceColour.WHITE),
    WHITE_KNIGHT(PieceType.KNIGHT, PieceColour.WHITE),
    WHITE_KNIGHT_2(PieceType.KNIGHT, PieceColour.WHITE),
    WHITE_PAWN(PieceType.PAWN, PieceColour.WHITE),
    WHITE_PAWN_2(PieceType.PAWN, PieceColour.WHITE),
    WHITE_PAWN_3(PieceType.PAWN, PieceColour.WHITE),

    BLACK_KING(PieceType.KING, PieceColour.BLACK), 
    BLACK_QUEEN(PieceType.QUEEN, PieceColour.BLACK),    
    BLACK_QUEEN_2(PieceType.QUEEN, PieceColour.BLACK), 
    BLACK_ROOK(PieceType.ROOK, PieceColour.BLACK),
    BLACK_ROOK_2(PieceType.ROOK, PieceColour.BLACK),
    BLACK_BISHOP(PieceType.BISHOP, PieceColour.BLACK),
    BLACK_BISHOP_2(PieceType.BISHOP, PieceColour.BLACK),
    BLACK_KNIGHT(PieceType.KNIGHT, PieceColour.BLACK),
    BLACK_KNIGHT_2(PieceType.KNIGHT, PieceColour.BLACK),
    BLACK_PAWN(PieceType.PAWN, PieceColour.BLACK),
    BLACK_PAWN_2(PieceType.PAWN, PieceColour.BLACK),
    BLACK_PAWN_3(PieceType.PAWN, PieceColour.BLACK);    
    
    /** Abstract chess piece type ("King", "Queen", etc.). */
    private final PieceType pieceType;
    
    /** Chess piece colour - either White, or Black. */
    private final PieceColour pieceColour;  
    
    /**
     * Instantiates a new piece.
     * 
     * @param pieceType
     *            abstract chess piece type ("King", "Queen", etc.)
     * @param pieceColour
     *            chess piece colour - either White, or Black
     */
    private Piece(PieceType pieceType, PieceColour pieceColour) {
	this.pieceType = pieceType;
	this.pieceColour = pieceColour;
    }
    
    /**
     * Gets the abstract type of this piece.
     * 
     * @return the abstract chess piece type ("King", "Queen", etc.)
     */
    public PieceType getPieceType() {
        return pieceType;
    }
    
    /**
     * Gets the colour of this piece.
     * 
     * @return the chess piece colour - either White, or Black
     */
    public PieceColour getPieceColour() {
        return pieceColour;
    }
    
    /**
     * Gets the abbreviation (short form) of this piece e.g., "WB2" for the
     * piece "WHITE_BISHOP_2". The abbreviation can be used as a buiding part of
     * the chess pattern names, in textual chess diagrams, and other situations
     * were the full piece name would be too long.
     * 
     * @return the short form of this piece's name
     */
    public String getPieceAbbreviation() {
	String pieceAbbreviation = "";
	pieceAbbreviation += this.pieceColour.getPieceColourFirstLetter();
	pieceAbbreviation += this.pieceType.getPieceTypeAbbreviation();
	char lastCharOfName = this.name().charAt(this.name().length() - 1);
	if (Character.isDigit(lastCharOfName))
	    pieceAbbreviation += lastCharOfName;
	return pieceAbbreviation;	
    }
    
    /**
     * Gets the abbreviations of all the pieces in this enumeration.
     * 
     * @return the list of the short forms of all the enumerated pieces
     */
    public static List<String> allAbbreviationsOfPieces() {
	List<String> allAbbreviations = new ArrayList<String>();
	for (Piece pc : EnumSet.allOf(Piece.class)) {
	    allAbbreviations.add(pc.getPieceAbbreviation());	    
	}
	return allAbbreviations;
    }

    /**
     * Gets the piece as a Piece object from the abbreviation. Assumes the
     * argument is valid, i.e., this enumeration contains a piece corresponding
     * to the provided abbreviation.
     * 
     * @param abbreviation
     *            the short form of the piece's name
     * @return the representation of the chess piece
     */
    public static Piece getPieceFromAbbreviation(String abbreviation) {
	String pieceName = "";
	pieceName += PieceColour.getPieceColourFromAbbreviation(abbreviation.charAt(0));
	pieceName += "_";
	pieceName += PieceType.getPieceTypeFromAbbreviation(abbreviation.charAt(1));
	if (abbreviation.length() == 3) {
	    pieceName += "_";
	    pieceName += abbreviation.charAt(2);
	}
	return Piece.valueOf(pieceName);
    }
    
}
package chess;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Real, physical chess piece. A legal chess position
 * can contain exactly one King, up to 8 pawns, and up
 * to 9 units of each other piece for each piece colour.
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
    
    private final PieceType pieceType;
    private final PieceColour pieceColour;   

    private Piece(PieceType pieceType, PieceColour pieceColour) {
	this.pieceType = pieceType;
	this.pieceColour = pieceColour;
    }
    
    /** @return chess piece type */
    public PieceType getPieceType() {
        return pieceType;
    }
    
    /** @return chess piece colour */
    public PieceColour getPieceColour() {
        return pieceColour;
    }
    
    /** @return short form of this piece's name */
    public String getPieceAbbreviation() {
	String pieceAbbreviation = "";
	pieceAbbreviation += this.pieceColour.getPieceColourAbbreviation();
	pieceAbbreviation += this.pieceType.getPieceTypeAbbreviation();
	char lastCharOfName = this.name().charAt(this.name().length() - 1);
	if (Character.isDigit(lastCharOfName))
	    pieceAbbreviation += lastCharOfName;
	return pieceAbbreviation;	
    }
    
    public static List<String> allAbbreviationsOfPieces() {
	List<String> allAbbreviations = new ArrayList<>();
	for (Piece pc : EnumSet.allOf(Piece.class)) {
	    allAbbreviations.add(pc.getPieceAbbreviation());	    
	}
	return allAbbreviations;
    }

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
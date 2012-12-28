package chess;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import chess.Square.Direction;
import chess.patterns.ControllableEntity;
import chess.patterns.ControllerEntity;

/**
 * Representation of the chess piece. A legal chess position can contain exactly
 * one King, up to 8 pawns, and up to 9 units of each other piece for each piece
 * colour. </br> </br>
 * 
 * If there is more than one piece of the same type and colour in the chess
 * position (e.g., two Black pawns), then the pieces are ordered according to
 * the values of the squares they occupy (from square a1 to square h8). The
 * second, third, etc., duplicated pieces are assigned numbers two, three, and
 * so on, and are recognized as "PieceColour_PieceType_PieceNumber", e.g.,
 * "BLACK_PAWN_2".
 * 
 * @author Kestutis
 * 
 */
public enum Piece implements ControllerEntity, ControllableEntity {

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
	pieceName += PieceColour.getPieceColourFromAbbreviation(abbreviation
		.charAt(0));
	pieceName += "_";
	pieceName += PieceType.getPieceTypeFromAbbreviation(abbreviation
		.charAt(1));
	if (abbreviation.length() == 3) {
	    pieceName += "_";
	    pieceName += abbreviation.charAt(2);
	}
	return Piece.valueOf(pieceName);
    }

    /* (non-Javadoc)
     * @see chess.patterns.ControllerEntity#getControlSquares(chess.ChessPosition)
     */
    @Override
    public Set<Square> getControlSquares(ChessPosition chessPosition) {
	if (!chessPosition.containsPiece(this))
	    throw new IllegalArgumentException("Specified position does not "
		    + "contain piece: " + this);
	Set<Square> squares = new HashSet<Square>();
	Square squareOfThisPiece = chessPosition.getSquareOfPiece(this);

	switch (this.pieceType) {
	case KING:
	    for (Direction dir : EnumSet.allOf(Direction.class)) {
		if (squareOfThisPiece.hasAdjacentSquareTo(dir))
		    squares.add(squareOfThisPiece.getAdjacentSquare(dir));
	    }
	    return squares;
	case QUEEN:
	    for (Direction dir : EnumSet.allOf(Direction.class)) {
		squares.addAll(squareOfThisPiece.getRangeOfSquares(
			chessPosition, dir));
	    }
	    return squares;
	case ROOK:
	    squares.addAll(squareOfThisPiece.getRangeOfSquares(chessPosition,
		    Direction.EAST));
	    squares.addAll(squareOfThisPiece.getRangeOfSquares(chessPosition,
		    Direction.SOUTH));
	    squares.addAll(squareOfThisPiece.getRangeOfSquares(chessPosition,
		    Direction.WEST));
	    squares.addAll(squareOfThisPiece.getRangeOfSquares(chessPosition,
		    Direction.NORTH));
	    return squares;
	case BISHOP:
	    squares.addAll(squareOfThisPiece.getRangeOfSquares(chessPosition,
		    Direction.SOUTHEAST));
	    squares.addAll(squareOfThisPiece.getRangeOfSquares(chessPosition,
		    Direction.SOUTHWEST));
	    squares.addAll(squareOfThisPiece.getRangeOfSquares(chessPosition,
		    Direction.NORTHWEST));
	    squares.addAll(squareOfThisPiece.getRangeOfSquares(chessPosition,
		    Direction.NORTHEAST));
	    return squares;
	case KNIGHT:
	    return squaresAttackedByKnight(squareOfThisPiece);
	case PAWN:
	    if (this.pieceColour == PieceColour.WHITE) {
		if (squareOfThisPiece.hasAdjacentSquareTo(Direction.NORTHWEST))
		    squares.add(squareOfThisPiece
			    .getAdjacentSquare(Direction.NORTHWEST));
		if (squareOfThisPiece.hasAdjacentSquareTo(Direction.NORTHEAST))
		    squares.add(squareOfThisPiece
			    .getAdjacentSquare(Direction.NORTHEAST));
	    } else if (this.pieceColour == PieceColour.BLACK) {
		if (squareOfThisPiece.hasAdjacentSquareTo(Direction.SOUTHWEST))
		    squares.add(squareOfThisPiece
			    .getAdjacentSquare(Direction.SOUTHWEST));
		if (squareOfThisPiece.hasAdjacentSquareTo(Direction.SOUTHEAST))
		    squares.add(squareOfThisPiece
			    .getAdjacentSquare(Direction.SOUTHEAST));
	    }
	    return squares;
	default:
	    throw new AssertionError("Unknown piece type: " + this.pieceType);
	}	
    }

    /**
     * Gets the squares reachable by the knight move from the specified square.
     * 
     * @param squareOfKnight
     *            chessboard square that can be empty or occupied by one of the
     *            chess pieces, including a knight
     * @return set of squares that would be attacked by a knight if it occupied
     *         this square
     */
    private Set<Square> squaresAttackedByKnight(Square squareOfKnight) {
	Set<Square> squares = new HashSet<Square>();
	int file = squareOfKnight.getFile();
	int rank = squareOfKnight.getRank();
	squares.add(file >= 7 || rank == 8 ? null 
		: Square.getSquareFromFileAndRank(file + 2, rank + 1));
	squares.add(file >= 7 || rank == 1 ? null 
		: Square.getSquareFromFileAndRank(file + 2, rank - 1));
	squares.add(file == 8 || rank >= 7 ? null 
		: Square.getSquareFromFileAndRank(file + 1, rank + 2));
	squares.add(file == 8 || rank <= 2 ? null 
		: Square.getSquareFromFileAndRank(file + 1, rank - 2));
	squares.add(file == 1 || rank >= 7 ? null 
		: Square.getSquareFromFileAndRank(file - 1, rank + 2));
	squares.add(file == 1 || rank <= 2 ? null 
		: Square.getSquareFromFileAndRank(file - 1, rank - 2));
	squares.add(file <= 2 || rank == 8 ? null 
		: Square.getSquareFromFileAndRank(file - 2, rank + 1));
	squares.add(file <= 2 || rank == 1 ? null 
		: Square.getSquareFromFileAndRank(file - 2, rank - 1));
	squares.remove(null);
	return squares;	
    }

    /**
     * Cheks if this piece is a duplicate, i.e., if it's name ends with a digit.
     * 
     * @return true, if the last character of this piece's name is a digit
     */
    public boolean isDuplicate() {
	String pieceName = this.name();
	return Character.isDigit(pieceName.charAt(pieceName.length() - 1));
    }

    @Override
    public Set<Square> getControllableSquares(ChessPosition chessPosition) {
	// TODO Auto-generated method stub
	return null;
    }   
    
}
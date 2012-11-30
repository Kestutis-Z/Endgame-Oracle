package chess;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.BiMap;
import com.google.common.collect.EnumBiMap;

import tablebases.Tablebase;

/**
 * Representation of a chess position: side to move (White / Black), White and
 * Black pieces, and their respective squares.
 * 
 * @author Kestutis
 * 
 */
public class ChessPosition { 
    
    /**
     * Possible squares for the White King are limited to the left side of the
     * chessboard, in order to exploit the chessboard symmetry in the d-e axis.
     */
    // TODO in pawnless positions, squares may be limited to the a1-d1-d4 triangle
    public static final List<Square> WHITE_KING_SQUARES_LIST = 
	    collectSquaresForTheWhiteKing();
    
    /**
     * Possible squares for the pawns are all squares on ranks 2 to 7.
     */
    public static final List<Square> PAWN_SQUARES_LIST = 
	    new ArrayList<Square>(EnumSet.range(Square.A7, Square.H2));
    
    /**
     * For the pieces except the White King and the pawns, possible squares are
     * all 64 squares of the chessboard.
     */
    public static final List<Square> ALL_SQUARES_LIST = 
	    new ArrayList<Square>(EnumSet.allOf(Square.class)); 
    
    private static List<Square> collectSquaresForTheWhiteKing() {
	EnumSet<Square> whiteKingSquares = 
				EnumSet.range(Square.A1, Square.D1);
	whiteKingSquares.addAll(EnumSet.range(Square.A2, Square.D2));
	whiteKingSquares.addAll(EnumSet.range(Square.A3, Square.D3));
	whiteKingSquares.addAll(EnumSet.range(Square.A4, Square.D4));
	whiteKingSquares.addAll(EnumSet.range(Square.A5, Square.D5));
	whiteKingSquares.addAll(EnumSet.range(Square.A6, Square.D6));
	whiteKingSquares.addAll(EnumSet.range(Square.A7, Square.D7));
	whiteKingSquares.addAll(EnumSet.range(Square.A8, Square.D8));
	return new ArrayList<Square>(whiteKingSquares);
    }
    
    private static final int WHITE_KING_SQUARES_COUNT = WHITE_KING_SQUARES_LIST.size();
    private static final int PAWN_SQUARES_COUNT = PAWN_SQUARES_LIST.size();
    private static final int ALL_SQUARES_COUNT = ALL_SQUARES_LIST.size();
    
    /**
     * Bidirectional map from the pieces present in this chess 
     * position, to the squares those pieces occupy.
     */
    private BiMap<Piece, Square> piecesWithSquares;
    
    /** The side to move - White or Black. */
    private SideToMove sideToMove;
    
    /**
     * Instantiates a new chess position.
     * 
     * @param piecesWithSquares
     *            bidirectional map from the pieces present in this chess
     *            position, to the squares they occupy
     * @param sideToMove
     *            side whose turn is to make a move - White or Black
     */
    private ChessPosition(BiMap<Piece, Square> piecesWithSquares,
	    SideToMove sideToMove) {
	this.piecesWithSquares = piecesWithSquares;
	this.sideToMove = sideToMove;
    }   

    /**
     * Instantiates a new chess position.
     * 
     * @param tablebase
     *            computerized database containing all possible legal chess
     *            positions and their evaluations, given the set of specific
     *            chess pieces
     * @param squares
     *            the squares to be assigned to the pieces in the tablebase
     * @param sideToMove
     *            side whose turn is to make a move - White or Black
     */
    private ChessPosition(Tablebase tablebase,
	    List<Square> squares, SideToMove sideToMove) {		
	throwExceptionIfNotAllSquaresAreDifferent(squares);
	throwExceptionIfThereAreNotEnoughSquaresForPieces(tablebase, squares);
	
	piecesWithSquares = EnumBiMap.create(Piece.class, Square.class);
	List<Piece> pieces = tablebase.getAllPieces();	
	for (int i = 0; i < pieces.size(); i++) {
	    piecesWithSquares.put(pieces.get(i), squares.get(i));
	}
	this.sideToMove = sideToMove;
    } 
    
    /**
     * Throws exception if not all squares are different. (Two different pieces
     * cannot occupy the same square in a legal chess position).
     * 
     * @param squares
     *            the squares to be assigned to the pieces in the tablebase
     */
    private void throwExceptionIfNotAllSquaresAreDifferent(List<Square> squares) {
	Set<Square> squareSet = new HashSet<Square>(squares);
	if (squares.size() != squareSet.size()) {
	    throw new IllegalArgumentException("Some of the squares provided " 
		    + "are the same: " + squares.toString());
	}
    }
    
    /**
     * Throws exception if there are not provided enough squares for pieces.
     * (Each piece in a chess position must occupy a unique square).
     * 
     * @param tablebase
     *            computerized database containing all possible legal chess
     *            positions and their evaluations, given the set of specific
     *            chess pieces
     * @param squares
     *            the squares to be assigned to the pieces in the tablebase
     */
    private void throwExceptionIfThereAreNotEnoughSquaresForPieces(
	    Tablebase tablebase, List<Square> squares) {
	if (tablebase.name().length() > squares.size()) {
	    throw new IllegalArgumentException("Pieces from " +
	    		"tablebase " + tablebase + " were not provided " +
	    		"enough of corresponding squares");	
	}
    }
    
    /**
     * Creates the new chess position using the provided pieces-to-squares
     * bidirectional map.
     * 
     * @param piecesWithSquares
     *            bidirectional map from the pieces present in this chess
     *            position, to the squares they occupy
     * @param sideToMove
     *            side whose turn is to make a move - White or Black
     * @return representation of the chess position: side to move (White /
     *         Black), White and Black pieces, and their respective squares
     */
    public static ChessPosition createNewChessPositionFromPiecesToSquaresBiMap(
	    BiMap<Piece, Square> piecesWithSquares, SideToMove sideToMove) {
	return new ChessPosition(piecesWithSquares, sideToMove);
    }   

    /**
     * Creates a new chess position using the provided tablebase and the list
     * of squares for the pieces.
     * 
     * @param tablebase
     *            computerized database containing all possible legal chess
     *            positions and their evaluations, given the set of specific
     *            chess pieces
     * @param squares
     *            the squares to be assigned to the pieces in the tablebase
     * @param sideToMove
     *            side whose turn is to make a move - White or Black
     * @return representation of the chess position: side to move (White /
     *         Black), White and Black pieces, and their respective squares
     */
    public static ChessPosition createFromTablebase(Tablebase tablebase,
	    List<Square> squares, SideToMove sideToMove) {
	return new ChessPosition(tablebase, squares, sideToMove);
    }
    
    /**
     * Creates a new chess position using the provided tablebase. The squares
     * for the pieces are chosen randomly.
     * 
     * @param tablebase
     *            computerized database containing all possible legal chess
     *            positions and their evaluations, given the set of specific
     *            chess pieces
     * @param sideToMove
     *            side whose turn is to make a move - White or Black
     * @return representation of the chess position: side to move (White /
     *         Black), White and Black pieces, and their respective squares
     */
    public static ChessPosition createRandomFromTablebase(
	    Tablebase tablebase, SideToMove sideToMove) {
	BiMap<Piece, Square> piecesWithSquares = generateRandomSquaresForPieces(tablebase);
	return new ChessPosition(piecesWithSquares, sideToMove);	
    }
    
    /**
     * Generates random squares for the pieces of the provided tablebase.
     * 
     * @param tablebase
     *            computerized database containing all possible legal chess
     *            positions and their evaluations, given the set of specific
     *            chess pieces
     * @return the bidirectional map from the pieces present in this chess
     *         position, to the squares they occupy
     */
    private static BiMap<Piece, Square> generateRandomSquaresForPieces(
	    Tablebase tablebase) {
	BiMap<Piece, Square> piecesWithSquares = 
		EnumBiMap.create(Piece.class, Square.class);
	List<Piece> pieces = tablebase.getAllPieces();

	MersenneTwisterFast numberGenerator = new MersenneTwisterFast();

	Square randSquare = null;
	for (Piece piece : pieces) {
	    do {
		if (piece == Piece.WHITE_KING) {
		    int wkRand = numberGenerator.nextInt(WHITE_KING_SQUARES_COUNT);
		    randSquare = WHITE_KING_SQUARES_LIST.get(wkRand);
		} else if (piece.getPieceType() == PieceType.PAWN) {
		    int pRand = numberGenerator.nextInt(PAWN_SQUARES_COUNT);
		    randSquare = PAWN_SQUARES_LIST.get(pRand);
		} else {
		    int allRand = numberGenerator.nextInt(ALL_SQUARES_COUNT);
		    randSquare = ALL_SQUARES_LIST.get(allRand);
		}
	    } while (piecesWithSquares.containsValue(randSquare));

	    piecesWithSquares.put(piece, randSquare);
	}
	return piecesWithSquares;
    }

    /**
     * Creates a new chess positition using the provided textual drawing.
     * 
     * @param drawing
     *            diagram showing a chess position
     * @param sideToMove
     *            side whose turn is to make a move - White or Black
     * @return representation of the chess position: side to move (White /
     *         Black), White and Black pieces, and their respective squares
     */
    public static ChessPosition createFromTextualDrawing(
	    ChessPositionDiagram drawing, SideToMove sideToMove) {
	BiMap<Piece, Square> piecesWithSquares = drawing.getPiecesWithSquaresFromDiagram(); 
	return new ChessPosition(piecesWithSquares, sideToMove);
    }
    
    /**
     * Gets the bidirectional map from the pieces present in this chess
     * position, to the squares they occupy.
     * 
     * @return the bidirectional map: Piece <-> Square
     */
    public BiMap<Piece, Square> getPiecesWithSquares() {
        return piecesWithSquares;
    }

    /**
     * Sets the bidirectional map: Piece <-> Square.
     * 
     * @param piecesWithSquares
     *            bidirectional map from the pieces present in the chess
     *            position, to the squares they occupy
     */
    public void setPiecesWithSquares(EnumBiMap<Piece, Square> piecesWithSquares) {
        this.piecesWithSquares = piecesWithSquares;
    }

    /**
     * Gets the side-to-move: White or Black.
     * 
     * @return the side to make a move (either White, or Black)
     */
    public SideToMove getSideToMove() {
        return sideToMove;
    }

    /**
     * Sets the side-to-move: White or Black.
     * 
     * @param sideToMove
     *            the new side-to-move (either White, or Black)
     */
    public void setSideToMove(SideToMove sideToMove) {
        this.sideToMove = sideToMove;
    }

    /**
     * Gets the list of all White pieces present in this chess position.
     * 
     * @return the list of the White pieces, ordered by their value (from the
     *         King to the pawns)
     */
    public List<Piece> getWhitePieces() {
	List<Piece> whitePieces = new ArrayList<Piece>();
	for (Piece p : piecesWithSquares.keySet()) {
	    if (p == Piece.BLACK_KING)
		break;	    
	    whitePieces.add(p);
	}
	return whitePieces;
    }

    /**
     * Gets the list of all Black pieces present in this chess position.
     * 
     * @return the list of the Black pieces, ordered by their value (from the
     *         King to the pawns)
     */
    public List<Piece> getBlackPieces() {
	List<Piece> blackPieces = new ArrayList<Piece>();
	for (Piece p : piecesWithSquares.keySet()) {
	    if (p.getPieceColour() == PieceColour.BLACK)
		blackPieces.add(p);
	}
	return blackPieces;
    }
    
    /**
     * Cheks if this position contains the specified chess piece.
     * 
     * @param piece
     *            representation of the chess piece
     * @return true, if the specified piece is present in this chess position
     */
    public boolean containsPiece(Piece piece) {
	return piecesWithSquares.keySet().contains(piece);
    }
    
    /**
     * Gets the list of all squares occupied by White in this chess position.
     * 
     * @return the list of squares, ordered by the value of the pieces that
     *         occupy them (from the King to the pawns)
     */
    public List<Square> getWhiteSquares() {
	List<Square> whiteSquares = new ArrayList<Square>();
	for (Piece p : getWhitePieces()) {
	    whiteSquares.add(piecesWithSquares.get(p));
	}
	return whiteSquares;
    }

    /**
     * Gets the list of all squares occupied by Black in this chess position.
     * 
     * @return the list of squares, ordered by the value of the pieces that
     *         occupy them (from the King to the pawns)
     */
    public List<Square> getBlackSquares() {
	List<Square> blackSquares = new ArrayList<Square>();
	for (Piece p : getBlackPieces()) {
	    blackSquares.add(piecesWithSquares.get(p));
	}
	return blackSquares;
    }    
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime
		* result
		+ ((piecesWithSquares == null) ? 0 : piecesWithSquares
			.hashCode());
	result = prime * result
		+ ((sideToMove == null) ? 0 : sideToMove.hashCode());
	return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	ChessPosition other = (ChessPosition) obj;
	if (piecesWithSquares == null) {
	    if (other.piecesWithSquares != null)
		return false;
	} else if (!piecesWithSquares.equals(other.piecesWithSquares))
	    return false;
	if (sideToMove != other.sideToMove)
	    return false;
	return true;
    }
    
}

package chess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
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
     * Bidirectional map from the pieces present in this chess 
     * position, to the squares those pieces occupy.
     */
    private BiMap<Piece, Square> piecesWithSquares;
    
    /** The side to move - White or Black. */
    private SideToMove sideToMove;

    /**
     * Instantiates a new chess position given the Piece <--> Square bimap and
     * side-to-move arguments.
     * 
     * @param piecesWithSquares
     *            bidirectional map from the pieces present in this chess
     *            position, to the squares they occupy
     * @param sideToMove
     *            side whose turn is to make a move - White or Black
     */
    private ChessPosition(BiMap<Piece, Square> piecesWithSquares,
	    SideToMove sideToMove) {
	setPiecesWithSquares(piecesWithSquares);
	this.sideToMove = sideToMove;
    }

    /**
     * Sets the bidirectional map: Piece <-> Square. 
     * <br/>
     * <br/>
     * If the bimap argument has two or more duplicate pieces (pieces of the
     * same piece type and piece colour) as keys, it is required that the IDs of
     * the squares corresponding to those pieces do follow the same ordering as
     * the pieces themselves. So if the incorrectly ordered bimap argument is
     * passed, a new bimap with rearranged squares is created and used instead
     * of the original bimap argument (this is necessary for consistency, e.g.,
     * for the equals method to work properly).
     * 
     * @param piecesWithSquares
     *            bidirectional map from the pieces present in the chess
     *            position, to the squares they occupy
     */
    public void setPiecesWithSquares(BiMap<Piece, Square> piecesWithSquares) {
	this.piecesWithSquares = containsDuplicatePieces(piecesWithSquares) 
		? rearrangedPiecesWithSquares(piecesWithSquares)
		: piecesWithSquares;
    }

    /**
     * Checks if this position contains any duplicate pieces.
     * 
     * @param piecesWithSquares
     *            bidirectional map from the pieces present in the chess
     *            position, to the squares they occupy
     * @return true, if there are at least two same-type, same-colour pieces in
     *         this position
     */
    private boolean containsDuplicatePieces(
	    BiMap<Piece, Square> piecesWithSquares) {
	for (Piece piece : piecesWithSquares.keySet()) {
	    if (piece.isDuplicate())
		return true;
	}
	return false;
    }

    /**
     * Checks if the squares of duplicate pieces (pieces of the same piece type
     * and piece colour) are "in order", that is, if the ordering of their IDs
     * is the same as the ordering of those duplicate pieces. If so, the
     * original bimap is returned; otherwise a new bimap with the squares
     * reordered is created and returned.
     * 
     * @param originalPiecesWithSquares
     *            the original pieces with squares
     * @return the Piece <--> Square bimap with a guaranteed correct ordering of
     *         squares for duplicate pieces squares for duplicate pieces
     */
    private BiMap<Piece, Square> rearrangedPiecesWithSquares(
	    BiMap<Piece, Square> originalPiecesWithSquares) {
	EnumBiMap<Piece, Square> newPiecesWithSquares = EnumBiMap.create(
		Piece.class, Square.class);
	Set<Piece> pieces = originalPiecesWithSquares.keySet();
	List<Piece> pieceList = new ArrayList<>();
	List<Square> squareList = new ArrayList<>();
	for (Piece p : pieces) {
	    pieceList.add(p);
	    squareList.add(originalPiecesWithSquares.get(p));
	}
	for (int i = 1; i < pieceList.size(); i++) {
	    Piece currentPiece = pieceList.get(i);
	    if (currentPiece.isDuplicate()) {
		Square currentSquare = squareList.get(i);
		Square previousSquare = squareList.get(i - 1);
		if (currentSquare.getSquareID() < previousSquare.getSquareID()) {
		    squareList.set(i - 1, currentSquare);
		    squareList.set(i, previousSquare);
		    for (int j = 0; j < pieceList.size(); j++) {
			newPiecesWithSquares.put(pieceList.get(j),
				squareList.get(j));
		    }
		    return rearrangedPiecesWithSquares(newPiecesWithSquares);
		}
	    }
	}
	return originalPiecesWithSquares;
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
	checkIfAllSquaresAreDifferent(squares);
	checkIfThereAreEnoughSquaresForPieces(tablebase, squares);
	
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
    private void checkIfAllSquaresAreDifferent(List<Square> squares) {
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
    private void checkIfThereAreEnoughSquaresForPieces(
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
    public static ChessPosition createFromPiecesToSquaresBiMap(
	    BiMap<Piece, Square> piecesWithSquares, SideToMove sideToMove) {
	return new ChessPosition(piecesWithSquares, sideToMove);
    }

    /**
     * Creates a new chess position using the provided tablebase and the list of
     * squares for the pieces.
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
    public static ChessPosition createRandomFromTablebase(Tablebase tablebase,
	    SideToMove sideToMove) {
	BiMap<Piece, Square> piecesWithSquares = RandomSquareGenerator
		.generateRandomSquaresForPieces(tablebase);
	return new ChessPosition(piecesWithSquares, sideToMove);	
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
	BiMap<Piece, Square> piecesWithSquares = drawing
		.getPiecesWithSquaresFromDiagram();
	return new ChessPosition(piecesWithSquares, sideToMove);
    }

    /**
     * Creates the deep copy of the specified chess position.
     * 
     * @param originalChessposition
     *            representation of the chess position: side to move (White /
     *            Black), White and Black pieces, and their respective squares
     * @return new chess position with fields deep-copied from the original
     *         position
     */
    public static ChessPosition createDeepCopy(
	    ChessPosition originalChessposition) {
	BiMap<Piece, Square> originalPiecesWithSquares = originalChessposition
		.getPiecesWithSquares();
	BiMap<Piece, Square> newPiecesWithSquares = EnumBiMap.create(
		Piece.class, Square.class);
	for (Entry<Piece, Square> originalEntry : originalPiecesWithSquares
		.entrySet()) {
	    newPiecesWithSquares.put(originalEntry.getKey(),
		    originalEntry.getValue());
	}
	return ChessPosition.createFromPiecesToSquaresBiMap(
		newPiecesWithSquares, originalChessposition.getSideToMove());
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
     * Gets the list of all White and Black pieces present in this chess position.
     * 
     * @return the list of all pieces, ordered by their value (from the
     *         King to the pawns)
     */
    public List<Piece> getAllPieces() {
	List<Piece> allPieces = new ArrayList<Piece>();
	allPieces.addAll(piecesWithSquares.keySet());
	return allPieces;
    }
    
    /**
     * Checks if this position contains the specified chess piece.
     * 
     * @param piece
     *            representation of the chess piece
     * @return true, if the specified piece is present in this chess position
     */
    public boolean containsPiece(Piece piece) {
	return piecesWithSquares.keySet().contains(piece);
    }    
    
    /**
     * Checks if this position contains any pawns.
     * 
     * @return true, if there is at least one pawn in this position, false
     *         otherwise
     */
    public boolean containsPawns() {
	return piecesWithSquares.containsKey(Piece.WHITE_PAWN)
		|| piecesWithSquares.containsKey(Piece.BLACK_PAWN);
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

    /**
     * Gets the square the specified piece occupies in this position.
     * 
     * @param piece
     *            representation of the chess piece
     * @return chessboard square that is occupied by the piece
     */
    public Square getSquareOfPiece(Piece piece) {
	return piecesWithSquares.get(piece);
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

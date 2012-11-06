package chess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.BiMap;
import com.google.common.collect.EnumBiMap;

import tablebases.Tablebase;

/**
 * Representation of chess position: side to move, White and Black 
 * pieces, and their respective squares.
 * 
 * @author Kestutis
 *
 */
public class ChessPosition {
    private BiMap<Piece, Square> piecesWithSquares;
    private SideToMove sideToMove;
    
    private ChessPosition(BiMap<Piece, Square> piecesWithSquares,
	    SideToMove sideToMove) {
	this.piecesWithSquares = piecesWithSquares;
	this.sideToMove = sideToMove;
    }
    
    private ChessPosition(Tablebase tablebase,
	    List<Square> squares, SideToMove sideToMove) {
	piecesWithSquares = EnumBiMap.create(Piece.class, Square.class);	
	if (!allSquaresAreDifferent(squares)) {
	    throw new IllegalArgumentException("Some of the squares provided " +
	    		"are the same: " + squares.toString());
	}	
	List<Piece> pieces = tablebase.getAllPieces();
	if (pieces.size() > squares.size()) {
	    throw new IllegalArgumentException("Pieces from " +
	    		"tablebase " + tablebase + " were not provided " +
	    		"enough of corresponding squares");	
	}
	for (int i = 0; i < pieces.size(); i++) {
	    piecesWithSquares.put(pieces.get(i), squares.get(i));
	}
	this.sideToMove = sideToMove;
    }
    
    /** Checks if there are no duplicate squares in the list. */
    private static boolean allSquaresAreDifferent(List<Square> squareList) {
   	Set<Square> squareSet = new HashSet<>(squareList);
   	return squareList.size() == squareSet.size();       
    }
    
    public static ChessPosition createFromPiecesToSquaresMap(BiMap<Piece, 
	    Square> piecesWithSquares, SideToMove sideToMove) {
	return new ChessPosition(piecesWithSquares, sideToMove);
    }   

    public static ChessPosition createFromTablebase(Tablebase tablebase,
	    List<Square> squares, SideToMove sideToMove) {
	return new ChessPosition(tablebase, squares, sideToMove);
    }
    
    public BiMap<Piece, Square> getPiecesWithSquares() {
        return piecesWithSquares;
    }

    public void setPiecesWithSquares(EnumBiMap<Piece, Square> piecesWithSquares) {
        this.piecesWithSquares = piecesWithSquares;
    }

    public SideToMove getSideToMove() {
        return sideToMove;
    }

    public void setSideToMove(SideToMove sideToMove) {
        this.sideToMove = sideToMove;
    }

    public List<Piece> getWhitePieces() {
	List<Piece> whitePieces = new ArrayList<>();
	for (Piece p : piecesWithSquares.keySet()) {
	    if (p == Piece.BLACK_KING)
		break;	    
	    whitePieces.add(p);
	}
	return whitePieces;
    }

    public List<Piece> getBlackPieces() {
	List<Piece> blackPieces = new ArrayList<>();
	for (Piece p : piecesWithSquares.keySet()) {
	    if (p.getPieceColour() == PieceColour.BLACK)
		blackPieces.add(p);
	}
	return blackPieces;
    }

    public List<Square> getWhiteSquares() {
	List<Square> whiteSquares = new ArrayList<>();
	for (Piece p : getWhitePieces()) {
	    whiteSquares.add(piecesWithSquares.get(p));
	}
	return whiteSquares;
    }

    public List<Square> getBlackSquares() {
	List<Square> blackSquares = new ArrayList<>();
	for (Piece p : getBlackPieces()) {
	    blackSquares.add(piecesWithSquares.get(p));
	}
	return blackSquares;
    }    
    
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

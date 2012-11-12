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
 * Representation of chess position: side to move, White and Black 
 * pieces, and their respective squares.
 * 
 * @author Kestutis
 *
 */
public class ChessPosition {
    
    public static EnumSet<Square> whiteKingSquares = EnumSet.range(Square.A1, Square.D1); 
    static {
	whiteKingSquares.addAll(EnumSet.range(Square.A2, Square.D2));
	whiteKingSquares.addAll(EnumSet.range(Square.A3, Square.D3));
	whiteKingSquares.addAll(EnumSet.range(Square.A4, Square.D4));	
	whiteKingSquares.addAll(EnumSet.range(Square.A5, Square.D5));
	whiteKingSquares.addAll(EnumSet.range(Square.A6, Square.D6));
	whiteKingSquares.addAll(EnumSet.range(Square.A7, Square.D7));
	whiteKingSquares.addAll(EnumSet.range(Square.A8, Square.D8)); 
    }
    private static final List<Square> whiteKingSquaresList = new ArrayList<Square>(whiteKingSquares);
    public static final List<Square> pawnSquaresList = new ArrayList<Square>(EnumSet.range(Square.A7, Square.H2));
    public static final List<Square> allSquaresList = new ArrayList<Square>(EnumSet.allOf(Square.class)); 
    private static final int whiteKingSquaresCount = whiteKingSquares.size();
    private static final int pawnSquaresCount = pawnSquaresList.size();
    private static final int allSquaresCount = allSquaresList.size();
    
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
   	Set<Square> squareSet = new HashSet<Square>(squareList);
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
    
    public static ChessPosition createRandomFromTablebase(
	    Tablebase tablebase, SideToMove sideToMove) {
	BiMap<Piece, Square> piecesWithSquares = generateRandomSquaresForPieces(tablebase);
	return new ChessPosition(piecesWithSquares, sideToMove);	
    }
    
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
		    int wkRand = numberGenerator.nextInt(whiteKingSquaresCount);
		    randSquare = whiteKingSquaresList.get(wkRand);
		} else if (piece.getPieceType() == PieceType.PAWN) {
		    int pRand = numberGenerator.nextInt(pawnSquaresCount);
		    randSquare = pawnSquaresList.get(pRand);
		} else {
		    int allRand = numberGenerator.nextInt(allSquaresCount);
		    randSquare = allSquaresList.get(allRand);
		}
	    } while (piecesWithSquares.containsValue(randSquare));

	    piecesWithSquares.put(piece, randSquare);
	}
	return piecesWithSquares;
    }

    public static ChessPosition createFromTextualDrawing(
	    ChessPositionDiagram drawing, SideToMove sideToMove) {
	BiMap<Piece, Square> piecesWithSquares = drawing.getPiecesWithSquaresFromDiagram(); 
	return new ChessPosition(piecesWithSquares, sideToMove);
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
	List<Piece> whitePieces = new ArrayList<Piece>();
	for (Piece p : piecesWithSquares.keySet()) {
	    if (p == Piece.BLACK_KING)
		break;	    
	    whitePieces.add(p);
	}
	return whitePieces;
    }

    public List<Piece> getBlackPieces() {
	List<Piece> blackPieces = new ArrayList<Piece>();
	for (Piece p : piecesWithSquares.keySet()) {
	    if (p.getPieceColour() == PieceColour.BLACK)
		blackPieces.add(p);
	}
	return blackPieces;
    }

    public List<Square> getWhiteSquares() {
	List<Square> whiteSquares = new ArrayList<Square>();
	for (Piece p : getWhitePieces()) {
	    whiteSquares.add(piecesWithSquares.get(p));
	}
	return whiteSquares;
    }

    public List<Square> getBlackSquares() {
	List<Square> blackSquares = new ArrayList<Square>();
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
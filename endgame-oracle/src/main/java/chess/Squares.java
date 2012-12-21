package chess;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * Set of chessboard squares. Useful when only a subset of the 64 squares (i.e.,
 * not necessarily all of them) is needed.
 * 
 * @author Kestutis
 * 
 */
public enum Squares {

    /** The set of all 64 chessboard squares. */
    ALL_SQUARES,

    /**
     * In chess positions containing pawns, possible squares for the White King
     * can be limited to the left side of the chessboard (32 squares in total),
     * in order to exploit the chessboard symmetry in the d-e axis.
     */
    WHITE_KING_SQUARES_FOR_POSITIONS_WITH_PAWNS,

    /**
     * In chess positions containing no pawns, possible squares for the White
     * King can be limited to the a1-d1-d4 triangle of the chessboard, in order
     * to exploit the horizontal reflection (symmetry in file d - file e axis),
     * vertical reflection (symmetry in rank 4 - rank 5 axis), and diagonal
     * reflection (symmetry in diagonal a1-h8).
     */
    WHITE_KING_SQUARES_FOR_PAWNLESS_POSITIONS,

    /** Possible squares for the pawns are all squares on ranks 2 to 7. */
    PAWN_SQUARES,
    
    /** Squares A1, B2, C3, ..., H8. */
    DIAGONAL_A1_H8,
    
    /** Chessboard squares above diagonal A1-H8. */
    SQUARES_ABOVE_DIAGONAL_A1_H8

    ;

    /**
     * Collects the set of squares and returns it as a list. (Conversion to list
     * is useful, as some client classes need to pick squares randomly).
     * 
     * @return list of different squares
     */
    public Set<Square> collectSquares() {
	
	switch (this) {
	case ALL_SQUARES:
	    return EnumSet.allOf(Square.class);
	case WHITE_KING_SQUARES_FOR_POSITIONS_WITH_PAWNS:
	    EnumSet<Square> whiteKingSquaresForPositionsWithPawns = EnumSet.range(
		    Square.A1, Square.D1);
	    whiteKingSquaresForPositionsWithPawns.addAll(EnumSet.range(
		    Square.A2, Square.D2));
	    whiteKingSquaresForPositionsWithPawns.addAll(EnumSet.range(
		    Square.A3, Square.D3));
	    whiteKingSquaresForPositionsWithPawns.addAll(EnumSet.range(
		    Square.A4, Square.D4));
	    whiteKingSquaresForPositionsWithPawns.addAll(EnumSet.range(
		    Square.A5, Square.D5));
	    whiteKingSquaresForPositionsWithPawns.addAll(EnumSet.range(
		    Square.A6, Square.D6));
	    whiteKingSquaresForPositionsWithPawns.addAll(EnumSet.range(
		    Square.A7, Square.D7));
	    whiteKingSquaresForPositionsWithPawns.addAll(EnumSet.range(
		    Square.A8, Square.D8));
	    return whiteKingSquaresForPositionsWithPawns;
	case WHITE_KING_SQUARES_FOR_PAWNLESS_POSITIONS:
	    return EnumSet.of(Square.A1, Square.B1, Square.C1, Square.D1,
		    	      Square.B2, Square.C2, Square.D2,
		    	      Square.C3, Square.D3,
		    	      Square.D4);
	case PAWN_SQUARES:
	    return EnumSet.range(Square.A7, Square.H2);
	case DIAGONAL_A1_H8:
	    return EnumSet.of(Square.A1, Square.B2, Square.C3, Square.D4,
		    	      Square.E5, Square.F6, Square.G7, Square.H8);
	case SQUARES_ABOVE_DIAGONAL_A1_H8:
	    EnumSet<Square> squaresAbove_A1_H8 = EnumSet.of(Square.A2);
	    squaresAbove_A1_H8.addAll(EnumSet.of(   Square.A3, Square.B3));
	    squaresAbove_A1_H8.addAll(EnumSet.range(Square.A4, Square.C4));
	    squaresAbove_A1_H8.addAll(EnumSet.range(Square.A5, Square.D5));
	    squaresAbove_A1_H8.addAll(EnumSet.range(Square.A6, Square.E6));
	    squaresAbove_A1_H8.addAll(EnumSet.range(Square.A7, Square.F7));
	    squaresAbove_A1_H8.addAll(EnumSet.range(Square.A8, Square.G8));
	    return squaresAbove_A1_H8;
	default:
	    throw new AssertionError("Unknown square set type: " + this);
	}
    }

    /**
     * Wraps the set of squares and returns it as a list. (Conversion to list
     * is useful, as some client classes need to pick squares randomly).
     * 
     * @return list of different squares
     */
    public List<Square> getSquaresAsList() {
	return new ArrayList<Square>(collectSquares());	
    }
    
    /**
     * Computes the total number of squares this set contains.
     *
     * @return the int
     */
    public int numberOfSquares() {
	return collectSquares().size();
    }

}

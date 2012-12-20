package chess;

import static org.junit.Assert.*;

import org.junit.Test;

public class SquaresTest {

    @Test
    public void testIfSetContainsCorrectNumberOfSquares() {
	
	Squares squares = Squares.ALL_SQUARES;
	assertEquals("Set should contain all 64 squares of the chessboard", 
		64, squares.numberOfSquares());
	
	squares = Squares.PAWN_SQUARES;
	assertEquals("Set should contain the 48 squares on ranks 2 to 7 of the chessboard", 
		48, squares.numberOfSquares());
	
	squares = Squares.WHITE_KING_SQUARES_FOR_PAWNLESS_POSITIONS;
	assertEquals("Set should contain the 10 squares in the A1-D1-D4 triangle of the chessboard", 
		10, squares.numberOfSquares());
	
	squares = Squares.WHITE_KING_SQUARES_FOR_POSITIONS_WITH_PAWNS;
	assertEquals("Set should contain the 32 squares on the left half of the chessboard", 
		32, squares.numberOfSquares());
    }

}

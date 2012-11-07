package chess;

import static org.junit.Assert.*;

import org.junit.Test;

public class PieceTest {

    @Test
    public void testGetPieceAbbreviation() {
	Piece pc1 = Piece.BLACK_BISHOP_2;
	String expectedAbbreviation = "BB2";
	String actualAbbreviation = pc1.getPieceAbbreviation();
	
	assertEquals(expectedAbbreviation, actualAbbreviation);
	
	Piece pc2 = Piece.WHITE_KNIGHT;
	expectedAbbreviation = "WN";
	actualAbbreviation = pc2.getPieceAbbreviation();
	
	assertEquals(expectedAbbreviation, actualAbbreviation);
	
	Piece pc3 = Piece.WHITE_PAWN_3;
	expectedAbbreviation = "WP3";
	actualAbbreviation = pc3.getPieceAbbreviation();
	
	assertEquals(expectedAbbreviation, actualAbbreviation);
    }

    @Test
    public void testAllAbbreviationsOfPieces() {
	
    }
    
}

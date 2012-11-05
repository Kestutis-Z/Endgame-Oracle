package tablebases;

import static org.junit.Assert.*;

import org.junit.Test;

import chess.PieceColour;

public class TablebaseTest {

    @Test
    public void testHowManyChessPiecesThisTablebaseHas() {
	Tablebase tablebase1 = Tablebase.KK;
	assertEquals(2, tablebase1.chessPiecesCount());
	
	Tablebase tablebase2 = Tablebase.KRK; 
	assertEquals(3, tablebase2.chessPiecesCount());	
    }

    @Test
    public void testGetPiecesAsCharsFromTablebase() {
	Tablebase tablebase1 = Tablebase.KRPKR;
	Tablebase tablebase2 = Tablebase.KK;
	
	char[] actualWhitePieces1 = tablebase1.getPiecesAsCharsFromTablebase(PieceColour.WHITE);
	char[] expectedWhitePieces1 = { 'K', 'R', 'P' };
	
	char[] actualBlackPieces1 = tablebase1.getPiecesAsCharsFromTablebase(PieceColour.BLACK);
	char[] expectedBlackPieces1 = { 'K', 'R' };
	
	char[] actualWhitePieces2 = tablebase2.getPiecesAsCharsFromTablebase(PieceColour.WHITE);
	char[] expectedWhitePieces2 = { 'K' };
	
	char[] actualBlackPieces2 = tablebase2.getPiecesAsCharsFromTablebase(PieceColour.BLACK);
	char[] expectedBlackPieces2 = { 'K' };
	
	assertArrayEquals(expectedWhitePieces1, actualWhitePieces1);
	assertArrayEquals(expectedBlackPieces1, actualBlackPieces1);
	assertArrayEquals(expectedWhitePieces2, actualWhitePieces2);
	assertArrayEquals(expectedBlackPieces2, actualBlackPieces2);
    }
    
}

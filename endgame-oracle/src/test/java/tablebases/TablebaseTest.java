package tablebases;

import static org.junit.Assert.*;

import org.junit.Test;

import chess.Piece;
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
    
    @Test
    public void testGetTablebasePositionCount() {
	Tablebase tablebase1 = Tablebase.KRKR;
	Tablebase tablebase2 = Tablebase.KPK;
	
	long expectedPositionCount, actualPositionCount;
	
	expectedPositionCount = tablebase1.getWhiteToMovePositionCount();
	actualPositionCount = 10780728;
	assertEquals(expectedPositionCount, actualPositionCount);
	
	expectedPositionCount = tablebase1.getBlackToMovePositionCount();
	actualPositionCount = 10780728;
	assertEquals(expectedPositionCount, actualPositionCount);
	
	expectedPositionCount = tablebase2.getWhiteToMovePositionCount();
	actualPositionCount = 163328;
	assertEquals(expectedPositionCount, actualPositionCount);
	
	expectedPositionCount = tablebase2.getBlackToMovePositionCount();
	actualPositionCount = 168024;
	assertEquals(expectedPositionCount, actualPositionCount);
    }
    
    @Test
    public void testGetWhitePieces() {
	Tablebase tablebase1 = Tablebase.KRPKR;
	Piece[] expectedPieces = new Piece[] { Piece.WHITE_KING, 
		Piece.WHITE_ROOK, Piece.WHITE_PAWN };	
	Piece[] actualPieces = tablebase1.getWhitePieces().toArray(new Piece[0]);
	
	assertArrayEquals(expectedPieces, actualPieces);	
    }
    
    @Test
    public void testGetBlackPieces() {
	Tablebase tablebase1 = Tablebase.KRPKR;
	Piece[] expectedPieces = new Piece[] { Piece.BLACK_KING, Piece.BLACK_ROOK };	
	Piece[] actualPieces = tablebase1.getBlackPieces().toArray(new Piece[0]);
	
	assertArrayEquals(expectedPieces, actualPieces);	
    }
    
    @Test
    public void testGetConsequtiveDuplicateCharsInArrayUpToIndex() {
	char[] chArr = { 'K', 'Q', 'Q', 'P', 'P', 'P', 'P' };
	
	int[] actualDuplicates = new int[chArr.length];
	for (int i = 0; i < actualDuplicates.length; i++) {
	    actualDuplicates[i] = 
		    Tablebase.getConsequtiveDuplicateCharsInArrayUpToIndex(chArr, i);
	}
	
	int[] expectedDuplicates = { 1, 1, 2, 1, 2, 3, 4 };
	
	assertArrayEquals(expectedDuplicates, actualDuplicates);	
    }
    
}

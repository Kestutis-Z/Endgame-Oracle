package tablebases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import chess.Piece;
import chess.PieceColour;

public class TablebaseTest {

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
    
    @Test
    public void testGetBlackPieces() {
	Tablebase tablebase1 = Tablebase.KRPKR;
	Piece[] expectedPieces = new Piece[] { Piece.BLACK_KING, Piece.BLACK_ROOK };	
	Piece[] actualPieces = tablebase1.getBlackPieces().toArray(new Piece[0]);
	
	assertArrayEquals(expectedPieces, actualPieces);	
    }
    
    @Test
    public void testGetAllPieces() {
	List<Piece> expectedPieces = new ArrayList<Piece>();
	expectedPieces.add(Piece.WHITE_KING);
	expectedPieces.add(Piece.WHITE_ROOK);
	expectedPieces.add(Piece.WHITE_PAWN);
	expectedPieces.add(Piece.BLACK_KING);
	expectedPieces.add(Piece.BLACK_ROOK);

	List<Piece> actualPieces = Tablebase.KRPKR.getAllPieces();

	assertEquals(expectedPieces, actualPieces);
    }
    
    @Test
    public void testRemovingOnePieceFromTablebase() {
	Tablebase tablebase = Tablebase.KRPKR;
	Tablebase expectedTablebase = Tablebase.KRKP;
	Tablebase actualTablebase = tablebase.removePiece(Piece.WHITE_ROOK);
	
	assertEquals(expectedTablebase, actualTablebase);
    }
    
    @Test
    public void testTablebaseToStringOutput() {
	Tablebase tablebase = Tablebase.KPK;
	String actualString = tablebase.toString();
	String expectedString = "KING + PAWN vs. KING";
	
	assertEquals(expectedString, actualString);
    }
    
}

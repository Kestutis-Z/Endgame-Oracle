package chess;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
	List<String> expectedList = new ArrayList<String>();
	
	expectedList.add("WK");
	expectedList.add("WQ");
	expectedList.add("WQ2");
	expectedList.add("WR");
	expectedList.add("WR2");
	expectedList.add("WB");
	expectedList.add("WB2");
	expectedList.add("WN");
	expectedList.add("WN2");
	expectedList.add("WP");
	expectedList.add("WP2");
	expectedList.add("WP3");
	
	expectedList.add("BK");
	expectedList.add("BQ");
	expectedList.add("BQ2");
	expectedList.add("BR");
	expectedList.add("BR2");
	expectedList.add("BB");
	expectedList.add("BB2");
	expectedList.add("BN");
	expectedList.add("BN2");
	expectedList.add("BP");
	expectedList.add("BP2");
	expectedList.add("BP3");
	
	List<String> actualList = Piece.allAbbreviationsOfPieces();
	
	assertEquals(expectedList, actualList);
    }    
    
    @Test
    public void testGetPieceFromAbbreviation() {
	String abbr1 = "BR2";
	Piece expectedPiece = Piece.BLACK_ROOK_2;
	Piece actualPiece = Piece.getPieceFromAbbreviation(abbr1);
	
	assertEquals(expectedPiece, actualPiece);
	
	String abbr2 = "WQ";
	expectedPiece = Piece.WHITE_QUEEN;
	actualPiece = Piece.getPieceFromAbbreviation(abbr2);
	
	assertEquals(expectedPiece, actualPiece);
    }
    
}

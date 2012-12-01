package chess;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    
    @Test
    public void testGetControlSquares() throws IncorrectChessDiagramDrawingException {
	
	String drawing =  		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|  BK  |      |  BR  |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|  BN  |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |  WB  |  WB2 |      |  WN2 |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |      |      |  WP  |      |  BP  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|  WK  |      |  BN2 |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|  WQ  |      |      |      |  WP2 |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|      |      |      |      |      |      |      |  WN  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;
	
	ChessPositionDiagram cpDiagram = 
		ChessPositionDiagram.createFromTextDiagram(drawing);
	ChessPosition chessPosition = ChessPosition
		.createFromTextualDrawing(cpDiagram, SideToMove.BLACK);
	
	Set<Square> expectedAttackSquares_WK = new HashSet<>();
	expectedAttackSquares_WK.add(Square.A2);
	expectedAttackSquares_WK.add(Square.A4);
	expectedAttackSquares_WK.add(Square.B2);
	expectedAttackSquares_WK.add(Square.B3);
	expectedAttackSquares_WK.add(Square.B4);
	Set<Square> actualAttackSquares_WK = Piece.WHITE_KING.getControlSquares(chessPosition);
	assertEquals(expectedAttackSquares_WK, actualAttackSquares_WK);
	
	Set<Square> expectedAttackSquares_BK = new HashSet<>();
	expectedAttackSquares_BK.add(Square.A7);
	expectedAttackSquares_BK.add(Square.B7);
	expectedAttackSquares_BK.add(Square.B8);
	Set<Square> actualAttackSquares_BK = Piece.BLACK_KING.getControlSquares(chessPosition);
	assertEquals(expectedAttackSquares_BK, actualAttackSquares_BK);
	
	Set<Square> expectedAttackSquares_WQ = new HashSet<>();
	expectedAttackSquares_WQ.add(Square.A1);
	expectedAttackSquares_WQ.add(Square.A3);
	expectedAttackSquares_WQ.add(Square.B1);
	expectedAttackSquares_WQ.add(Square.B2);
	expectedAttackSquares_WQ.add(Square.B3);
	expectedAttackSquares_WQ.add(Square.C2);
	expectedAttackSquares_WQ.add(Square.C4);
	expectedAttackSquares_WQ.add(Square.D2);
	expectedAttackSquares_WQ.add(Square.D5);
	expectedAttackSquares_WQ.add(Square.E2);
	Set<Square> actualAttackSquares_WQ = Piece.WHITE_QUEEN.getControlSquares(chessPosition);
	assertEquals(expectedAttackSquares_WQ, actualAttackSquares_WQ);
	
	Set<Square> expectedAttackSquares_BR = new HashSet<>();
	expectedAttackSquares_BR.add(Square.A8);
	expectedAttackSquares_BR.add(Square.B8);
	expectedAttackSquares_BR.addAll(Square.getRectangleZone(Square.C3, Square.C7));
	expectedAttackSquares_BR.addAll(Square.getRectangleZone(Square.D8, Square.H8));
	Set<Square> actualAttackSquares_BR = Piece.BLACK_ROOK.getControlSquares(chessPosition);
	assertEquals(expectedAttackSquares_BR, actualAttackSquares_BR);
	
	Set<Square> expectedAttackSquares_WB = new HashSet<>();
	expectedAttackSquares_WB.add(Square.A2);
	expectedAttackSquares_WB.add(Square.A8);
	expectedAttackSquares_WB.add(Square.B3);
	expectedAttackSquares_WB.add(Square.B7);
	expectedAttackSquares_WB.add(Square.C4);
	expectedAttackSquares_WB.add(Square.C6);
	expectedAttackSquares_WB.add(Square.E4);
	expectedAttackSquares_WB.add(Square.E6);
	expectedAttackSquares_WB.add(Square.F3);
	expectedAttackSquares_WB.add(Square.F7);
	expectedAttackSquares_WB.add(Square.G2);
	expectedAttackSquares_WB.add(Square.G8);
	expectedAttackSquares_WB.add(Square.H1);
	Set<Square> actualAttackSquares_WB = Piece.WHITE_BISHOP.getControlSquares(chessPosition);
	assertEquals(expectedAttackSquares_WB, actualAttackSquares_WB);
	
	Set<Square> expectedAttackSquares_WB2 = new HashSet<>();
	expectedAttackSquares_WB2.add(Square.B8);
	expectedAttackSquares_WB2.add(Square.C3);
	expectedAttackSquares_WB2.add(Square.C7);
	expectedAttackSquares_WB2.add(Square.D4);
	expectedAttackSquares_WB2.add(Square.D6);
	expectedAttackSquares_WB2.add(Square.F4);
	expectedAttackSquares_WB2.add(Square.F6);
	expectedAttackSquares_WB2.add(Square.G7);
	expectedAttackSquares_WB2.add(Square.H8);
	Set<Square> actualAttackSquares_WB2 = Piece.WHITE_BISHOP_2.getControlSquares(chessPosition);
	assertEquals(expectedAttackSquares_WB2, actualAttackSquares_WB2);
	
	Set<Square> expectedAttackSquares_WN = new HashSet<>();
	expectedAttackSquares_WN.add(Square.F2);
	expectedAttackSquares_WN.add(Square.G3);
	Set<Square> actualAttackSquares_WN = Piece.WHITE_KNIGHT.getControlSquares(chessPosition);
	assertEquals(expectedAttackSquares_WN, actualAttackSquares_WN);
	
	Set<Square> expectedAttackSquares_WN2 = new HashSet<>();
	expectedAttackSquares_WN2.add(Square.E4);
	expectedAttackSquares_WN2.add(Square.E6);
	expectedAttackSquares_WN2.add(Square.F3);
	expectedAttackSquares_WN2.add(Square.F7);
	expectedAttackSquares_WN2.add(Square.H3);
	expectedAttackSquares_WN2.add(Square.H7);
	Set<Square> actualAttackSquares_WN2 = Piece.WHITE_KNIGHT_2.getControlSquares(chessPosition);
	assertEquals(expectedAttackSquares_WN2, actualAttackSquares_WN2);
	
	Set<Square> expectedAttackSquares_BN = new HashSet<>();
	expectedAttackSquares_BN.add(Square.B5);
	expectedAttackSquares_BN.add(Square.C6);
	expectedAttackSquares_BN.add(Square.C8);
	Set<Square> actualAttackSquares_BN = Piece.BLACK_KNIGHT.getControlSquares(chessPosition);
	assertEquals(expectedAttackSquares_BN, actualAttackSquares_BN);
	
	Set<Square> expectedAttackSquares_BN2 = new HashSet<>();
	expectedAttackSquares_BN2.add(Square.A2);
	expectedAttackSquares_BN2.add(Square.A4);
	expectedAttackSquares_BN2.add(Square.B1);
	expectedAttackSquares_BN2.add(Square.B5);
	expectedAttackSquares_BN2.add(Square.D1);
	expectedAttackSquares_BN2.add(Square.D5);
	expectedAttackSquares_BN2.add(Square.E2);
	expectedAttackSquares_BN2.add(Square.E4);
	Set<Square> actualAttacksquares_BN2 = Piece.BLACK_KNIGHT_2.getControlSquares(chessPosition);
	assertEquals(expectedAttackSquares_BN2, actualAttacksquares_BN2);
	
	Set<Square> expectedAttackSquares_WP = new HashSet<>();
	expectedAttackSquares_WP.add(Square.E5);
	expectedAttackSquares_WP.add(Square.G5);
	Set<Square> actualAttackSquares_WP = Piece.WHITE_PAWN.getControlSquares(chessPosition);
	assertEquals(expectedAttackSquares_WP, actualAttackSquares_WP);
	
	Set<Square> expectedAttackSquares_WP2 = new HashSet<>();
	expectedAttackSquares_WP2.add(Square.D3);
	expectedAttackSquares_WP2.add(Square.F3);
	Set<Square> actualAttackSquares_WP2 = Piece.WHITE_PAWN_2.getControlSquares(chessPosition);
	assertEquals(expectedAttackSquares_WP2, actualAttackSquares_WP2);
	
	Set<Square> expectedAttackSquares_BP = new HashSet<>();
	expectedAttackSquares_BP.add(Square.G3);
	Set<Square> actualAttackSquares_BP = Piece.BLACK_PAWN.getControlSquares(chessPosition);
	assertEquals(expectedAttackSquares_BP, actualAttackSquares_BP);
    }
    
}

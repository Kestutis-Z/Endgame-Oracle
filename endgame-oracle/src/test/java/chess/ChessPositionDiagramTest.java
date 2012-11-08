package chess;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.common.collect.BiMap;
import com.google.common.collect.EnumBiMap;

public class ChessPositionDiagramTest {

    @Test
    public void testGetChessPositionDiagram() throws IncorrectChessDiagramDrawingException {
	String drawing =  		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|  WK  |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|      |      |      |  WP  |      |      |  WP2 |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|      |      |      |      |  BR  |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |  BN  |      |      |      |      |  BK  |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;
	
	ChessPositionDiagram chessPositionDiagram = 
		ChessPositionDiagram.createFromTextDiagram(drawing);
	
	String actual = chessPositionDiagram.getDiagram();
	
	assertEquals(drawing, actual);
    }
    
    @Test
    public void testGetPiecesWithSquares() throws IncorrectChessDiagramDrawingException {
	String drawing =  		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|      |      |      |      |      |      |  BP3 |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |      |  WB  |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |      |      |      |      |  BP  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|  WK  |      |      |      |  WN  |      |      |  BP2 |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |      |      |      |      |      |  BK  |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;
	
	ChessPositionDiagram cpDiagram = ChessPositionDiagram.createFromTextDiagram(drawing);
	
	BiMap<Piece, Square> expectedPiecesWithSquaresKBNKPPP = 
		EnumBiMap.create(Piece.class, Square.class);	
	expectedPiecesWithSquaresKBNKPPP.put(Piece.WHITE_KING, Square.A3);
	expectedPiecesWithSquaresKBNKPPP.put(Piece.WHITE_BISHOP, Square.E5);
	expectedPiecesWithSquaresKBNKPPP.put(Piece.WHITE_KNIGHT, Square.E3);
	expectedPiecesWithSquaresKBNKPPP.put(Piece.BLACK_KING, Square.G2);
	expectedPiecesWithSquaresKBNKPPP.put(Piece.BLACK_PAWN, Square.H4);
	expectedPiecesWithSquaresKBNKPPP.put(Piece.BLACK_PAWN_2, Square.H3);
	expectedPiecesWithSquaresKBNKPPP.put(Piece.BLACK_PAWN_3, Square.G6);
	
	BiMap<Piece, Square> actualPiecesWithSquaresKBNKPPP = cpDiagram.getPiecesWithSquaresFromDiagram();
	
	assertEquals(expectedPiecesWithSquaresKBNKPPP, actualPiecesWithSquaresKBNKPPP);
    }    

}

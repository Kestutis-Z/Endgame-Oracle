package chess;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.BiMap;
import com.google.common.collect.EnumBiMap;

import tablebases.Tablebase;

public class ChessPositionTest {
    private ChessPosition mockedChessPosition;
    private ChessPosition chessPositionFromKRPKR, chessPositionFromKBNKPPP;    

    @Before
    public void setUp() throws Exception {
	mockedChessPosition = mock(ChessPosition.class);
	
	List<Square> squaresKRPKR = new ArrayList<Square>();
	squaresKRPKR.add(Square.E5);
	squaresKRPKR.add(Square.E3);
	squaresKRPKR.add(Square.D5);
	squaresKRPKR.add(Square.H8);
	squaresKRPKR.add(Square.D1);
	chessPositionFromKRPKR = 
		ChessPosition.createFromTablebase(
		Tablebase.KRPKR, squaresKRPKR, SideToMove.WHITE);
	
	BiMap<Piece, Square> piecesWithSquaresKBNKPPP = 
		EnumBiMap.create(Piece.class, Square.class);	
	piecesWithSquaresKBNKPPP.put(Piece.WHITE_KING, Square.A3);
	piecesWithSquaresKBNKPPP.put(Piece.WHITE_BISHOP, Square.E5);
	piecesWithSquaresKBNKPPP.put(Piece.WHITE_KNIGHT, Square.E3);
	piecesWithSquaresKBNKPPP.put(Piece.BLACK_KING, Square.G2);
	piecesWithSquaresKBNKPPP.put(Piece.BLACK_PAWN, Square.H4);
	piecesWithSquaresKBNKPPP.put(Piece.BLACK_PAWN_2, Square.H3);
	piecesWithSquaresKBNKPPP.put(Piece.BLACK_PAWN_3, Square.G6);
	
	chessPositionFromKBNKPPP = 
		ChessPosition.createNewChessPositionFromPiecesToSquaresBiMap(
			piecesWithSquaresKBNKPPP, SideToMove.BLACK);
		
    }

    @Test
    public void testGetPiecesAndSquaresMap() {	
	mockedChessPosition.getPiecesWithSquares();
	verify(mockedChessPosition).getPiecesWithSquares();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetSquaresToPiecesMapThrowsIllegalArgumentException() {
	List<Square> squares2 = new ArrayList<Square>();
	squares2.add(Square.E5);
	squares2.add(Square.E5);
	squares2.add(Square.D5);
	squares2.add(Square.H8);
	squares2.add(Square.D1);
	ChessPosition chessPositionFromTbDuplicateSquares = 
		ChessPosition.createFromTablebase(
		Tablebase.KRPKR, squares2, SideToMove.WHITE);
	Map<Piece,Square> actualMap = chessPositionFromTbDuplicateSquares.getPiecesWithSquares();
	
	assertEquals(5, actualMap.keySet().size());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetPiecesToSquaresThrowsIllegalArgumentException() {
	List<Square> squares3 = new ArrayList<Square>();
	squares3.add(Square.E3);
	squares3.add(Square.D5);
	squares3.add(Square.H8);
	squares3.add(Square.D1);
	ChessPosition chessPositionFromTbTooFewSquares = 
		ChessPosition.createFromTablebase(
			Tablebase.KRPKR, squares3, SideToMove.WHITE);
	Map<Piece,Square> actualMap = 
		chessPositionFromTbTooFewSquares.getPiecesWithSquares();
	
	assertEquals(4, actualMap.keySet().size());
    }
    
    @Test
    public void testGetWhitePieces() {
	List<Piece> expectedWhitePiecesKRPKR = new ArrayList<Piece>();
	expectedWhitePiecesKRPKR.add(Piece.WHITE_KING);
	expectedWhitePiecesKRPKR.add(Piece.WHITE_ROOK);
	expectedWhitePiecesKRPKR.add(Piece.WHITE_PAWN);
	
	List<Piece> actualWhitePiecesKRPKR = chessPositionFromKRPKR.getWhitePieces();
	
	assertEquals(expectedWhitePiecesKRPKR, actualWhitePiecesKRPKR);
	
	List<Piece> expectedWhitePiecesKBNKPPP = new ArrayList<Piece>();
	expectedWhitePiecesKBNKPPP.add(Piece.WHITE_KING);
	expectedWhitePiecesKBNKPPP.add(Piece.WHITE_BISHOP);
	expectedWhitePiecesKBNKPPP.add(Piece.WHITE_KNIGHT);
	
	List<Piece> actualWhitePiecesKBNKPPP = chessPositionFromKBNKPPP.getWhitePieces();
	
	assertEquals(expectedWhitePiecesKBNKPPP, actualWhitePiecesKBNKPPP);
    }
    
    @Test
    public void testGetBlackPieces() {
	List<Piece> expectedBlackPiecesKRPKR = new ArrayList<Piece>();
	expectedBlackPiecesKRPKR.add(Piece.BLACK_KING);
	expectedBlackPiecesKRPKR.add(Piece.BLACK_ROOK);
	
	List<Piece> actualBlackPiecesKRPKR = chessPositionFromKRPKR.getBlackPieces();
	
	assertEquals(expectedBlackPiecesKRPKR, actualBlackPiecesKRPKR);
	
	List<Piece> expectedBlackPiecesKBNKPPP = new ArrayList<Piece>();
	expectedBlackPiecesKBNKPPP.add(Piece.BLACK_KING);
	expectedBlackPiecesKBNKPPP.add(Piece.BLACK_PAWN);
	expectedBlackPiecesKBNKPPP.add(Piece.BLACK_PAWN_2);
	expectedBlackPiecesKBNKPPP.add(Piece.BLACK_PAWN_3);
	
	List<Piece> actualBlackPiecesKBNKPPP = chessPositionFromKBNKPPP.getBlackPieces();
	
	assertEquals(expectedBlackPiecesKBNKPPP, actualBlackPiecesKBNKPPP);
    }
    
    @Test
    public void testGetWhiteSquares() {
	List<Square> expectedWhiteSquaresKRPKR = new ArrayList<Square>();
	expectedWhiteSquaresKRPKR.add(Square.E5);
	expectedWhiteSquaresKRPKR.add(Square.E3);
	expectedWhiteSquaresKRPKR.add(Square.D5);

	List<Square> actualWhiteSquaresKRPKR = chessPositionFromKRPKR.getWhiteSquares();
	
	assertEquals(expectedWhiteSquaresKRPKR, actualWhiteSquaresKRPKR);
	
	List<Square> expectedWhiteSquaresKBNKPPP = new ArrayList<Square>();
	expectedWhiteSquaresKBNKPPP.add(Square.A3);
	expectedWhiteSquaresKBNKPPP.add(Square.E5);
	expectedWhiteSquaresKBNKPPP.add(Square.E3);
	
	List<Square> actualWhiteSquaresKBNKPPP = chessPositionFromKBNKPPP.getWhiteSquares();
	
	assertEquals(expectedWhiteSquaresKBNKPPP, actualWhiteSquaresKBNKPPP);
    }
    
    @Test
    public void testGetBlackSquares() {
	List<Square> expectedBlackSquaresKRPKR = new ArrayList<Square>();
	expectedBlackSquaresKRPKR.add(Square.H8);
	expectedBlackSquaresKRPKR.add(Square.D1);
	
	List<Square> actualBlackSquaresKRPKR = chessPositionFromKRPKR.getBlackSquares();
	
	assertEquals(expectedBlackSquaresKRPKR, actualBlackSquaresKRPKR);
	
	List<Square> expectedBlackSquaresKBNKPPP = new ArrayList<Square>();
	expectedBlackSquaresKBNKPPP.add(Square.G2);
	expectedBlackSquaresKBNKPPP.add(Square.H4);
	expectedBlackSquaresKBNKPPP.add(Square.H3);
	expectedBlackSquaresKBNKPPP.add(Square.G6);
	
	List<Square> actualBlackSquaresKBNKPPP = chessPositionFromKBNKPPP.getBlackSquares();
	
	assertEquals(expectedBlackSquaresKBNKPPP, actualBlackSquaresKBNKPPP);
    }
    
    @Test
    public void testCreateFromTextualDrawing() throws IncorrectChessDiagramDrawingException {
	
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
	
	ChessPositionDiagram cpDiagram = 
		ChessPositionDiagram.createFromTextDiagram(drawing);
	ChessPosition chessPosition = ChessPosition
		.createFromTextualDrawing(cpDiagram, SideToMove.BLACK);
	
	List<Piece> expectedWhitePieces = new ArrayList<Piece>();
	expectedWhitePieces.add(Piece.WHITE_KING);
	expectedWhitePieces.add(Piece.WHITE_BISHOP);
	expectedWhitePieces.add(Piece.WHITE_KNIGHT);
	List<Piece> actualWhitePieces = chessPosition.getWhitePieces();
	
	assertEquals(expectedWhitePieces, actualWhitePieces);
	
	List<Square> expectedBlackSquares = new ArrayList<Square>();
	expectedBlackSquares.add(Square.G2);
	expectedBlackSquares.add(Square.H4);
	expectedBlackSquares.add(Square.H3);
	expectedBlackSquares.add(Square.G6);
	List<Square> actualBlackSquares = chessPosition.getBlackSquares();
	
	assertEquals(expectedBlackSquares, actualBlackSquares);	
    }
    
    @Test(expected = IncorrectChessDiagramDrawingException.class)
    public void testCreateFromTextDiagramThrowsIncorrectChessDiagramDrawingException() 
	    throws IncorrectChessDiagramDrawingException, InterruptedException {
	
	String incorrectDrawingWithTheTopShiftedOnePositionToTheRight =  
		
		    "     _______________________________________________________   \n" +
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
	
	ChessPositionDiagram cpDiagram = 
		ChessPositionDiagram.createFromTextDiagram(
			incorrectDrawingWithTheTopShiftedOnePositionToTheRight);
	
	cpDiagram.wait();
    }
    
    @Test
    public void testIfContainsPiece() throws IncorrectChessDiagramDrawingException {
	
	String drawing =  		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |      |  WB  |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |      |      |      |      |  BP  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|  WK  |      |      |      |  WN  |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |      |      |      |      |      |  BK  |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;
	
	ChessPositionDiagram cpDiagram = 
		ChessPositionDiagram.createFromTextDiagram(drawing);
	ChessPosition chessPosition = ChessPosition
		.createFromTextualDrawing(cpDiagram, SideToMove.BLACK);
	
	assertTrue(chessPosition.containsPiece(Piece.BLACK_KING));
	assertTrue(chessPosition.containsPiece(Piece.WHITE_KNIGHT));
	assertFalse(chessPosition.containsPiece(Piece.BLACK_QUEEN));
	assertFalse(chessPosition.containsPiece(Piece.WHITE_ROOK_2));
    }

    @Test
    public void testGetSquareOfPiece() throws IncorrectChessDiagramDrawingException {
	
	String drawing =  		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|  BK  |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |  WB  |  WB2 |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |      |      |      |      |  BP  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|  WK  |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;
	
	ChessPositionDiagram cpDiagram = 
		ChessPositionDiagram.createFromTextDiagram(drawing);
	ChessPosition chessPosition = ChessPosition
		.createFromTextualDrawing(cpDiagram, SideToMove.BLACK);
	
	Square expectedSquareOfWhiteBishop = Square.D5;
	Square expectedSquareOfWhiteBishop2 = Square.E5;
	Square expectedSquareOfBlacKing = Square.A8;
	
	Square actualSquareOfWhiteBishop = chessPosition.getSquareOfPiece(Piece.WHITE_BISHOP);
	Square actualSquareOfWhiteBishop2 = chessPosition.getSquareOfPiece(Piece.WHITE_BISHOP_2);
	Square actualSquareOfBlacKing = chessPosition.getSquareOfPiece(Piece.BLACK_KING);
	
	assertEquals(expectedSquareOfWhiteBishop, actualSquareOfWhiteBishop);
	assertEquals(expectedSquareOfWhiteBishop2, actualSquareOfWhiteBishop2);
	assertEquals(expectedSquareOfBlacKing, actualSquareOfBlacKing);
    }

}

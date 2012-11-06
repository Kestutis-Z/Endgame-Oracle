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
	
	List<Square> squaresKRPKR = new ArrayList<>();
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
		ChessPosition.createFromPiecesToSquaresMap(
			piecesWithSquaresKBNKPPP, SideToMove.BLACK);
		
    }

    @Test
    public void getPiecesAndSquaresMapTest1() {	
	mockedChessPosition.getPiecesWithSquares();
	verify(mockedChessPosition).getPiecesWithSquares();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void getSquaresToPiecesMapThrowsIllegalArgumentExceptionTest1() {
	List<Square> squares2 = new ArrayList<>();
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
    public void getPiecesToSquaresThrowsIllegalArgumentExceptionTest2() {
	List<Square> squares3 = new ArrayList<>();
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
    public void getWhitePiecesTest() {
	List<Piece> expectedWhitePiecesKRPKR = new ArrayList<>();
	expectedWhitePiecesKRPKR.add(Piece.WHITE_KING);
	expectedWhitePiecesKRPKR.add(Piece.WHITE_ROOK);
	expectedWhitePiecesKRPKR.add(Piece.WHITE_PAWN);
	
	List<Piece> actualWhitePiecesKRPKR = chessPositionFromKRPKR.getWhitePieces();
	
	assertEquals(expectedWhitePiecesKRPKR, actualWhitePiecesKRPKR);
	
	List<Piece> expectedWhitePiecesKBNKPPP = new ArrayList<>();
	expectedWhitePiecesKBNKPPP.add(Piece.WHITE_KING);
	expectedWhitePiecesKBNKPPP.add(Piece.WHITE_BISHOP);
	expectedWhitePiecesKBNKPPP.add(Piece.WHITE_KNIGHT);
	
	List<Piece> actualWhitePiecesKBNKPPP = chessPositionFromKBNKPPP.getWhitePieces();
	
	assertEquals(expectedWhitePiecesKBNKPPP, actualWhitePiecesKBNKPPP);
    }
    
    @Test
    public void getBlackPiecesTest() {
	List<Piece> expectedBlackPiecesKRPKR = new ArrayList<>();
	expectedBlackPiecesKRPKR.add(Piece.BLACK_KING);
	expectedBlackPiecesKRPKR.add(Piece.BLACK_ROOK);
	
	List<Piece> actualBlackPiecesKRPKR = chessPositionFromKRPKR.getBlackPieces();
	
	assertEquals(expectedBlackPiecesKRPKR, actualBlackPiecesKRPKR);
	
	List<Piece> expectedBlackPiecesKBNKPPP = new ArrayList<>();
	expectedBlackPiecesKBNKPPP.add(Piece.BLACK_KING);
	expectedBlackPiecesKBNKPPP.add(Piece.BLACK_PAWN);
	expectedBlackPiecesKBNKPPP.add(Piece.BLACK_PAWN_2);
	expectedBlackPiecesKBNKPPP.add(Piece.BLACK_PAWN_3);
	
	List<Piece> actualBlackPiecesKBNKPPP = chessPositionFromKBNKPPP.getBlackPieces();
	
	assertEquals(expectedBlackPiecesKBNKPPP, actualBlackPiecesKBNKPPP);
    }
    
    @Test
    public void getWhiteSquaresTest() {
	List<Square> expectedWhiteSquaresKRPKR = new ArrayList<>();
	expectedWhiteSquaresKRPKR.add(Square.E5);
	expectedWhiteSquaresKRPKR.add(Square.E3);
	expectedWhiteSquaresKRPKR.add(Square.D5);

	List<Square> actualWhiteSquaresKRPKR = chessPositionFromKRPKR.getWhiteSquares();
	
	assertEquals(expectedWhiteSquaresKRPKR, actualWhiteSquaresKRPKR);
	
	List<Square> expectedWhiteSquaresKBNKPPP = new ArrayList<>();
	expectedWhiteSquaresKBNKPPP.add(Square.A3);
	expectedWhiteSquaresKBNKPPP.add(Square.E5);
	expectedWhiteSquaresKBNKPPP.add(Square.E3);
	
	List<Square> actualWhiteSquaresKBNKPPP = chessPositionFromKBNKPPP.getWhiteSquares();
	
	assertEquals(expectedWhiteSquaresKBNKPPP, actualWhiteSquaresKBNKPPP);
    }
    
    @Test
    public void getBlackSquaresTest() {
	List<Square> expectedBlackSquaresKRPKR = new ArrayList<>();
	expectedBlackSquaresKRPKR.add(Square.H8);
	expectedBlackSquaresKRPKR.add(Square.D1);
	
	List<Square> actualBlackSquaresKRPKR = chessPositionFromKRPKR.getBlackSquares();
	
	assertEquals(expectedBlackSquaresKRPKR, actualBlackSquaresKRPKR);
	
	List<Square> expectedBlackSquaresKBNKPPP = new ArrayList<>();
	expectedBlackSquaresKBNKPPP.add(Square.G2);
	expectedBlackSquaresKBNKPPP.add(Square.H4);
	expectedBlackSquaresKBNKPPP.add(Square.H3);
	expectedBlackSquaresKBNKPPP.add(Square.G6);
	
	List<Square> actualBlackSquaresKBNKPPP = chessPositionFromKBNKPPP.getBlackSquares();
	
	assertEquals(expectedBlackSquaresKBNKPPP, actualBlackSquaresKBNKPPP);
    }
    
    
    
}

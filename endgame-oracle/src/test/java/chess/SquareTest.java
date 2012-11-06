package chess;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.BiMap;
import com.google.common.collect.EnumBiMap;

import chess.Square.Direction;

public class SquareTest {

    private Square squareA1, squareE3, squareH8;
    private Square[] testedSquares;
    private int testedSquaresCount;
    private Set<Direction> allDirectionsClockwiseFromEastToNorthEast;
    private ChessPosition chessPosition1;
    
    @Before
    public void setUp() throws Exception {
	squareA1 = Square.A1;
	squareE3 = Square.E3;
	squareH8 = Square.H8;
	testedSquares = new Square[] { squareA1, squareE3, squareH8 };
	testedSquaresCount = testedSquares.length;
	allDirectionsClockwiseFromEastToNorthEast = EnumSet.allOf(Direction.class);
	
	BiMap<Piece, Square> map1 = EnumBiMap.create(Piece.class, Square.class);
	map1.put(Piece.WHITE_KING, Square.E5);
	map1.put(Piece.WHITE_PAWN, Square.D5);
	map1.put(Piece.BLACK_KING, Square.H8);
	map1.put(Piece.BLACK_ROOK, Square.D1);
	chessPosition1 = ChessPosition
		.createFromPiecesToSquaresMap(map1, SideToMove.WHITE); 
    }

    @Test
    public void testGetSquareID() {
	int[] expectedIDs = new int[] { 11, 53, 88 };
	int[] actualIDs = new int[testedSquaresCount];
	for (int i = 0; i < testedSquaresCount; i++) {
	    actualIDs[i] = testedSquares[i].getSquareID();
	}
	
	assertArrayEquals(expectedIDs, actualIDs);
    }
    
    @Test
    public void testGetSquareFile() {
	int[] expectedFiles = new int[] { 1, 5, 8 };
	int[] actualFiles = new int[testedSquaresCount];
	for (int i = 0; i < testedSquaresCount; i++) {
	    actualFiles[i] = testedSquares[i].getFile();
	}
	
	assertArrayEquals(expectedFiles, actualFiles);
    }
    
    @Test
    public void testGetSquareRank() {
	int[] expectedRanks = new int[] { 1, 3, 8 };
	int[] actualRanks = new int[testedSquaresCount];
	for (int i = 0; i < testedSquaresCount; i++) {
	    actualRanks[i] = testedSquares[i].getRank();
	}
	
	assertArrayEquals(expectedRanks, actualRanks);
    }
    
    @Test
    public void testGetSquareFromFileAndRank() {
	Square[] expectedSquares = testedSquares;
	Square[] actualSquares = new Square[] {
	    Square.getSquareFromFileAndRank(1, 1),
	    Square.getSquareFromFileAndRank(5, 3),
	    Square.getSquareFromFileAndRank(8, 8) 
	};
	
	assertArrayEquals(expectedSquares, actualSquares);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetSquareFromFileAndRankThrowsIllegalArgumentException() {
	Square.getSquareFromFileAndRank(0, 1);	    
    }

    @Test
    public void testGetSquareFromID() {
	Square[] expectedSquares = testedSquares;
	Square[] actualSquares = new Square[] {
	    Square.getSquareFromID(11),
	    Square.getSquareFromID(53),
	    Square.getSquareFromID(88) 	    
	};
	
	assertArrayEquals(expectedSquares, actualSquares);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetSquareFromIDThrowsIllegalArgumentException() {
	Square.getSquareFromID(0);	    
    }
    
    @Test
    public void testThisSquareHasAdjacentSquare() {
	for (Direction dir : allDirectionsClockwiseFromEastToNorthEast) {
	    assertTrue(squareE3.hasAdjacentSquareTo(dir));	    
	}
	
	EnumSet<Direction> directionsFromSquareA1ThatAreOutsideChessBoard = 
		EnumSet.range(Direction.SOUTHEAST, Direction.NORTHWEST);
	for (Direction dir : directionsFromSquareA1ThatAreOutsideChessBoard) {
	    assertFalse(squareA1.hasAdjacentSquareTo(dir));
	}
	for (Direction dir : EnumSet.complementOf(directionsFromSquareA1ThatAreOutsideChessBoard)) {
	    assertTrue(squareA1.hasAdjacentSquareTo(dir));
	}
	
	EnumSet<Direction> directionsFromSquareH8ThatAreInsideChessBoard = 
		EnumSet.of(Direction.SOUTH, Direction.SOUTHWEST, Direction.WEST);
	for (Direction dir : directionsFromSquareH8ThatAreInsideChessBoard) {
	    assertTrue(squareH8.hasAdjacentSquareTo(dir));
	}
	for (Direction dir : EnumSet.complementOf(directionsFromSquareH8ThatAreInsideChessBoard)) {
	    assertFalse(squareH8.hasAdjacentSquareTo(dir));
	}
    }
    
    @Test 
    public void testGetAdjacentSquare() {
	Square[] expectedSquaresToTheEast = new Square[] { Square.B1, Square.F3 };
	Square[] actualSquaresToTheEast = new Square[] {
		squareA1.getAdjacentSquare(Direction.EAST), 
		squareE3.getAdjacentSquare(Direction.EAST) }; 
	assertArrayEquals(expectedSquaresToTheEast, actualSquaresToTheEast);
    }
    
    @Test 
    public void testSquareContainsPiece() {
	assertFalse(squareA1.containsPiece(chessPosition1));
	assertFalse(squareE3.containsPiece(chessPosition1));
	assertTrue(squareH8.containsPiece(chessPosition1));
    }
    
    @Test 
    public void testRangeOfSquaresEndsWithPiece() {	
	List<Square> expectedRange = new ArrayList<>();
	expectedRange.add(Square.B1);
	expectedRange.add(Square.C1);
	expectedRange.add(Square.D1);
	
	List<Square> actualRange = squareA1.rangeOfSquares(chessPosition1, Direction.EAST);
	
	assertEquals(expectedRange, actualRange);
    }
    
    @Test 
    public void testRangeOfSquaresEndsChessboardEdge() {	
	List<Square> expectedRange = new ArrayList<>();
	expectedRange.add(Square.F2);
	expectedRange.add(Square.G1);
	
	List<Square> actualRange = squareE3.rangeOfSquares(
		chessPosition1, Direction.SOUTHEAST);
	
	assertEquals(expectedRange, actualRange);
    }
    
    @Test 
    public void testRangeOfSquaresIsEmpty() {	
	List<Square> expectedRange = new ArrayList<>();
	
	List<Square> actualRange = squareH8.rangeOfSquares(chessPosition1, Direction.NORTHWEST);
	actualRange.addAll(squareH8.rangeOfSquares(chessPosition1, Direction.NORTH));
	actualRange.addAll(squareH8.rangeOfSquares(chessPosition1, Direction.NORTHEAST));
	actualRange.addAll(squareH8.rangeOfSquares(chessPosition1, Direction.EAST));
	actualRange.addAll(squareH8.rangeOfSquares(chessPosition1, Direction.SOUTHEAST));

	assertEquals(expectedRange, actualRange);
    }
    
    @Test 
    public void testGetSquareAtTheEndOfRange() {
	Square[] expectedSquares = { Square.E5, Square.H8, Square.B8, Square.A8 };
	
	Square sq1 = null, sq2 = null, sq3 = null, sq4 = null;
	try {
	    sq1 = squareE3.getSquareAtTheEndOfRange(chessPosition1, Direction.NORTH);
	    sq2 = Square.E5.getSquareAtTheEndOfRange(chessPosition1, Direction.NORTHEAST);
	    sq3 = Square.E5.getSquareAtTheEndOfRange(chessPosition1, Direction.NORTHWEST);
	    sq4 = squareA1.getSquareAtTheEndOfRange(chessPosition1, Direction.NORTH);
	} catch (SquareHasNoAdjacentSquareException e) {
	    e.printStackTrace();
	}
	Square[] actualSquares = { sq1, sq2, sq3, sq4 };
	
	assertArrayEquals(expectedSquares, actualSquares);
    }
    
    @Test(expected = SquareHasNoAdjacentSquareException.class) 
    public void testGetSquareAtTheEndOfRangeThrowsSquareHasNoAdjacentSquareException() 
	    throws SquareHasNoAdjacentSquareException {
	Square sq = null;
	try {
	    sq = squareA1.getSquareAtTheEndOfRange(chessPosition1, Direction.SOUTH);
	} catch (SquareHasNoAdjacentSquareException e) {
	    assertEquals("\nSquare A1", e.getMessage().substring(0, 10));
	    throw e;
	}
	
	assertFalse(sq == null);
    }
    
    @Test 
    public void testGetPiece() {
	Piece[] expectedPieces = { Piece.WHITE_KING, Piece.BLACK_KING, Piece.BLACK_ROOK };
	
	Piece pc1 = null, pc2 = null, pc3 = null;
	try {
	    pc1 = Square.E5.getPiece(chessPosition1);
	    pc2 = Square.H8.getPiece(chessPosition1);
	    pc3 = Square.D1.getPiece(chessPosition1);
	} catch (SquareContainsNoPiecesException e) {
	    e.printStackTrace();
	}
	Piece[] actualPieces = { pc1, pc2, pc3 };
	
	assertArrayEquals(expectedPieces, actualPieces);	
    }
    
    @Test(expected = SquareContainsNoPiecesException.class) 
    public void testGetPieceThrowsSquareContainsNoPiecesException() 
	    throws SquareContainsNoPiecesException {
	Piece pc = null;
	try {
	    pc = squareA1.getPiece(chessPosition1);
	} catch (SquareContainsNoPiecesException e) {
	    assertEquals("\nSquare A1", e.getMessage().substring(0, 10));
	    throw e;
	}
	
	assertFalse(pc == null);
    }
    
    @Test 
    public void testGetPieceAtTheEndOfRange() 
	    throws SquareHasNoAdjacentSquareException, SquareContainsNoPiecesException {
	Piece[] expectedPieces = { Piece.WHITE_KING, Piece.WHITE_KING, Piece.BLACK_ROOK };
	
	Piece pc1 = squareE3.getPieceAtTheEndOfRange(chessPosition1, Direction.NORTH);
	Piece pc2 = squareA1.getPieceAtTheEndOfRange(chessPosition1, Direction.NORTHEAST);
	Piece pc3 = squareA1.getPieceAtTheEndOfRange(chessPosition1, Direction.EAST);
	Piece[] actualPieces = { pc1, pc2, pc3 };
	
	assertArrayEquals(expectedPieces, actualPieces);
    }
    
    @Test 
    public void testSquareIsAdjacentToOtherSquare() {
	assertTrue(squareA1.isAdjacent(Square.A2));
	assertFalse(squareA1.isAdjacent(squareE3));
    }
    
    @Test 
    public void testGetRectangleZone() {
	Set<Square> expectedSquares = new HashSet<Square>();
	expectedSquares.add(Square.A1);
	expectedSquares.add(Square.A2);
	expectedSquares.add(Square.B1);
	expectedSquares.add(Square.B2);
	expectedSquares.add(Square.C1);
	expectedSquares.add(Square.C2);
	
	Set<Square> actualSquares = Square.getRectangleZone(Square.C2, Square.A1);
	
	assertEquals(expectedSquares, actualSquares);
    }
    
}

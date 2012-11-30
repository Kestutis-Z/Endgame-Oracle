package tablebases.gaviotaTablebases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tablebases.Tablebase;
import tablebases.gaviotaTablebases.GaviotaTablebasesQueryTemplate;

import chess.ChessPosition;
import chess.SideToMove;
import chess.Square;

public class GaviotaTablebasesQueryTemplateTest {

    private List<Square> squares = new ArrayList<Square>();
    
    @Before
    public void setUp() throws Exception {
	squares.add(Square.A1);
	squares.add(Square.G1);
	squares.add(Square.A7);
	squares.add(Square.D3);
	squares.add(Square.F8);
	ChessPosition.createFromTablebase(Tablebase.KRPKR, squares, SideToMove.BLACK);
	
    }

    @Test
    public void testConvertionFromSquaresAndPiecesToGaviotaTablebasesInts() {
	int[] expectedGTBInts_Sq = new int[] { 0, 6, 48, 19, 61, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	int[] actualGTBInts_Sq = GaviotaTablebasesQueryTemplate.convertSquaresToGaviotaTablebasesInts(squares);
	assertArrayEquals(expectedGTBInts_Sq, actualGTBInts_Sq);
	
	byte[] expectedGTBInts_Pc = new byte[] { 6, 4, 1, 6, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	byte[] actualGTBInts_Pc = GaviotaTablebasesQueryTemplate.convertPiecesToGaviotaTablebasesInts(Tablebase.KRPKR.getAllPieces());
	assertArrayEquals(expectedGTBInts_Pc, actualGTBInts_Pc);
    }

}

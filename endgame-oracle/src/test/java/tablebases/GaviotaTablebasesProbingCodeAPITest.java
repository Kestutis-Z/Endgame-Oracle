package tablebases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;

import chess.ChessPosition;
import chess.ChessPositionDiagram;
import chess.ChessPositionEvaluation;
import chess.IncorrectChessDiagramDrawingException;
import chess.SideToMove;
import chess.Square;

public class GaviotaTablebasesProbingCodeAPITest {

    private static final String MAVEN_GTB_PATH = 
	    System.getProperty("user.dir") + "/src/main/resources/Gaviota Tablebases";
    
    private GaviotaTablebasesLibrary mockedGTBLibrary;
    private ChessPosition chessPositionIllegal, chessPositionKK, chessPositionKRK;
    private EndgameTablebasesProbingCodeAPI endtablebases;

    @BeforeClass
    public static void intGTB() {
	GaviotaTablebasesLibrary.initializeGaviotaTablebases(MAVEN_GTB_PATH);
    }
    
    @Before
    public void set() throws IncorrectChessDiagramDrawingException {
	mockedGTBLibrary = mock(GaviotaTablebasesLibrary.class);
  
	List<Square> squaresIL = new ArrayList<Square>();
	squaresIL.add(Square.A7);
	squaresIL.add(Square.E7);
	squaresIL.add(Square.A8);
	chessPositionIllegal = ChessPosition.createFromTablebase(
		Tablebase.KPK, squaresIL, SideToMove.BLACK);
	
	List<Square> squaresKK = new ArrayList<Square>();
	squaresKK.add(Square.E7);
	squaresKK.add(Square.A8);
	chessPositionKK = ChessPosition.createFromTablebase(
		Tablebase.KK, squaresKK, SideToMove.WHITE);
	
	List<Square> squaresKRK = new ArrayList<Square>();
	squaresKRK.add(Square.C1);
	squaresKRK.add(Square.E7);
	squaresKRK.add(Square.A8);
	chessPositionKRK = ChessPosition.createFromTablebase(
		Tablebase.KRK, squaresKRK, SideToMove.WHITE);
	
	endtablebases = new GaviotaTablebasesProbingCodeAPI();
    }
    
    @SuppressWarnings("static-access")
    @Test(expected = RuntimeException.class)
    public void testInitializeGTBMethodMock() {	
	doThrow(new RuntimeException()).when(mockedGTBLibrary)
		.initializeGaviotaTablebases(MAVEN_GTB_PATH);
	verify(mockedGTBLibrary);
    } 
    
    @Test
    public void testQueryGaviotaTablebasesForResultOnly() {
	
	ChessPositionEvaluation expectedResult2 = ChessPositionEvaluation.DRAW;
	ChessPositionEvaluation actualResult2 = endtablebases.queryTablebaseForResultOnly(chessPositionKK);	
	assertEquals(expectedResult2, actualResult2);
	
	ChessPositionEvaluation expectedResult3 = ChessPositionEvaluation.WHITE_WINS;
	ChessPositionEvaluation actualResult3 = endtablebases.queryTablebaseForResultOnly(chessPositionKRK);	
	assertEquals(expectedResult3, actualResult3);
    }
    
    @Test
    public void testQueryGaviotaTablebasesForResultAndDtm() {
	ChessPositionEvaluation expectedResult1 = ChessPositionEvaluation.ILLEGAL;
	ChessPositionEvaluation actualResult1 = endtablebases.queryTablebaseForResultOnly(chessPositionIllegal);	
	assertEquals(expectedResult1, actualResult1);
	
	int expectedDtm1 = 0;
	int actualDtm1 = endtablebases.queryTablebaseForResultAndDTM(chessPositionIllegal).getDistanceToMate();
	assertEquals(expectedDtm1, actualDtm1);
	
	ChessPositionEvaluation expectedResult2 = ChessPositionEvaluation.DRAW;
	ChessPositionEvaluation actualResult2 = endtablebases.queryTablebaseForResultOnly(chessPositionKK);	
	assertEquals(expectedResult2, actualResult2);
	
	int expectedDtm2 = 0;
	int actualDtm2 = endtablebases.queryTablebaseForResultAndDTM(chessPositionKK).getDistanceToMate();
	assertEquals(expectedDtm2, actualDtm2);
	
	ChessPositionEvaluation expectedResult3 = ChessPositionEvaluation.WHITE_WINS;
	ChessPositionEvaluation actualResult3 = endtablebases.queryTablebaseForResultOnly(chessPositionKRK);	
	assertEquals(expectedResult3, actualResult3);
	
	int expectedDtm3 = 10;
	int actualDtm3 = endtablebases.queryTablebaseForResultAndDTM(chessPositionKRK).getDistanceToMate();
	assertEquals(expectedDtm3, actualDtm3);
	
	List<Square> squares4 = new ArrayList<Square>();
	squares4.add(Square.G7);
	squares4.add(Square.E3);
	squares4.add(Square.B2);
	ChessPosition cp4 = ChessPosition.createFromTablebase(
		Tablebase.KRK, squares4, SideToMove.BLACK);
	int expectedDtm4 = 12;
	int actualDtm4 = endtablebases.queryTablebaseForResultAndDTM(cp4).getDistanceToMate();
	assertEquals(expectedDtm4, actualDtm4);
	
	List<Square> squares5 = new ArrayList<Square>();
	squares5.add(Square.G7);
	squares5.add(Square.G2);
	squares5.add(Square.B2);
	ChessPosition cp5 = ChessPosition.createFromTablebase(
		Tablebase.KPK, squares5, SideToMove.BLACK);
	int expectedDtm5 = 13;
	int actualDtm5 = endtablebases.queryTablebaseForResultAndDTM(cp5).getDistanceToMate();
	assertEquals(expectedDtm5, actualDtm5);
	
	List<Square> squares6 = new ArrayList<Square>();
	squares6.add(Square.D5);
	squares6.add(Square.D3);
	squares6.add(Square.B7);
	ChessPosition cp6 = ChessPosition.createFromTablebase(
		Tablebase.KPK, squares6, SideToMove.BLACK);
	int expectedDtm6 = 16;
	int actualDtm6 = endtablebases.queryTablebaseForResultAndDTM(cp6).getDistanceToMate();
	assertEquals(expectedDtm6, actualDtm6);
    }    
    
    @Test
    public void testQueryTablebaseUsingChessDiagrams() throws IncorrectChessDiagramDrawingException {
	
	String drawingKPK =  
		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|  BK  |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|      |      |      |      |  WP  |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|  WK  |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;
	
	ChessPositionDiagram diagram = ChessPositionDiagram.createFromTextDiagram(drawingKPK);
	ChessPosition chessPosition = ChessPosition.createFromTextualDrawing(diagram, SideToMove.BLACK);	
	int expectedDtm = 2;
	int actualDtm = endtablebases.queryTablebaseForResultAndDTM(chessPosition).getDistanceToMate();
	assertEquals(expectedDtm, actualDtm);
	
	drawingKPK =  
		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|      |      |      |      |  WP  |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|  WK  |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;
	
	diagram = ChessPositionDiagram.createFromTextDiagram(drawingKPK);
	chessPosition = ChessPosition.createFromTextualDrawing(diagram, SideToMove.BLACK);	
	expectedDtm = 0;
	actualDtm = endtablebases.queryTablebaseForResultAndDTM(chessPosition).getDistanceToMate();
	assertEquals(expectedDtm, actualDtm);
	
	drawingKPK =  
		
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
		    "  5|      |      |      |      |      |      |  WK  |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |  BK  |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |      |      |      |      |  WR  |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;
	
	diagram = ChessPositionDiagram.createFromTextDiagram(drawingKPK);
	chessPosition = ChessPosition.createFromTextualDrawing(diagram, SideToMove.WHITE);	
	expectedDtm = 13;
	actualDtm = endtablebases.queryTablebaseForResultAndDTM(chessPosition).getDistanceToMate();
	assertEquals(expectedDtm, actualDtm);
    }

    @Test
    public void testEvaluateRandomChessPosition() {
	ChessPosition randomChessPosition;
	ChessPositionEvaluation actualResult;
	
	int countIllegals = 0, countDraws = 0, countWhiteWins = 0, countBlackWins = 0;
	for (int i = 0; i < 10000; i++) {
	    randomChessPosition = ChessPosition.createRandomFromTablebase(
			Tablebase.KPK, SideToMove.WHITE);
	    actualResult = endtablebases.queryTablebaseForResultOnly(randomChessPosition);
	   
	    switch(actualResult) {
	    case ILLEGAL:
		countIllegals++;
		break;
	    case DRAW:
		countDraws++;
		break;
	    case WHITE_WINS:
		countWhiteWins++;
		break;
	    case BLACK_WINS:
		countBlackWins++;
		break;
	    default:
		throw new AssertionError("Unsupported evaluation: " + actualResult);
	    }	    
	}
	
	assertTrue(countIllegals > 0);
	assertTrue(countDraws > 0);
	assertTrue(countWhiteWins > 0);
	assertTrue(countBlackWins == 0);
	
    }
    
}




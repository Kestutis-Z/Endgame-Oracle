package chess;

import static org.junit.Assert.*;

import org.junit.Test;

public class CanonicalChessPositionTest {

    @Test
    public void testIfCorrectCannonicalPositionCreated_OriginalPositionAlreadyCanonnical() 
	    throws IncorrectChessDiagramDrawingException {

	String drawingForOriginalPosition_NoDuplicatePieces_NoPawns =
		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|  BK  |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|      |      |      |      |      |      |      |  WN  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |  WB  |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |      |      |      |      |  BQ  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |      |  WK  |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;
	
	ChessPositionDiagram cpDiagram = ChessPositionDiagram
		.createFromTextDiagram(drawingForOriginalPosition_NoDuplicatePieces_NoPawns);
	ChessPosition regularChessPosition = ChessPosition
		.createFromTextualDrawing(cpDiagram, SideToMove.BLACK);
	CanonicalChessPosition canonicalChessPosition = CanonicalChessPosition
		.createFromRegularChessPosition(regularChessPosition);

	assertEquals(regularChessPosition,
		canonicalChessPosition.getChessPosition());
	
	/* ===========================================================================*/
	
	String drawingForOriginalPosition_NoDuplicatePieces_WithPawns =
		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|  BK  |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|      |      |      |      |      |      |      |  WN  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |  WP  |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |      |      |      |      |  BP  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |      |  WK  |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;
	
	cpDiagram = ChessPositionDiagram
		.createFromTextDiagram(drawingForOriginalPosition_NoDuplicatePieces_WithPawns);
	regularChessPosition = ChessPosition
		.createFromTextualDrawing(cpDiagram, SideToMove.BLACK);
	canonicalChessPosition = CanonicalChessPosition
		.createFromRegularChessPosition(regularChessPosition);

	assertEquals(regularChessPosition,
		canonicalChessPosition.getChessPosition());
	
	/* ===========================================================================*/

	String drawingForOriginalPosition_WithDuplicatePieces_NoPawns =
		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|  BK  |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|      |      |      |      |      |      |      |  WB2 |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |  WB  |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |      |      |      |      |  BQ  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |      |  WK  |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;
	
	cpDiagram = ChessPositionDiagram
		.createFromTextDiagram(drawingForOriginalPosition_WithDuplicatePieces_NoPawns);
	regularChessPosition = ChessPosition
		.createFromTextualDrawing(cpDiagram, SideToMove.BLACK);
	canonicalChessPosition = CanonicalChessPosition
		.createFromRegularChessPosition(regularChessPosition);

	assertEquals(regularChessPosition,
		canonicalChessPosition.getChessPosition());
	
	/* ===========================================================================*/
	
	String drawingForOriginalPosition_WithDuplicatePieces_WithPawns =
		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|  BK  |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|      |      |      |      |      |      |      |  WN  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |  WP  |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |      |      |      |      |  WP2 |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |      |  WK  |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;
	
	cpDiagram = ChessPositionDiagram
		.createFromTextDiagram(drawingForOriginalPosition_WithDuplicatePieces_WithPawns);
	regularChessPosition = ChessPosition
		.createFromTextualDrawing(cpDiagram, SideToMove.BLACK);
	canonicalChessPosition = CanonicalChessPosition
		.createFromRegularChessPosition(regularChessPosition);

	assertEquals(regularChessPosition,
		canonicalChessPosition.getChessPosition());
	
	/* ===========================================================================*/

	String drawingForOriginalPosition_AllPiecesOnA1H8Diagonal =
		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|      |      |      |      |      |      |  BK  |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|      |      |      |      |      |  BQ2 |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |      |  WB2 |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |  WB  |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|      |      |  WK  |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |  BQ  |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;
	
	cpDiagram = ChessPositionDiagram
		.createFromTextDiagram(drawingForOriginalPosition_AllPiecesOnA1H8Diagonal);
	regularChessPosition = ChessPosition.createFromTextualDrawing(
		cpDiagram, SideToMove.WHITE);
	canonicalChessPosition = CanonicalChessPosition
		.createFromRegularChessPosition(regularChessPosition);

	assertEquals(regularChessPosition,
		canonicalChessPosition.getChessPosition());
    }

    @Test
    public void testIfCorrectCannonicalPositionCreated_OriginalPositionNeedsVerticalReflection()
	    throws IncorrectChessDiagramDrawingException {

	String drawingForOriginalPosition_NoDuplicatePieces_NoPawns =
		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|  BK  |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|      |      |      |      |      |      |      |  WN  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |  WB  |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |      |      |      |      |  BQ  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |      |      |      |      |  WK  |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;
	
	ChessPositionDiagram cpDiagramOriginal = ChessPositionDiagram
		.createFromTextDiagram(drawingForOriginalPosition_NoDuplicatePieces_NoPawns);
	ChessPosition regularChessPosition = ChessPosition
		.createFromTextualDrawing(cpDiagramOriginal, SideToMove.BLACK);
	CanonicalChessPosition canonicalChessPosition = CanonicalChessPosition
		.createFromRegularChessPosition(regularChessPosition);

	String drawingForExpectedCanonicalPosition =
		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|      |      |      |      |      |      |      |  BK  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|  WN  |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |      |  WB  |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|  BQ  |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |      |  WK  |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;
	
	ChessPositionDiagram cpDiagramCanonical = ChessPositionDiagram
		.createFromTextDiagram(drawingForExpectedCanonicalPosition);
	ChessPosition expectedCanonicalChessPosition = ChessPosition
		.createFromTextualDrawing(cpDiagramCanonical, SideToMove.BLACK);
	
	assertEquals(expectedCanonicalChessPosition,
		canonicalChessPosition.getChessPosition());	
	
	/* ===========================================================================*/

	String drawingForOriginalPosition_WithDuplicatePieces_WithPawns =
		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|  BK  |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|      |      |      |      |      |      |      |  WN  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |      |  WP  |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |      |      |  WP3 |      |  WP2 |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |      |      |      |      |      |      |  WK  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;	
	
	cpDiagramOriginal = ChessPositionDiagram
		.createFromTextDiagram(drawingForOriginalPosition_WithDuplicatePieces_WithPawns);
	regularChessPosition = ChessPosition.createFromTextualDrawing(
		cpDiagramOriginal, SideToMove.BLACK);
	canonicalChessPosition = CanonicalChessPosition
		.createFromRegularChessPosition(regularChessPosition);

	drawingForExpectedCanonicalPosition =
		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|      |      |      |      |      |      |      |  BK  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|  WN  |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |  WP  |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|  WP2 |      |  WP3 |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|  WK  |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;
	
	cpDiagramCanonical = ChessPositionDiagram
		.createFromTextDiagram(drawingForExpectedCanonicalPosition);
	expectedCanonicalChessPosition = ChessPosition
		.createFromTextualDrawing(cpDiagramCanonical, SideToMove.BLACK);

	assertEquals(expectedCanonicalChessPosition,
		canonicalChessPosition.getChessPosition());
    }
    
    @Test
    public void testIfCorrectCannonicalPositionCreated_OriginalPositionNeedsHorizontalReflection()
	    throws IncorrectChessDiagramDrawingException {

	String drawingForOriginalPosition_NoDuplicatePieces_NoPawns =
		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|  BK  |      |  WK  |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|      |      |      |      |      |      |      |  WN  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |  WB  |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |      |      |      |      |  BQ  |  \n" + 
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
	
	ChessPositionDiagram cpDiagramOriginal = ChessPositionDiagram
		.createFromTextDiagram(drawingForOriginalPosition_NoDuplicatePieces_NoPawns);
	ChessPosition regularChessPosition = ChessPosition
		.createFromTextualDrawing(cpDiagramOriginal, SideToMove.BLACK);
	CanonicalChessPosition canonicalChessPosition = CanonicalChessPosition
		.createFromRegularChessPosition(regularChessPosition);

	String drawingForExpectedCanonicalPosition =
		
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
		    "  5|      |      |      |      |      |      |      |  BQ  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |  WB  |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|      |      |      |      |      |      |      |  WN  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|  BK  |      |  WK  |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;
	
	ChessPositionDiagram cpDiagramCanonical = ChessPositionDiagram
		.createFromTextDiagram(drawingForExpectedCanonicalPosition);
	ChessPosition expectedCanonicalChessPosition = ChessPosition
		.createFromTextualDrawing(cpDiagramCanonical, SideToMove.BLACK);
	
	assertEquals(expectedCanonicalChessPosition,
		canonicalChessPosition.getChessPosition());	
	
	/* ===========================================================================*/

	String drawingForOriginalPosition_WithDuplicatePieces_NoPawns =
		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|  BK  |      |      |      |  WK  |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|      |      |      |      |      |      |      |  BQ  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |      |  WR  |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |      |      |  WR2 |      |      |  \n" + 
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
	
	cpDiagramOriginal = ChessPositionDiagram
		.createFromTextDiagram(drawingForOriginalPosition_WithDuplicatePieces_NoPawns);
	regularChessPosition = ChessPosition.createFromTextualDrawing(
		cpDiagramOriginal, SideToMove.BLACK);
	canonicalChessPosition = CanonicalChessPosition
		.createFromRegularChessPosition(regularChessPosition);

	drawingForExpectedCanonicalPosition =
		
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
		    "  5|      |      |  WR2 |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |  WR  |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|  BQ  |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|      |      |      |  WK  |      |      |      |  BK  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;
	
	cpDiagramCanonical = ChessPositionDiagram
		.createFromTextDiagram(drawingForExpectedCanonicalPosition);
	expectedCanonicalChessPosition = ChessPosition
		.createFromTextualDrawing(cpDiagramCanonical, SideToMove.BLACK);

	assertEquals(expectedCanonicalChessPosition,
		canonicalChessPosition.getChessPosition());
    }
    
    @Test
    public void testIfCorrectCannonicalPositionCreated_OriginalPositionNeedsDiagonalRotation()
	    throws IncorrectChessDiagramDrawingException {

	String drawingForOriginalPosition =
		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|  BK  |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|      |      |      |      |      |      |      |  WN  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |  WN2 |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |      |      |      |      |  BQ  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|      |      |      |      |      |  WK  |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;
	
	ChessPositionDiagram cpDiagramOriginal = ChessPositionDiagram
		.createFromTextDiagram(drawingForOriginalPosition);
	ChessPosition regularChessPosition = ChessPosition
		.createFromTextualDrawing(cpDiagramOriginal, SideToMove.BLACK);
	CanonicalChessPosition canonicalChessPosition = CanonicalChessPosition
		.createFromRegularChessPosition(regularChessPosition);

	String drawingForExpectedCanonicalPosition =
		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|      |      |      |      |      |      |      |  BK  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |      |  WN2 |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|      |      |  WK  |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|      |      |      |  BQ  |      |  WN  |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;
	
	ChessPositionDiagram cpDiagramCanonical = ChessPositionDiagram
		.createFromTextDiagram(drawingForExpectedCanonicalPosition);
	ChessPosition expectedCanonicalChessPosition = ChessPosition
		.createFromTextualDrawing(cpDiagramCanonical, SideToMove.BLACK);
	
	assertEquals(expectedCanonicalChessPosition,
		canonicalChessPosition.getChessPosition());	
	
	/* ===========================================================================*/

	drawingForOriginalPosition =
		
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
		    "  5|      |      |      |      |  WQ  |  WK  |      |      |  \n" + 
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
	
	cpDiagramOriginal = ChessPositionDiagram
		.createFromTextDiagram(drawingForOriginalPosition);
	regularChessPosition = ChessPosition.createFromTextualDrawing(
		cpDiagramOriginal, SideToMove.BLACK);
	canonicalChessPosition = CanonicalChessPosition
		.createFromRegularChessPosition(regularChessPosition);

	drawingForExpectedCanonicalPosition =
		
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
		    "  5|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |  WQ  |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|      |      |      |  WK  |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;
	
	cpDiagramCanonical = ChessPositionDiagram
		.createFromTextDiagram(drawingForExpectedCanonicalPosition);
	expectedCanonicalChessPosition = ChessPosition
		.createFromTextualDrawing(cpDiagramCanonical, SideToMove.BLACK);

	assertEquals(expectedCanonicalChessPosition,
		canonicalChessPosition.getChessPosition());
	
	/* ===========================================================================*/

	drawingForOriginalPosition =
		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|      |      |      |      |      |  WN  |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|      |      |      |      |      |  WN2 |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |      |  WR  |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|      |      |  BK  |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|  WK  |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;	
	
	cpDiagramOriginal = ChessPositionDiagram
		.createFromTextDiagram(drawingForOriginalPosition);
	regularChessPosition = ChessPosition.createFromTextualDrawing(
		cpDiagramOriginal, SideToMove.BLACK);
	canonicalChessPosition = CanonicalChessPosition
		.createFromRegularChessPosition(regularChessPosition);

	drawingForExpectedCanonicalPosition =
		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|      |      |      |      |      |  WN2 |      |  WN  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |      |  WR  |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|      |      |  BK  |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|  WK  |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;	
	
	cpDiagramCanonical = ChessPositionDiagram
		.createFromTextDiagram(drawingForExpectedCanonicalPosition);
	expectedCanonicalChessPosition = ChessPosition
		.createFromTextualDrawing(cpDiagramCanonical, SideToMove.BLACK);

	assertEquals(expectedCanonicalChessPosition,
		canonicalChessPosition.getChessPosition());
	
	/* ===========================================================================*/
	
	drawingForOriginalPosition =
		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|      |      |      |      |      |      |      |  WN  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |      |  WN2 |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |      |      |      |      |  BK  |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|      |  WK  |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;	
	
	cpDiagramOriginal = ChessPositionDiagram
		.createFromTextDiagram(drawingForOriginalPosition);
	regularChessPosition = ChessPosition.createFromTextualDrawing(
		cpDiagramOriginal, SideToMove.BLACK);
	canonicalChessPosition = CanonicalChessPosition
		.createFromRegularChessPosition(regularChessPosition);

	drawingForExpectedCanonicalPosition =
		
		    "    _______________________________________________________   \n" +
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  8|      |      |      |  BK  |      |  WN  |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  7|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  6|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  5|      |      |      |      |  WN2 |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  4|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  3|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  2|      |      |  WK  |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "   |      |      |      |      |      |      |      |      |  \n" + 
		    "  1|      |      |      |      |      |      |      |      |  \n" + 
		    "   |______|______|______|______|______|______|______|______|  \n" + 
		    "      a      b      c      d      e      f      g      h      \n" ;	
	
	cpDiagramCanonical = ChessPositionDiagram
		.createFromTextDiagram(drawingForExpectedCanonicalPosition);
	expectedCanonicalChessPosition = ChessPosition
		.createFromTextualDrawing(cpDiagramCanonical, SideToMove.BLACK);

	assertEquals(expectedCanonicalChessPosition,
		canonicalChessPosition.getChessPosition());	
    }
    
}

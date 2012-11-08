package chess;

import com.google.common.collect.BiMap;
import com.google.common.collect.EnumBiMap;

/**
 * A "drawing" of a chess position on the screen. 
 * This is useful for better readability of ChessPosition tests.
 * 
 * @author Kestutis
 *
 */
public class ChessPositionDiagram {
    
    /*
     * A String of the form <br/>
     * 
     * "    _______________________________________________________   \n" +
     * "   |      |      |      |      |      |      |      |      |  \n" + 
     * "  8|      |      |      |      |      |      |      |      |  \n" + 
     * "   |______|______|______|______|______|______|______|______|  \n" + 
     * "   |      |      |      |      |      |      |      |      |  \n" + 
     * "  7|      |      |      |      |  WK  |      |      |      |  \n" + 
     * "   |______|______|______|______|______|______|______|______|  \n" + 
     * "   |      |      |      |      |      |      |      |      |  \n" + 
     * "  6|      |      |      |      |      |      |      |      |  \n" + 
     * "   |______|______|______|______|______|______|______|______|  \n" + 
     * "   |      |      |      |      |      |      |      |      |  \n" + 
     * "  5|      |      |      |      |      |      |      |      |  \n" + 
     * "   |______|______|______|______|______|______|______|______|  \n" + 
     * "   |      |      |      |      |      |      |      |      |  \n" + 
     * "  4|      |      |  BK  |      |      |      |      |      |  \n" + 
     * "   |______|______|______|______|______|______|______|______|  \n" + 
     * "   |      |      |      |      |      |      |      |      |  \n" + 
     * "  3|      |      |      |      |      |      |      |      |  \n" + 
     * "   |______|______|______|______|______|______|______|______|  \n" + 
     * "   |      |      |      |      |      |      |      |      |  \n" + 
     * "  2|      |      |      |      |      |      |      |      |  \n" + 
     * "   |______|______|______|______|______|______|______|______|  \n" + 
     * "   |      |      |      |      |      |      |      |      |  \n" + 
     * "  1|      |      |      |      |      |      |      |      |  \n" + 
     * "   |______|______|______|______|______|______|______|______|  \n" + 
     * "      a      b      c      d      e      f      g      h      \n" ;
     
     The diagram above contains two pieces: White King on square e7, 
     and Black King on square c4. 
     */
    private String diagram;

    private ChessPositionDiagram(String diagram) throws IncorrectChessDiagramDrawingException {
	verifyDiagram(diagram);
	this.diagram = diagram;
    }
    
    private void verifyDiagram(String diagram) throws IncorrectChessDiagramDrawingException {
	String[] diagramLines = diagram.split("\n");
	if (diagramLines.length != 26)
	    throw new IncorrectChessDiagramDrawingException("Diagram must contain 26 lines!");
	String lastLine = diagramLines[diagramLines.length - 1];
	verifyDiagramTop(diagramLines[0]);
	verifyDiagramBody(diagramLines);
	verifyDiagramBottom(lastLine);
    }

    private void verifyDiagramTop(String firstLine) throws IncorrectChessDiagramDrawingException {
	if (!firstLine.equals("    _______________________________________________________   "))
	    throw new IncorrectChessDiagramDrawingException("Incorrect diagram top:\n" + firstLine);
    }

    private void verifyDiagramBody(String[] diagramLines) throws IncorrectChessDiagramDrawingException {
	for (int rank = 8; rank >= 1; rank--) {
	    String upperThird = diagramLines[3*(8 - rank) + 1];
	    String middleThird = diagramLines[3*(8 - rank) + 2];
	    String lowerThird = diagramLines[3*(8 - rank) + 3];
	    
	    if (!upperThird.equals("   |      |      |      |      |      |      |      |      |  "))
		    throw new IncorrectChessDiagramDrawingException("Incorrect upper third " +
		    		"in rank " + rank + ":\n" + upperThird);
	    verifyMiddleThird(rank, middleThird);		    
	    if (!lowerThird.equals("   |______|______|______|______|______|______|______|______|  "))
		    throw new IncorrectChessDiagramDrawingException("Incorrect lower third " +
		    		"in rank " + rank + ":\n" + lowerThird);	    
	}
    }

    private void verifyMiddleThird(int rank, String middleThird) 
	    throws IncorrectChessDiagramDrawingException {
	if (!(middleThird.charAt(2) - '0' == rank))
	    throw new IncorrectChessDiagramDrawingException("Incorrect rank in middle third: was " +
	    		"expected " + rank + " but obtained" + (middleThird.charAt(2) - '0'));
	for (int file = 1; file <= 8; file++) {	   
	    String pieceAbbreviation = getDiagramSquareContent(middleThird, file);
	    if (!(Piece.allAbbreviationsOfPieces().contains(pieceAbbreviation)
		    || pieceAbbreviation.equals("  ")))		    
		throw new IncorrectChessDiagramDrawingException("Incorrect piece " +
				"abbreviation in file " + file + ", rank " + 
				rank + ": " + pieceAbbreviation);		    		
	}
    }

    /**
     * @return abbreviation of Piece on the square, or "  " (two spaces) 
     * if the square is empty
     */
    private String getDiagramSquareContent(String middleThird, int file) {
	String pieceAbbreviation = Character.isDigit(middleThird.charAt(7 * (file - 1) + 8)) 
	    ? middleThird.substring(7 * (file - 1) + 6, 7 * (file - 1) + 9) 
		    : middleThird.substring(7 * (file - 1) + 6, 7 * (file - 1) + 8);
	return pieceAbbreviation;
    }

    private void verifyDiagramBottom(String lastLine) throws IncorrectChessDiagramDrawingException {
	if (!lastLine.equals("      a      b      c      d      e      f      g      h      "))
	    throw new IncorrectChessDiagramDrawingException("Incorrect diagram bottom:\n" + lastLine);
    }

    public static ChessPositionDiagram createFromTextDiagram(String diagram) 
	    throws IncorrectChessDiagramDrawingException {
	return new ChessPositionDiagram(diagram);	
    }

    public String getDiagram() {
        return diagram;
    }

    public void setDiagram(String diagram) {
        this.diagram = diagram;
    }

    public BiMap<Piece, Square> getPiecesWithSquaresFromDiagram() {
	BiMap<Piece, Square> piecesWithSquares = EnumBiMap.create(Piece.class, Square.class);
	String[] diagramLines = diagram.split("\n");
	for (int rank = 8; rank >= 1; rank--) {
	    String middleThird = diagramLines[3*(8 - rank) + 2];
	    for (int file = 1; file <= 8; file++) {	   
		String pieceAbbreviation = getDiagramSquareContent(middleThird, file);
		if (!pieceAbbreviation.equals("  ")) {
		    Piece piece = Piece.getPieceFromAbbreviation(pieceAbbreviation);
		    Square square = Square.getSquareFromFileAndRank(file, rank);
		    piecesWithSquares.put(piece, square);
		}
	    }		    
	}
	return piecesWithSquares;
    }    

}

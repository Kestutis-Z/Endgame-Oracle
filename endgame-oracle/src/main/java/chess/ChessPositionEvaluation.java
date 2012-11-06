package chess;

/**
 * Game-theoretical value of position obtained from tablebase. 
 * 
 * @author Kestutis
 *
 */
public enum ChessPositionEvaluation {
    
    DRAW, WHITE_WINS, BLACK_WINS, ILLEGAL, NOT_EVALUATED;
    
    
    /** 
     * Simple data structure representing the evaluation of 
     * some chess position, which include the additional data member - 
     * distance-to-mate. 
     * 
     * @author Kestutis
     *
     */
    public static class ChessPositionEvaluationWithDTM {
	private ChessPositionEvaluation chessPositionEvaluation;
	/** A non-negative number of moves to mate in a position, theoretically won 
	 * by White or Black. */
	private int distanceToMate;
	
	public ChessPositionEvaluation getChessPositionEvaluation() {
	    return chessPositionEvaluation;
	}
	
	public void setChessPositionEvaluation(
		ChessPositionEvaluation chessPositionEvaluation) {
	    this.chessPositionEvaluation = chessPositionEvaluation;
	}
	
	public int getDistanceToMate() {
	    return distanceToMate;
	}
	
	public void setDistanceToMate(int distanceToMate) {
	    this.distanceToMate = distanceToMate;
	}
	
    }	
    
}

package chess;

/**
 * The gaame-theoretical value of the chess position obtained 
 * from the tablebases. 
 * 
 * @author Kestutis
 *
 */
public enum ChessPositionEvaluation {
    
    DRAW, WHITE_WINS, BLACK_WINS, ILLEGAL, NOT_EVALUATED;
    
    
    /** 
     * Simple data structure representing the evaluation of 
     * some chess position, which include the additional data 
     * member - distance-to-mate. 
     * 
     * @author Kestutis
     *
     */
    public static class ChessPositionEvaluationWithDTM {
	
	/** 
	 * The game-theoretical value of the chess position obtained 
	 * from the tablebase. 
	 */
	private ChessPositionEvaluation chessPositionEvaluation;
	
	/**
	 * A non-negative number of moves to mate in a chess position that 
	 * is theoretically won by White or Black.
	 */
	private int distanceToMate;
	
	/**
	 * Gets the basic chess position evaluation - one of "White wins" /
	 * "Black wins" / "Draw" / "Illegal position".
	 * 
	 * @return game-theoretical value of the chess position obtained from
	 *         the tablebases
	 */
	public ChessPositionEvaluation getChessPositionEvaluation() {
	    return chessPositionEvaluation;
	}	
	
	/**
	 * Sets the chess position evaluation - one of "White wins" /
	 * "Black wins" / "Draw" / "Illegal position"..
	 * 
	 * @param chessPositionEvaluation
	 *            game-theoretical value of the chess position obtained from
	 *            the tablebases
	 */
	public void setChessPositionEvaluation(
		ChessPositionEvaluation chessPositionEvaluation) {
	    this.chessPositionEvaluation = chessPositionEvaluation;
	}
	
	/**
	 * Gets the distance to mate.
	 *
	 * @return the distance to mate
	 */
	public int getDistanceToMate() {
	    return distanceToMate;
	}
	
	public void setDistanceToMate(int distanceToMate) {
	    this.distanceToMate = distanceToMate;
	}
	
    }	
    
}
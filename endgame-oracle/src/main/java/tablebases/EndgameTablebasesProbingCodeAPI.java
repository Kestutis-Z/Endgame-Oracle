package tablebases;

import chess.ChessPosition;
import chess.ChessPositionEvaluation;
import chess.ChessPositionEvaluation.ChessPositionEvaluationWithDTM;

/** 
 * Software, typically written in C/C++, to generate, 
 * compress, access and probe endgame tablebases. 
 * 
 * @author Kestutis
 *
 */
public interface EndgameTablebasesProbingCodeAPI {
    
    /**
     * Probes tablebase for the basic evaluation of the specified 
     * chess position.
     *
     * @param chessPosition representation of the chess position: side to move
     * (White / Black), White and Black pieces, and their respective squares
     * @return game-theoretical value of the chess position obtained
     * from the tablebase
     */
    public abstract ChessPositionEvaluation 
    	queryTablebaseForResultOnly(ChessPosition chessPosition);
    
    /**
     * Probes tablebase for the advanced evaluation of the specified 
     * chess position. 
     *
     * @param chessPosition representation of the chess position: side to move
     * (White / Black), White and Black pieces, and their respective squares
     * @return evaluation and distance to mate (a non-negative 
     * number in case the evaluation is "White wins" / "Black wins")
     */
    public abstract ChessPositionEvaluationWithDTM 
    	queryTablebaseForResultAndDTM(ChessPosition chessPosition);    

}

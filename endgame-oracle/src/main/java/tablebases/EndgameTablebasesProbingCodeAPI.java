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
    
    /** Probes tablebase for the specified chess position. 
     * @return basic evaluation (one of "White wins" / 
     * "Black wins" / "Draw" / "Illegal position") only. */
    public abstract ChessPositionEvaluation 
    	queryTablebaseForResultOnly(ChessPosition chessPosition);
    
    /** Probes tablebase for the specified chess position. 
     * @return evaluation and distance to mate (a non-negative 
     * number in case the evaluation is "White wins" / "Black wins"). */
    public abstract ChessPositionEvaluationWithDTM 
    	queryTablebaseForResultAndDTM(ChessPosition chessPosition);    

}

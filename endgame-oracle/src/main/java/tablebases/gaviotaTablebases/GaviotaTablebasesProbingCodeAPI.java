package tablebases.gaviotaTablebases;

import tablebases.EndgameTablebasesProbingCodeAPI;
import chess.ChessPosition;
import chess.ChessPositionEvaluation;
import chess.ChessPositionEvaluation.ChessPositionEvaluationWithDTM;

/**
 * Software to generate, compress, access and probe Gaviota Tablebases. 
 * 
 * <br/><br/>
 * See <a href="https://github.com/michiguel/Gaviota-Tablebases">
 * https://github.com/michiguel/Gaviota-Tablebases</a>
 * 
 * @author Kestutis
 * 
 */
public class GaviotaTablebasesProbingCodeAPI implements EndgameTablebasesProbingCodeAPI {      
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * tablebases.EndgameTablebasesProbingCodeAPI#queryTablebaseForResultOnly
     * (chess.ChessPosition)
     */
    public ChessPositionEvaluation queryTablebaseForResultOnly(
	    ChessPosition chessPosition) {
	GaviotaTablebasesQueryTemplate<ChessPositionEvaluation> query = new GaviotaTablebasesQueryForResultOnly(
		chessPosition);
	return query.tablebaseEvaluation;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * tablebases.EndgameTablebasesProbingCodeAPI#queryTablebaseForResultAndDTM
     * (chess.ChessPosition)
     */
    public ChessPositionEvaluationWithDTM queryTablebaseForResultAndDTM(
	    ChessPosition chessPosition) {
	GaviotaTablebasesQueryTemplate<ChessPositionEvaluationWithDTM> query = new GaviotaTablebasesQueryForResultAndDTM(
		chessPosition);
	return query.tablebaseEvaluation;
    }  

}
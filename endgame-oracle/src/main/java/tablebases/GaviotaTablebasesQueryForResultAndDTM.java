package tablebases;

import chess.ChessPosition;
import chess.ChessPositionEvaluation.ChessPositionEvaluationWithDTM;

/**
 * Algorithm to query (probe) Gaviota Tablebases, 
 * with the game-theoretical chess position evaluation 
 * <b>and</b> distance-to-mate information expected as query result.
 * 
 * @author Kestutis
 *
 */
public class GaviotaTablebasesQueryForResultAndDTM extends
	GaviotaTablebasesQueryTemplate<ChessPositionEvaluationWithDTM> {

    GaviotaTablebasesQueryForResultAndDTM(ChessPosition chessPosition) {
	super(chessPosition);
    }

    @Override
    public ChessPositionEvaluationWithDTM queryGaviotaTablebases() {
	return GaviotaTablebasesLibrary.queryGaviotaTablebasesForResultAndDistanceToMate(
		sideToMoveForGaviotaTablebases, 
		enpassantSquareForGaviotaTablebases, 
		castlingOptionForGaviotaTablebases,
		whiteSquaresForGaviotaTablebases,
		blackSquaresForGaviotaTablebases,
		whitePiecesForGaviotaTablebases,
		blackPiecesForGaviotaTablebases);
    }

}
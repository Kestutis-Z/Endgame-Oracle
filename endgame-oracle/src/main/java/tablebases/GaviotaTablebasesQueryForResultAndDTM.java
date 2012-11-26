package tablebases;

import chess.ChessPosition;
import chess.ChessPositionEvaluation.ChessPositionEvaluationWithDTM;

/**
 * Algorithm to query (probe) Gaviota Tablebases, with the game-theoretical
 * chess position evaluation <b>and</b> distance-to-mate information expected as
 * the result of the query.
 * 
 * @author Kestutis
 * 
 */
public class GaviotaTablebasesQueryForResultAndDTM extends
	GaviotaTablebasesQueryTemplate<ChessPositionEvaluationWithDTM> {

    /**
     * Instantiates a new query for both the result and the distance-to-mate.
     *
     * @param chessPosition representation of the chess position: side to move
     * (White / Black), White and Black pieces, and their respective squares
     */
    GaviotaTablebasesQueryForResultAndDTM(ChessPosition chessPosition) {
	super(chessPosition);
    }

    /* (non-Javadoc)
     * @see tablebases.GaviotaTablebasesQueryTemplate#queryGaviotaTablebases()
     */
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
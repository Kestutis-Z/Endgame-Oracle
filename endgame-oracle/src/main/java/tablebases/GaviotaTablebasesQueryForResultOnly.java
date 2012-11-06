package tablebases;

import chess.ChessPosition;
import chess.ChessPositionEvaluation;

/**
 * Algorithm to query (probe) Gaviota Tablebases, 
 * with only the game-theoretical chess position evaluation 
 * expected as query result.
 * 
 * @author Kestutis
 *
 */
public class GaviotaTablebasesQueryForResultOnly extends
	GaviotaTablebasesQueryTemplate<ChessPositionEvaluation> {

    GaviotaTablebasesQueryForResultOnly(ChessPosition chessPosition) {
	super(chessPosition);
    }

    @Override
    public ChessPositionEvaluation queryGaviotaTablebases() {
	return GaviotaTablebasesLibrary.queryGaviotaTablebasesForResultOnly(
		sideToMoveForGaviotaTablebases, 
		enpassantSquareForGaviotaTablebases, 
		castlingOptionForGaviotaTablebases,
		whiteSquaresForGaviotaTablebases,
		blackSquaresForGaviotaTablebases,
		whitePiecesForGaviotaTablebases,
		blackPiecesForGaviotaTablebases);
    }

}

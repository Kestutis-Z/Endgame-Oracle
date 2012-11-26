package tablebases;

import chess.ChessPosition;
import chess.ChessPositionEvaluation;

/**
 * Algorithm to query (probe) Gaviota Tablebases, with only the game-theoretical
 * chess position evaluation expected as the result of the query.
 * 
 * @author Kestutis
 * 
 */
public class GaviotaTablebasesQueryForResultOnly extends
	GaviotaTablebasesQueryTemplate<ChessPositionEvaluation> {

    /**
     * Instantiates a new query for the game-theoretical chess position
     * evaluation only.
     * 
     * @param chessPosition
     *            representation of the chess position: side to move (White /
     *            Black), White and Black pieces, and their respective squares
     */
    GaviotaTablebasesQueryForResultOnly(ChessPosition chessPosition) {
	super(chessPosition);
    }

    /* (non-Javadoc)
     * @see tablebases.GaviotaTablebasesQueryTemplate#queryGaviotaTablebases()
     */
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
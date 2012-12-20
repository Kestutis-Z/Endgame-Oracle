package chess;

import java.util.List;

import tablebases.Tablebase;

import com.google.common.collect.BiMap;
import com.google.common.collect.EnumBiMap;

/**
 * Algorithm generating random squares for pieces in the specified tablebase.
 * 
 * @author Kestutis
 * 
 */
public class RandomSquareGenerator {

    /**
     * Generates random squares for the pieces of the provided tablebase.
     * 
     * @param tablebase
     *            computerized database containing all possible legal chess
     *            positions and their evaluations, given the set of specific
     *            chess pieces
     * @return the bidirectional map from the pieces present in this chess
     *         position, to the squares they occupy
     */
    static BiMap<Piece, Square> generateRandomSquaresForPieces(
	    Tablebase tablebase) {
	BiMap<Piece, Square> piecesWithSquares = EnumBiMap.create(Piece.class,
		Square.class);
	List<Piece> pieces = tablebase.getAllPieces();
	boolean positionContainsPawn = tablebase.name().contains("P");

	MersenneTwisterFast numberGenerator = new MersenneTwisterFast();

	Square randSquare = null;
	for (Piece piece : pieces) {
	    do {
		if (piece == Piece.WHITE_KING) {
		    if (positionContainsPawn) {
			int wkRand = numberGenerator
				.nextInt(Squares.WHITE_KING_SQUARES_FOR_POSITIONS_WITH_PAWNS
					.numberOfSquares());
			randSquare = Squares.WHITE_KING_SQUARES_FOR_POSITIONS_WITH_PAWNS
				.getSquaresAsList().get(wkRand);
		    } else {
			int wkRand = numberGenerator
				.nextInt(Squares.WHITE_KING_SQUARES_FOR_PAWNLESS_POSITIONS
					.numberOfSquares());
			randSquare = Squares.WHITE_KING_SQUARES_FOR_PAWNLESS_POSITIONS
				.getSquaresAsList().get(wkRand);
		    }
		} else if (piece.getPieceType() == PieceType.PAWN) {
		    int pRand = numberGenerator.nextInt(Squares.PAWN_SQUARES
			    .numberOfSquares());
		    randSquare = Squares.PAWN_SQUARES.getSquaresAsList().get(
			    pRand);
		} else {
		    int allRand = numberGenerator.nextInt(Squares.ALL_SQUARES
			    .numberOfSquares());
		    randSquare = Squares.ALL_SQUARES.getSquaresAsList().get(
			    allRand);
		}
	    } while (piecesWithSquares.containsValue(randSquare));

	    piecesWithSquares.put(piece, randSquare);
	}
	return piecesWithSquares;
    }

}

package chess;

import java.util.List;

import com.google.common.collect.BiMap;
import com.google.common.collect.EnumBiMap;

/**
 * Chess positon unique to an equivalence class of positions, obtained from a
 * (possibly) non-canonical position by exploiting various chessboard
 * symmetries. Can be used instead of the regular position in order to reduce
 * computational and space requirements. Assumes that castling is not allowed in
 * the original position.
 * 
 * @author Kestutis
 * 
 */
public class CanonicalChessPosition {

    /**
     * The kind of symmetry of the chessboard.
     * 
     * @author Kestutis
     * 
     */
    private enum ChessboardSymmetry {

	/** Symmetry along the file d - file e axis. */
	VERTICAL,

	/** Symmetry along the rank 4 - rank 5 axis. */
	HORIZONTAL,

	/**
	 * Symmetry along the A1 - H8 diagonal (squares on the diagonal remain
	 * the same after the rotation).
	 */
	DIAGONAL;

	/**
	 * Reflects the specified square to a symmetric chessboard square.
	 * 
	 * @param originalSquare
	 *            chessboard square that can be empty or occupied by one of
	 *            the chess pieces
	 * @return reflected square
	 */
	Square reflectSquare(Square originalSquare) {
	    int originalFile = originalSquare.getFile();
	    int originalRank = originalSquare.getRank();
	    switch (this) {
	    case VERTICAL:
		return Square.getSquareFromFileAndRank(9 - originalFile,
			originalRank);
	    case HORIZONTAL:
		return Square.getSquareFromFileAndRank(originalFile,
			9 - originalRank);
	    case DIAGONAL:
		return Square.getSquareFromFileAndRank(originalRank,
			originalFile);
	    default:
		throw new AssertionError("Unknown reflection: " + this);
	    }
	}

    }

    /**
     * Representation of the chess position: side to move (White / Black), White
     * and Black pieces, and their respective squares.
     */
    private ChessPosition chessPosition;

    /**
     * @param regularChessPosition
     *            representation of the chess position: side to move (White /
     *            Black), White and Black pieces, and their respective squares
     */
    private CanonicalChessPosition(ChessPosition regularChessPosition) {
	convertToCanonicalForm(regularChessPosition);
    }

    /**
     * Creates the canonical chess position from the given regular chess
     * position.
     * 
     * @param regularChessPosition
     *            a regular, not necessarily canonical chess position
     * @return chess position in canonical form
     */
    public static CanonicalChessPosition createFromRegularChessPosition(
	    ChessPosition regularChessPosition) {
	return new CanonicalChessPosition(regularChessPosition);
    }

    /**
     * Converts a regular, not necessarily canonical chess position to the
     * canonical form.
     * 
     * @param originalChessPosition
     *            regular chess position that may or may not be in canonical
     *            form
     */
    private void convertToCanonicalForm(ChessPosition originalChessPosition) {
	chessPosition = ChessPosition.createDeepCopy(originalChessPosition);
	if (chessPosition.getSquareOfPiece(Piece.WHITE_KING).getFile() >= 5)
	    reflect(ChessboardSymmetry.VERTICAL);

	if (originalChessPosition.containsPawns())
	    return;

	if (chessPosition.getSquareOfPiece(Piece.WHITE_KING).getRank() >= 5)
	    reflect(ChessboardSymmetry.HORIZONTAL);

	List<Piece> allPieces = chessPosition.getAllPieces();
	rotateDiagonallyIfNecessary(allPieces);
    }

    /**
     * Reflects a chess position given the symmetry.
     * 
     * @param symmetry
     *            the kind of symmetry of the chessboard
     */
    private void reflect(ChessboardSymmetry symmetry) {
	BiMap<Piece, Square> originalPiecesWithSquares = chessPosition
		.getPiecesWithSquares();
	BiMap<Piece, Square> newPiecesWithSquares = EnumBiMap.create(
		Piece.class, Square.class);
	for (Piece piece : originalPiecesWithSquares.keySet()) {
	    Square originalSquare = originalPiecesWithSquares.get(piece);
	    Square newSquare = symmetry.reflectSquare(originalSquare);
	    newPiecesWithSquares.put(piece, newSquare);
	}
	chessPosition.setPiecesWithSquares(newPiecesWithSquares);
    }

    /**
     * Rotates the chess position along the A1-H8 diagonal, if it is not in
     * canonical form; otherwise does nothing.
     * 
     * @param pieces
     *            the ordered list of pieces present in the chess position. The
     *            list contains only those pieces, that have not yet been found
     *            to be on the A1-H8 diagonal
     * 
     */
    private void rotateDiagonallyIfNecessary(List<Piece> pieces) {
	Piece piece = pieces.get(0);
	Square square = chessPosition.getSquareOfPiece(piece);
	if (square.isBelowDiagonal_A1_H8()) {
	    return;
	} else if (square.isAboveDiagonal_A1_H8()) {
	    reflect(ChessboardSymmetry.DIAGONAL);
	    return;
	} else {
	    if (pieces.size() > 1) {
		pieces.remove(0);
		rotateDiagonallyIfNecessary(pieces);
	    }
	}
	return;
    }
   
    /**
     * Gets the chess position as the ChessPosition object, that is guaranteed
     * to be in canonical form.
     * 
     * @return representation of the chess position: side to move (White /
     *         Black), White and Black pieces, and their respective squares
     */
    public ChessPosition getChessPosition() {
	return chessPosition;
    }

}

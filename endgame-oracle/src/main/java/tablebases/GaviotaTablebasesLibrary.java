package tablebases;

import chess.ChessPositionEvaluation;
import chess.ChessPositionEvaluation.ChessPositionEvaluationWithDTM;

/**
 * Container for native methods to initialize and probe the Gaviota tablebases.
 * 
 * @author Kestutis
 *
 */
public class GaviotaTablebasesLibrary {

    static {
	try {
	    System.loadLibrary("GaviotaTablebasesLibrary");
	} catch (Exception e) {
	    System.out.println("GaviotaTablebasesLibrary could not be loaded.");
	    e.printStackTrace();
	}
    }

    /**
     * Prepares the Gaviota tablebases API for probing.
     * 
     * @param path
     *            full path to the directory where tablebases are located. If
     *            there are multiple paths, separate them with semicolons, for
     *            example, "C:/tablebases/path1;C:/tablebases/path2".
     */
    public static native void initializeGaviotaTablebases(String path);

    /**
     * Queries the Gaviota tablebases for the game-theoretical result
     * ("White wins" / "Black wins" / "Draw") only.
     * 
     * @param sideToMove
     *            - integer corresponding to one of the TB_return_values in the
     *            the Gaviota Tablebases API
     * @param enpassantSquare
     *            - integer corresponding to one of the TB_squares in the the
     *            Gaviota Tablebases API
     * @param castlingOption
     *            - integer corresponding to one of the TB_castling options in
     *            the the Gaviota Tablebases API
     * @param whiteSquares
     *            - integer array corresponding to the array of TB_squares in
     *            the the Gaviota Tablebases API
     * @param blackSquares
     *            - integer array corresponding to the array of TB_squares in
     *            the the Gaviota Tablebases API
     * @param whitePieces
     *            - byte array corresponding to the array of TB_pieces in the
     *            the Gaviota Tablebases API
     * @param blackPieces
     *            - byte array corresponding to the array of TB_pieces in the
     *            the Gaviota Tablebases API
     * @return game-theoretical result
     */
    public static native ChessPositionEvaluation queryGaviotaTablebasesForResultOnly(
	    int sideToMove, int enpassantSquare, int castlingOption,
	    int[] whiteSquares, int[] blackSquares, byte[] whitePieces,
	    byte[] blackPieces);

    /**
     * Queries the Gaviota tablebases for the game-theoretical result
     * ("White wins" / "Black wins" / "Draw") and distance-to-mate information.
     * 
     * @param sideToMove
     *            - integer corresponding to one of the TB_return_values in the
     *            the Gaviota Tablebases API
     * @param enpassantSquare
     *            - integer corresponding to one of the TB_squares in the the
     *            Gaviota Tablebases API
     * @param castlingOption
     *            - integer corresponding to one of the TB_castling options in
     *            the the Gaviota Tablebases API
     * @param whiteSquares
     *            - integer array corresponding to the array of TB_squares in
     *            the the Gaviota Tablebases API
     * @param blackSquares
     *            - integer array corresponding to the array of TB_squares in
     *            the the Gaviota Tablebases API
     * @param whitePieces
     *            - byte array corresponding to the array of TB_pieces in the
     *            the Gaviota Tablebases API
     * @param blackPieces
     *            - byte array corresponding to the array of TB_pieces in the
     *            the Gaviota Tablebases API
     * @return game-theoretical result and distance-to-mate in moves.
     */
    public static native ChessPositionEvaluationWithDTM queryGaviotaTablebasesForResultAndDistanceToMate(
	    int sideToMove, int enpassantSquare, int castlingOption,
	    int[] whiteSquares, int[] blackSquares, byte[] whitePieces,
	    byte[] blackPieces);

}

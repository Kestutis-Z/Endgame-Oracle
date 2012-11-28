package tablebases.gaviotaTablebases;

import chess.ChessPositionEvaluation;
import chess.ChessPositionEvaluation.ChessPositionEvaluationWithDTM;

/**
 * Container for the native methods used to initialize and probe the Gaviota
 * tablebases.
 * 
 * @author Kestutis
 * 
 */
public class GaviotaTablebasesLibrary {

    static {
	try {
	    System.loadLibrary("GaviotaTablebasesProbingAPI");	    
	} catch (Exception e) {
	    System.out.println("GaviotaTablebasesProbingAPI could not be loaded.");
	    e.printStackTrace();
	}
    }

    /**
     * Prepares the Gaviota tablebases API for probing.
     * 
     * @param path
     *            full path to the directory where tablebases are located. If
     *            there are multiple paths, separate them with semicolons, for
     *            example, "C:/tablebases/path1;C:/tablebases/path2"     
     */
    public static native void initializeGaviotaTablebases(String path); 

    /**
     * Queries the Gaviota tablebases for the game-theoretical result
     * ("White wins" / "Black wins" / "Draw" / "Illegal position") only.
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
     * @return the game-theoretical value of the chess position obtained from
     *         the tablebases
     */
    public static native ChessPositionEvaluation queryGaviotaTablebasesForResultOnly(
	    int sideToMove, int enpassantSquare, int castlingOption,
	    int[] whiteSquares, int[] blackSquares, byte[] whitePieces,
	    byte[] blackPieces);

    /**
     * Queries the Gaviota tablebases for the game-theoretical result
     * ("White wins" / "Black wins" / "Draw" / "Illegal position"), and the
     * distance-to-mate information.
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
     * @return the game-theoretical value of the chess position obtained from
     *         the tablebases, and the distance to mate (a non-negative number
     *         in case the evaluation is "White wins" / "Black wins", zero
     *         otherwise)
     */
    public static native ChessPositionEvaluationWithDTM queryGaviotaTablebasesForResultAndDistanceToMate(
	    int sideToMove, int enpassantSquare, int castlingOption,
	    int[] whiteSquares, int[] blackSquares, byte[] whitePieces,
	    byte[] blackPieces);

}
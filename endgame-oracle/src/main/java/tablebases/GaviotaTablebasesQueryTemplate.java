package tablebases;

import java.util.List;

import chess.ChessPosition;
import chess.Piece;
import chess.SideToMove;
import chess.Square;

/**
 * Algorithm to query (probe) Gaviota Tablebases.
 * 
 * @author Kestutis
 *
 * @param <T> the nature of query (what information it should return)
 */
public abstract class GaviotaTablebasesQueryTemplate<T> {
    protected int sideToMoveForGaviotaTablebases;
    protected int enpassantSquareForGaviotaTablebases;
    protected int castlingOptionForGaviotaTablebases;
    protected int[] whiteSquaresForGaviotaTablebases;
    protected int[] blackSquaresForGaviotaTablebases;
    protected byte[] whitePiecesForGaviotaTablebases;
    protected byte[] blackPiecesForGaviotaTablebases;
    protected T tablebaseEvaluation;
   
    /* ====================================================================== */
    /*    Enums corresponding to the gtb_probe.h in Gaviota Tablebases API    */
    /* ====================================================================== */

    /** Maximum number of pieces that one side (White/Black) can have (includes
     * the NOPIECE option). Required for the Gaviota Tablebases queries.  */
    private static final int MAX_PIECES = 17;
    private static final String GTB_ENUM_PREFIX = "tb_";

    private enum TB_pieces {
	tb_NOPIECE, tb_PAWN, tb_KNIGHT, tb_BISHOP, tb_ROOK, tb_QUEEN, tb_KING
    }

    enum TB_sides {
	tb_WHITE_TO_MOVE, tb_BLACK_TO_MOVE
    }

    enum TB_squares {
	tb_A1, tb_B1, tb_C1, tb_D1, tb_E1, tb_F1, tb_G1, tb_H1, 
	tb_A2, tb_B2, tb_C2, tb_D2, tb_E2, tb_F2, tb_G2, tb_H2, 
	tb_A3, tb_B3, tb_C3, tb_D3, tb_E3, tb_F3, tb_G3, tb_H3, 
	tb_A4, tb_B4, tb_C4, tb_D4, tb_E4, tb_F4, tb_G4, tb_H4, 
	tb_A5, tb_B5, tb_C5, tb_D5, tb_E5, tb_F5, tb_G5, tb_H5, 
	tb_A6, tb_B6, tb_C6, tb_D6, tb_E6, tb_F6, tb_G6, tb_H6, 
	tb_A7, tb_B7, tb_C7, tb_D7, tb_E7, tb_F7, tb_G7, tb_H7, 
	tb_A8, tb_B8, tb_C8, tb_D8, tb_E8, tb_F8, tb_G8, tb_H8, 
	tb_NOSQUARE
    }

    enum TB_castling {
	tb_NOCASTLE(0), tb_WOO(8), tb_WOOO(4), tb_BOO(2), tb_BOOO(1);

	private int value;

	private TB_castling(int value) {
	    this.value = value;
	}

	public int getValue() {
	    return value;
	}

    }
    
    /* ====================================================================== */

    
    GaviotaTablebasesQueryTemplate(ChessPosition chessPosition) {
	sideToMoveForGaviotaTablebases = chessPosition.getSideToMove() == SideToMove.WHITE ? TB_sides.tb_WHITE_TO_MOVE
		.ordinal() : TB_sides.tb_BLACK_TO_MOVE.ordinal();
	enpassantSquareForGaviotaTablebases = TB_squares.tb_NOSQUARE.ordinal(); // TODO Expand
	castlingOptionForGaviotaTablebases = TB_castling.tb_NOCASTLE.value;

	List<Square> whiteSquares = chessPosition.getWhiteSquares();
	List<Square> blackSquares = chessPosition.getBlackSquares();
	List<Piece> whitePieces = chessPosition.getWhitePieces();
	List<Piece> blackPieces = chessPosition.getBlackPieces();

	whiteSquaresForGaviotaTablebases = new int[MAX_PIECES];
	blackSquaresForGaviotaTablebases = new int[MAX_PIECES];
	whitePiecesForGaviotaTablebases = new byte[MAX_PIECES];
	blackPiecesForGaviotaTablebases = new byte[MAX_PIECES];

	whiteSquaresForGaviotaTablebases = convertSquaresToGaviotaTablebasesInts(whiteSquares);
	blackSquaresForGaviotaTablebases = convertSquaresToGaviotaTablebasesInts(blackSquares);
	whitePiecesForGaviotaTablebases = convertPiecesToGaviotaTablebasesInts(whitePieces);
	blackPiecesForGaviotaTablebases = convertPiecesToGaviotaTablebasesInts(blackPieces);
	
	tablebaseEvaluation = queryGaviotaTablebases();	
    }	    
	
    protected static int[] convertSquaresToGaviotaTablebasesInts(List<Square> squares) {
	int[] squaresForGTB = new int[MAX_PIECES];
	int len = squares.size();
	for (int i = 0; i < len; i++) {
	    Square sq = squares.get(i);
	    String sqForGTB = GTB_ENUM_PREFIX + sq.name();
	    squaresForGTB[i] = TB_squares.valueOf(sqForGTB).ordinal();
	}
	squaresForGTB[len] = TB_squares.tb_NOSQUARE.ordinal();
	return squaresForGTB;
    }

    protected static byte[] convertPiecesToGaviotaTablebasesInts(List<Piece> pieces) {
	byte[] piecesForGTB = new byte[MAX_PIECES];
	int len = pieces.size();
	for (int i = 0; i < len; i++) {
	    Piece pc = pieces.get(i);
	    String pcForGTB = GTB_ENUM_PREFIX + pc.getPieceType().name();
	    piecesForGTB[i] = (byte) TB_pieces.valueOf(pcForGTB).ordinal();
	}
	piecesForGTB[len] = (byte) TB_pieces.tb_NOPIECE.ordinal();
	return piecesForGTB;
    }
    
    public abstract T queryGaviotaTablebases(); 
    
}

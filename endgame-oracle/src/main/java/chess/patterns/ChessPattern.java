package chess.patterns;

import chess.ChessPosition;

/**
 * Configuration of chess pieces that recurs in chess games, and is meaningful
 * to human players.
 * 
 * @author Kestutis
 * 
 */
public interface ChessPattern {
    
    /**
     * Checks if this pattern is present in the specified chess position..
     * 
     * @param chessPosition
     *            representation of the chess position: side to move (White /
     *            Black), White and Black pieces, and their respective squares
     * @return true, if the position contains this pattern
     */
    public abstract boolean isPresent(ChessPosition chessPosition);
    
}

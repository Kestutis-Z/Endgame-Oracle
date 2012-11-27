package chess.patterns;

import java.util.Set;

import chess.ChessPosition;
import chess.Square;

/**
 * Chess entity that can be under control by some other (controller) chess
 * entity. Here "controllable" means that all the squares that are in some way
 * related to this entity, are attacked by the controller. <br/>
 * <br/>
 * 
 * For example, in the pattern "White King defends White Rook", the White Rook
 * is the controllable - the square it occupies is controlled by the White King.
 * 
 * @author Kestutis
 * 
 */
public interface ControllableEntity extends ChessEntity {

    /**
     * Gets the squares that are in some way related (e.g., occupied by) to this
     * controllable entity. Those squares may (or may not) be controlled
     * (attacked) by some controller entity.
     * 
     * @param chessPosition
     *            representation of the chess position: side to move (White /
     *            Black), White and Black pieces, and their respective squares
     * @return the set of controllable squares
     */
    public abstract Set<Square> getControllableSquares(ChessPosition chessPosition);
    
}
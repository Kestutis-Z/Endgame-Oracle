package chess.patterns;

import java.util.Set;

import tablebases.TablebaseWithSTM;

/**
 * An idea in chess that can be comprehended and used by a chess player.
 * 
 * @author Kestutis
 * 
 */
interface ChessConcept {

    /**
     * Generates all the chess patterns for this concept, given the tablebase
     * with the side-to-move.
     * 
     * @param tablebaseWithStm
     *            the tablebase positions with the specified side-to-move only
     * @return the set of patterns relevant to the combination of this chess
     *         concept, tablebase, and the side-to-move
     */
    abstract Set<ChessPattern> generateChessPatterns(TablebaseWithSTM tablebaseWithStm);
    
    @Override
    abstract String toString();
}

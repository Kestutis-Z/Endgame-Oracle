package chess.patterns;

import chess.ChessPosition;

/**
 * Chess pattern concerned with one chess entity (the "controller") being able
 * to control another chess entity (the "controllable).
 * 
 * @author Kestutis
 * 
 */
public class ControlPattern implements ChessPattern {
    
    /** The chess entity that is able to control some other (controllable) chess entity. */
    private ControllerEntity controller;
    
    /** The chess entity that can be under control by some other (controller) chess entity. */
    private ControllableEntity controllable;   

    /**
     * Instantiates a new control pattern.
     * 
     * @param controller
     *            the chess entity that is able to control some other
     *            (controllable) chess entity
     * @param controllable
     *            the chess entity that can be under control by some other
     *            (controller) chess entity
     */
    ControlPattern(ControllerEntity controller,
	    ControllableEntity controllable) {
	this.controller = controller;
	this.controllable = controllable;
    }

    /* (non-Javadoc)
     * @see chess.patterns.ChessPattern#isPresent(chess.ChessPosition)
     */
    @Override
    public boolean isPresent(ChessPosition chessPosition) {
	return controller.getControlSquares(chessPosition)
		.containsAll(
			controllable.getControllableSquares(chessPosition));
    }

}
package chess;

import org.junit.Assert;

/**
 * Side whose turn is to make a move - either White or Black <dt>
 * <b>Precondition:</b>
 * <dd>Must correspond with the enum PieceColour.
 * 
 * @author Kestutis
 * 
 */
public enum SideToMove {
    
    WHITE, BLACK
    
    ;
    
    static {
	Assert.assertTrue(
		"The SideToMove and PieceColour constants must correspond",
		SideToMove.WHITE.name().equals(PieceColour.WHITE.name()));
	Assert.assertTrue(
		"The SideToMove and PieceColour constants must correspond",
		SideToMove.BLACK.name().equals(PieceColour.BLACK.name()));
    }
    
}

package chess.patterns;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import chess.SideToMove;

import tablebases.Tablebase;
import tablebases.TablebaseWithSTM;

public class ControlConceptTest {

    @Test
    public void test() {
	TablebaseWithSTM tablebaseWithStm = 
		new TablebaseWithSTM(Tablebase.KPK, SideToMove.BLACK);
	
	Set<String> expectedPatternNames = new HashSet<>();
	expectedPatternNames.add("WHITE_KING defends WHITE_PAWN");
	expectedPatternNames.add("BLACK_KING attacks WHITE_PAWN");
	expectedPatternNames.add("WHITE_PAWN attacks BLACK_KING");

	Set<ChessPattern> patterns = ControlConcept.PIECE_CONTROLS_PIECE
		.generateChessPatterns(tablebaseWithStm);

	Set<String> actualPatternNames = new HashSet<>();
	for (ChessPattern cp : patterns) {
	    actualPatternNames.add(cp.toString());
	}
	
	assertEquals(expectedPatternNames, actualPatternNames);
    }

}

package tablebases;

import static org.junit.Assert.*;

import org.junit.Test;

public class TablebaseTest {

    @Test
    public void testHowManyChessPiecesThisTablebaseHas() {
	Tablebase tablebase1 = Tablebase.KK;
	assertEquals(2, tablebase1.chessPiecesCount());
	
	Tablebase tablebase2 = Tablebase.KRK; 
	assertEquals(3, tablebase2.chessPiecesCount());	
    }

}

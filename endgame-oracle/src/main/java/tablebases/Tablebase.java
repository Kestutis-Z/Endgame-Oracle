package tablebases;

/**
 * Computerized database containing all possible legal chess positions 
 * and their evaluations, given the set of specific chess pieces. 
 * @author Kestutis
 *
 */
public enum Tablebase {
    
    KK,
    
    KRK;

    /** 
     * @return number of pieces (White and Black) that 
     * a chess position from this tablebase contains.
     */
    public int chessPiecesCount() {
	return this.name().length();
    }

}

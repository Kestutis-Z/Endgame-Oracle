package chess;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Chessboard square that can be empty or occupied by one of the chess pieces.
 * There are 64 squares on the chessboard. Each square can be uniquely
 * identified by its file (a, b, c, d, e, f, g, or h, corresponding to integers
 * from 1 to 8) and rank (1, 2, ..., 8).
 * 
 * @author Kestutis
 * 
 */
public enum Square {

    A8(18), B8(28), C8(38), D8(48), E8(58), F8(68), G8(78), H8(88), A7(17), B7(
	    27), C7(37), D7(47), E7(57), F7(67), G7(77), H7(87), A6(16), B6(26), C6(
	    36), D6(46), E6(56), F6(66), G6(76), H6(86), A5(15), B5(25), C5(35), D5(
	    45), E5(55), F5(65), G5(75), H5(85), A4(14), B4(24), C4(34), D4(44), E4(
	    54), F4(64), G4(74), H4(84), A3(13), B3(23), C3(33), D3(43), E3(53), F3(
	    63), G3(73), H3(83), A2(12), B2(22), C2(32), D2(42), E2(52), F2(62), G2(
	    72), H2(82), A1(11), B1(21), C1(31), D1(41), E1(51), F1(61), G1(71), H1(
	    81);

    /**
     * Direction from this Square to the adjacent Square - East, Southeast, etc.
     */
    public static enum Direction {

	EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST, NORTH, NORTHEAST

    }

    /**
     * The 2-digit integer identifying this square. The first digit represents
     * the file, the second - the rank of this square.
     */
    private final int squareID;

    /**
     * Instantiates a new square.
     * 
     * @param squareID
     *            the 2-digit integer identifying this square
     */
    private Square(int squareID) {
	this.squareID = squareID;
    }

    /**
     * Gets the integer from 11 to 88, where the first digit corresponds to the
     * file of the square, and the second digit - to the rank of the square.
     * 
     * @return the 2-digit integer identifying this square
     */
    public int getSquareID() {
	return squareID;
    }

    /**
     * Gets the file of this square as an integer (1 to 8, corresponding to
     * files a to h).
     * 
     * @return the file as an integer 1, 2, ..., 8
     */
    public int getFile() {
	return squareID / 10;
    }

    /**
     * Gets the rank of this square as an integer (1 to 8).
     * 
     * @return the rank as an integer 1, 2, ..., 8
     */
    public int getRank() {
	return squareID % 10;
    }

    /**
     * Converts file and rank, specified as integers between 1 and 8, to the
     * corresponding square.
     * 
     * @param file
     *            the file of this square as an integer (1 to 8, corresponding
     *            to files a to h)
     * @param rank
     *            the rank of this square as an integer (1 to 8)
     * @return the chessboard square that can be empty or occupied by one of the
     *         chess pieces
     */
    protected static Square getSquareFromFileAndRank(int file, int rank) {
	if (file < 1 || file > 8)
	    throw new IllegalArgumentException("Invalid file value: " + file);
	if (rank < 1 || rank > 8)
	    throw new IllegalArgumentException("Invalid rank value: " + rank);
	int squareID = file * 10 + rank;
	return getSquareFromID(squareID);
    }

    /**
     * Converts the square identification number to the corresponding square.
     * 
     * @param squareID
     *            the square identification number (integer from 11 to 88)
     * @return the chessboard square that can be empty or occupied by one of the
     *         chess pieces
     */
    public static Square getSquareFromID(int squareID) {
	for (Square sq : EnumSet.allOf(Square.class)) {
	    if (sq.getSquareID() == squareID)
		return sq;
	}
	throw new IllegalArgumentException("Square with value " + squareID
		+ " does not exist!");
    }

    /**
     * Checks if this square has an adjacent square to the specified direction.
     * 
     * @param direction
     *            the direction from this Square to the adjacent Square - East,
     *            Southeast, etc.
     * @return true, if there is a square to the specified direction
     * @see #getAdjacentSquare(Direction)
     */
    public boolean hasAdjacentSquareTo(Direction direction) {
	switch (direction) {
	case EAST:
	    return this.getFile() == 8 ? false : true;
	case SOUTHEAST:
	    return this.getFile() == 8 || this.getRank() == 1 ? false : true;
	case SOUTH:
	    return this.getRank() == 1 ? false : true;
	case SOUTHWEST:
	    return this.getFile() == 1 || this.getRank() == 1 ? false : true;
	case WEST:
	    return this.getFile() == 1 ? false : true;
	case NORTHWEST:
	    return this.getFile() == 1 || this.getRank() == 8 ? false : true;
	case NORTH:
	    return this.getRank() == 8 ? false : true;
	case NORTHEAST:
	    return this.getFile() == 8 || this.getRank() == 8 ? false : true;
	default:
	    throw new AssertionError("Unknown direction: " + direction);
	}
    }

    /**
     * Gets the square adjacent to this square.
     * 
     * @param direction
     *            the direction from this square to the adjacent square - East,
     *            Southeast, etc.
     * @return the chessboard square that can be empty or occupied by one of the
     *         chess pieces
     * <dt><b>Precondition:</b>
     * 		<dd>Must have an adjacent square to the specified direction
     * @see #hasAdjacentSquareTo(Direction direction) 
     */
    public Square getAdjacentSquare(Direction direction) {
	switch (direction) {
	case EAST:
	    return getSquareFromFileAndRank(this.getFile() + 1, this.getRank());
	case SOUTHEAST:
	    return getSquareFromFileAndRank(this.getFile() + 1,
		    this.getRank() - 1);
	case SOUTH:
	    return getSquareFromFileAndRank(this.getFile(), this.getRank() - 1);
	case SOUTHWEST:
	    return getSquareFromFileAndRank(this.getFile() - 1,
		    this.getRank() - 1);
	case WEST:
	    return getSquareFromFileAndRank(this.getFile() - 1, this.getRank());
	case NORTHWEST:
	    return getSquareFromFileAndRank(this.getFile() - 1,
		    this.getRank() + 1);
	case NORTH:
	    return getSquareFromFileAndRank(this.getFile(), this.getRank() + 1);
	case NORTHEAST:
	    return getSquareFromFileAndRank(this.getFile() + 1,
		    this.getRank() + 1);
	default:
	    throw new AssertionError("Unknown direction: " + direction);
	}
    }

    /**
     * Assembles the list of consequtive squares (the "range") from the starting
     * square, given the chess position and the direction. Range ends with the
     * edge of the board, or with the square containing another piece (that
     * square is included in the range). This method is applicable to long-range
     * pieces.
     * 
     * @param chessPosition
     *            representation of the chess position: side to move (White /
     *            Black), White and Black pieces, and their respective squares
     * @param direction
     *            the direction from this square to the adjacent square - East,
     *            Southeast, etc.
     * @return the list of consequtive squares (the "range")
     */
    public List<Square> getRangeOfSquares(ChessPosition chessPosition,
	    Direction direction) {
	List<Square> squares = new ArrayList<Square>();
	Square nextSq = this;
	while (nextSq.hasAdjacentSquareTo(direction)) {
	    nextSq = nextSq.getAdjacentSquare(direction);
	    squares.add(nextSq);
	    if (nextSq.containsPiece(chessPosition))
		return squares;
	}
	return squares;
    }

    /**
     * Checks if this square does contain some chess piece, i.e. if it is not
     * empty.
     * 
     * @param chessPosition
     *            representation of the chess position: side to move (White /
     *            Black), White and Black pieces, and their respective squares
     * @return true, if this square is occupied by some chess piece
     */
    public boolean containsPiece(ChessPosition chessPosition) {
	return chessPosition.getPiecesWithSquares().values().contains(this);
    }

    /**
     * Gets the square at the end of range (the last square in the range). 
     *      
     * @param chessPosition
     *            representation of the chess position: side to move (White /
     *            Black), White and Black pieces, and their respective squares
     * @param direction
     *            the direction from this square to the adjacent square - East,
     *            Southeast, etc.
     * @return the chessboard square that can be empty or occupied by one of the
     *         chess pieces
     * @throws SquareHasNoAdjacentSquareException
     *             if this square has no adjacent square to the specified
     *             direction
     * <dt><b>Precondition:</b>
     * 		<dd>the range must contain at least one square
     */
    public Square getSquareAtTheEndOfRange(ChessPosition chessPosition,
	    Direction direction) throws SquareHasNoAdjacentSquareException {
	if (!this.hasAdjacentSquareTo(direction))
	    throw new SquareHasNoAdjacentSquareException("Square " + this
		    + " has " + "no adjacent square to the " + direction
		    + " in the chess " + "position:\n\n" + chessPosition
		    + "\n\n");
	List<Square> range = this.getRangeOfSquares(chessPosition, direction);
	return range.get(range.size() - 1);
    }

    /**
     * Gets the piece at the end of range. 
    
     * @param chessPosition
     *            representation of the chess position: side to move (White /
     *            Black), White and Black pieces, and their respective squares
     * @param direction
     *            the direction from this square to the adjacent square - East,
     *            Southeast, etc.
     * @return the representation of the chess piece
     * @throws SquareHasNoAdjacentSquareException
     *             if this square has no adjacent square to the specified
     *             direction
     * @throws SquareContainsNoPiecesException
     *             if the square at the end of the range is empty
     * <dt><b>Precondition:</b>
     * 		<dd>the range to the specified direction must contain a square 
     * 		with some piece on it.
     */
    public Piece getPieceAtTheEndOfRange(ChessPosition chessPosition,
	    Direction direction) throws SquareHasNoAdjacentSquareException,
	    SquareContainsNoPiecesException {
	Square sq = this.getSquareAtTheEndOfRange(chessPosition, direction);
	Piece pc = sq.getPiece(chessPosition);
	return pc;
    }

    /**
     * Gets the piece that this square contains.
     * 
     * @param chessPosition
     *            representation of the chess position: side to move (White /
     *            Black), White and Black pieces, and their respective squares
     * @return the representation of the chess piece
     * @throws SquareContainsNoPiecesException
     *             if this square is empty
     */
    public Piece getPiece(ChessPosition chessPosition)
	    throws SquareContainsNoPiecesException {
	if (!this.containsPiece(chessPosition)) {
	    throw new SquareContainsNoPiecesException("Square " + this
		    + " contains " + "no piece in the chess " + "position:\n\n"
		    + chessPosition + "\n\n");
	}
	return chessPosition.getPiecesWithSquares().inverse().get(this);
    }

    /**
     * Checks if this square is adjacent to the given square.
     * 
     * @param sq
     *            the chessboard square that can be empty or occupied by one of
     *            the chess pieces
     * @return true, if this and the specified squares are adjacent
     */
    public boolean isAdjacentTo(Square sq) {
	return Math.abs(this.getFile() - sq.getFile()) < 2
		&& Math.abs(this.getRank() - sq.getRank()) < 2;
    }

    /**
     * Assembles the rectangle zone of squares between the specified corner
     * squares.
     * 
     * @param begin
     *            one chosen corner square of the rectangle
     * @param end
     *            the corner square of the rectangle opposite (both vertically
     *            and horizontally) to begin.
     * @return the set containing all the squares in the rectangle zone
     */
    public static Set<Square> getRectangleZone(Square begin, Square end) {
	if (begin.getSquareID() > end.getSquareID())
	    return getRectangleZone(end, begin);
	/* Now rank of begin <= than rank of end */
	Set<Square> squareZone = new HashSet<Square>();
	int beginFile = begin.getFile();
	int beginRank = begin.getRank();
	int endFile = end.getFile();
	int endRank = end.getRank();
	if (beginFile <= endFile) {
	    for (int file = beginFile; file <= endFile; file++) {
		for (int rank = beginRank; rank <= endRank; rank++) {
		    squareZone.add(Square.getSquareFromFileAndRank(file, rank));
		}
	    }
	} else {
	    for (int file = endFile; file <= beginFile; file++) {
		for (int rank = beginRank; rank <= endRank; rank++) {
		    squareZone.add(Square.getSquareFromFileAndRank(file, rank));
		}
	    }
	}
	return squareZone;
    }

}
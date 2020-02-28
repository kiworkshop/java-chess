package mychess.domain.piece;

public class Queen implements Piece {

    private static final String name = "pawn";
    private static final char symbol = '\u2655';

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
}

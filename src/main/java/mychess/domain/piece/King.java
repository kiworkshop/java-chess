package mychess.domain.piece;

public class King implements Piece {

    private static final String name = "pawn";
    private static final char symbol = '\u2654';

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
}

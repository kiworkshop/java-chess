package mychess.domain.piece;

public class Knight implements Piece {

    private static final String name = "pawn";
    private static final char symbol = '\u2658';

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
}

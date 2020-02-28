package mychess.domain.piece;

public class Rook implements Piece {

    private static final String name = "pawn";
    private static final char symbol = '\u2656';

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
}

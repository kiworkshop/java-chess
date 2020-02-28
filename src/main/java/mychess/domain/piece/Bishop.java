package mychess.domain.piece;

public class Bishop implements Piece {

    private static final String name = "bishop";
    private static final char symbol = '\u2657';

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
}

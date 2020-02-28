package mychess.domain.piece;

public class Pawn implements Piece{

    private static final String name = "pawn";
    private static final char symbol = '\u265F';

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
}

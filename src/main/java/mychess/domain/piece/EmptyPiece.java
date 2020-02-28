package mychess.domain.piece;

public class EmptyPiece implements Piece {

    private static final String name = "empty";
    private static final char symbol = 'ㅁ';

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
}

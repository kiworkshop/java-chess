package mychess.domain.piece;

public class Rook implements Piece {

    private static final String name = "pawn";
    private static final char whiteSymbol = '\u2656';
    private static final char blackSymbol = '\u265C';

    private final boolean isWhite;

    public Rook(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        if (isWhite) return whiteSymbol;
        return blackSymbol;
    }
}

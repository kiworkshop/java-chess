package mychess.domain.piece;

public class Queen implements Piece {

    private static final String name = "pawn";
    private static final char whiteSymbol = '\u2655';
    private static final char blackSymbol = '\u265B';

    private final boolean isWhite;

    public Queen(boolean isWhite) {
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
package mychess.domain.piece;

public class King implements Piece {

    private static final String name = "pawn";
    private static final char whiteSymbol = '\u2654';
    private static final char blackSymbol = '\u265A';

    private final boolean isWhite;

    public King(boolean isWhite) {
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
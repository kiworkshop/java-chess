package mychess.domain.piece;

public class Knight implements Piece {

    private static final String name = "pawn";
    private static final char whiteSymbol = '\u2658';
    private static final char blackSymbol = '\u265E';

    private final boolean isWhite;

    public Knight(boolean isWhite) {
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

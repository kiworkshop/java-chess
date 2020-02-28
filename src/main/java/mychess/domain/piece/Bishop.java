package mychess.domain.piece;

public class Bishop implements Piece {

    private static final String name = "bishop";
    private static final char whiteSymbol = '\u2657';
    private static final char blackSymbol = '\u265D';

    private final boolean isWhite;

    public Bishop(boolean isWhite) {
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

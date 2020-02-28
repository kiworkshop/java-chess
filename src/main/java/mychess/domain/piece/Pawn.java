package mychess.domain.piece;

public class Pawn implements Piece {

    private static final String name = "pawn";
    private static final char whiteSymbol = '\u2659';
    private static final char blackSymbol = '\u265F';

    private final boolean isWhite;

    public Pawn(boolean isWhite) {
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

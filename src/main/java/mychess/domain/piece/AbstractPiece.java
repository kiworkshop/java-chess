package mychess.domain.piece;

import mychess.domain.Color;

public abstract class AbstractPiece implements Piece {

    private final String name;
    private final Color color;
    private final char whiteSymbol;
    private final char blackSymbol;

    public AbstractPiece(String name, Color color, char whiteSymbol, char blackSymbol) {
        this.name = name;
        this.color = color;
        this.whiteSymbol = whiteSymbol;
        this.blackSymbol = blackSymbol;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color.getColorName();
    }

    public char getSymbol() {
        if (color.equals(Color.WHITE)) return whiteSymbol;
        return blackSymbol;
    }
}

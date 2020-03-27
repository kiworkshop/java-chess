package mychess.domain.piece;

import mychess.domain.Color;

public class Knight extends AbstractPiece {

    private static final String name = "knight";
    private static final char whiteSymbol = '\u2658';
    private static final char blackSymbol = '\u265E';

    public Knight(Color color) {
        super(name, color, whiteSymbol, blackSymbol);
    }
}

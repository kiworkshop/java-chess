package mychess.domain.piece;

import mychess.domain.Color;

public class Bishop extends AbstractPiece {

    private static final String name = "bishop";
    private static final char whiteSymbol = '\u2657';
    private static final char blackSymbol = '\u265D';

    public Bishop(Color color) {
        super(name, color, whiteSymbol, blackSymbol);
    }
}

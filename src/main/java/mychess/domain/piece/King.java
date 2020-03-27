package mychess.domain.piece;

import mychess.domain.Color;

public class King extends AbstractPiece {

    private static final String name = "king";
    private static final char whiteSymbol = '\u2654';
    private static final char blackSymbol = '\u265A';

    public King(Color color) {
        super(name, color, whiteSymbol, blackSymbol);
    }
}
package mychess.domain.piece;

import mychess.domain.Color;

public class Queen extends AbstractPiece {

    private static final String name = "queen";
    private static final char whiteSymbol = '\u2655';
    private static final char blackSymbol = '\u265B';

    public Queen(Color color) {
        super(name, color, whiteSymbol, blackSymbol);
    }
}
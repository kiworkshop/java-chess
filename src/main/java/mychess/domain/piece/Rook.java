package mychess.domain.piece;

import mychess.domain.Color;

public class Rook extends AbstractPiece {

    private static final String name = "pawn";
    private static final char whiteSymbol = '\u2656';
    private static final char blackSymbol = '\u265C';

    public Rook(Color color) {
        super(name, color, whiteSymbol, blackSymbol);
    }
}

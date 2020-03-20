package mychess.domain.piece;

import mychess.domain.Color;

public class Pawn extends AbstractPiece {

    private static final String name = "pawn";
    private static final char whiteSymbol = '\u2659';
    private static final char blackSymbol = '\u265F';

    public Pawn(Color color) {
        super(name, color, whiteSymbol, blackSymbol);
    }
}

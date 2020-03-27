package mychess.domain.piece;

import mychess.domain.Color;

public class EmptyPiece implements Piece {

    private static final String name = "empty";
    private static final char symbol = 'ㅁ';

    public String getName() {
        return name;
    }

    public String getColor() { return ""; }

    public char getSymbol() {
        return symbol;
    }
}

package model.chessDomain.pieces;

import model.chessDomain.ChessBoardSnapshot;
import model.chessDomain.Position;

public class ChessPiece implements Movable{
    private final PieceColor color;
    private final PieceType type;

    public ChessPiece(PieceColor color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    public PieceColor getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    @Override
    public boolean isValidMove(Position source, Position destination, ChessBoardSnapshot boardSnapshot) {
        return true;
    }
}

package chess.model.domain.game.pieces;

import chess.model.domain.game.ChessBoardSnapshot;
import chess.model.domain.game.Position;

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
    public boolean isValidMove(ChessBoardSnapshot boardSnapshot, Position source, Position destination) {
        return true;
    }
}

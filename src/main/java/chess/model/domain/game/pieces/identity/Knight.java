package chess.model.domain.game.pieces;

import chess.model.domain.game.board.ChessBoardSnapshot;
import chess.model.domain.game.Position;

public class Knight extends ChessPiece {
    public Knight(PieceColor color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public boolean isValidMove(ChessBoardSnapshot boardSnapshot, Position source, Position destination) {
        if (Math.abs(source.getX() - destination.getX()) == 1
                && Math.abs(source.getY() - destination.getY()) == 2) {
            return true;
        } else if (Math.abs(source.getX() - destination.getX()) == 2
                && Math.abs(source.getY() - destination.getY()) == 1) {
            return true;
        }
        return false;
    }
}

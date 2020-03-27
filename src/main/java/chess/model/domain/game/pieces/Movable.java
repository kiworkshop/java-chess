package chess.model.domain.game.pieces;

import chess.model.domain.game.ChessBoardSnapshot;
import chess.model.domain.game.Position;

public interface Movable {
    public boolean isValidMove(Position source, Position destination, ChessBoardSnapshot boardSnapshot);
}

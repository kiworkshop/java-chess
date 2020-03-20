package chess.domain.piece;

import chess.domain.board.Position;

public interface Movable {

    boolean canMove(Position source, Position destination);

    String getMovingPolicy();

}

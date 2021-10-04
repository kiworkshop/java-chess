package chess.domain.movement;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.Set;

public class BlankMoveStrategy extends MoveStrategy {
    @Override
    public boolean canMove(Board board, Piece source, Piece target) {
        checkBlankPosition(source);
        return false;
    }

    @Override
    public Set<Position> getMovablePositions(Board board, Piece source) {
        return Collections.emptySet();
    }
}

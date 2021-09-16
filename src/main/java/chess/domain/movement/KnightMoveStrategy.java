package chess.domain.movement;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class KnightMoveStrategy extends MoveStrategy {
    private static final List<Direction> KNIGHT_DIRECTIONS = Direction.KNIGHT_DIRECTIONS;

    @Override
    public boolean canMove(Board board, Piece source, Piece target) {
        checkBlankPosition(source);
        checkSameTeamPosition(source.getTeam(), target.getTeam());
        Set<Position> movablePositions = getMovablePositions(board, source);
        return checkMovablePosition(movablePositions, target.getPosition());
    }

    @Override
    public Set<Position> getMovablePositions(Board board, Piece source) {
        return onceMovablePositions(KNIGHT_DIRECTIONS, source.getPosition()).stream()
                .filter(target -> board.isNotSameTeam(source.getPosition(), target))
                .collect(Collectors.toSet());
    }
}

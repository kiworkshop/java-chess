package chess.domain.movement;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PawnMoveStrategy extends MoveStrategy {
    private static final int WHITE_PAWN_INIT_RANK = 2;
    private static final int BLACK_PAWN_INIT_RANK = 7;

    private final List<Direction> pawnDirection;
    private final List<Direction> pawnFirstDirections;
    private final List<Direction> pawnAttackDirections;

    public PawnMoveStrategy(Team team) {
        this.pawnDirection = Direction.pawnDirection(team);
        this.pawnFirstDirections = Direction.pawnFirstDirection(team);
        this.pawnAttackDirections = Direction.pawnAttackDirection(team);
    }

    @Override
    public boolean canMove(Board board, Piece source, Piece target) {
        checkBlankPosition(source);
        checkSameTeamPosition(source.getTeam(), target.getTeam());

        Set<Position> movablePositions = getMovablePositions(board, source);
        return checkMovablePosition(movablePositions, target.getPosition());
    }

    @Override
    public Set<Position> getMovablePositions(Board board, Piece source) {
        Set<Position> movablePositions = onceMovablePositions(pawnDirection, source.getPosition()).stream()
                .filter(target -> board.isNotSameTeam(source.getPosition(), target))
                .collect(Collectors.toSet());
        movablePositions.addAll(getFirstMovePositions(board, source));
        movablePositions.addAll(getAttackPositions(board, source.getPosition()));
        return movablePositions;
    }

    private Set<Position> getFirstMovePositions(Board board, Piece source) {
        Position position = source.getPosition();
        if (isFirstMove(source.getTeam(), position)) {
            return onceMovablePositions(pawnFirstDirections, position).stream()
                    .filter(target -> board.isNotSameTeam(position, target))
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

    private boolean isFirstMove(Team team, Position position) {
        if (team.equals(Team.WHITE)) {
            return position.getRankNumber() == WHITE_PAWN_INIT_RANK;
        }
        return position.getRankNumber() == BLACK_PAWN_INIT_RANK;
    }

    private Set<Position> getAttackPositions(Board board, Position source) {
        return onceMovablePositions(pawnAttackDirections, source).stream()
                .filter(target -> board.isOtherTeam(source, target))
                .collect(Collectors.toSet());
    }
}

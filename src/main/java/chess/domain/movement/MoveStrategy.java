package chess.domain.movement;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class MoveStrategy {
    public static final int MIN_MOVEMENT = 1;
    public static final int MAX_MOVEMENT = 8;

    public abstract boolean canMove(Board board, Piece source, Piece target);

    public abstract Set<Position> getMovablePositions(Board board, Piece source);

    protected void validate(Piece source, Piece target) {
        checkBlankPosition(source);
        checkSameTeamPosition(source.getTeam(), target.getTeam());
    }

    protected void checkBlankPosition(Piece sourcePiece) {
        if (isBlank(sourcePiece.getTeam())) {
            throw new IllegalArgumentException("해당 위치에 체스말이 없습니다.");
        }
    }

    private boolean isBlank(Team team) {
        return team.equals(Team.NEUTRAL);
    }

    private void checkSameTeamPosition(Team attackTeam, Team defenseTeam) {
        if (attackTeam.equals(defenseTeam)) {
            throw new IllegalArgumentException("아군이 있는 칸에는 이동할 수 없습니다.");
        }
    }

    protected boolean checkMovablePosition(Set<Position> movablePositions, Position target) {
        if (movablePositions.contains(target)) {
            return true;
        }
        throw new IllegalArgumentException("체스말을 옮길 수 있는 위치가 아닙니다.");
    }

    protected Set<Position> onceMovablePositions(List<Direction> directions, Position source) {
        return directions.stream()
                .map(source::findBy)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    protected Set<Position> repeatMovablePositions(Board board, Direction direction, Position source) {
        return getPositionStream(direction, source)
                .takeWhile(board::isBlank)
                .collect(Collectors.toSet());
    }

    protected Set<Position> attackMovablePosition(Board board, Direction direction, Position source) {
        return getPositionStream(direction, source)
                .takeWhile(target -> board.isOtherTeam(source, target) || board.isBlank(target))
                .dropWhile(board::isBlank)
                .limit(1)
                .collect(Collectors.toSet());
    }

    private Stream<Position> getPositionStream(Direction direction, Position source) {
        return IntStream.range(MIN_MOVEMENT, MAX_MOVEMENT)
                .boxed()
                .map(count -> source.findBy(direction, count))
                .filter(Optional::isPresent)
                .map(Optional::get);
    }
}
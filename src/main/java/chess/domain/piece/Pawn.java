package chess.domain.piece;

import chess.domain.board.Rank;
import chess.domain.player.Direction;
import chess.domain.player.Position;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class Pawn extends Piece {

    private static final Rank WHITE_INITIAL_RANK = Rank.R2;
    private static final Rank BLACK_INITIAL_RANK = Rank.R7;
    private static final int MAX_RANK_THRESHOLD = 2;

    public Pawn(final Color color) {
        super(MovePattern.findPawnMovePattern(color), color, false);
    }

    @Override
    public Collection<Position> findPath(final Position source, final Position target) {
        int fileGap = target.calculateFileGap(source);
        int rankGap = target.calculateRankGap(source);
        boolean initialMove = isInitialMove(source);
        int absoluteRankGap = abs(rankGap);

        validateInitialMoveThreshold(absoluteRankGap, initialMove);
        validateFollowingMoveThreshold(absoluteRankGap, initialMove);

        Direction direction = movePattern.findDirection(fileGap, rankGap);
        return findPassingPositions(source, target, direction);
    }

    private boolean isInitialMove(final Position source) {
        return source.hasSameRank(WHITE_INITIAL_RANK) || source.hasSameRank(BLACK_INITIAL_RANK);
    }

    private void validateInitialMoveThreshold(final int absoluteRankGap, final boolean isInitialMove) {
        if (isGreaterThanMaxRankThreshold(absoluteRankGap) && isInitialMove) {
            throw new IllegalArgumentException("최초 이동 시 최대 2칸까지 이동할 수 있습니다.");
        }
    }

    private void validateFollowingMoveThreshold(final int absoluteRankGap, final boolean isInitialMove) {
        if ((isGreaterOrEqualToMaxThreshold(absoluteRankGap) && !isInitialMove)) {
            throw new IllegalArgumentException("최초 이동이 아닐 시 1칸만 이동할 수 있습니다.");
        }
    }

    private boolean isGreaterThanMaxRankThreshold(final int absoluteRankGap) {
        return absoluteRankGap > MAX_RANK_THRESHOLD;
    }

    private boolean isGreaterOrEqualToMaxThreshold(final int absoluteRankGap) {
        return absoluteRankGap >= MAX_RANK_THRESHOLD;
    }

    @Override
    public Set<Position> findAvailableAttackPositions(final Position source) {
        return movePattern.getDirections().stream()
                .filter(Direction::isDiagonal)
                .map(direction -> findAvailablePositions(source, direction, false))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isNotPawn() {
        return false;
    }
}

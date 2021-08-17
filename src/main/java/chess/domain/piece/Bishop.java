package chess.domain.piece;

import chess.domain.board.Position;

import java.util.HashSet;
import java.util.Set;

public class Bishop extends Piece {

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> findPaths(final Position source, final Position target) {
        int fileGap = target.calculateFileGap(source);
        int rankGap = target.calculateRankGap(source);
        validatePattern(fileGap, rankGap);

        Direction direction = Direction.of(fileGap, rankGap);
        return collectPositions(source, target, direction);
    }

    private Set<Position> collectPositions(final Position source, final Position target, final Direction direction) {
        Set<Position> positions = new HashSet<>();
        Position current = source;

        while (!target.equals(current)) {
            current = current.move(direction);
            positions.add(current);
        }

        positions.remove(target);
        return positions;
    }

    private void validatePattern(final int fileGap, final int rankGap) {
        if (Math.abs(fileGap) != Math.abs(rankGap)) {
            throw new IllegalArgumentException("비숍이 이동할 수 없는 위치입니다.");
        }
    }
}

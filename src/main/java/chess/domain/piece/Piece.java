package chess.domain.piece;

import chess.domain.board.Position;

import java.util.HashSet;
import java.util.Set;

import static chess.domain.piece.Color.WHITE;

public abstract class Piece {
    private final Color color;

    Piece(final Color color) {
        this.color = color;
    }

    protected abstract void validatePattern(final int fileGap, final int rankGap);

    public boolean isWhite() {
        return WHITE == color;
    }

    public boolean hasSameColor(final Piece target) {
        return this.color == target.color;
    }

    public Set<Position> findPaths(final Position source, final Position target) {
        int fileGap = target.calculateFileGap(source);
        int rankGap = target.calculateRankGap(source);
        validatePattern(fileGap, rankGap);

        Direction direction = Direction.of(fileGap, rankGap);
        return collectPositions(source, target, direction);
    }

    protected boolean isStraight(final int fileGap, final int rankGap) {
        return fileGap == 0 || rankGap == 0;
    }

    protected boolean isDiagonal(final int fileGap, final int rankGap) {
        return Math.abs(fileGap) == Math.abs(rankGap);
    }

    public Set<Position> collectPositions(final Position source, final Position target, final Direction direction) {
        Set<Position> positions = new HashSet<>();
        Position current = source;

        while (!target.equals(current)) {
            current = current.move(direction);
            positions.add(current);
        }

        positions.remove(target);
        return positions;
    }
}

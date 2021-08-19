package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static java.lang.Math.abs;

public abstract class Piece {
    private final Collection<Direction> DIRECTIONS;
    private final Color color;

    Piece(final Collection<Direction> DIRECTIONS, final Color color) {
        this.DIRECTIONS = DIRECTIONS;
        this.color = color;
    }

    protected abstract void validatePattern(final int fileGap, final int rankGap);

    public boolean isWhite() {
        return color == WHITE;
    }

    public boolean isBlack() {
        return color == BLACK;
    }

    public Set<Position> findPaths(final Position source, final Position target) {
        int fileGap = target.calculateFileGap(source);
        int rankGap = target.calculateRankGap(source);
        validatePattern(abs(fileGap), abs(rankGap));

        Direction direction = Direction.of(fileGap, rankGap);
        return collectPositions(source, target, direction);
    }

    protected boolean isStraight(final int fileGap, final int rankGap) {
        return fileGap == 0 || rankGap == 0;
    }

    protected boolean isFiniteStraight(final int fileGap, final int rankGap) {
        return (fileGap + rankGap) == 1;
    }

    protected boolean isDiagonal(final int fileGap, final int rankGap) {
        return fileGap == rankGap;
    }

    protected boolean isFiniteDiagonal(final int fileGap, final int rankGap) {
        return (fileGap == 1) && (rankGap == 1);
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

    public Collection<Position> findAvailableAttackPositions(Position position) {
        return DIRECTIONS.stream()
                .map(position::findAvailablePositions)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}

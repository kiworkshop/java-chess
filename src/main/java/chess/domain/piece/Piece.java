package chess.domain.piece;

import chess.domain.player.Direction;
import chess.domain.player.Position;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Piece {

    protected final MovePattern movePattern;
    private final Color color;
    private final boolean canMoveInfinitely;

    Piece(final MovePattern movePattern, final Color color, final boolean canMoveInfinitely) {
        this.movePattern = movePattern;
        this.color = color;
        this.canMoveInfinitely = canMoveInfinitely;
    }

    public boolean isWhite() {
        return color.isWhite();
    }

    public Collection<Position> findPath(final Position source, final Position target) {
        int fileGap = target.calculateFileGap(source);
        int rankGap = target.calculateRankGap(source);

        Direction direction = movePattern.findDirection(fileGap, rankGap);

        return findPassingPositions(source, target, direction);
    }

    protected Collection<Position> findPassingPositions(final Position source, final Position target, final Direction direction) {
        Set<Position> positions = new HashSet<>();
        Position current = source;

        while (current.isDifferent(target)) {
            current = current.move(direction);
            positions.add(current);
        }

        positions.remove(target);
        return positions;
    }

    public Collection<Position> findAvailableAttackPositions(final Position source) {
        return movePattern.getDirections().stream()
                .map(direction -> findAvailablePositions(source, direction, canMoveInfinitely))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    protected Collection<Position> findAvailablePositions(final Position source, final Direction direction, final boolean canMoveInfinitely) {
        if (canMoveInfinitely) {
            return findInfinitePositions(source, direction);
        }

        return findFinitePositions(source, direction);
    }

    private Collection<Position> findInfinitePositions(final Position source, final Direction direction) {
        Collection<Position> positions = new HashSet<>();
        Position current = source;

        while (current.isMovable(direction)) {
            current = current.move(direction);
            positions.add(current);
        }

        return positions;
    }

    private Collection<Position> findFinitePositions(final Position source, final Direction direction) {
        if (source.isMovable(direction)) {
            Position movedPosition = source.move(direction);
            return Collections.singleton(movedPosition);
        }

        return Collections.emptySet();
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isNotPawn() {
        return true;
    }
}


package chess.domain.piece;

import chess.domain.player.Direction;
import chess.domain.player.Position;

import java.util.Collection;
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

    public Set<Position> findPath(final Position source, final Position target) {
        int fileGap = target.calculateFileGap(source);
        int rankGap = target.calculateRankGap(source);

        Direction direction = movePattern.findDirection(fileGap, rankGap);
        return source.findPassingPositions(target, direction);
    }

    public Collection<Position> findAvailableAttackPositions(final Position position) {
        return movePattern.getCoordinates().stream()
                .map(direction -> position.findAvailablePositions(direction, canMoveInfinitely))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
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


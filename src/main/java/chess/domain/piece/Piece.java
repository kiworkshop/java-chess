package chess.domain.piece;

import chess.domain.piece.type.MovePattern;
import chess.domain.player.MoveCoordinate;
import chess.domain.player.Position;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Piece {

    protected final MovePattern movePattern;
    private final Color color;

    Piece(final MovePattern movePattern, final Color color) {
        this.movePattern = movePattern;
        this.color = color;
    }

    public boolean isWhite() {
        return color.isWhite();
    }

    public Set<Position> findPath(final Position source, final Position target) {
        int fileGap = target.calculateFileGap(source);
        int rankGap = target.calculateRankGap(source);

        MoveCoordinate moveCoordinate = movePattern.findMoveCoordinate(fileGap, rankGap);
        return source.findPassingPositions(target, moveCoordinate);
    }

    public Collection<Position> findAvailableAttackPositions(final Position position) {
        Set<Position> finitePositions = movePattern.finiteMoveCoordinates().stream()
                .map(moveCoordinate -> position.findAvailablePositions(moveCoordinate, true))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        Set<Position> infinitePositions = movePattern.infiniteMoveCoordinates().stream()
                .map(moveCoordinate -> position.findAvailablePositions(moveCoordinate, false))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        Collection<Position> positions = new HashSet<>(finitePositions);
        positions.addAll(infinitePositions);
        return positions;
    }
}


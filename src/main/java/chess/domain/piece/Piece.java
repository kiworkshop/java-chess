package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.pattern.MovePattern;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

public abstract class Piece {

    protected final MovePattern movePattern;
    private final Color color;

    Piece(final MovePattern movePattern, final Color color) {
        this.movePattern = movePattern;
        this.color = color;
    }

    public boolean isWhite() {
        return color == WHITE;
    }

    public boolean isBlack() {
        return color == BLACK;
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

package chess.domain.piece.move;

import chess.domain.board.Position;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

import static chess.domain.piece.move.MoveUnit.*;

public enum MoveUnits {

    CARDINAL(NORTH, SOUTH, WEST, EAST),
    DIAGONAL(NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST),
    CARDINAL_AND_DIAGONAL(NORTH, SOUTH, WEST, EAST, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST),
    KNIGHT(NORTH_EAST_LEFT, NORTH_EAST_RIGHT, NORTH_WEST_LEFT, NORTH_WEST_RIGHT, SOUTH_EAST_LEFT, SOUTH_EAST_RIGHT, SOUTH_WEST_LEFT, SOUTH_WEST_RIGHT),
    WHITE_PAWN(WHITE_PAWN_INITIAL, NORTH_EAST, NORTH_WEST, NORTH),
    WHITE_PAWN_ATTACK(NORTH_EAST, NORTH_WEST),
    BLACK_PAWN(BLACK_PAWN_INITIAL, SOUTH_EAST, SOUTH_WEST, SOUTH),
    BLACK_PAWN_ATTACK(SOUTH_EAST, SOUTH_WEST);

    private final Collection<MoveUnit> moveUnits;

    MoveUnits(MoveUnit... moveUnits) {
        this.moveUnits = Collections.unmodifiableCollection(
                Arrays.stream(moveUnits)
                        .collect(Collectors.toSet())
        );
    }

    public MoveUnit findMatchMoveUnit(final Gap gap, final MoveLimit moveLimit) {
        return moveUnits.stream()
                .filter(moveUnit -> moveUnit.matches(gap, moveLimit))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 방향으로 이동할 수 없습니다."));
    }

    public Collection<Path> findReachableFinitePaths(final Position source) {
        Collection<Path> paths = new HashSet<>();

        moveUnits.stream()
                .filter(source::isMovable)
                .forEach(moveUnit -> {
                    Path path = buildPath(source, moveUnit);
                    paths.add(path);
                });

        return paths;
    }

    private Path buildPath(Position source, MoveUnit moveUnit) {
        Position target = source.move(moveUnit);
        return new Path(source, target);
    }

    public Collection<Path> findReachableInfinitePaths(final Position source) {
        return moveUnits.stream()
                .filter(source::isMovable)
                .map(source::findReachablePositions)
                .map(Path::new)
                .collect(Collectors.toList());
    }
}
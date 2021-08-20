package chess.domain.piece.pattern;

import chess.domain.piece.Color;
import chess.domain.piece.MoveCoordinate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static chess.domain.piece.MoveCoordinate.BLACK_PAWN_INITIAL_SOUTH;
import static chess.domain.piece.MoveCoordinate.EAST;
import static chess.domain.piece.MoveCoordinate.NORTH;
import static chess.domain.piece.MoveCoordinate.NORTH_EAST;
import static chess.domain.piece.MoveCoordinate.NORTH_EAST_LEFT;
import static chess.domain.piece.MoveCoordinate.NORTH_EAST_RIGHT;
import static chess.domain.piece.MoveCoordinate.NORTH_WEST;
import static chess.domain.piece.MoveCoordinate.NORTH_WEST_LEFT;
import static chess.domain.piece.MoveCoordinate.NORTH_WEST_RIGHT;
import static chess.domain.piece.MoveCoordinate.SOUTH;
import static chess.domain.piece.MoveCoordinate.SOUTH_EAST;
import static chess.domain.piece.MoveCoordinate.SOUTH_EAST_LEFT;
import static chess.domain.piece.MoveCoordinate.SOUTH_EAST_RIGHT;
import static chess.domain.piece.MoveCoordinate.SOUTH_WEST;
import static chess.domain.piece.MoveCoordinate.SOUTH_WEST_LEFT;
import static chess.domain.piece.MoveCoordinate.SOUTH_WEST_RIGHT;
import static chess.domain.piece.MoveCoordinate.WEST;
import static chess.domain.piece.MoveCoordinate.WHITE_PAWN_INITIAL_NORTH;

public class MovePattern {

    private static final Collection<MoveCoordinate> CARDINAL_COORDINATES = Collections.unmodifiableList(Arrays.asList(
            NORTH, SOUTH, WEST, EAST
    ));

    private static final Collection<MoveCoordinate> DIAGONAL_COORDINATES = Collections.unmodifiableList(Arrays.asList(
            NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST
    ));

    private static final Collection<MoveCoordinate> WHITE_PAWN_COORDINATES = Collections.unmodifiableList(Arrays.asList(
            WHITE_PAWN_INITIAL_NORTH, NORTH_EAST, NORTH_WEST, NORTH
    ));

    private static final Collection<MoveCoordinate> BLACK_PAWN_COORDINATES = Collections.unmodifiableList(Arrays.asList(
            BLACK_PAWN_INITIAL_SOUTH, SOUTH_EAST, SOUTH_WEST, SOUTH
    ));

    private static final Collection<MoveCoordinate> KNIGHT_COORDINATES = Collections.unmodifiableList(Arrays.asList(
            NORTH_EAST_LEFT, NORTH_EAST_RIGHT, NORTH_WEST_LEFT, NORTH_WEST_RIGHT,
            SOUTH_EAST_LEFT, SOUTH_EAST_RIGHT, SOUTH_WEST_LEFT, SOUTH_WEST_RIGHT
    ));

    private final Collection<MoveCoordinate> infiniteMoveCoordinates;
    private final Collection<MoveCoordinate> finiteMoveCoordinates;

    private MovePattern(final Collection<MoveCoordinate> infiniteMoveCoordinates, final Collection<MoveCoordinate> finiteMoveCoordinates) {
        this.infiniteMoveCoordinates = Collections.unmodifiableCollection(infiniteMoveCoordinates);
        this.finiteMoveCoordinates = Collections.unmodifiableCollection(finiteMoveCoordinates);
    }

    public static MovePattern queenPattern() {
        return new MovePattern(DIAGONAL_COORDINATES, CARDINAL_COORDINATES);
    }

    public static MovePattern kingPattern() {
        List<MoveCoordinate> finiteMoveCoordinates = new ArrayList<>(CARDINAL_COORDINATES);
        finiteMoveCoordinates.addAll(DIAGONAL_COORDINATES);
        return new MovePattern(Collections.emptyList(), finiteMoveCoordinates);
    }

    public static MovePattern knightPattern() {
        return new MovePattern(Collections.emptyList(), KNIGHT_COORDINATES);
    }

    public static MovePattern rookPattern() {
        return new MovePattern(CARDINAL_COORDINATES, Collections.emptyList());
    }

    public static MovePattern bishopPattern() {
        return new MovePattern(DIAGONAL_COORDINATES, Collections.emptyList());
    }

    public static MovePattern pawnPattern(final Color color) {
        if (color.isWhite()) {
            new MovePattern(Collections.emptyList(), WHITE_PAWN_COORDINATES);
        }
        return new MovePattern(Collections.emptyList(), BLACK_PAWN_COORDINATES);
    }

    public MoveCoordinate findMoveCoordinate(int fileGap, int rankGap) {
        List<MoveCoordinate> result = new ArrayList<>();

        infiniteMoveCoordinates.stream()
                .filter(moveCoordinate -> moveCoordinate.matches(fileGap, rankGap, false))
                .findAny()
                .ifPresent(result::add);

        finiteMoveCoordinates.stream()
                .filter(moveCoordinate -> moveCoordinate.matches(fileGap, rankGap, true))
                .findAny()
                .ifPresent(result::add);

        if (result.isEmpty()) {
            throw new IllegalArgumentException("매칭되는 방향이 없습니다.");
        }

        return result.get(0);
    }

    public Collection<MoveCoordinate> finiteMoveCoordinates() {
        return finiteMoveCoordinates;
    }

    public Collection<MoveCoordinate> infiniteMoveCoordinates() {
        return infiniteMoveCoordinates;
    }
}

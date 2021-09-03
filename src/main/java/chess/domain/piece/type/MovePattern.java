package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.player.MoveCoordinate;

import java.util.*;

import static chess.domain.player.MoveCoordinate.*;

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

    private static final Collection<MoveCoordinate> WHITE_PAWN_ATTACK_COORDINATES = Collections.unmodifiableList(Arrays.asList(
            NORTH_EAST, NORTH_WEST
    ));

    private static final Collection<MoveCoordinate> BLACK_PAWN_COORDINATES = Collections.unmodifiableList(Arrays.asList(
            BLACK_PAWN_INITIAL_SOUTH, SOUTH_EAST, SOUTH_WEST, SOUTH
    ));

    private static final Collection<MoveCoordinate> BLACK_PAWN_ATTACK_COORDINATES = Collections.unmodifiableList(Arrays.asList(
            SOUTH_EAST, SOUTH_WEST
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
        List<MoveCoordinate> infiniteMoveCoordinates = new ArrayList<>(CARDINAL_COORDINATES);
        infiniteMoveCoordinates.addAll(DIAGONAL_COORDINATES);
        return new MovePattern(infiniteMoveCoordinates, Collections.emptyList());
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
            return new MovePattern(Collections.emptyList(), WHITE_PAWN_COORDINATES);
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
            throw new IllegalArgumentException("해당 방향으로 이동할 수 없습니다.");
        }

        return result.get(0);
    }

    public Collection<MoveCoordinate> finiteMoveCoordinates() {
        return finiteMoveCoordinates;
    }

    public Collection<MoveCoordinate> infiniteMoveCoordinates() {
        return infiniteMoveCoordinates;
    }

    public Collection<MoveCoordinate> pawnAttackMoveCoordinates(boolean isWhite) {
        if (isWhite) {
            return WHITE_PAWN_ATTACK_COORDINATES;
        }
        return BLACK_PAWN_ATTACK_COORDINATES;
    }
}

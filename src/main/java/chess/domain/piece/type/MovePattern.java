package chess.domain.piece.type;

import chess.domain.piece.Color;

import java.util.*;

import static chess.domain.piece.type.MoveUnit.*;

public class MovePattern {

    private static final Collection<MoveUnit> CARDINAL_UNITS = Collections.unmodifiableList(Arrays.asList(
            NORTH, SOUTH, WEST, EAST
    ));

    private static final Collection<MoveUnit> DIAGONAL_UNITS = Collections.unmodifiableList(Arrays.asList(
            NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST
    ));

    private static final Collection<MoveUnit> WHITE_PAWN_UNITS = Collections.unmodifiableList(Arrays.asList(
            WHITE_PAWN_INITIAL_NORTH, NORTH_EAST, NORTH_WEST, NORTH
    ));

    private static final Collection<MoveUnit> WHITE_PAWN_ATTACK_UNITS = Collections.unmodifiableList(Arrays.asList(
            NORTH_EAST, NORTH_WEST
    ));

    private static final Collection<MoveUnit> BLACK_PAWN_UNITS = Collections.unmodifiableList(Arrays.asList(
            BLACK_PAWN_INITIAL_SOUTH, SOUTH_EAST, SOUTH_WEST, SOUTH
    ));

    private static final Collection<MoveUnit> BLACK_PAWN_ATTACK_UNITS = Collections.unmodifiableList(Arrays.asList(
            SOUTH_EAST, SOUTH_WEST
    ));

    private static final Collection<MoveUnit> KNIGHT_UNITS = Collections.unmodifiableList(Arrays.asList(
            NORTH_EAST_LEFT, NORTH_EAST_RIGHT, NORTH_WEST_LEFT, NORTH_WEST_RIGHT,
            SOUTH_EAST_LEFT, SOUTH_EAST_RIGHT, SOUTH_WEST_LEFT, SOUTH_WEST_RIGHT
    ));

    private final Collection<MoveUnit> infiniteMoveUnits;
    private final Collection<MoveUnit> finiteMoveUnits;

    private MovePattern(final Collection<MoveUnit> infiniteMoveUnits, final Collection<MoveUnit> finiteMoveUnits) {
        this.infiniteMoveUnits = Collections.unmodifiableCollection(infiniteMoveUnits);
        this.finiteMoveUnits = Collections.unmodifiableCollection(finiteMoveUnits);
    }

    public static MovePattern queenPattern() {
        List<MoveUnit> infiniteMoveUnits = new ArrayList<>(CARDINAL_UNITS);
        infiniteMoveUnits.addAll(DIAGONAL_UNITS);
        return new MovePattern(infiniteMoveUnits, Collections.emptyList());
    }

    public static MovePattern kingPattern() {
        List<MoveUnit> finiteMoveUnits = new ArrayList<>(CARDINAL_UNITS);
        finiteMoveUnits.addAll(DIAGONAL_UNITS);
        return new MovePattern(Collections.emptyList(), finiteMoveUnits);
    }

    public static MovePattern knightPattern() {
        return new MovePattern(Collections.emptyList(), KNIGHT_UNITS);
    }

    public static MovePattern rookPattern() {
        return new MovePattern(CARDINAL_UNITS, Collections.emptyList());
    }

    public static MovePattern bishopPattern() {
        return new MovePattern(DIAGONAL_UNITS, Collections.emptyList());
    }

    public static MovePattern pawnPattern(final Color color) {
        if (color.isWhite()) {
            return new MovePattern(Collections.emptyList(), WHITE_PAWN_UNITS);
        }
        return new MovePattern(Collections.emptyList(), BLACK_PAWN_UNITS);
    }

    public MoveUnit findMoveUnit(int fileGap, int rankGap) {
        List<MoveUnit> result = new ArrayList<>();

        infiniteMoveUnits.stream()
                .filter(moveUnit -> moveUnit.matches(fileGap, rankGap, false))
                .findAny()
                .ifPresent(result::add);

        finiteMoveUnits.stream()
                .filter(moveUnit -> moveUnit.matches(fileGap, rankGap, true))
                .findAny()
                .ifPresent(result::add);

        if (result.isEmpty()) {
            throw new IllegalArgumentException("해당 방향으로 이동할 수 없습니다.");
        }

        return result.get(0);
    }

    public Collection<MoveUnit> finiteMoveUnits() {
        return finiteMoveUnits;
    }

    public Collection<MoveUnit> infiniteMoveUnits() {
        return infiniteMoveUnits;
    }

    public Collection<MoveUnit> pawnAttackMoveUnits(boolean isWhite) {
        if (isWhite) {
            return WHITE_PAWN_ATTACK_UNITS;
        }
        return BLACK_PAWN_ATTACK_UNITS;
    }
}

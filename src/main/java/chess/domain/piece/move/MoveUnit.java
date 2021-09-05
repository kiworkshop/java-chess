package chess.domain.piece.move;

public enum MoveUnit {

    NORTH_EAST(1, 1),
    SOUTH_EAST(1, -1),
    NORTH_WEST(-1, 1),
    SOUTH_WEST(-1, -1),
    NORTH(0, 1),
    SOUTH(0, -1),
    EAST(1, 0),
    WEST(-1, 0),

    WHITE_PAWN_INITIAL(0, 2),
    BLACK_PAWN_INITIAL(0, -2),

    NORTH_EAST_LEFT(1, 2),
    NORTH_EAST_RIGHT(2, 1),
    NORTH_WEST_LEFT(-2, 1),
    NORTH_WEST_RIGHT(-1, 2),
    SOUTH_EAST_LEFT(2, -1),
    SOUTH_EAST_RIGHT(1, -2),
    SOUTH_WEST_LEFT(-1, -2),
    SOUTH_WEST_RIGHT(-2, -1);

    public static MoveUnit whitePawnMove() {
        return NORTH;
    }

    public static MoveUnit blackPawnMove() {
        return SOUTH;
    }

    private final int file;
    private final int rank;

    MoveUnit(final int file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean matches(final Gap gap, final MoveLimit moveLimit) {
        if (moveLimit.isFinite()) {
            return this.file == gap.file() && this.rank == gap.rank();
        }

        if (this.file == 0) {
            return this.file == gap.file() && (gap.rank() % this.rank == 0) && hasSameSign(gap);
        }

        if (this.rank == 0) {
            return this.rank == gap.rank() && (gap.file() % this.file == 0) && hasSameSign(gap);
        }

        return isMultiple(gap) && hasSameSign(gap);
    }

    private boolean isMultiple(final Gap gap) {
        return hasSameRate(gap) && isDivisible(gap);
    }

    private boolean isDivisible(final Gap gap) {
        return (gap.file() % this.file == 0) && (gap.rank() % this.rank == 0);
    }

    private boolean hasSameRate(final Gap gap) {
        return (gap.file() / this.file) == (gap.rank() / this.rank);
    }

    private boolean hasSameSign(final Gap gap) {
        return (file ^ gap.file()) >= 0 && (rank ^ gap.rank()) >= 0;
    }

    public boolean isInitialPawn() {
        return this == WHITE_PAWN_INITIAL || this == BLACK_PAWN_INITIAL;
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }
}

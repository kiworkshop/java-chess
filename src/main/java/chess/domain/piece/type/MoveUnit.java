package chess.domain.piece.type;

public enum MoveUnit {

    NORTH_EAST(1, 1),
    SOUTH_EAST(1, -1),
    NORTH_WEST(-1, 1),
    SOUTH_WEST(-1, -1),
    NORTH(0, 1),
    SOUTH(0, -1),
    EAST(1, 0),
    WEST(-1, 0),

    WHITE_PAWN_INITIAL_NORTH(0, 2),
    BLACK_PAWN_INITIAL_SOUTH(0, -2),

    NORTH_EAST_LEFT(1, 2),
    NORTH_EAST_RIGHT(2, 1),
    NORTH_WEST_LEFT(-2, 1),
    NORTH_WEST_RIGHT(-1, 2),
    SOUTH_EAST_LEFT(2, -1),
    SOUTH_EAST_RIGHT(1, -2),
    SOUTH_WEST_LEFT(-1, -2),
    SOUTH_WEST_RIGHT(-2, -1);

    private final int file;
    private final int rank;

    MoveUnit(final int file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public boolean matches(final int fileGap, final int rankGap, boolean isFinite) {
        if (isFinite) {
            return this.file == fileGap && this.rank == rankGap;
        }

        if (this.file == 0) {
            return this.file == fileGap && (rankGap % this.rank == 0) && hasSameSign(fileGap, rankGap);
        }

        if (this.rank == 0) {
            return this.rank == rankGap && (fileGap % this.file == 0) && hasSameSign(fileGap, rankGap);
        }

        return isMultiple(fileGap, rankGap) && hasSameSign(fileGap, rankGap);
    }

    private boolean isMultiple(final int fileGap, final int rankGap) {
        return hasSameRate(fileGap, rankGap) && isDivisible(fileGap, rankGap);
    }

    private boolean isDivisible(final int fileGap, final int rankGap) {
        return (fileGap % this.file == 0) && (rankGap % this.rank == 0);
    }

    private boolean hasSameRate(final int fileGap, final int rankGap) {
        return (fileGap / this.file) == (rankGap / this.rank);
    }

    private boolean hasSameSign(final int fileGap, final int rankGap) {
        return (file ^ fileGap) >= 0 && (rank ^ rankGap) >= 0;
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }
}

package chess.domain.piece;

public enum Direction {
    NORTH_EAST(1, 1),
    SOUTH_EAST(1, -1),
    NORTH_WEST(-1, 1),
    SOUTH_WEST(-1, -1),
    NORTH(0, 1),
    SOUTH(0, -1),
    EAST(1, 0),
    WEST(-1, 0);

    private final int file;
    private final int rank;

    Direction(final int file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Direction of(final int fileGap, final int rankGap) {
        if (fileGap > 0 && rankGap > 0) {
            return NORTH_EAST;
        }
        if (fileGap > 0 && rankGap < 0) {
            return SOUTH_EAST;
        }
        if (fileGap < 0 && rankGap > 0) {
            return NORTH_WEST;
        }
        if (fileGap < 0 && rankGap < 0) {
            return SOUTH_WEST;
        }
        if (fileGap > 0) {
            return EAST;
        }
        if (fileGap < 0) {
            return WEST;
        }
        if (rankGap > 0) {
            return NORTH;
        }
        return SOUTH;
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }
}

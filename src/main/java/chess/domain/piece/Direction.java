package chess.domain.piece;

import java.util.Arrays;

public enum Direction {
    NORTH_EAST(1, 1),
    SOUTH_EAST(1, -1),
    NORTH_WEST(-1, 1),
    SOUTH_WEST(-1, -1),
    NORTH(0, 1),
    SOUTH(0, -1),
    EAST(1, 0),
    WEST(-1, 0);

    private static final int ZERO = 0;

    private final int file;
    private final int rank;

    Direction(final int file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Direction of(final int fileGap, final int rankGap) {
        int fileSign = Integer.compare(fileGap, ZERO);
        int rankSign = Integer.compare(rankGap, ZERO);
        return findByFileAndRankSign(fileSign, rankSign);
    }

    private static Direction findByFileAndRankSign(final int fileSign, final int rankSign) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.hasSameSigns(fileSign, rankSign))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("매칭되는 방향이 없습니다."));
    }

    private boolean hasSameSigns(final int fileSign, final int rankSign) {
        return this.file == fileSign && this.rank == rankSign;
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }
}

package chess.domain.board;

import java.util.Arrays;

public enum Rank {

    R8(8),
    R7(7),
    R6(6),
    R5(5),
    R4(4),
    R3(3),
    R2(2),
    R1(1);

    private final int index;

    Rank(final int index) {
        this.index = index;
    }

    public static Rank of(final int rankIndex) {
        return Arrays.stream(Rank.values())
                .filter(rank -> hasSameIndex(rankIndex, rank))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    private static boolean hasSameIndex(final int rankIndex, final Rank rank) {
        return rank.index == rankIndex;
    }

    public int getIndex() {
        return index;
    }

    public int calculateGap(final Rank rank) {
        return this.index - rank.index;
    }

    public Rank add(final int amount) {
        return Rank.of(this.index + amount);
    }

    public boolean canMove(final int amount) {
        int rankIndex = index + amount;
        return rankIndex >= R1.index && rankIndex <= R8.index;
    }
}


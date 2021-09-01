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
                .filter(rank -> rank.hasSameIndex(rankIndex))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 랭크가 존재하지 않습니다."));
    }

    private boolean hasSameIndex(final int rankIndex) {
        return this.index == rankIndex;
    }

    public int getIndex() {
        return index;
    }

    public int calculateGap(final Rank rank) {
        return this.index - rank.index;
    }

    public Rank move(final int amount) {
        return Rank.of(this.index + amount);
    }

    public boolean canMove(final int amount) {
        int rankIndex = index + amount;

        return isInRange(rankIndex);
    }

    private boolean isInRange(final int rankIndex) {
        return rankIndex >= R1.index && rankIndex <= R8.index;
    }
}


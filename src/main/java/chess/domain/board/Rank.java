package chess.domain.board;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Rank {
    R8(8),
    R7(7),
    R6(6),
    R5(5),
    R4(4),
    R3(3),
    R2(2),
    R1(1);

    private static final Map<Integer, Rank> RANKS = createRanks();

    private final int index;

    Rank(final int index) {
        this.index = index;
    }

    private static Map<Integer, Rank> createRanks() {
        Map<Integer, Rank> ranks = new HashMap<>();

        Arrays.stream(values())
                .forEach(rank -> ranks.put(rank.index, rank));

        return Collections.unmodifiableMap(ranks);
    }

    public static Rank of(final int rankIndex) {
        Rank rank = RANKS.get(rankIndex);

        if (rank == null) {
            throw new IllegalArgumentException("일치하는 랭크가 존재하지 않습니다.");
        }

        return rank;
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


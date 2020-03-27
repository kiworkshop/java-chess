package chess.domain.position;

import chess.model.postiion.Rank;

import java.util.Arrays;

public enum EnumRank implements Rank {

    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private final int value;

    EnumRank(int value) {
        this.value = value;
    }

    public String getRank() {
        return String.valueOf(value);
    }

    @Override
    public Rank add(int value) {
        int newRank = this.value + value;
        return Arrays.stream(values()).filter(ranks -> ranks.value == newRank)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("움직일 수 없는 positon입니다."));
    }

    @Override
    public boolean isMax() {
        return this == EIGHT;
    }

    @Override
    public boolean isMin() {
        return this == ONE;
    }

    @Override
    public int rankDifference(Rank targetRank) {
        return targetRank.value() - value;
    }

    @Override
    public int value() {
        return value;
    }
}

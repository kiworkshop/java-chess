package chess.domain.position;

import java.util.Arrays;

public enum Rank {
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ONE(1);

    private final int number;

    Rank(int number) {
        this.number = number;
    }

    public static Rank findBy(int rankNumber) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.number == rankNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Rank를 찾을 수 없습니다."));
    }

    public int getNumber() {
        return number;
    }
}

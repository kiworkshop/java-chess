package chess.domain.piece;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Rank {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private int rankPosition;

    Rank(int rankPosition) {
        this.rankPosition = rankPosition;
    }

    public static Rank findBy(String rankPosition) {
        return Arrays.stream(Rank.values())
                .filter(r -> r.rankPosition == Integer.parseInt(rankPosition))
                .findFirst()
                .orElseThrow(()->new IllegalAccessError("해당되는 위치가 없습니다."));
    }

    public static Rank findBy(int rankPosition) {
        return Arrays.stream(Rank.values())
                .filter(r -> r.rankPosition == rankPosition)
                .findFirst()
                .orElseThrow(()->new IllegalAccessError("해당되는 위치가 없습니다."));
    }
}

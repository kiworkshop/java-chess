package chess.domain.team;

import chess.domain.RankComparator;
import chess.domain.piece.Rank;

import java.util.Arrays;

public class RankComparatorTest {
    public static void main(String[] args) {
        RankComparator rankComparator = new RankComparator();
        Arrays.stream(Rank.values())
                .sorted(rankComparator::compare)
                .forEach(rank -> {
                    System.out.println(rank.getRankPosition());
                });
    }
}

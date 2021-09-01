package chess.domain;

import chess.domain.piece.Rank;

import java.util.Comparator;

public class RankComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        if(o1 instanceof Rank && o2 instanceof Rank) {
            Rank r1 = (Rank) o1;
            Rank r2 = (Rank) o2;

            return r2.getRankPosition() - r1.getRankPosition();
        }
        return 0;
    }
}

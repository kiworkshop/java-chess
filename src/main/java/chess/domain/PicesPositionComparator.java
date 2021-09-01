package chess.domain;

import chess.domain.piece.PiecePosition;
import chess.domain.piece.Rank;

import java.util.Comparator;

public class PicesPositionComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        if(o1 instanceof PiecePosition && o2 instanceof PiecePosition) {
            PiecePosition p1 = (PiecePosition) o1;
            PiecePosition p2 = (PiecePosition) o2;

            int pvalue1 = p1.getRank().getRankPosition();
            int pvalue2 = p2.getRank().getRankPosition();

            if(pvalue1 == pvalue2) {
                return p1.getFile().getFilePosition() - p2.getFile().getFilePosition();
            }
            return pvalue1 - pvalue2;
        }
        return 0;
    }
}

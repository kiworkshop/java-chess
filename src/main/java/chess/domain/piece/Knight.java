package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Collections;
import java.util.Set;

import static java.lang.Math.abs;

public class Knight extends Piece {

    public Knight(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> findPaths(final Position source, final Position target) {
        int fileGap = target.calculateFileGap(source);
        int rankGap = target.calculateRankGap(source);
        validatePattern(abs(fileGap), abs(rankGap));
        return Collections.emptySet();
    }

    @Override
    protected void validatePattern(final int fileGap, final int rankGap) {
        if (!isKnightPattern(fileGap, rankGap)) {
            throw new IllegalArgumentException("나이트가 이동할 수 없는 위치입니다.");
        }
    }

    private boolean isKnightPattern(final int fileGap, final int rankGap) {
        return (fileGap == 2 && rankGap == 1) || (fileGap == 1 && rankGap == 2);
    }
}

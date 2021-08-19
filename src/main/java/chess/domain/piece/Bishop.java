package chess.domain.piece;

import java.util.Arrays;
import java.util.Collection;

import static chess.domain.piece.Direction.NORTH_EAST;
import static chess.domain.piece.Direction.NORTH_WEST;
import static chess.domain.piece.Direction.SOUTH_EAST;
import static chess.domain.piece.Direction.SOUTH_WEST;

public class Bishop extends Piece {

    private static final Collection<Direction> DIRECTIONS = Arrays.asList(NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST);

    public Bishop(final Color color) {
        super(DIRECTIONS, color);
    }

    @Override
    protected void validatePattern(int fileGap, int rankGap) {
        if (!isDiagonal(fileGap, rankGap)) {
            throw new IllegalArgumentException("비숍이 이동할 수 없는 위치입니다.");
        }
    }
}

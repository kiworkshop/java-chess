package chess.domain.piece;

import java.util.Arrays;
import java.util.Collection;

import static chess.domain.piece.Direction.EAST;
import static chess.domain.piece.Direction.NORTH;
import static chess.domain.piece.Direction.SOUTH;
import static chess.domain.piece.Direction.WEST;

public class Rook extends Piece {

    private static final Collection<Direction> DIRECTIONS = Arrays.asList(EAST, WEST, NORTH, SOUTH);

    public Rook(final Color color) {
        super(DIRECTIONS, color);
    }

    @Override
    protected void validatePattern(int fileGap, int rankGap) {
        if (!isStraight(fileGap, rankGap)) {
            throw new IllegalArgumentException("룩이 이동할 수 없는 위치입니다.");
        }
    }
}

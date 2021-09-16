package chess.domain.piece;

import chess.domain.player.Direction;
import chess.domain.player.Position;

import java.util.Collection;

import static java.lang.Math.abs;

public class King extends Piece {

    private static final int MAX_THRESHOLD = 2;

    public King(final Color color) {
        super(MovePattern.ALL_DIRECTIONS, color, false);
    }

    @Override
    public Collection<Position> findPath(final Position source, final Position target) {
        int fileGap = target.calculateFileGap(source);
        int rankGap = target.calculateRankGap(source);
        int absoluteFileGap = abs(fileGap);
        int absoluteRankGap = abs(rankGap);

        validateThreshold(absoluteFileGap, absoluteRankGap);

        Direction direction = movePattern.findDirection(fileGap, rankGap);
        return findPassingPositions(source, target, direction);
    }

    private void validateThreshold(final int absoluteFileGap, final int absoluteRankGap) {
        if (isGreaterOrEqualThan(absoluteFileGap) || isGreaterOrEqualThan(absoluteRankGap)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isGreaterOrEqualThan(final int fileGap) {
        return fileGap >= MAX_THRESHOLD;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}

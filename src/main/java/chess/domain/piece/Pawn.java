package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.board.Rank;

import java.util.Set;

import static java.lang.Math.abs;

public class Pawn extends Piece {

    private static final int VALID_GAP_ON_ATTACK = 1;
    private static final int MINIMUM_MOVE_COUNT = 1;
    private static final int MAXIMUM_MOVE_COUNT = 2;
    private static final Rank WHITE_INITIAL_RANK = Rank.of(2);
    private static final Rank BLACK_INITIAL_RANK = Rank.of(7);

    private boolean isInitialMove = true;

    public Pawn(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> findPaths(final Position source, final Position target) {
        setInitialMoveFlag(source);

        int fileGap = target.calculateFileGap(source);
        int rankGap = target.calculateRankGap(source);
        validatePattern(fileGap, rankGap);

        Direction direction = Direction.of(fileGap, rankGap);
        return collectPositions(source, target, direction);
    }

    private void setInitialMoveFlag(final Position source) {
        if (isWhite() && !source.hasSameRank(WHITE_INITIAL_RANK)) {
            isInitialMove = false;
        }

        if (isBlack() && !source.hasSameRank(BLACK_INITIAL_RANK)) {
            isInitialMove = false;
        }
    }

    @Override
    protected void validatePattern(int fileGap, int rankGap) {
        validateForward(rankGap);

        fileGap = abs(fileGap);
        rankGap = abs(rankGap);
        validateMoveCount(rankGap, fileGap);
    }

    private void validateForward(final int rankGap) {
        if (isWhite() && rankGap < MINIMUM_MOVE_COUNT) {
            throw new IllegalArgumentException();
        }

        if (isBlack() && rankGap > MINIMUM_MOVE_COUNT * -1) {
            throw new IllegalArgumentException();
        }
    }

    private void validateMoveCount(final int rankGap, final int fileGap) {
        if (fileGap != 0) {
            validateAttackMoveCount(rankGap, fileGap);
        }

        validateForwardMoveCount(rankGap);
    }

    private void validateAttackMoveCount(final int rankGap, final int fileGap) {
        if (rankGap > VALID_GAP_ON_ATTACK || fileGap > VALID_GAP_ON_ATTACK) {
            throw new IllegalArgumentException();
        }
    }

    private void validateForwardMoveCount(final int rankGap) {
        if (isInitialMove && rankGap > MAXIMUM_MOVE_COUNT) {
            throw new IllegalArgumentException();
        }

        if (!isInitialMove && rankGap > MINIMUM_MOVE_COUNT) {
            throw new IllegalArgumentException();
        }
    }
}

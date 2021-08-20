package chess.domain.piece;

import chess.domain.board.Rank;
import chess.domain.piece.type.MovePattern;
import chess.domain.player.MoveCoordinate;
import chess.domain.player.Position;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static chess.domain.player.MoveCoordinate.*;

public class Pawn extends Piece {

    private static final Rank WHITE_INITIAL_RANK = Rank.R2;
    private static final Rank BLACK_INITIAL_RANK = Rank.R7;

    public Pawn(final Color color) {
        super(MovePattern.pawnPattern(color), color);
    }

    @Override
    public Set<Position> findPath(final Position source, final Position target) {
        int fileGap = target.calculateFileGap(source);
        int rankGap = target.calculateRankGap(source);

        MoveCoordinate moveCoordinate = movePattern.findMoveCoordinate(fileGap, rankGap);
        moveCoordinate = resetIfInitialMove(moveCoordinate, isInitialMove(source));

        return source.findPassingPositions(target, moveCoordinate);
    }

    private boolean isInitialMove(Position source) {
        return source.hasSameRank(WHITE_INITIAL_RANK) || source.hasSameRank(BLACK_INITIAL_RANK);
    }

    private MoveCoordinate resetIfInitialMove(MoveCoordinate moveCoordinate, boolean isInitialMove) {
        validateMoveCount(moveCoordinate, isInitialMove);

        if (isWhiteInitialMove(moveCoordinate)) {
            return NORTH;
        }

        if (isBlackInitialMove(moveCoordinate)) {
            return SOUTH;
        }

        return moveCoordinate;
    }

    private void validateMoveCount(MoveCoordinate moveCoordinate, boolean isInitialMove) {
        if (!isInitialMove && (isBlackInitialMove(moveCoordinate) || isWhiteInitialMove(moveCoordinate))) {
            throw new IllegalArgumentException("최초 이동 시에만 2칸 이동할 수 있습니다.");
        }
    }

    private boolean isWhiteInitialMove(final MoveCoordinate moveCoordinate) {
        return moveCoordinate == WHITE_PAWN_INITIAL_NORTH;
    }

    private boolean isBlackInitialMove(final MoveCoordinate moveCoordinate) {
        return moveCoordinate == BLACK_PAWN_INITIAL_SOUTH;
    }

    @Override
    public Collection<Position> findAvailableAttackPositions(final Position position) {
        return movePattern.pawnAttackMoveCoordinates(isWhite()).stream()
                .map(moveCoordinate -> position.findAvailablePositions(moveCoordinate, true))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}

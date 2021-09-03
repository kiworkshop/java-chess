package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.type.MovePattern;
import chess.domain.piece.type.MoveUnit;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static chess.domain.piece.type.MoveUnit.*;

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

        MoveUnit moveUnit = movePattern.findMoveUnit(fileGap, rankGap);
        moveUnit = resetIfInitialMove(moveUnit, isInitialMove(source));

        return source.findPassingPositions(target, moveUnit);
    }

    private boolean isInitialMove(Position source) {
        return source.hasSameRank(WHITE_INITIAL_RANK) || source.hasSameRank(BLACK_INITIAL_RANK);
    }

    private MoveUnit resetIfInitialMove(MoveUnit moveUnit, boolean isInitialMove) {
        validateMoveCount(moveUnit, isInitialMove);

        if (isWhiteInitialMove(moveUnit)) {
            return NORTH;
        }

        if (isBlackInitialMove(moveUnit)) {
            return SOUTH;
        }

        return moveUnit;
    }

    private void validateMoveCount(MoveUnit moveUnit, boolean isInitialMove) {
        if (!isInitialMove && (isBlackInitialMove(moveUnit) || isWhiteInitialMove(moveUnit))) {
            throw new IllegalArgumentException("최초 이동 시에만 2칸 이동할 수 있습니다.");
        }
    }

    private boolean isWhiteInitialMove(final MoveUnit moveUnit) {
        return moveUnit == WHITE_PAWN_INITIAL_NORTH;
    }

    private boolean isBlackInitialMove(final MoveUnit moveUnit) {
        return moveUnit == BLACK_PAWN_INITIAL_SOUTH;
    }

    @Override
    public Collection<Position> findAvailableAttackPositions(final Position position) {
        return movePattern.pawnAttackMoveUnits(isWhite()).stream()
                .map(moveUnit -> position.findAvailablePositions(moveUnit, true))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}

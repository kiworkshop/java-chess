package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.move.Gap;
import chess.domain.piece.move.MovePattern;
import chess.domain.piece.move.MoveUnit;
import chess.domain.piece.move.Path;
import chess.domain.player.Color;

import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {

    private final MovePattern attackPattern;

    public Pawn(final Color color) {
        super(MovePattern.pawnPattern(color), color);
        this.attackPattern = MovePattern.pawnAttackPattern(color);
    }

    @Override
    public Path findMovePath(final Position source, final Position target) {
        Gap gap = target.calculateGap(source);
        MoveUnit moveUnit = movePattern.findMatchMoveUnit(gap);

        if (moveUnit.isInitialPawn()) {
            validateInitialMoveAvailable(source);
            moveUnit = resetMoveUnit();
        }

        List<Position> passingPositions = source.findPassingPositions(target, moveUnit);
        return new Path(passingPositions);
    }

    private void validateInitialMoveAvailable(Position source) {
        if (isInitialMoveUnavailable(source)) {
            throw new IllegalArgumentException("최초 이동 시에만 2칸 이동할 수 있습니다.");
        }
    }

    private MoveUnit resetMoveUnit() {
        if (color.isWhite()) {
            return MoveUnit.whitePawnMove();
        }
        return MoveUnit.blackPawnMove();
    }

    private boolean isInitialMoveUnavailable(final Position source) {
        return !isWhiteInitialMoveAvailable(source) && !isBlackInitialMoveAvailable(source);
    }

    private boolean isWhiteInitialMoveAvailable(final Position source) {
        return color.isWhite() && source.isWhitePawnInitialRank();
    }

    private boolean isBlackInitialMoveAvailable(final Position source) {
        return color.isBlack() && source.isBlackPawnInitialRank();
    }

    @Override
    public Collection<Path> findAttackPaths(final Position source) {
        return attackPattern.findAttackPaths(source);
    }

    public boolean isAttacking(final Position source, final Position target) {
        try {
            Gap gap = target.calculateGap(source);
            attackPattern.findMatchMoveUnit(gap);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}

package chess.domain.piece.move;

import chess.domain.board.Position;
import chess.domain.player.Color;

import java.util.Collection;

import static chess.domain.piece.move.MoveLimit.FINITE;
import static chess.domain.piece.move.MoveLimit.INFINITE;
import static chess.domain.piece.move.MoveUnits.*;

public enum MovePattern {

    KING(CARDINAL_AND_DIAGONAL, FINITE),
    QUEEN(CARDINAL_AND_DIAGONAL, INFINITE),
    BISHOP(DIAGONAL, INFINITE),
    ROOK(CARDINAL, INFINITE),
    KNIGHT(MoveUnits.KNIGHT, FINITE),
    WHITE_PAWN(MoveUnits.WHITE_PAWN, FINITE),
    BLACK_PAWN(MoveUnits.BLACK_PAWN, FINITE),
    WHITE_PAWN_ATTACK(MoveUnits.WHITE_PAWN_ATTACK, FINITE),
    BLACK_PAWN_ATTACK(MoveUnits.BLACK_PAWN_ATTACK, FINITE);

    public static MovePattern pawnPattern(final Color color) {
        if (color.isWhite()) {
            return WHITE_PAWN;
        }
        return BLACK_PAWN;
    }

    public static MovePattern pawnAttackPattern(final Color color) {
        if (color.isWhite()) {
            return WHITE_PAWN_ATTACK;
        }
        return BLACK_PAWN_ATTACK;
    }

    private final MoveUnits moveUnits;
    private final MoveLimit moveLimit;

    MovePattern(final MoveUnits moveUnits, final MoveLimit moveLimit) {
        this.moveUnits = moveUnits;
        this.moveLimit = moveLimit;
    }

    public MoveUnit findMatchMoveUnit(final Gap gap) {
        return moveUnits.findMatchMoveUnit(gap, moveLimit);
    }

    public Collection<Path> findAttackPaths(final Position source) {
        if (moveLimit.isFinite()) {
            return moveUnits.findReachableFinitePaths(source);
        }
        return moveUnits.findReachableInfinitePaths(source);
    }
}

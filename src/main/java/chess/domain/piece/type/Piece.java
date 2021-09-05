package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.move.Gap;
import chess.domain.piece.move.MovePattern;
import chess.domain.piece.move.MoveUnit;
import chess.domain.piece.move.Path;
import chess.domain.player.Color;

import java.util.Collection;
import java.util.List;

public abstract class Piece {

    protected final MovePattern movePattern;
    protected final Color color;

    Piece(final MovePattern movePattern, final Color color) {
        this.movePattern = movePattern;
        this.color = color;
    }

    public Path findMovePath(final Position source, final Position target) {
        Gap gap = target.calculateGap(source);

        MoveUnit moveUnit = movePattern.findMatchMoveUnit(gap);
        List<Position> passingPositions = source.findPassingPositions(target, moveUnit);

        return new Path(passingPositions);
    }

    public Collection<Path> findAttackPaths(final Position source) {
        return movePattern.findAttackPaths(source);
    }

    public boolean isWhite() {
        return color.isWhite();
    }

    public boolean isPawn() {
        return PieceType.isPawn(this);
    }

    public boolean isNotPawn() {
        return !isPawn();
    }

    public boolean isKing() {
        return PieceType.isKing(this);
    }
}

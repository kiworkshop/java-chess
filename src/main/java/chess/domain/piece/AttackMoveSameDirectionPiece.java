package chess.domain.piece;

import chess.domain.direction.EnumDirection;
import chess.exception.MovingDirectionException;
import chess.model.MovingDirection;
import chess.model.Team;
import chess.model.board.BoardState;
import chess.model.piece.PieceType;
import chess.model.postiion.Position;

import java.util.List;

public abstract class AttackMoveSameDirectionPiece extends ChessPiece {
    AttackMoveSameDirectionPiece(PieceType pieceType, Position position, Team team) {
        super(pieceType, position, team);
    }

    @Override
    protected void validateMovingPolicy(Position target, BoardState boardState) {
        MovingDirection movingDirection = EnumDirection.of(position, target);
        validateDirection(movingDirection);
        validatePolicy(movingDirection, target, boardState);
    }

    private void validateDirection(MovingDirection movingDirection) {
        List<MovingDirection> movingDirections = getMovingDirections();
        if (!movingDirections.contains(movingDirection)) {
            throw new MovingDirectionException();
        }
    }

    protected abstract List<MovingDirection> getMovingDirections();

    protected abstract void validatePolicy(MovingDirection movingDirection, Position target, BoardState boardState);
}

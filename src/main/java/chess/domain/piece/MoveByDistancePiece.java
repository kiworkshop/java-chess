package chess.domain.piece;

import chess.exception.MovingDistanceException;
import chess.model.MovingDirection;
import chess.model.Team;
import chess.model.board.BoardState;
import chess.model.piece.PieceType;
import chess.model.postiion.Position;

import java.util.HashSet;
import java.util.Set;

public abstract class MoveByDistancePiece extends AttackMoveSameDirectionPiece {

    protected MoveByDistancePiece(PieceType pieceType, Position position, Team team) {
        super(pieceType, position, team);
    }

    @Override
    protected void validatePolicy(MovingDirection movingDirection, Position target, BoardState boardState) {
        validateDistance(movingDirection, target);
    }

    @Override
    public Set<Position> movablePosition(BoardState boardState) {
        Set<Position> movablePositions = new HashSet<>();
        for (MovingDirection movingDirection : getMovingDirections()) {
            Position targetPosition = position.moveByDirection(movingDirection);
            if (boardState.isEmpty(targetPosition)) {
                movablePositions.add(targetPosition);
            }
        }
        return movablePositions;
    }

    private void validateDistance(MovingDirection movingDirection, Position target) {
        int fileDifference = position.fileDifference(target);
        int rankDifference = position.rankDifference(target);
        if (!(movingDirection.getFileDirection() == fileDifference &&
                movingDirection.getRankDirection() == rankDifference)) {
            throw new MovingDistanceException();
        }
    }
}

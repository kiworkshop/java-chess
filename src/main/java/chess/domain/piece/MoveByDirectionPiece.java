package chess.domain.piece;

import chess.exception.ObstacleOnPathException;
import chess.model.MovingDirection;
import chess.model.Team;
import chess.model.board.BoardState;
import chess.model.piece.PieceType;
import chess.model.postiion.Position;

import java.util.HashSet;
import java.util.Set;

public abstract class MoveByDirectionPiece extends AttackMoveSameDirectionPiece {

    protected MoveByDirectionPiece(PieceType pieceType, Position position, Team team) {
        super(pieceType, position, team);
    }

    @Override
    protected void validatePolicy(MovingDirection movingDirection, Position target, BoardState boardState) {
        validateObstacle(movingDirection, target, boardState);
    }

    @Override
    public Set<Position> movablePosition(BoardState boardState) {
        Set<Position> positions = new HashSet<>();
        for (MovingDirection movingDirection : getMovingDirections()) {
            Position startPathPosition = position.moveByDirection(movingDirection);
            while (!startPathPosition.isBoundary()) {
                if (boardState.isNotEmpty(startPathPosition)) {
                    break;
                }
                startPathPosition = startPathPosition.moveByDirection(movingDirection);
            }
        }
        return positions;
    }

    private void validateObstacle(MovingDirection movingDirection, Position target, BoardState boardState) {
        Position startPathPosition = position.moveByDirection(movingDirection);
        while (!startPathPosition.equals(target)) {
            if (boardState.isNotEmpty(startPathPosition)) {
                throw new ObstacleOnPathException();
            }
            startPathPosition = startPathPosition.moveByDirection(movingDirection);
        }
    }
}

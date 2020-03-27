package chess.domain.piece.implementation;

import chess.domain.piece.AttackablePawn;
import chess.exception.MovingDistanceException;
import chess.exception.ObstacleOnPathException;
import chess.model.MovingDirection;
import chess.model.Team;
import chess.model.board.BoardState;
import chess.model.piece.PieceState;
import chess.model.postiion.Position;

import java.util.HashSet;
import java.util.Set;

public class MovedPawn extends AttackablePawn {

    private MovedPawn(Position position, Team team) {
        super(position, team);
    }

    public static MovedPawn of(Position position, Team team) {
        return new MovedPawn(position, team);
    }

    @Override
    protected void validateMove(MovingDirection movingDirection, Position target, BoardState boardState) {
        if (MOVING_DIRECTION_BY_PLAYER.get(team).equals(movingDirection)) {
            validateMoveDistance(target, movingDirection);
            validateObstacle(target, boardState);
        }
    }

    private void validateMoveDistance(Position target, MovingDirection movingDirection) {
        if (position.rankDifference(target) != movingDirection.getRankDirection()) {
            throw new MovingDistanceException();
        }
    }

    private void validateObstacle(Position target, BoardState boardState) {
        if (boardState.isNotEmpty(target)) {
            throw new ObstacleOnPathException();
        }
    }

    @Override
    protected Set<Position> movable(BoardState boardState) {
        Set<Position> positions = new HashSet<>();
        MovingDirection movingDirection = MOVING_DIRECTION_BY_PLAYER.get(team);
        Position target = position.moveByDirection(movingDirection);
        if (boardState.isEmpty(target)) {
            positions.add(target);
        }
        return positions;
    }

    @Override
    protected PieceState movedPieceState(Position target) {
        return new MovedPawn(target, team);
    }
}

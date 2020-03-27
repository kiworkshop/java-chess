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

public class NotMovedPawn extends AttackablePawn {

    private NotMovedPawn(Position position, Team team) {
        super(position, team);
    }

    public static NotMovedPawn of(Position position, Team team) {
        return new NotMovedPawn(position, team);
    }

    @Override
    protected void validateMove(MovingDirection movingDirection, Position target, BoardState boardState) {
        if (MOVING_DIRECTION_BY_PLAYER.get(team).equals(movingDirection)) {
            validateMoveDistance(target, movingDirection);
            validateObstacle(movingDirection, target, boardState);
        }
    }

    private void validateMoveDistance(Position target, MovingDirection movingDirection) {
        int rankDifference = position.rankDifference(target);
        if (rankDifference != movingDirection.getRankDirection() &&
                rankDifference != movingDirection.getRankDirection() * 2) {
            throw new MovingDistanceException();
        }
    }

    private void validateObstacle(MovingDirection movingDirection, Position target, BoardState boardState) {
        Position startPosition = position.moveByDirection(movingDirection);
        validateEmpty(startPosition, boardState);
        validateEmpty(target, boardState);
    }

    @Override
    protected Set<Position> movable(BoardState boardState) {
        Set<Position> positions = new HashSet<>();
        MovingDirection movingDirection = MOVING_DIRECTION_BY_PLAYER.get(team);
        Position startPosition = position.moveByDirection(movingDirection);
        addIfEmpty(boardState, positions, startPosition);
        if (!startPosition.isBoundary()) {
            startPosition = position.moveByDirection(movingDirection);
            addIfEmpty(boardState, positions, startPosition);
        }
        return positions;
    }

    private void validateEmpty(Position position, BoardState boardState) {
        if (boardState.isNotEmpty(position)) {
            throw new ObstacleOnPathException();
        }
    }

    private void addIfEmpty(BoardState boardState, Set<Position> positions, Position startPosition) {
        if (boardState.isEmpty(startPosition)) {
            positions.add(startPosition);
        }
    }

    @Override
    protected PieceState movedPieceState(Position target) {
        return MovedPawn.of(target, team);
    }
}

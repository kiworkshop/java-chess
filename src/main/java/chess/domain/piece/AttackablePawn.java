package chess.domain.piece;

import chess.exception.MovingDirectionException;
import chess.exception.MovingDistanceException;
import chess.model.MovingDirection;
import chess.model.Team;
import chess.model.board.BoardState;
import chess.model.postiion.Position;

import java.util.HashSet;
import java.util.Set;

public abstract class AttackablePawn extends Pawn {

    protected AttackablePawn(Position position, Team team) {
        super(position, team);
    }

    @Override
    protected void validateAttack(MovingDirection movingDirection, Position target, BoardState boardState) {
        if (ATTACK_DIRECTION_BY_TEAM.get(team).contains(movingDirection)) {
            validateEnemy(target, boardState);
            validateAttackDistance(target, movingDirection);
        }
    }

    private void validateAttackDistance(Position target, MovingDirection movingDirection) {
        if (position.rankDifference(target) != movingDirection.getRankDirection()) {
            throw new MovingDistanceException();
        }
    }

    private void validateEnemy(Position target, BoardState boardState) {
        if (!boardState.isEnemy(target, team)) {
            throw new MovingDirectionException();
        }
    }

    @Override
    public Set<Position> movablePositions(BoardState boardState) {
        Set<Position> movablePositions = attackable(boardState);
        movablePositions.addAll(movable(boardState));
        return movablePositions;
    }

    private Set<Position> attackable(BoardState boardState) {
        Set<Position> positions = new HashSet<>();
        for (MovingDirection movingDirection : ATTACK_DIRECTION_BY_TEAM.get(team)) {
            Position target = position.moveByDirection(movingDirection);
            if (boardState.isEnemy(target, team)) {
                positions.add(target);
            }
        }
        return positions;
    }

    protected abstract Set<Position> movable(BoardState boardState);
}

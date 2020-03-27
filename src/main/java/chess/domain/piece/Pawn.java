package chess.domain.piece;

import chess.domain.direction.EnumDirection;
import chess.domain.player.EnumTeam;
import chess.exception.MovingDirectionException;
import chess.model.MovingDirection;
import chess.model.Team;
import chess.model.board.BoardState;
import chess.model.postiion.Position;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Pawn extends ChessPiece {

    protected static final Map<Team, MovingDirection> MOVING_DIRECTION_BY_PLAYER;
    protected static final Map<Team, List<MovingDirection>> ATTACK_DIRECTION_BY_TEAM;

    static {
        MOVING_DIRECTION_BY_PLAYER = new HashMap<>();
        MOVING_DIRECTION_BY_PLAYER.put(EnumTeam.WHITE, EnumDirection.NORTH);
        MOVING_DIRECTION_BY_PLAYER.put(EnumTeam.BLACK, EnumDirection.SOUTH);

        ATTACK_DIRECTION_BY_TEAM = new HashMap<>();
        ATTACK_DIRECTION_BY_TEAM.put(EnumTeam.WHITE, Arrays.asList(
                EnumDirection.NORTH_EAST,
                EnumDirection.NORTH_WEST
        ));
        ATTACK_DIRECTION_BY_TEAM.put(EnumTeam.BLACK, Arrays.asList(
                EnumDirection.SOUTH_EAST,
                EnumDirection.SOUTH_WEST
        ));
    }

    Pawn(Position position, Team team) {
        super(EnumPieceType.PAWN, position, team);
    }

    @Override
    protected void validateMovingPolicy(Position target, BoardState boardState) {
        MovingDirection movingDirection = EnumDirection.of(position, target);
        validateDirection(movingDirection);
        validateAttack(movingDirection, target, boardState);
        validateMove(movingDirection, target, boardState);
    }

    private void validateDirection(MovingDirection movingDirection) {
        if (!MOVING_DIRECTION_BY_PLAYER.get(team).equals(movingDirection) &&
                !ATTACK_DIRECTION_BY_TEAM.get(team).contains(movingDirection)) {
            throw new MovingDirectionException();
        }
    }

    protected abstract void validateAttack(MovingDirection movingDirection, Position target, BoardState boardState);

    protected abstract void validateMove(MovingDirection movingDirection, Position target, BoardState boardState);
}

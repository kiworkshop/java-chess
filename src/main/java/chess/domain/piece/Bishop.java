package chess.domain.piece;

import chess.domain.direction.EnumDirection;
import chess.model.MovingDirection;
import chess.model.Team;
import chess.model.piece.PieceState;
import chess.model.postiion.Position;

import java.util.Arrays;
import java.util.List;

public class Bishop extends MoveByDirectionPiece {

    private static final List<MovingDirection> MOVING_DIRECTIONS;

    static {
        MOVING_DIRECTIONS = Arrays.asList(
                EnumDirection.NORTH_EAST,
                EnumDirection.NORTH_WEST,
                EnumDirection.SOUTH_EAST,
                EnumDirection.SOUTH_WEST
        );
    }

    private Bishop(Position position, Team team) {
        super(EnumPieceType.BISHOP, position, team);
    }

    public static Bishop of(Position position, Team team) {
        return new Bishop(position, team);
    }

    @Override
    protected List<MovingDirection> getMovingDirections() {
        return MOVING_DIRECTIONS;
    }

    @Override
    protected PieceState movedPieceState(Position target) {
        return new Bishop(target, team);
    }
}

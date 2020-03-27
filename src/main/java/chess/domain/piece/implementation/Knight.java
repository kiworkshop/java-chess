package chess.domain.piece.implementation;

import chess.domain.direction.EnumDirection;
import chess.domain.piece.EnumPieceType;
import chess.domain.piece.MoveByDistancePiece;
import chess.model.MovingDirection;
import chess.model.Team;
import chess.model.piece.PieceState;
import chess.model.postiion.Position;

import java.util.Arrays;
import java.util.List;

public class Knight extends MoveByDistancePiece {

    private static final List<MovingDirection> MOVING_DIRECTIONS;

    static {
        MOVING_DIRECTIONS = Arrays.asList(
                EnumDirection.NORTH_NORTH_EAST,
                EnumDirection.NORTH_EAST_EAST,
                EnumDirection.SOUTH_EAST_EAST,
                EnumDirection.SOUTH_SOUTH_EAST,
                EnumDirection.SOUTH_SOUTH_WEST,
                EnumDirection.SOUTH_WEST_WEST,
                EnumDirection.NORTH_WEST_WEST,
                EnumDirection.NORTH_NORTH_WEST
        );
    }

    private Knight(Position position, Team team) {
        super(EnumPieceType.KNIGHT, position, team);
    }

    public static Knight of(Position position, Team team) {
        return new Knight(position, team);
    }


    @Override
    protected PieceState movedPieceState(Position target) {
        return new Knight(target, team);
    }

    @Override
    protected List<MovingDirection> getMovingDirections() {
        return MOVING_DIRECTIONS;
    }
}

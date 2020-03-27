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

public class King extends MoveByDistancePiece {

    private static final List<MovingDirection> MOVING_DIRECTIONS;

    static {
        MOVING_DIRECTIONS = Arrays.asList(
                EnumDirection.NORTH,
                EnumDirection.EAST,
                EnumDirection.SOUTH,
                EnumDirection.WEST,
                EnumDirection.NORTH_EAST,
                EnumDirection.NORTH_WEST,
                EnumDirection.SOUTH_EAST,
                EnumDirection.SOUTH_WEST
        );
    }

    private King(Position position, Team team) {
        super(EnumPieceType.KING, position, team);
    }

    public static King of(Position position, Team team) {
        return new King(position, team);
    }

    @Override
    protected PieceState movedPieceState(Position target) {
        return new King(target, team);
    }

    @Override
    protected List<MovingDirection> getMovingDirections() {
        return MOVING_DIRECTIONS;
    }
}

package chess.domain.piece.implementation;

import chess.domain.direction.EnumDirection;
import chess.domain.piece.EnumPieceType;
import chess.domain.piece.MoveByDirectionPiece;
import chess.model.MovingDirection;
import chess.model.Team;
import chess.model.piece.PieceState;
import chess.model.postiion.Position;

import java.util.Arrays;
import java.util.List;

public class Queen extends MoveByDirectionPiece {

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

    private Queen(Position position, Team team) {
        super(EnumPieceType.QUEEN, position, team);
    }

    public static Queen of(Position position, Team team) {
        return new Queen(position, team);
    }

    @Override
    protected List<MovingDirection> getMovingDirections() {
        return MOVING_DIRECTIONS;
    }

    @Override
    protected PieceState movedPieceState(Position target) {
        return new Queen(target, team);
    }
}

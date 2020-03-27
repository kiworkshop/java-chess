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

public class Rook extends MoveByDirectionPiece {

    private static final List<MovingDirection> MOVING_DIRECTIONS;

    static {
        MOVING_DIRECTIONS = Arrays.asList(
                EnumDirection.NORTH,
                EnumDirection.EAST,
                EnumDirection.SOUTH,
                EnumDirection.WEST
        );
    }

    private Rook(Position position, Team team) {
        super(EnumPieceType.ROOK, position, team);
    }

    public static Rook of(Position position, Team team) {
        return new Rook(position, team);
    }

    @Override
    protected List<MovingDirection> getMovingDirections() {
        return MOVING_DIRECTIONS;
    }

    @Override
    protected PieceState movedPieceState(Position target) {
        return new Rook(target, team);
    }
}

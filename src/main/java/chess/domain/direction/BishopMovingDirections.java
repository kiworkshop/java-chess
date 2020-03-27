package chess.domain.direction;

import chess.model.MovingDirection;
import chess.model.MovingDirections;

import java.util.Arrays;
import java.util.List;

public class BishopMovingDirections implements MovingDirections {

    private static final List<MovingDirection> MOVING_DIRECTIONS;

    static {
        MOVING_DIRECTIONS = Arrays.asList(
                EnumDirection.NORTH_EAST,
                EnumDirection.NORTH_WEST,
                EnumDirection.SOUTH_EAST,
                EnumDirection.SOUTH_WEST
        );
    }

    @Override
    public boolean contains(MovingDirection movingDirection) {
        return MOVING_DIRECTIONS.contains(movingDirection);
    }
}

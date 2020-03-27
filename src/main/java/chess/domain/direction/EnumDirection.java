package chess.domain.direction;

import chess.exception.MovingDirectionException;
import chess.model.postiion.Position;
import chess.model.MovingDirection;

import java.util.Arrays;

public enum EnumDirection implements MovingDirection {
    NORTH(0, 1),
    NORTH_EAST(1, 1),
    EAST(1, 0),
    SOUTH_EAST(1, -1),
    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0),
    NORTH_WEST(-1, 1),
    NORTH_NORTH_EAST(1, 2),
    NORTH_EAST_EAST(2, 1),
    SOUTH_EAST_EAST(2, -1),
    SOUTH_SOUTH_EAST(1, -2),
    SOUTH_SOUTH_WEST(-1, -2),
    SOUTH_WEST_WEST(-2, -1),
    NORTH_WEST_WEST(-2, 1),
    NORTH_NORTH_WEST(-1, 2);

    private final int fileDirection;
    private final int rankDirection;

    EnumDirection(int fileDirection, int rankDirection) {
        this.fileDirection = fileDirection;
        this.rankDirection = rankDirection;
    }

    public static MovingDirection of(Position source, Position target) {
        double fileDifference = source.fileDifference(target);
        double rankDifference = source.rankDifference(target);

        Double tangent = rankDifference / fileDifference;

        return Arrays.stream(values())
                .filter(direction -> direction.isSameDirection(tangent))
                .filter(direction -> direction.isSameFileDirection(fileDifference))
                .filter(direction -> direction.isSameRankDirection(rankDifference))
                .findFirst()
                .orElseThrow(MovingDirectionException::new);
    }

    @Override
    public int getFileDirection() {
        return fileDirection;
    }

    @Override
    public int getRankDirection() {
        return rankDirection;
    }

    private boolean isSameDirection(Double tangent) {
        Double directionTangent = rankDirection / (double) fileDirection;
        return directionTangent.equals(tangent);
    }

    private boolean isSameFileDirection(double fileDifference) {
        return fileDirection * fileDifference >= 0;
    }

    private boolean isSameRankDirection(double rankDifference) {
        return rankDirection * rankDifference >= 0;
    }
}

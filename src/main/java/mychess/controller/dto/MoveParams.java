package mychess.controller.dto;

import mychess.domain.position.Coordinate;
import mychess.domain.position.Position;

import java.util.List;

public class MoveParams {
    private Position source;
    private Position destination;

    private MoveParams(Position source, Position destination) {
        this.source = source;
        this.destination = destination;
    }

    public static MoveParams of(List<String> parameters) {
        if (isValid(parameters)) {
            return new MoveParams(toPosition(parameters.get(0)), toPosition(parameters.get(1)));
        }
        throw new IllegalArgumentException("잘못된 파라미터입니다.");
    }

    private static boolean isValid(List<String> parameters) {
        if (!isSizeValid(parameters)) return false;

        Position source = toPosition(parameters.get(0));
        Position destination = toPosition(parameters.get(1));

        return source.isValid() &&
                destination.isValid() &&
                !source.equals(destination);
    }

    private static boolean isSizeValid(List<String> parameters) {
        return parameters.size() == 2;
    }

    private static Position toPosition(String naked) {
        String upperCased = naked.toUpperCase();
        Coordinate x = Coordinate.of(upperCased.toCharArray()[0] - 'A');
        Coordinate y = Coordinate.of('8' - upperCased.toCharArray()[1]);
        return Position.of(x, y);
    }

    public Position getSource() {
        return source;
    }

    public Position getDestination() {
        return destination;
    }
}
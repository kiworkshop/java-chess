package chess.controller.dto;

import chess.domain.position.EnumPosition;
import chess.model.postiion.Position;

import java.util.List;

public class MoveParams {
    private static final int MOVE_PARAM_SIZE = 2;

    private Position source;
    private Position destination;

    private MoveParams(Position source, Position destination) {
        this.source = source;
        this.destination = destination;
    }

    public static MoveParams of(List<String> parameters) {
        validateParamSize(parameters);
        Position source = EnumPosition.from(parameters.get(0));
        Position destination = EnumPosition.from(parameters.get(1));
        return new MoveParams(source, destination);
    }

    private static void validateParamSize(List<String> parameters) {
        if (parameters.size() != MOVE_PARAM_SIZE) {
            throw new IllegalArgumentException("이동을 위한 파라미터는 2개가 입력되어야 합니다.");
        }
    }

    public Position getSource() {
        return source;
    }

    public Position getDestination() {
        return destination;
    }
}

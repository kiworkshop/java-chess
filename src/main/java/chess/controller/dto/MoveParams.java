package chess.controller.dto;

import chess.domain.board.Position;

import java.util.List;

public class MoveParams {
    private Position source;
    private Position destination;

    private MoveParams(Position source, Position destination) {
        this.source = source;
        this.destination = destination;
    }

    public static MoveParams of(List<String> parameters) {
        Position source = Position.from(parameters.get(0));
        Position destination = Position.from(parameters.get(1));
        return new MoveParams(source, destination);
    }

    public Position getSource() {
        return source;
    }

    public Position getDestination() {
        return destination;
    }
}

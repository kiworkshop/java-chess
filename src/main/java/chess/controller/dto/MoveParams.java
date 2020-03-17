package chess.controller.dto;

import java.util.List;

public class MoveParams {
    private Position source;
    private Position destination;

    private MoveParams(Position source, Position destination) {
        this.source = source;
        this.destination = destination;
    }

    public static MoveParams of(List<String> parameters) {
        //validation
    }
}

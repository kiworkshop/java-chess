package chess.controller.dto;


import chess.controller.Command;

import java.util.Collections;
import java.util.List;

public class ChessRequest {
    public Command command;
    public List<String> parameters;

    private ChessRequest(Command command, List<String> parameters) {
        this.command = command;
        this.parameters = parameters;
    }

    public static ChessRequest of(Command command, List<String> parameters) {
        return new ChessRequest(command, parameters);
    }

    public static ChessRequest of(Command command) {
        return new ChessRequest(command, Collections.emptyList());
    }

    public Command getCommand() {
        return command;
    }

    public List<String> getParameters() {
        return parameters;
    }
}

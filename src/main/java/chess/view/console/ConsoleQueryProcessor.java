package chess.view.console;

import chess.controller.Command;
import chess.controller.dto.ChessRequest;

import java.util.Arrays;
import java.util.List;

public class ConsoleQueryProcessor {
    public static ChessRequest parse(String query) {
        List<String> terms = Arrays.asList(query.split(" "));
        Command command = getCommand(terms);
        if (!hasParams(terms)) {
            return ChessRequest.of(command);
        }
        return ChessRequest.of(command, terms.subList(1, terms.size()));
    }

    private static Command getCommand(List<String> terms) {
        try {
            return Command.valueOf(terms.get(0).toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    private static boolean hasParams(List<String> terms) {
        return terms.size() > 1;
    }
}

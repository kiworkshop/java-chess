package chess.domain.command;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command {

    private static final String DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;

    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";

    private final String command;
    private final List<String> parameters;

    public Command(final String commandLine) {
        List<String> chunks = Arrays.stream(commandLine.split(DELIMITER))
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        if (chunks.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.command = chunks.get(COMMAND_INDEX);
        this.parameters = chunks.subList(COMMAND_INDEX + 1, chunks.size());
    }

    public boolean isStart() {
        return command.equals(START);
    }

    public boolean isEnd() {
        return command.equals(END);
    }

    public boolean isMove() {
        return command.equals(MOVE);
    }

    public MoveParameters getMoveParameters() {
        if (parameters.size() != 2) {
            throw new IllegalArgumentException();
        }

        return new MoveParameters(parameters);
    }
}

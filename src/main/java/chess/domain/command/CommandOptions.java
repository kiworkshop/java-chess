package chess.domain.command;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CommandOptions {

    private static final String DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int CHECK_VALUE_IF_COMMAND_HAS_OPTIONS = 1;
    private static final int FIRST_OPTION_INDEX = 1;

    private final Command command;
    private final List<String> options;

    private CommandOptions(final Command command, final List<String> options) {
        this.command = command;
        this.options = options;
    }

    public static CommandOptions of(final String text) {
        validateEmpty(text);
        List<String> commandAndOptions = split(text);
        Command command = Command.of(commandAndOptions.get(COMMAND_INDEX));
        List<String> options = extractOptions(commandAndOptions);

        return new CommandOptions(command, options);
    }

    private static void validateEmpty(final String text) {
        if (text == null) {
            throw new IllegalArgumentException("명령어가 존재하지 않습니다.");
        }
    }

    private static List<String> split(final String text) {
        return Arrays.stream(text.split(DELIMITER))
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    private static List<String> extractOptions(final List<String> commandAndOptions) {
        if (hasOptions(commandAndOptions)) {
            return commandAndOptions.subList(FIRST_OPTION_INDEX, commandAndOptions.size());
        }

        return Collections.emptyList();
    }

    private static boolean hasOptions(final List<String> splitText) {
        return splitText.size() > CHECK_VALUE_IF_COMMAND_HAS_OPTIONS;
    }

    public void validateInitialCommand() {
        command.validateInitialCommand();
    }

    public boolean isEnd() {
        return command.isEnd();
    }

    public boolean isMove() {
        return command.isMove();
    }

    public boolean isStatus() {
        return command.isStatus();
    }

    public MoveOptions getMoveOptions() {
        return new MoveOptions(options);
    }
}
